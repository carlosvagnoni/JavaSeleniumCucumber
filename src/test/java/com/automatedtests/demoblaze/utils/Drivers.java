package com.automatedtests.demoblaze.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * The Drivers class manages the creation of different WebDriver instances
 * for various browsers like Chrome, Edge, and Firefox.
 */
public class Drivers {
    private boolean isHeadless; // Flag to determine if the browser should run in headless mode
    public WebDriver driver; // WebDriver instance to interact with the browser

    /**
     * Constructs a Drivers instance using the provided WebDriver type, defaulting headless mode to false.
     *
     * @param webdriverType The type of WebDriver to initialize (e.g., "chrome", "edge", "firefox")
     */
    public Drivers(String webdriverType) {
        this(webdriverType, false);
    }

    /**
     * Constructs a Drivers instance using the provided WebDriver type and headless mode flag.
     *
     * @param webdriverType The type of WebDriver to initialize (e.g., "chrome", "edge", "firefox")
     * @param isHeadless    Flag indicating whether to run the browser in headless mode
     */
    public Drivers(String webdriverType, boolean isHeadless) {
        this.isHeadless = isHeadless;
        this.driver = getWebDriver(webdriverType);
    }

    /**
     * Gets the appropriate WebDriver based on the specified type.
     *
     * @param webdriverType The type of WebDriver to initialize (e.g., "chrome", "edge", "firefox")
     * @return WebDriver instance for the specified browser type
     * @throws IllegalArgumentException if an unsupported webdriverType is provided
     */
    private WebDriver getWebDriver(String webdriverType) {
        switch (webdriverType) {
            case "chrome":
                return chromeDriver();
            case "edge":
                return edgeDriver();
            case "firefox":
                return firefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported webdriverType: " + webdriverType);
        }
    }

    private WebDriver chromeDriver() {
        // Creating an instance of Chrome
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
        }
        return new ChromeDriver(options);
    }

    private WebDriver edgeDriver() {
        // Creating an instance of Microsoft Edge
        EdgeOptions options = new EdgeOptions();
        if (isHeadless) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
        }
        return new EdgeDriver(options);
    }

    private WebDriver firefoxDriver() {
        // Creating an instance of Firefox
        FirefoxOptions options = new FirefoxOptions();
        if (isHeadless) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
        }
        return new FirefoxDriver(options);
    }
}
