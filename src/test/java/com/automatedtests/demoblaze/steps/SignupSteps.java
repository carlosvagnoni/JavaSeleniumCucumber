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
 * This class contains Cucumber steps for user registration on the application.
 */
public class SignupSteps {
    // Logger instance for logging purposes
    private static final Logger logging = Logger.getLogger(SignupSteps.class);

    // WebDriver instance retrieved from Hooks class
    private WebDriver driver = Hooks.driver;

    // Configuration instance retrieved from Hooks class
    private Configuration config = Hooks.config;

    // Variables to store username and password
    private String username;
    private String password;

    // Instance of BasePage for common page interactions
    private BasePage basePage = new BasePage(driver);

    // Represents the current scenario being executed
    public static Scenario currentScenario;


    @Given("the user is on the Registration Page.")
    public void theUserIsOnRegistrationPage() {
        driver.get(config.getUrl());
        basePage.clickSignupButton();
        basePage.waitForSignupTitle();
        basePage.verifySignupTitle();
        try {
            byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            currentScenario.attach(screenshotAs, "image/png", "the user is on the Registration Page.");
        } catch (UnhandledAlertException e) {
            logging.error("No screenshot could be taken.");
            logging.error(e);
        }
    }

    @When("the user provides the following registration details: {string}, {string}.")
    public void theUserProvidesRegistrationDetails(String username, String password) {
        this.username = username;
        this.password = password;
        basePage.enterSignupUsername(username);
        basePage.enterSignupPassword(password);
        basePage.verifyEnteredCredentials(username, password);
        try {
            byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            currentScenario.attach(screenshotAs, "image/png", "the user provides the following registration details:");
        } catch (UnhandledAlertException e) {
            logging.error("No screenshot could be taken.");
            logging.error(e);
        }
    }

    @When("the user clicks on the Sign Up button.")
    public void theUserClicksSignUpButton() {
        basePage.submitSignup();
    }

    @Then("the user should be registered successfully.")
    public void theUserShouldBeRegisteredSuccessfully() {
        String expectedText = "Sign up successful.";
        basePage.switchToAlert();
        basePage.verifyAlertSuccessfulSignup(expectedText);
        basePage.submitAlert();
    }

}
