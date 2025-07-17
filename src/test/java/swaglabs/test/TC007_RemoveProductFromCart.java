package swaglabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.P02_HomePage;
import com.swaglabs.utils.JsonUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC007_RemoveProductFromCart {


    JsonUtils testData;

    @Test
    public void removingProductFromCart() {
        new P02_HomePage(DriverManager.getDriver())
                .removeProductFromCart(testData.getJsonData("product-names.item1.name"))
                .assertProductRemovedFromCart(testData.getJsonData("product-names.item1.name"));
    }



    @BeforeTest
    public void beforeClass() {
        testData = new JsonUtils("test-data");
    }

}
