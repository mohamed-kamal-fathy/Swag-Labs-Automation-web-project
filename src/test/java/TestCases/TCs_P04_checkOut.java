package TestCases;

import utilities.constants.Constants;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static utilities.actions.Waits.driver;


@Listeners({AllureTestNg.class})
@Epic("Swag Labs E2E Testing")
@Feature("Checkout Process")
public class TCs_P04_checkOut extends BaseTests {

    private static final Logger log = LogManager.getLogger(TCs_P04_checkOut.class);

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user can complete the checkout process successfully after adding products to cart.")
    @Story("User Story: As a user," +
            " I want to complete a purchase with valid information " +
            "so I can receive my products.")
    public void validCheckout() {
        log.info("Starting valid checkout test case");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword(Constants.loginPassword)
                .clickLogin();

        log.info("Login successful");

        selectProduct.addToCart(Constants.Products_Item1)
                .addToCart(Constants.Products_Item2);

        log.info("Added products to cart: {}, {}", Constants.Products_Item1, Constants.Products_Item2);

        Cart.clickCartICon();
        log.info("Navigated to cart page");

        Checkout.checkOut()
                .enterFirstName(Constants.checkOut_FirstName)
                .enterLastName(Constants.checkOut_LastName)
                .enterPostalCode(Constants.checkOut_PostalCode)
                .ClickContinue()
                .clickFinishOrderButton();

        String actualUrl = driver.getCurrentUrl();
        log.info("Actual URL after checkout: {}", actualUrl);
        log.info("Expected URL: {}", Constants.completeOrder_PageURL);

        Assert.assertEquals(actualUrl, Constants.completeOrder_PageURL,
                "Checkout failed - User was not redirected to the complete order page.");

        log.info("Checkout process completed successfully - test passed");
    }
}
