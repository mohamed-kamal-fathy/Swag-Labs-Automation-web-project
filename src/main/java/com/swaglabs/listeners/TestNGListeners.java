package com.swaglabs.listeners;

import com.swaglabs.utils.*;
import org.testng.*;

import java.io.File;

import static com.swaglabs.utils.PropertiesUtils.loadProperties;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {

    File allure_results = new File("test-outputs/allure-results");
    File logs = new File("test-outputs/logs");
    File screenshots = new File("test-outputs/screenshots");

    @Override
    public void onExecutionStart() {
        LogsUtils.info("Test execution started");
        loadProperties();
        FilesUtils.deleteFiles(allure_results);
        FilesUtils.cleanDirectory(logs);
        FilesUtils.cleanDirectory(screenshots);
    }

    @Override
    public void onExecutionFinish() {
        LogsUtils.info("Test execution finished");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        if(method.isTestMethod())
        {
            try {
                CustomSoftAssertion.customAssertAll();
            }
            catch (AssertionError e)
            {
                testResult.setStatus(ITestResult.FAILURE);
                testResult.setThrowable(e);
            }

            switch (testResult.getStatus())
            {
                case ITestResult.SUCCESS:
                    ScreenshotsUtils.captureScreenshot("Sucess- " +testResult.getName());
                    break;

                case ITestResult.FAILURE:
                    ScreenshotsUtils.captureScreenshot("Failed- " +testResult.getName());
                    break;

                case ITestResult.SKIP:
                    ScreenshotsUtils.captureScreenshot("Skipped- " +testResult.getName());
                    break;


            }
            AllureUtils.attachLogsToAllureReport();
        }
    }
    @Override
    public void onTestSuccess(ITestResult result) {

        LogsUtils.info("Test is successful: " + result.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtils.info("Test is failed: " + result.getName());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("Test is skipped: " + result.getName());

    }

    }


