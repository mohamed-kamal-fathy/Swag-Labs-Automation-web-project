package utilities.actions;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utilities.logs.LogsUtil;

public class Scrolling {

private Scrolling() {}


    @Step("Scrolling on the element:{locator}")
    public static void ScrollToElement(WebDriver driver , By locator)
    {
        LogsUtil.info("Scrolling to the element :",locator.toString());
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(locator));
    }
}
