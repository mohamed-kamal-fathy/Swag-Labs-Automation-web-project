package media;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static utilities.actions.Waits.validateInitialized;

public class ScreenShot {
    private ScreenShot() {super();}
    public static final String SCREENSHOTS_PATH =  "test-outputs/screenshots/";

    public static void takeFullScreenShot(WebDriver driver, String screenShotsName) throws IOException {
        validateInitialized();
        File screenSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenDest = new File(SCREENSHOTS_PATH + screenShotsName + ".png");
        FileUtils.copyFile(screenSrc, screenDest);
        Allure.addAttachment(screenShotsName, Files.newInputStream(Path.of(screenDest.getPath())));
    }

}
