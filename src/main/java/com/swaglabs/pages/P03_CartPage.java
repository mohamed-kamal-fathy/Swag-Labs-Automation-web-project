package com.swaglabs.pages;

import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_CartPage {

    //variables
    private WebDriver driver ;

    //constructor
    public P03_CartPage(WebDriver driver)
    {
        this.driver = driver;
    }

    //locators
    private final By productName = By.cssSelector(".inventory_item_name");
    private final By productPrice = By.cssSelector(".inventory_item_price");
    private final By checkoutButton = By.cssSelector(".checkout_button");

    //Actions
    @Step("Get Product Name")
    private String getProductName()
    {
        return ElementActions.getText(driver,productName);
    }


    @Step("Get Product Price")
    private String getProductPrice()
    {
        return ElementActions.getText(driver,productPrice);
    }


    @Step("Click Checkout button")
    public P04_InformationPage clickCheckoutButton()
    {
        ElementActions.clickElement(driver,checkoutButton);
        return new P04_InformationPage(driver);
    }


    //Validations

    @Step("Assert product details: {productName}, {productPrice}")
    public P03_CartPage assertProductDetails(String productName, String productPrice)
    {
        String actualProductName = getProductName();
        String actualProductPrice = getProductPrice();
        CustomSoftAssertion.softAssertion.assertEquals(actualProductName,productName,"Product name is not as expected");
        CustomSoftAssertion.softAssertion.assertEquals(actualProductPrice,productPrice,"Product price is not as expected");
        return this;
    }



}
