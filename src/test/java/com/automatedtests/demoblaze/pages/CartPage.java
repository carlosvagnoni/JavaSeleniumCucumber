package com.automatedtests.demoblaze.pages;

import com.automatedtests.demoblaze.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * CartPage class represents the cart page functionalities and elements for the application.
 */
public class CartPage extends PageObject {
    // Element locators
    private By macbookTitleInCartLocator = By.xpath("//*[@id=\"tbodyid\"]/tr/td[2]");

    /**
     * Constructor for CartPage.
     * @param driver The WebDriver object used for interacting with the browser.
     */
    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Methods to interact with elements on the page
    public boolean waitForMacbookTitleInCart() {
        waitForElementPresence(macbookTitleInCartLocator, 10);
        return waitForElementInnerTextToBe(macbookTitleInCartLocator, "MacBook air", 10);
    }

    public void verifyMacbookTitleInCart(String expectText) {
        verifyElementText(macbookTitleInCartLocator, expectText);
    }
}
