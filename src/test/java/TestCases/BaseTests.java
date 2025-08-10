package TestCases;

import Pages.*;
import drivers.DriverWebManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.logs.LogsUtil;
import java.time.Duration;
import static utilities.constants.Constants.*;

public class BaseTests {


    // objectPages
    protected P01_Login loginPage;
    protected P02_Landing selectProduct;
    protected P03_Cart Cart;
    protected P04_Checkout Checkout;

    @Parameters(value = "browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) {

        DriverWebManager.createInstance(browser);
        WebDriver driver = DriverWebManager.getDriver();

        LogsUtil.info(browser + " browser launched with factory setup");

        driver.manage().window().maximize();
        driver.get(loginBaseURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Initialize page objects
        loginPage = new P01_Login(driver);
        selectProduct = new P02_Landing(driver);
        Cart = new P03_Cart(driver);
        Checkout = new P04_Checkout(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        WebDriver driver = DriverWebManager.getDriver();
        if (driver != null) {
            driver.quit();
            LogsUtil.info("Browser session is closed");
        }

        Cart = null;
        loginPage = null;
        selectProduct = null;
        Checkout = null;
        LogsUtil.info("Page objects set to null");
    }
}
