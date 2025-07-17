package com.swaglabs.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {
    private AllureUtils(){

    }
    public static final String ALLURE_Results_PATH = "test-outputs/allure-results";

    public static  void attachLogsToAllureReport( )
    {
        try {
            File logFile = FilesUtils.getLatestFile(LogsUtils.LOGS_PATH);
            if (!logFile.exists())
            {
                LogsUtils.warn("No Logs Found in the Directory: "+ LogsUtils.LOGS_PATH);
                return;
            }
            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
            LogsUtils.info("Logs attached to allure report");
        }
        catch (Exception e)
        {
            LogsUtils.error("Failed to attach logs to allure report: "+ e.getMessage());
        }


    }
    public static void attachScreenshotToAllure(String screenshotName,String screenshotPath)
    {
        try {
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotPath)));
        }

        catch (Exception e)
        {
            LogsUtils.error("Failed to attach screenshot to allure report: "+ e.getMessage());
        }
    }
}
