package com.swaglabs.pages;

import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_InformationPage {
    //variables
    private WebDriver driver;

    //constructor
    public P04_InformationPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueButton = By.id("continue");


    //actions

    @Step("Fill Information Fourm")
    public P04_InformationPage fillInformationFourm(String firstName, String lastName, String zipCode) {
        ElementActions.sendData(driver, this.firstName, firstName);
        ElementActions.sendData(driver, this.lastName, lastName);
        ElementActions.sendData(driver, this.zipCode, zipCode);
        return this;

    }

    @Step("Click Continue button")
    public P05_OverviewPage clickContinueButton() {
        ElementActions.clickElement(driver, this.continueButton);
        return new P05_OverviewPage(driver);
    }


//validations

    @Step("Assert Information Page")
    public void assertInfoPage(String firstName,String lastName,String zipCode)
    {
        CustomSoftAssertion.softAssertion.assertEquals(ElementActions.getTextFromInput(driver,this.firstName),firstName);
        CustomSoftAssertion.softAssertion.assertEquals(ElementActions.getTextFromInput(driver,this.lastName),lastName);
        CustomSoftAssertion.softAssertion.assertEquals(ElementActions.getTextFromInput(driver,this.zipCode),zipCode);

    }



}