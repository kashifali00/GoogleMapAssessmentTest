package com.qa.Listeners;

import com.aventstack.extentreports.Status;
import com.qa.BaseTest;
import com.qa.Utils.TestUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;


public class TestListeners implements ITestListener {
    BaseTest baseTest;
    TestUtils utils;
    File src;

    public TestListeners(){
         baseTest = new BaseTest();
         utils = new TestUtils();
    }
    public void onTestStart(ITestResult result)
    {
        //Before the start of tests
        baseTest.extentTest = baseTest.extentReports.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result)
    {
        baseTest.extentTest.log(Status.PASS,"Test Case:" + result.getMethod().getMethodName() + "passed");
    }

    public void onTestFailure(ITestResult result)
    {
        baseTest.extentTest.log(Status.FAIL,"Test Case:" + result.getMethod().getMethodName() + "failed");
        baseTest.extentTest.log(Status.FAIL, result.getThrowable());
        result.getThrowable().printStackTrace();
        try {
             src = ((TakesScreenshot) baseTest.getDriver()).getScreenshotAs(OutputType.FILE);
        }catch (Exception e){
            e.printStackTrace();
        }
        String imagePath = System.getProperty("user.dir") + File.separator + "html-report" +File.separator + "Screenshot" +
                File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator
                + result.getName()+"_"+utils.getDateTime() + ".png";
        try {
            FileUtils.copyFile(src, new File(imagePath));
           // Reporter.log("This is sample Screenshot");
            //Reporter.log("<a href='" + imagePath + "'> <image src='" + imagePath + "' height='200' width='200'/></a>");
        } catch (IOException e) {
            e.printStackTrace();
        }

        baseTest.extentTest.addScreenCaptureFromPath(imagePath);
    }

    public void onTestSkipped(ITestResult result)
    { /* compiled code */ }

    public void onTestFailedWithTimeout(ITestResult result)
    { /* compiled code */ }

    public void onStart(ITestContext context)
    {
        // setup the extent report here
        baseTest.extentReports = baseTest.setupExtentReporter();
    }

    public void onFinish(ITestContext context)
    {
        // closing the report
        baseTest.extentReports.flush();
    }

}
