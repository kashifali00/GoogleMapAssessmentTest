package com.qa;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.Utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class BaseTest {

    Properties props = new Properties();
    private static WebDriver driver = null;
    public  ExtentReports extentReports;
    public ExtentTest extentTest;
    TestUtils utils;
    public SoftAssert softAssert;

    public BaseTest(){
        utils = new TestUtils();
        softAssert = new SoftAssert();


    }

    public WebDriver getDriver(){
        return driver;
    }

    public void loadAllProperties(String configFileName) {
        try {

            System.out.println("Executing loadAllProperties Method....");
            String fileName = System.getProperty("user.dir") + File.separator + "src" +File.separator + "main" + File.separator + "resources"+ File.separator +configFileName;
            System.out.println("File name == " + fileName);
            props.load(new FileInputStream(fileName));
        }catch (IOException e){
            throw new RuntimeException("File not found ["+configFileName+" ]");
        }
    }


    public void setupDriver(String browser, String configFileName){
        System.out.println("Executing BeforeTest Method....");
        System.out.println("Executing com.qa.BaseTest Constructor....");
        loadAllProperties(configFileName);

        if(browser.equalsIgnoreCase("chrome")){
            System.out.println("Setting up Chrome driver...");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "Drivers" + File.separator + "chromedriver.exe" );
            driver = new ChromeDriver();
        }

        else if(browser.equalsIgnoreCase("firefox")){
            System.out.println("Setting up firefox driver...");
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "Drivers" + File.separator + "geckodriver.exe" );
            driver = new FirefoxDriver();
        }

        else {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "Drivers" + File.separator + "geckodriver.exe" );
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.get(props.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }



    public void driverTearDown(){
        driver.quit();
    }

    public ExtentReports setupExtentReporter(){
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +
                File.separator + "html-report/ExecutionReport_"+utils.getDateTime()+".html" );
        extentReports.attachReporter(sparkReporter);

        sparkReporter.config().setDocumentTitle("Document Title");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Regression Test Results");
        return extentReports;
    }

    // wait for the visibility of element
    public WebElement waitforvisibilityofElement(By elementLocation){
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(elementLocation));

    }

    public WebElement wiatForElementToBeClickable (By elementLocation){
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(elementLocation));
    }

    public void ClickOnElement( By locator){
        driver.findElement(locator).click();
    }

    public String getElementText(By locator){
        return driver.findElement(locator).getText();
    }

    public void sendTextToElement(By locator, String text){
        driver.findElement(locator).sendKeys(text);
    }

    public void moveToElement(By locator){
        new Actions(driver).moveToElement(driver.findElement(locator)).build().perform();
    }

    public String getPageTitle(){
        return driver.getTitle();

    }

} // End of class

