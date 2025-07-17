package swaglabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.P01_LoginPage;
import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.JsonUtils;
import com.swaglabs.utils.PropertiesUtils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import net.bytebuddy.build.Plugin;
import org.testng.annotations.*;

@Listeners(TestNGListeners.class)
public class TC001_SuccessFullLogin {

    JsonUtils testData;

    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void successfulLogin() {
        new P01_LoginPage(DriverManager.getDriver()).enterUsername(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSuccessfullLogin();
    }

    @BeforeTest
    public void beforeClass() {
        testData = new JsonUtils("test-data");
        String browserName = PropertiesUtils.getPropertyValue("browserType");
        DriverManager.createInstance(browserName);
        new P01_LoginPage(DriverManager.getDriver()).navigateToLoginPage();
    }

    @AfterTest
    public void tearDown() {
        BrowserActions.closeBrowser(DriverManager.getDriver());
    }
}