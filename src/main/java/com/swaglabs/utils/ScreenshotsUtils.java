package com.swaglabs.utils;

import com.swaglabs.drivers.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;


public class ScreenshotsUtils {

    public static final String SCREENSHOTS_PATH = "test-outputs/screenshots/";

    private ScreenshotsUtils(){
    }
    public static void captureScreenshot(String screenshotName)
    {
        try {
            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File screenShotFile = new File(SCREENSHOTS_PATH + screenshotName + ".png");
            FileUtils.copyFile(screenshot, screenShotFile);
            AllureUtils.attachScreenshotToAllure(screenshotName,screenShotFile.getPath());

        }
        catch (Exception e)
        {
            LogsUtils.error("Failed to capture screenshot: "+ e.getMessage());
        }

    }


}
