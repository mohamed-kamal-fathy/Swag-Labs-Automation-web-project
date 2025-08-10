package utilities.report;

import io.qameta.allure.Allure;
import utilities.dataReader.FilesUtils;
import utilities.logs.LogsUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {

    public static final String ALLURE_RESULTS_PATH =  "test-outputs/allure-results";
    private AllureUtils() {super();}

    public static void attachLogsToAllureReport() {
        try {
            // Get the latest log file
            File logFile = FilesUtils.getLatestFile(LogsUtil.LOGS_PATH);

            // Verify the log file exists
            if (logFile == null || !logFile.exists()) {
                LogsUtil.warn("Log file does not exist in path: " + LogsUtil.LOGS_PATH);
                return;
            }

            // Read and attach the log file to Allure report
            String logContent = Files.readString(Path.of(logFile.getPath()));
            Allure.addAttachment("Logs.log", "text/plain", logContent);

        } catch (IOException e) {
            LogsUtil.error("Failed to read log file: " + e.getMessage());
        } catch (Exception e) {
            LogsUtil.error("Failed to attach logs to Allure report: " + e.getMessage());
        }

    }
    public static void attachScreenshotToAllure(String screenshotName, String screenshotPath) {
        try {
            Allure.addAttachment(screenshotName,Files.newInputStream(Path.of(screenshotPath)));
        }catch (Exception e)
        {
            LogsUtil.error("Failed To Attach screen shot to allure report:"+e.getMessage());
        }

    }
}
