package TestCases;

import utilities.constants.Constants;
import utilities.logs.LogsUtil;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import media.ScreenShot;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;

import static utilities.actions.Waits.driver;

@Listeners({AllureTestNg.class})
@Epic("Login Functionality")
@Feature("P01 - Login Page")
public class TCs_P01_loginPage extends BaseTests {

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login with valid username and password. Expect redirection to landing page.")
    @Story("User Story #10 - As a registered user," +
            " I want to log in with valid credentials " +
            "so I can access the landing page.")
    public void ValidUsernameAndPassword() {
        LogsUtil.info("Starting: ValidUsernameAndPassword");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword(Constants.loginPassword)
                .clickLogin();

        String actualURL = driver.getCurrentUrl();
        LogsUtil.info("Expected URL: " + Constants.landing_PageURL);
        LogsUtil.info("Actual URL: " + actualURL);

        Assert.assertEquals(actualURL, Constants.landing_PageURL, "Login failed: URL mismatch");
        LogsUtil.info("Login successful - redirected to landing page");
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Try to log in without entering a username.")
    @Story("User Story #102 - As a user," +
            " I want to be notified if I leave the username field empty.")
    public void InvalidLogin_EmptyUsername() {
        LogsUtil.info("Starting: InvalidLogin_EmptyUsername");

        loginPage.enterUserName("")
                .enterPassword(Constants.loginPassword)
                .clickLogin();

        String actualURL = driver.getCurrentUrl();
        LogsUtil.info("Expected URL: " + Constants.loginBaseURL);
        LogsUtil.info("Actual URL: " + actualURL);

        Assert.assertEquals(actualURL, Constants.loginBaseURL, "Should stay on login page");
       LogsUtil.info("Handled empty username correctly");
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.NORMAL)
    @Description("Try to log in without entering a password.")
    @Story("User Story #103 - As a user," +
            " I want to be notified if I leave the password field empty.")
    public void InvalidLogin_EmptyPassword() {
        LogsUtil.info("Starting: InvalidLogin_EmptyPassword");

        loginPage.enterUserName(Constants.loginUsername)
                .enterPassword("")
                .clickLogin();

        String actualURL = driver.getCurrentUrl();
       LogsUtil.info("Expected URL: " + Constants.loginBaseURL);
       LogsUtil.info("Actual URL: " + actualURL);

        Assert.assertEquals(actualURL, Constants.loginBaseURL, "Should stay on login page");
       LogsUtil.info("Handled empty password correctly");
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.MINOR)
    @Description("Try to log in with both fields empty and validate error message.")
    @Story("User Story #104 - As a user," +
            " I want to be informed when both login fields are empty.")
    public void InvalidLogin_EmptyFields() throws IOException {
       LogsUtil.info("Starting: InvalidLogin_EmptyFields");

        loginPage.clickLogin();

        String errorText = driver.findElement(By.cssSelector("form > div > h3[data-test=\"error\"]")).getText();
       LogsUtil.info("Captured error message: " + errorText);

        String expectedMessage = "Epic sadface: Username is required";
        boolean isCorrectMessage = errorText.equals(expectedMessage);
       LogsUtil.info("Is correct message shown? " + isCorrectMessage);

        ScreenShot.takeFullScreenShot(driver, "loginEmptyFieldsTC_bug_in_error_message");

        Assert.assertTrue(isCorrectMessage, "Expected error message is not displayed");
       LogsUtil.info("Error message verified successfully");
    }
}
