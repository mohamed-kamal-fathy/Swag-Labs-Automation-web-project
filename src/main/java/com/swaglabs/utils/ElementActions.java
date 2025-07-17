package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {
    private ElementActions(){}

    //send keys
    @Step("Sending Data : {data} to element : {locator}")
    public static void sendData(WebDriver driver , By locator , String data){

        Waits.waitForElementVisible(driver,locator);
        Scroll.scrollToElement(driver,locator);
        findElement(driver,locator).sendKeys(data);
        LogsUtils.info("Data Entered : "+data+" into the field: ",locator.toString());

    }

    //click
    @Step("Clicking on element : {locator}")
    public static void clickElement(WebDriver driver , By locator){

        Waits.waitForElementClickable(driver,locator);
        Scroll.scrollToElement(driver,locator);
        findElement(driver,locator).click();
        LogsUtils.info("Clicked on the element: ",locator.toString());
    }

    @Step("Getting text of the element : {locator}")
    public static String getText(WebDriver driver,By locator)
    {
        Waits.waitForElementVisible(driver, locator);
        Scroll.scrollToElement(driver,locator);
        LogsUtils.info("Text of the element: ",locator.toString(),"Text: ",findElement(driver,locator).getText());
        return findElement(driver,locator).getText();



    }

    //find element
    public static WebElement findElement(WebDriver driver,By locator)
    {
        LogsUtils.info("Finding element: ",locator.toString());
        return driver.findElement(locator);

    }

    public static String getTextFromInput(WebDriver driver, By locator)
    {
        Waits.waitForElementVisible(driver, locator);
        Scroll.scrollToElement(driver,locator);
        LogsUtils.info("Getting text from input: ",locator.toString(),"Text: ",findElement(driver,locator).getDomAttribute("value"));
        return findElement(driver,locator).getDomAttribute("value");
    }
}
