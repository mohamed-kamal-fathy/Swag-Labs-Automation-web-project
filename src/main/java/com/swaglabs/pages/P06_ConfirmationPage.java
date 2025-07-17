package com.swaglabs.pages;

import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_ConfirmationPage {

    //variables
    private WebDriver driver;

    //constructor
    public P06_ConfirmationPage(WebDriver driver)
    {
        this.driver = driver;
    }

    //locators
    private final By confirmationMessage = By.cssSelector(".complete-header");


    //actions

    @Step("Get Confirmation Message")
    public String getConfirmationMessage()
    {
        return ElementActions.getText(driver,confirmationMessage);
    }


    //validations

    @Step("Assert Confirmation Message: {expectedMessage}")
    public void assertConfirmationMessage(String expectedMessage)
    {
        String actualMessage = getConfirmationMessage();
        Validations.validateEquals(actualMessage,expectedMessage,"Confirmation message is not as expected");

    }



}
