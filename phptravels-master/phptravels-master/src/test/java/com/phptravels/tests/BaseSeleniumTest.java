package com.phptravels.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.phptravels.helpers.SeleniumHelper;
import com.phptravels.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public abstract class BaseSeleniumTest {

    protected WebDriver driver;
    protected ExtentHtmlReporter reporter;
    protected ExtentReports reports;

    @BeforeTest
    public void setUpReporter() {
        reporter = new ExtentHtmlReporter("C:\\Users\\laptop\\IdeaProjects\\phptravels\\src\\test\\resources\\reports\\index.html");
        reports = new ExtentReports();
        reports.attachReporter(reporter);
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("http://www.kurs-selenium.pl/demo/");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        DriverFactory.resetDriver();
    }

    @AfterTest
    public void tearDownReporter() {
        reporter.flush();
        reports.flush();
    }

    MediaEntityModelProvider getScreenshot() throws IOException {
        return MediaEntityBuilder.createScreenCaptureFromPath(SeleniumHelper.takeScreenshot(driver)).build();

    }
}