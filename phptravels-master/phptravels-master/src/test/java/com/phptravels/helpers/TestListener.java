package com.phptravels.helpers;

import com.phptravels.utils.DriverFactory;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener implements ITestListener {

    public Logger log = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.debug("On test start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.debug("On test succes");

    }

    @Override // musi byc try catch poniewaz nie mozna nadpisac metody z interfejsu
    public void onTestFailure(ITestResult iTestResult) {
        try {
            log.debug("On test failure");
            SeleniumHelper.takeScreenshot(DriverFactory.getDriver());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getStackTrace());
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.debug("On test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.debug("On test failed but within percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.debug("On test start");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.debug("On test finish");
    }
}
