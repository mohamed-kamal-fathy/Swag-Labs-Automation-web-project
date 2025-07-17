package com.swaglabs.pages;

import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.swaglabs.utils.PropertiesUtils.getPropertyValue;

public class P05_OverviewPage {
    //variables
    private WebDriver driver;
    private final By errorMessage =By.cssSelector("[data-test='error']");


    //constructor
    public P05_OverviewPage(WebDriver driver)
    {
        this.driver = driver;
    }

    //locators
    private final By finishButton =By.id("finish");

    //actions
    @Step("Get error message")
    public String getErrorMessage()
    {
        return ElementActions.getText(driver, errorMessage);
    }

    @Step("Click Finish button")
    public P06_ConfirmationPage clickFinishButton()
    {
        ElementActions.clickElement(driver,finishButton);
        return new P06_ConfirmationPage(driver);
    }


    @Step("Assert Error Message")
    public P05_OverviewPage assertErrorMSQuestion()
    {
        Validations.validateEquals(getErrorMessage(),getPropertyValue("errorMSQ(InfoPage)"),"Error message is not as expected");
        return this;
    }
}
