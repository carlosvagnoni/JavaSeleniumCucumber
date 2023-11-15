package com.automatedtests.demoblaze.steps;

import com.automatedtests.demoblaze.utils.Expect;
import com.automatedtests.demoblaze.pages.BasePage;
import com.automatedtests.demoblaze.pages.CartPage;
import com.automatedtests.demoblaze.pages.HomePage;
import com.automatedtests.demoblaze.pages.ProductPage;
import com.automatedtests.demoblaze.utils.Configuration;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;

import org.apache.log4j.Logger;

/**
 * This class contains Cucumber steps for adding a product to the shopping cart on a demo e-commerce site.
 */
public class AddProductToCartSteps {
    // Logger instance for logging purposes
    private static final Logger logging = Logger.getLogger(AddProductToCartSteps.class);

    // WebDriver instance retrieved from Hooks class
    private WebDriver driver = Hooks.driver;

    // Configuration instance retrieved from Hooks class
    private Configuration config = Hooks.config;

    // Instances of page objects for different pages involved in the process
    private BasePage basePage = new BasePage(driver);
    private HomePage homePage = new HomePage(driver);
    private ProductPage productPage = new ProductPage(driver);
    private CartPage cartPage = new CartPage(driver);

    // Represents the current scenario being executed
    public static Scenario currentScenario;


    @Given("the user is browsing the list of available products.")
    public void theUserIsBrowsingProducts() {
        try {
            new Expect(driver.getCurrentUrl()).toBeEqual("https://www.demoblaze.com/");
        } catch (AssertionError e) {
            logging.error(e);
        }
        try {
            byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            currentScenario.attach(screenshotAs, "image/png", "the user is browsing the list of available products.");
        } catch (UnhandledAlertException e) {
            logging.error("No screenshot could be taken.");
            logging.error(e);
        }
    }

    @When("the user selects a product from the 'laptops' category.")
    public void theUserSelectsProductFromCategory() {
        homePage.clickLaptopsCategoryButton();
        homePage.waitForMacbookButton();
        homePage.clickMacbookButton();
    }

    @When("the user adds the selected product to the shopping cart.")
    public void theUserAddsProductToCart() {
        productPage.waitForMacbookTitle();
        productPage.clickAddToCart();
        String expectedText = "Product added.";
        productPage.verifyAlertSuccessfulAddedToCart(expectedText);
        productPage.submitAlert();
    }

    @Then("the product should be added to the user's shopping cart.")
    public void theProductShouldBeInCart() {
        basePage.clickCartButton();
        cartPage.waitForMacbookTitleInCart();
        String expectedText = "MacBook air";
        cartPage.verifyMacbookTitleInCart(expectedText);
        try {
            byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            currentScenario.attach(screenshotAs, "image/png", "the product should be added to the user's shopping cart.");
        } catch (UnhandledAlertException e) {
            logging.error("No screenshot could be taken.");
            logging.error(e);
        }
    }
}
