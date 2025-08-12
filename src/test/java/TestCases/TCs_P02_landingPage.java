package TestCases;

import utilities.constants.Constants;
import io.qameta.allure.*;
import listeners.MethodListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static utilities.actions.Waits.driver;

@Listeners(MethodListener.class)
@Epic("Landing Page Functionality")
@Feature("P02 - Cart, Logout, and Reset Operations")
public class TCs_P02_landingPage extends BaseTests {

    private static final Logger log = LogManager.getLogger(TCs_P02_landingPage.class);

//

    @Test(priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Add all available products to the cart and verify cart count.")
    @Story("User Story #22 - As a user, " +
            "I want to add all products to the cart to complete a bulk purchase.")
    public void addingAllProductsToCartTC() {
        log.info("Starting: addingAllProductsToCartTC");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword(Constants.loginPassword)
                .clickLogin()
                .addAllProductsToCart();

        boolean isMatching = selectProduct.comparingNumberOfSelectedProductsWithCart();
        int expected = Integer.parseInt(selectProduct.getNumberOfSelectedProducts());
        int actual = Integer.parseInt(selectProduct.getNumberOfProductsOnCart());

        log.info("Expected cart count: {}, Actual: {}", expected, actual);
        Assert.assertTrue(isMatching, "Cart count doesn't match selected products");
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Add random products to cart and validate cart count.")
    @Story("User Story #23 - As a user," +
            " I want to select a few random items to test flexible cart behavior.")
    public void addingRandomProductsToCartTC() {
        log.info("Starting: addingRandomProductsToCartTC");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword(Constants.loginPassword)
                .clickLogin()
                .addRandomProducts(3, 6);

        boolean isMatching = selectProduct.comparingNumberOfSelectedProductsWithCart();
        int expected = Integer.parseInt(selectProduct.getNumberOfSelectedProducts());
        int actual = Integer.parseInt(selectProduct.getNumberOfProductsOnCart());

        log.info("Expected cart count: {}, Actual: {}", expected, actual);
        Assert.assertTrue(isMatching, "Cart count doesn't match selected products");
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Click on cart icon and verify redirection to cart page.")
    @Story("User Story #24 - As a user, " +
            "I want to access my cart to review selected products before checkout.")
    public void clickOnCartIconTC() {
        log.info("Starting: clickOnCartIconTC");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword(Constants.loginPassword)
                .clickLogin()
                .clickCartICon();

        String actualURL = driver.getCurrentUrl();
        log.info("Redirected URL: " + actualURL);
        Assert.assertEquals(actualURL, Constants.Cart_PageURL, "User is not redirected to cart page");
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Perform logout and validate redirection to login page.")
    @Story("User Story #205 - As a user," +
            " I want to log out securely and be redirected to the login page.")
    public void logOutTC() {
        log.info("Starting: logOutTC");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword(Constants.loginPassword)
                .clickLogin()
                .addAllProductsToCart()
                .click_On_Menu()
                .click_On_LogOUt();

        String actualURL = driver.getCurrentUrl();
        log.info("Redirected URL after logout: " + actualURL);
        Assert.assertEquals(actualURL, Constants.loginBaseURL, "User is not redirected to login page after logout");
    }
}
