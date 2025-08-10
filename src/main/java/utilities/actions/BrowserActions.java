package utilities.actions;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utilities.logs.LogsUtil;

public class BrowserActions {
    private BrowserActions(){}


    @Step("Navigating To URL :{Url}")
    public static void NavigateToUrl(WebDriver driver,String Url)
    {

        driver.get(Url);
        LogsUtil.info("Browser Navigate to URL: ", Url);
    }
    @Step("Getting Current Title ")
    public static String GetPageTitle(WebDriver driver)
    {
        LogsUtil.info("Page Title: ",driver.getTitle());
        return driver.getTitle();
    }
    @Step("Getting Current URL")
    public static String GetCurrentURL(WebDriver driver)
    {
        LogsUtil.info("Current URL: ",driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }
    @Step("Refreshing the page")
    public static void RefreshPage(WebDriver driver,String Url)
    {
        LogsUtil.info("Refreshing Page ");
        driver.navigate().refresh();
    }
@Step("Closing the browser")
    public static void CloseBrowser (WebDriver D)
    {
        LogsUtil.info("Close Browser");
        D.quit();
    }

}
