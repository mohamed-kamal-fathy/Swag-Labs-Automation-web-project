package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.actions.ElementActions.*;

public class P04_Checkout {

    private final WebDriver driver;

    //Locators
    private final By checkoutButton = By.id("checkout");
    private final By ConfirmOrder_FirstName = By.id("first-name");
    private final By ConfirmOrder_LastName = By.id("last-name");
    private final By ConfirmOrder_PostalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By FinishOrder = By.id("finish");
    private final By CancelOrder = By.id("cancel");

    //constructor

    public P04_Checkout(WebDriver driver) {

        this.driver = driver;
    }

    //actions

    public P04_Checkout checkOut() {
        clickElement(driver, checkoutButton);
        return new P04_Checkout(driver);
    }

    public P04_Checkout enterFirstName(String firstNameText) {
        SendData(driver, ConfirmOrder_FirstName, firstNameText);
        return this;
    }

    public P04_Checkout enterLastName(String lastNameText) {
        SendData(driver, ConfirmOrder_LastName, lastNameText);
        return this;
    }

    public P04_Checkout enterPostalCode(String postalCodeText) {
        SendData(driver, ConfirmOrder_PostalCode, postalCodeText);
        return this;
    }

    public P04_Checkout ClickContinue() {
        clickElement(driver, continueButton);
        return this;
    }

    public P02_Landing ClickCancel() {
        clickElement(driver, CancelOrder);
        return new P02_Landing(driver);
    }

    public void clickFinishOrderButton() {
        clickElement(driver, FinishOrder);

    }


}
