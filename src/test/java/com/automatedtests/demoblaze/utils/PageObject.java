package com.automatedtests.demoblaze.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.apache.log4j.Logger;

/**
 * PageObject class encapsulates selenium common methods and functionalities used across multiple pages.
 */
public class PageObject {
    private static final Logger logging = Logger.getLogger(PageObject.class);
    private final WebDriver web;

    /**
     * Constructor for PageObject.
     * @param driver The WebDriver object used for interacting with the browser.
     */
    public PageObject(WebDriver driver) {
        this.web = driver;
    }

    // Selenium methods for common interactions with web elements
    public void clickElement(By elementLocator) {
        handleException(() -> {
            web.findElement(elementLocator).click();
            return null;
        });
    }

    public WebElement waitForElementVisibility(By elementLocator, int timeout) {
        return handleException(() -> {
            return new WebDriverWait(web, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        });

    }

    public WebElement waitForElementPresence(By elementLocator, int timeout) {
        return handleException(() -> {
            return new WebDriverWait(web, Duration.ofSeconds(timeout)).until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        });
    }

    public Boolean waitForElementToDisappear(By elementLocator, int timeout) {
        return handleException(() -> {
            return new WebDriverWait(web, Duration.ofSeconds(timeout)).until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
        });
    }

    public Boolean waitForElementInnerTextToBe(By elementLocator, String expectedText, int timeout) {
        return handleException(() -> {
            return new WebDriverWait(web, Duration.ofSeconds(timeout)).until(ExpectedConditions.textToBePresentInElementLocated(elementLocator, expectedText));
        });
    }

    public void verifyElementText(By elementLocator, String expectedText) {
        handleException(() -> {
            String actualText = web.findElement(elementLocator).getText();
            try {
                assert actualText.equals(expectedText);
            } catch (AssertionError e) {
                String errorMessage = "Expected text to be '" + expectedText + "', but was '" + actualText + "'";
                logging.error(new AssertionError(errorMessage, e));
                throw new AssertionError(errorMessage, e);
            }
            return null;
        });
    }

    public void verifyElementTextValue(By elementLocator, String expectedText) {
        handleException(() -> {
            String actualText = web.findElement(elementLocator).getAttribute("value");
            try {
                assert actualText.equals(expectedText);
            } catch (AssertionError e) {
                String errorMessage = "Expected text to be '" + expectedText + "', but was '" + actualText + "'";
                logging.error(new AssertionError(errorMessage, e));
                throw new AssertionError(errorMessage, e);
            }
            return null;
        });
    }

    public Alert switchToAlert() {
        return handleException(() -> {
            return web.switchTo().alert();
        });
    }

    public void verifyAlertText(String expectedText) {
        handleException(() -> {
            Alert alert = web.switchTo().alert();
            String alertMessage = alert.getText();
            try {
                assert alertMessage.equals(expectedText);
            } catch (AssertionError e) {
                String errorMessage = "Assertion failed: Actual text '" + alertMessage + "' does not match expected text '" + expectedText + "'";
                logging.error(new AssertionError(errorMessage, e));
                throw new AssertionError(errorMessage);
            }
            return null;
        });
    }

    public void submitAlert() {
        handleException(() -> {
            Alert alert = web.switchTo().alert();
            alert.accept();
            return null;
        });
    }

    public void enterText(By elementLocator, String text) {
        handleException(() -> {
            web.findElement(elementLocator).sendKeys(text);
            return null;
        });
    }

    public void simulateEnter(By elementLocator) {
        handleException(() -> {
            web.findElement(elementLocator).sendKeys(Keys.ENTER);
            return null;
        });
    }

    public void waitForSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentUrl() {
        return handleException(() -> {
            return web.getCurrentUrl();
        });
    }

    /**
     * Handles exceptions and logs relevant information for various WebDriver interactions.
     *
     * @param action The action to be performed.
     * @param <T>    Type of the return value.
     * @return Returns the result of the action or null if an exception occurs.
     */
    public <T> T handleException(Supplier<T> action) {
        try {
            return action.get();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            logging.debug("Element not found or not visible.", e);
        } catch (ElementClickInterceptedException e) {
            logging.debug("Element click intercepted.", e);
        } catch (ElementNotInteractableException e) {
            logging.debug("Element not interactable.", e);
        } catch (TimeoutException e) {
            logging.debug("Timeout expired.", e);
        } catch (NoAlertPresentException e) {
            logging.debug("Alert not found.", e);
        } catch (WebDriverException e) {
            logging.debug("WebDriver exception occurred.", e);
        }
        return null;
    }
}