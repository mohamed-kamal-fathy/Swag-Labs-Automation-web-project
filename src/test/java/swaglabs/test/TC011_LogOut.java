package swaglabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.P02_HomePage;
import com.swaglabs.utils.BrowserActions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TC011_LogOut {


    @Test
    public static void logOut() {

        new P02_HomePage(DriverManager.getDriver())
                .openMenu()
                .clickLogout()
                .assertOnLoginPage();
    }

    @AfterTest
    public void tearDown() {
        BrowserActions.closeBrowser(DriverManager.getDriver());
    }
}
