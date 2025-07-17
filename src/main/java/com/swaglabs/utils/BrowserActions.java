package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private BrowserActions() {
    }

    @Step("Navigate to URL: {url}")
    public static void navigateToURL(WebDriver driver, String url)
    {
        driver.get(url);
        LogsUtils.info("Navigated to URL: " + url);
    }

    //get current URL
    @Step("Get current URL")
    public static String getCurrentURL(WebDriver driver)
    {
        LogsUtils.info("Current URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    //get page title
    @Step("Get page title")
    public static String getPageTitle(WebDriver driver)
    {
        LogsUtils.info("Page Title: " + driver.getTitle());
        return driver.getTitle();
    }

    //refresh page
    @Step("Refresh the page")
    public static void refreshPage(WebDriver driver)
    {
        LogsUtils.info("Refreshing the page");
        driver.navigate().refresh();
    }

    //close Browser
    @Step("Close the browser")
    public static void closeBrowser(WebDriver driver)
    {
        LogsUtils.info("Closing the browser");
        driver.quit();
    }
}
