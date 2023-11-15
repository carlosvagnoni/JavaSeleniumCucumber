package com.automatedtests.demoblaze.steps;

import com.automatedtests.demoblaze.pages.BasePage;
import com.automatedtests.demoblaze.utils.Configuration;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;

/**
 * This class contains Cucumber steps for user login functionality on the application.
 */
public class LoginSteps {
    // Logger instance for logging purposes
    private static final Logger logging = Logger.getLogger(LoginSteps.class);

    // WebDriver instance retrieved from Hooks class
    private WebDriver driver = Hooks.driver;

    // Configuration instance retrieved from Hooks class
    private Configuration config = Hooks.config;

    // User credentials
    private String username;
    private String password;

    // BasePage instance initialized with the WebDriver
    private BasePage basePage = new BasePage(driver);

    // Represents the current scenario being executed
    public static Scenario currentScenario;


    @Given("the user has signed up with credentials: {string}, {string}.")
    public void theUserHasSignedUpWithCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Given("the user is on the Login Page.")
    public void theUserIsOnLoginPage() {
        driver.get(config.getUrl());
        basePage.clickLoginButton();
        basePage.waitForLoginTitle();
        basePage.verifyLoginTitle();
        try {
            byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            currentScenario.attach(screenshotAs, "image/png", "the user is on the Login Page.");
        } catch (UnhandledAlertException e) {
            logging.error("No screenshot could be taken.");
            logging.error(e);
        }
    }

    @When("the user inputs their username and password into the form.")
    public void theUserInputsUsernameAndPassword() {
        basePage.enterLoginUsername(username);
        basePage.enterLoginPassword(password);
    }

    @When("the user clicks on the Submit button.")
    public void theUserClicksSubmitButton() {
        basePage.submitLogin();
    }

    @Then("the user should be logged in.")
    public void theUserShouldBeLoggedIn() {
        String expectedText = String.format("Welcome %s", username);
        basePage.waitForLoggedInUsername(expectedText);
        basePage.verifyLoggedInUsername(expectedText);
        try {
            byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            currentScenario.attach(screenshotAs, "image/png", "the user should be logged in.");
        } catch (UnhandledAlertException e) {
            logging.error("No screenshot could be taken.");
            logging.error(e);
        }
    }
}
