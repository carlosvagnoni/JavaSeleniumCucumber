package com.automatedtests.demoblaze;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * This class serves as the Test Runner for DemoBlaze automated tests.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/automatedtests/demoblaze/features", // Location of feature files
        glue = {                                                          // Glue between feature files and step definition files
                "com/automatedtests/demoblaze/steps",
                "com/automatedtests/demoblaze/utils"
        },
        plugin = {                                                        // Format and location of reports
                "pretty", "html:target/reports/demoblaze.html",
                "json:target/reports/cucumber.json"
        }
)
public class TestRunner {
}
