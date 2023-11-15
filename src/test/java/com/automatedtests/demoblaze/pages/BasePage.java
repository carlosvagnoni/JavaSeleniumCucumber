package com.automatedtests.demoblaze.pages;

import com.automatedtests.demoblaze.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * BasePage class represents the header and footer functionalities and elements for the application.
 */
public class BasePage extends PageObject {
    // Element locators
    private By signupButtonLocator = By.xpath("//*[@id=\"signin2\"]");
    private By signupTitleLocator = By.xpath("//*[@id=\"signInModalLabel\"]");
    private By loginButtonLocator = By.xpath("//*[@id=\"login2\"]");
    private By loginTitleLocator = By.xpath("//*[@id=\"logInModalLabel\"]");
    private By loginUsernameInputLocator = By.xpath("//*[@id=\"loginusername\"]");
    private By loginPasswordInputLocator = By.xpath("//*[@id=\"loginpassword\"]");
    private By signupUsernameInputLocator = By.xpath("//*[@id=\"sign-username\"]");
    private By signupPasswordInputLocator = By.xpath("//*[@id=\"sign-password\"]");
    private By submitSignupButtonLocator = By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]");
    private By submitLoginButtonLocator = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
    private By loggedInUsernameLocator = By.xpath("//*[@id=\"nameofuser\"]");
    private By cartButtonLocator = By.xpath("//*[@id=\"cartur\"]");

    /**
     * Constructor for BasePage.
     * @param driver The WebDriver object used for interacting with the browser.
     */
    public BasePage(WebDriver driver) {
        super(driver);
    }

    // Methods to interact with elements on the page
    public void clickSignupButton() {
        clickElement(signupButtonLocator);
    }

    public WebElement waitForSignupTitle() {
        return waitForElementVisibility(signupTitleLocator, 10);
    }

    public void verifySignupTitle() {
        verifyElementText(signupTitleLocator, "Sign up");
    }

    public void clickLoginButton() {
        clickElement(loginButtonLocator);
    }

    public WebElement waitForLoginTitle() {
        return waitForElementVisibility(loginTitleLocator, 10);
    }

    public void verifyLoginTitle() {
        verifyElementText(loginTitleLocator, "Log in");
    }

    public void enterLoginUsername(String inputValue) {
        enterText(loginUsernameInputLocator, inputValue);
    }

    public void enterLoginPassword(String inputValue) {
        enterText(loginPasswordInputLocator, inputValue);
    }

    public void enterSignupUsername(String inputValue) {
        enterText(signupUsernameInputLocator, inputValue);
    }

    public void enterSignupPassword(String inputValue) {
        enterText(signupPasswordInputLocator, inputValue);
    }

    public void verifyEnteredCredentials(String username, String password) {
        verifyElementTextValue(signupUsernameInputLocator, username);
        verifyElementTextValue(signupPasswordInputLocator, password);
    }

    public void submitSignup() {
        clickElement(submitSignupButtonLocator);
        waitForSeconds(1);
    }

    public void verifyAlertSuccessfulSignup(String expectedText) {
        verifyAlertText(expectedText);
    }

    public void submitLogin() {
        clickElement(submitLoginButtonLocator);
    }

    public boolean waitForLoggedInUsername(String text) {
        waitForElementToDisappear(loginTitleLocator, 10);
        return waitForElementInnerTextToBe(loggedInUsernameLocator, text, 10);
    }

    public void verifyLoggedInUsername(String expectText) {
        verifyElementText(loggedInUsernameLocator, expectText);
    }

    public void clickCartButton() {
        clickElement(cartButtonLocator);
    }

}
