package com.automatedtests.demoblaze.pages;

import com.automatedtests.demoblaze.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * HomePage class represents the home page functionalities and elements for the application.
 */
public class HomePage extends PageObject {
    // Element locators
    private By laptopsCategoryButtonLocator = By.xpath("/html/body/div[5]/div/div[1]/div/a[3]");
    private By macbookButtonLocator = By.xpath("//*[@id=\"tbodyid\"]/div[3]/div/div/h4/a");

    /**
     * Constructor for HomePage.
     * @param driver The WebDriver object used for interacting with the browser.
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Methods to interact with elements on the page
    public void clickLaptopsCategoryButton() {
        clickElement(laptopsCategoryButtonLocator);
    }

    public boolean waitForMacbookButton() {
        return waitForElementInnerTextToBe(macbookButtonLocator, "MacBook air", 10);
    }

    public void clickMacbookButton() {
        clickElement(macbookButtonLocator);
    }
}
