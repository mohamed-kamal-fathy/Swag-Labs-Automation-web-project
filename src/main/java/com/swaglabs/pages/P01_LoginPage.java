package com.swaglabs.pages;

import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.swaglabs.utils.PropertiesUtils.getPropertyValue;

public class P01_LoginPage {
    //Locators
    private final WebDriver driver;
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage =By.cssSelector("[data-test='error']");

    //Constructor
    public P01_LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    //Navigate to login page
    @Step("Navigate to login page")
    public void navigateToLoginPage()
    {
        BrowserActions.navigateToURL(driver, "https://www.saucedemo.com");
    }

    //Actions > wait -scroll-find-sendKeys

    @Step("Enter username: {username}")
    public P01_LoginPage enterUsername(String username)
    {
        ElementActions.sendData(driver, this.username, username);
        return this;
    }

    @Step("Enter password: {password}")
    public P01_LoginPage enterPassword(String password)
    {
        ElementActions.sendData(driver, this.password, password);
        return this;
    }


    @Step("Click Login button")
    public P01_LoginPage clickLoginButton()

    {
        ElementActions.clickElement(driver, loginButton);
        return this;
    }

    @Step("Get error message")
    public String getErrorMessage()
    {
        return ElementActions.getText(driver, errorMessage);
    }


    //Validations

    @Step("Assert Login Page URL")
    public P01_LoginPage assertLoginPageURL()
    {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getCurrentURL(driver),getPropertyValue("homeURL"),"URL is not as expected");
        return this;
    }

    @Step("Assert Login Page Title")
    public P01_LoginPage assertLoginPageTitle()
    {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getPageTitle(driver),getPropertyValue("appTitle"),"Title is not as expected");
        return this;

    }


    @Step("Assert Sucessfull Login Soft")
    public P01_LoginPage assertSuccessfullLoginSoft()
    {
        assertLoginPageURL().assertLoginPageTitle();
        return this;

    }


    @Step("Assert Successfull Login")
    public P01_LoginPage assertSuccessfullLogin()
    {
        Validations.ValidatePageUrl(driver,getPropertyValue("homeURL"));

        return this;

    }

    @Step("Assert Unsuccessfull Login")
    public P01_LoginPage assertUnsuccessfullLogin()
    {
        Validations.validateEquals(getErrorMessage(),getPropertyValue("errorMSG"),"error message is not as expected");
        return this;


    }

    @Step("Assert Empty Field Login")
    public P01_LoginPage assertEmptyFieldLogin()
    {
        Validations.validateEquals(getErrorMessage(),getPropertyValue("errorMSG(EmptyField)"),"error message is not as expected");
        return this;
    }

    @Step("Assert Locked Out Login")
    public P01_LoginPage assertLockedOutLogin()
    {
        Validations.validateEquals(getErrorMessage(),getPropertyValue("errorMSG(LockedOut)"),"error message is not as expected");
        return this;
    }

    @Step("Assert On Login Page")
    public P01_LoginPage assertOnLoginPage() {
        Validations.ValidatePageUrl(driver, getPropertyValue("baseURL"));
        Validations.ValidateElementIsVisible(driver, driver.findElement(By.id("login-button")));
        return this;
    }


}



