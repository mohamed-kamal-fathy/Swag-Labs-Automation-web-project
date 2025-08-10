package utilities.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.logs.LogsUtil;

import java.time.Duration;
public class Waits {

    public static WebDriver driver;
    public static WebDriverWait wait;

    private Waits(){}//Private Constructor to prevent from any calling

    public static void initializeWait(WebDriver webDriver) {
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static void validateInitialized() {
        if (driver == null || wait == null) {
            throw new IllegalStateException("Utils is not initialized. Call initializeWait(driver) first.");
        }
    }
    //W8 Element To Present
    public static WebElement WaitForElementPresent(WebDriver driver, By locator)
    {
        LogsUtil.info("Waiting for the element present :",locator.toString());
       return new WebDriverWait(driver,Duration.ofSeconds(10)).until(d->d.findElement(locator));
    }
    //W8 Element To Visible
    public static WebElement WaitForElementVisible(WebDriver driver, By locator)
    {
        LogsUtil.info("Waiting for the element Visible :",locator.toString());
        return new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(d->
                        {
                            WebElement E = WaitForElementPresent(driver,locator);
                            return  E.isDisplayed() ? E :null ;
                        });
    }
    //W8 Element To Visible
    public static WebElement WaitForElementClickable(WebDriver driver, By locator)
    {
        LogsUtil.info("Waiting for the element to be Clickable :",locator.toString());
        return new WebDriverWait(driver,Duration.ofSeconds(20))
                .until(d->
                {
                    WebElement E = WaitForElementPresent(driver,locator);
                    return  E.isEnabled()?E :null ;
                });
    }

}
