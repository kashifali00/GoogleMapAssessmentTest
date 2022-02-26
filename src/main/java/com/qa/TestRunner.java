package com.qa;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/Features/",
        glue = {"stepDefinations", "com.qa.Hook"},
        tags = "@Smoke",
        plugin = {"pretty",
        "html:target/cucumber-reports/cucumber-pretty",
        "json:target/cucumber-reports/CucumberTestReport.json"})


public class TestRunner extends AbstractTestNGCucumberTests {

    BaseTest baseTest;
    public TestRunner(){
        baseTest = new BaseTest();
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser", "configFileName"})
    public void setUpClass(String browser, String configFileName) {
            baseTest.setupDriver(browser,configFileName);
        }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        baseTest.driverTearDown();
    }

    }


