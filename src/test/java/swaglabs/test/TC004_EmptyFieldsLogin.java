package swaglabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.P01_LoginPage;
import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.PropertiesUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)
public class TC004_EmptyFieldsLogin {



    @Test
    public void emptyFieldsLogin() {
        new P01_LoginPage(DriverManager.getDriver())
                .enterUsername("")                      // Empty username
                .enterPassword("")                      // Empty password
                .clickLoginButton()
                .assertEmptyFieldLogin();  // Expected error message
    }

    @BeforeTest
    public void beforeClass() {

        String browserName = PropertiesUtils.getPropertyValue("browserType");
        DriverManager.createInstance(browserName);
        new P01_LoginPage(DriverManager.getDriver()).navigateToLoginPage();
    }

    @AfterTest
    public void tearDown() {
        BrowserActions.closeBrowser(DriverManager.getDriver());
    }


}

