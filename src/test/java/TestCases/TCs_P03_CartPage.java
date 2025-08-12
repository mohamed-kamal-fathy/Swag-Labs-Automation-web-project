package TestCases;

import utilities.constants.Constants;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

import static utilities.actions.Waits.driver;


@Epic("Cart Page Functionality")
@Feature("P03 - Cart Price Validation and Product Management")
public class TCs_P03_CartPage extends BaseTests {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    private final Logger logger = LogManager.getLogger(TCs_P03_CartPage.class);

    @Test(priority = 0)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that total price in cart matches the total price of selected products.")
    @Story("User Story #31 - As a user, " +
            "I want to see the correct total price in the cart after adding products.")
    public void comparingPricesTC_001() {
        logger.info("Starting: comparingPricesTC_001 - Comparing full cart prices");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword(Constants.loginPassword)
                .clickLogin()
                .addAllProductsToCart()
                .getTotalPriceOfSelectedProducts();

        selectProduct.clickCartICon();

        boolean isPriceMatching = Cart.comparingPrices();
        logger.info("Is price matching: " + isPriceMatching);

        Assert.assertTrue(isPriceMatching, "Cart total does not match the sum of individual product prices.");
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Add random products and validate number of items in cart.")
    @Story("User Story #32 - As a user," +
            " I want to add random products and verify their count in the cart.")
    public void comparingPricesTC_002() {
        logger.info("Starting: comparingPricesTC_002 - Adding random products");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword(Constants.loginPassword)
                .clickLogin()
                .addRandomProducts(3, 6);

        String cartCount = selectProduct.getNumberOfProductsOnCart();
        logger.info("Number of products in cart: " + cartCount);

        Assert.assertEquals(cartCount, "3", "Expected 3 items to be added to the cart.");
    }
}
