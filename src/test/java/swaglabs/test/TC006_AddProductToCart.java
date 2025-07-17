package swaglabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.P02_HomePage;
import com.swaglabs.utils.JsonUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestNGListeners.class)
public class TC006_AddProductToCart {

    JsonUtils testData;


    @Test
    public void addingProductToCart() {
        new P02_HomePage(DriverManager.getDriver()).addProductToCart(testData.getJsonData("product-names.item1.name"))
                .assertProductAddedToCart(testData.getJsonData("product-names.item1.name"));
    }

    @BeforeTest
    public void beforeClass() {
        testData = new JsonUtils("test-data");

    }
}

