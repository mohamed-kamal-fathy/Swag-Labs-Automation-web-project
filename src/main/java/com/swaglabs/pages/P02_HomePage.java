package com.swaglabs.pages;

import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class P02_HomePage {

    //variables
    private WebDriver driver ;
    public P02_HomePage(WebDriver driver)
    {
        this.driver = driver;
    }

    //locators
    private final By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutButton = By.id("logout_sidebar_link");


    //Actions
    @Step("Navigate to Home Page")
    public P02_HomePage navigateToHomePage()
    {
        BrowserActions.navigateToURL(driver, PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }

    @Step("Adding product to cart: {productName}")
    public P02_HomePage addProductToCart(String productName)
    {
        LogsUtils.info("Adding product to cart: ",productName);
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='"+productName+"']"));
        ElementActions.clickElement(driver,addToCartButton);
        return this;
    }

    @Step("Click cart icon")
    public P03_CartPage clickCartIcon()
    {
        ElementActions.clickElement(driver,cartIcon);
        return new P03_CartPage(driver);
    }

    @Step("Removing product from cart: {productName}")
    public P02_HomePage removeProductFromCart(String productName)
    {
        LogsUtils.info("Removing product from cart: ",productName);
        By removeButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='"+productName+"']"));
        ElementActions.clickElement(driver,removeButton);
        return this;
    }

    @Step("Open Menu")
    public P02_HomePage openMenu() {
        ElementActions.clickElement(driver, menuButton);
        return this;
    }

    @Step("Click Logout")
    public P01_LoginPage clickLogout() {
        ElementActions.clickElement(driver, logoutButton);
        return new P01_LoginPage(driver);
    }



    //Validations

    @Step("Assert product added to cart: {productName}")
    public P02_HomePage assertProductAddedToCart(String productName)
    {
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='"+productName+"']"));
        String actualValue = ElementActions.getText(driver,addToCartButton);
        LogsUtils.info("Actual value: ",actualValue);
        Validations.validateEquals(actualValue,"Remove","Product is not added to cart");
        LogsUtils.info("Asserting product added to cart: ",productName);

        return this;

    }



    @Step("Assert product removed from cart: {productName}")
    public P02_HomePage assertProductRemovedFromCart(String productName)
    {
        By removeButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='"+productName+"']"));
        String actualValue = ElementActions.getText(driver,removeButton);
        LogsUtils.info("Actual value: ",actualValue);
        Validations.validateEquals(actualValue,"Add to cart","Product is not removed from cart");
        LogsUtils.info("Asserting product removed from cart: ",productName);
        return this;
    }





}
