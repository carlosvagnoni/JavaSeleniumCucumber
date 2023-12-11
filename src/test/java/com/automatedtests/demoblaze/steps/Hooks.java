package com.automatedtests.demoblaze.steps;

import com.automatedtests.demoblaze.utils.Configuration;
import com.automatedtests.demoblaze.utils.Drivers;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * This class contains setup and teardown methods (hooks) for scenarios in a test suite.
 * It initializes the WebDriver and handles actions before and after scenario execution.
 */
public class Hooks {
    private static final Logger logging = Logger.getLogger(Hooks.class);
    public static WebDriver driver;
    public static Configuration config;

    @Before
    public void beforeScenario(Scenario scenario) {
        logging.info("Running SCENARIO: " + scenario.getName());
        try {
            config = Configuration.loadFromFile("config.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = new Drivers(config.getNavigator(), config.isHeadlessMode()).driver;
        driver.manage().window().maximize();
        SignupSteps.currentScenario = scenario;
        LoginSteps.currentScenario = scenario;
        AddProductToCartSteps.currentScenario = scenario;
    }

    @After
    public void afterScenario(Scenario scenario) {
        logging.info("Finished SCENARIO: " + scenario.getName());
        switch (scenario.getStatus()) {
            case SKIPPED -> logging.info("One or more steps of this scenario was passed over during testing.");
            case PASSED -> logging.info("The scenario was tested successfully.");
            case FAILED -> {
                logging.error("One or more steps of this scenario failed.");
                try {
                    byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshotAs, "image/png", scenario.getName());
                } catch (UnhandledAlertException e) {
                    logging.error("No screenshot could be taken.");
                    logging.error(e);
                }
                driver.quit();
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
