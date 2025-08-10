package TestCases;

import org.testng.annotations.Parameters;
import utilities.constants.Constants;
import utilities.dataReader.JsonUtils;
import utilities.logs.LogsUtil;
import io.qameta.allure.testng.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import static utilities.actions.Waits.driver;

@Epic("E2E - SwagLabs Orders")
@Feature("Order Placement Scenarios")
public class EndToEnd extends BaseTests {

    JsonUtils testData;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Scenario 01 - Add two items to cart and complete the order successfully.")
    @Story("User Story #001 - As a user, I want to buy products and place an order")
    @Tag("smoke")
    @Parameters(value = "browser")
    public void scenario_01_validShopping_AddItemOnly() {

        testData = new JsonUtils(("test-data"));
        LogsUtil.info("Scenario 01: Start - Add items to cart and complete checkout");

        loginPage.enterUserName(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLogin();

        selectProduct.addToCart(testData.getJsonData("product-names.item1.name"));

        Cart.clickCartICon();

        Checkout.checkOut()
                .enterFirstName(Constants.checkOut_FirstName)
                .enterLastName(Constants.checkOut_LastName)
                .enterPostalCode(Constants.checkOut_PostalCode)
                .ClickContinue()
                .clickFinishOrderButton();

        Assert.assertEquals(driver.getCurrentUrl(), Constants.completeOrder_PageURL, "Thank you for your order!");
        LogsUtil.info("Scenario 01: Completed successfully");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Scenario 02 - Add multiple items and remove some before checkout (on landing page).")
    @Story("User Story #002 - As a user, I want to manage items in cart before purchasing")
    @Tag("Smoke")
    public void scenario_02_validShopping_addAndRemoveItem_InLandingPage() {
        LogsUtil.info("Scenario 02: Start - Add and remove items on landing page before checkout");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword(Constants.loginPassword)
                .clickLogin();

        selectProduct.addToCart(Constants.Products_Item1)
                .addToCart(Constants.Products_Item2)
                .addToCart(Constants.Products_Item5)
                .addToCart(Constants.Products_Item6)
                .removeFromCart(Constants.Products_Item1_Remove)
                .removeFromCart(Constants.Products_Item6_Remove);

        Cart.clickCartICon();

        Checkout.checkOut()
                .enterFirstName(Constants.checkOut_FirstName)
                .enterLastName(Constants.checkOut_LastName)
                .enterPostalCode(Constants.checkOut_PostalCode)
                .ClickContinue()
                .clickFinishOrderButton();

        Assert.assertEquals(driver.getCurrentUrl(), Constants.completeOrder_PageURL, "Thank you for your order!");
        LogsUtil.info("Scenario 02: Completed successfully");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Scenario 03 - Remove one item from cart page before completing checkout.")
    @Story("User Story #003 - As a user, I want to review and update cart before checkout")
    @Tag("smoke")
    public void scenario_03_validShopping_RemoveItem_InCartPage() {
        LogsUtil.info("Scenario 03: Start - Remove item from cart page before checkout");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword(Constants.loginPassword)
                .clickLogin();

        selectProduct.addToCart(Constants.Products_Item1)
                .addToCart(Constants.Products_Item5)
                .addToCart(Constants.Products_Item4);

        Cart.clickCartICon()
                .removeFromCart(Constants.Products_Item4_Remove);

        Checkout.checkOut()
                .enterFirstName(Constants.checkOut_FirstName)
                .enterLastName(Constants.checkOut_LastName)
                .enterPostalCode(Constants.checkOut_PostalCode)
                .ClickContinue()
                .clickFinishOrderButton();

        Assert.assertEquals(driver.getCurrentUrl(), Constants.completeOrder_PageURL, "Thank you for your order!");
        LogsUtil.info("Scenario 03: Completed successfully");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Scenario 04 - Remove items from both landing page and cart page, then complete the order.")
    @Story("User Story #004 - As a user, I want to fully manage my cart from both pages")
    @Tag("smoke")
    public void scenario_04_validShopping_RemoveItem_InCartPageAndLandingPage() {
        LogsUtil.info("Scenario 04: Start - Remove items from landing page and cart page before completing order");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword(Constants.loginPassword)
                .clickLogin();

        selectProduct.addToCart(Constants.Products_Item4)
                .addToCart(Constants.Products_Item5)
                .addToCart(Constants.Products_Item3)
                .addToCart(Constants.Products_Item2)
                .removeFromCart(Constants.Products_Item5_Remove)
                .removeFromCart(Constants.Products_Item3_Remove);

        Cart.clickCartICon()
                .removeFromCart(Constants.Products_Item2_Remove);

        Cart.click_On_ContinueShopping()
                .addToCart(Constants.Products_Item4)
                .clickCartICon();

        Checkout.checkOut()
                .enterFirstName(Constants.checkOut_FirstName)
                .enterLastName(Constants.checkOut_LastName)
                .enterPostalCode(Constants.checkOut_PostalCode)
                .ClickContinue()
                .clickFinishOrderButton();

        Assert.assertEquals(driver.getCurrentUrl(), Constants.completeOrder_PageURL, "Thank you for your order!");
        LogsUtil.info("Scenario 04: Completed successfully");
    }
}
