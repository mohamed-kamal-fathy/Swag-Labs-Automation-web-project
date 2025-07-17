package swaglabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.P02_HomePage;
import com.swaglabs.utils.JsonUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)
public class TC008_checkingProductDetails {

    JsonUtils testData;


    @Test
    public void checkingProductDetails() {
        new P02_HomePage(DriverManager.getDriver()).clickCartIcon()
                .assertProductDetails(testData.getJsonData("product-names.item1.name"), testData.getJsonData("product-names.item1.price"));
    }

    @BeforeTest
    public void beforeClass() {
        testData = new JsonUtils("test-data");
    }
}
