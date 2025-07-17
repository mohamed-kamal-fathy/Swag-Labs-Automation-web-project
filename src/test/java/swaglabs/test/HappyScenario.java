package swaglabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.P03_CartPage;
import com.swaglabs.pages.P04_InformationPage;
import com.swaglabs.pages.P01_LoginPage;
import com.swaglabs.pages.P02_HomePage;
import com.swaglabs.utils.*;
import org.testng.annotations.*;


@Listeners(TestNGListeners.class)
public class HappyScenario {

    //Variables
    JsonUtils testData;


    //tests
    @Test
    public void successfulLogin() {
        new P01_LoginPage(DriverManager.getDriver()).enterUsername(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSuccessfullLogin();


    }


    @Test(dependsOnMethods = "successfulLogin")
    public void addingProductToCart() {
        new P02_HomePage(DriverManager.getDriver()).addProductToCart(testData.getJsonData("product-names.item1.name"))
                .assertProductAddedToCart(testData.getJsonData("product-names.item1.name"));
    }

    @Test(dependsOnMethods = "addingProductToCart")
    public void checkingProductDetails() {
        new P02_HomePage(DriverManager.getDriver()).clickCartIcon()
                .assertProductDetails(testData.getJsonData("product-names.item1.name"),testData.getJsonData("product-names.item1.price"));
    }


    @Test(dependsOnMethods = "checkingProductDetails")
    public void fillInformationFourm()
    {
        new P03_CartPage(DriverManager.getDriver()).clickCheckoutButton()
                .fillInformationFourm(testData.getJsonData("information-fourm.first-name"),
                testData.getJsonData("information-fourm.last-name"),
                testData.getJsonData("information-fourm.zip-code"))
                .assertInfoPage(testData.getJsonData("information-fourm.first-name"),
                        testData.getJsonData("information-fourm.last-name"),
                        testData.getJsonData("information-fourm.zip-code"));

    }

    @Test(dependsOnMethods = "fillInformationFourm")
    public void finishCheckout()
    {
        new P04_InformationPage(DriverManager.getDriver())
                .clickContinueButton()
                .clickFinishButton()
                .assertConfirmationMessage(testData.getJsonData("confirmation-message"));
    }


    //configuration

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






