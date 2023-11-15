package com.automatedtests.demoblaze.pages;

import com.automatedtests.demoblaze.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * ProductPage class represents the product page functionalities and elements for the application.
 */
public class ProductPage extends PageObject {
    // Element locators
    private By macbookTitleLocator = By.xpath("//*[@id=\"tbodyid\"]/h2");
    private By addToCartButtonLocator = By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a");

    /**
     * Constructor for ProductPage.
     * @param driver The WebDriver object used for interacting with the browser.
     */
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Methods to interact with elements on the page
    public WebElement waitForMacbookTitle() {
        return waitForElementPresence(macbookTitleLocator, 10);
    }

    public void clickAddToCart() {
        clickElement(addToCartButtonLocator);
        waitForSeconds(1);
    }

    public void verifyAlertSuccessfulAddedToCart(String expectedText) {
        verifyAlertText(expectedText);
    }

}
