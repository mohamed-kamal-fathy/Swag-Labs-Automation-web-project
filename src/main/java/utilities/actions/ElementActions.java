package utilities.actions;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.logs.LogsUtil;

import static utilities.actions.Waits.initializeWait;
import static utilities.actions.Waits.wait;

public class ElementActions {
    private ElementActions(){}


    @Step("Sending Data :{data} to the element :{locator}")
    public static void SendData(WebDriver driver, By locator, String Text) {
        initializeWait(driver);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(Text);
    }
    @Step("Clicking on element : {locator}")
    public static void clickElement(WebDriver driver, By locator) {
        initializeWait(driver);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public static void selectFromDropdown(WebDriver driver, By locator, String option) {
        Waits.WaitForElementVisible(driver, locator);
        Scrolling.ScrollToElement(driver, locator);
        new Select(driver.findElement(locator)).selectByVisibleText(option);
    }

    public static void selectFromDropdownByValue(WebDriver driver, By locator, String value) {
        Waits.WaitForElementVisible(driver, locator);
        Scrolling.ScrollToElement(driver, locator);
        new Select(driver.findElement(locator)).selectByValue(value);
    }



    public static void selectFromDropdownByIndex(WebDriver driver, By locator, int Data) {
        Waits.WaitForElementVisible(driver, locator);
        Scrolling.ScrollToElement(driver, locator);
        new Select(driver.findElement(locator)).selectByIndex(Data);
    }

    @Step("Scrolling to the element :{locator}")
    public static void ScrollToElement(WebDriver driver , By locator)
    {
        LogsUtil.info("Scrolling to the element :",locator.toString());
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(locator));
    }
    @Step("Getting Text from the element :{locator}")
    public static String getText(WebDriver driver, By locator) {
        initializeWait(driver);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
}
