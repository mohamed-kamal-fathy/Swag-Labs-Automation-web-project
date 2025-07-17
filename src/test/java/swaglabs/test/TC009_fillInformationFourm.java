package swaglabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.P03_CartPage;
import com.swaglabs.utils.JsonUtils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TC009_fillInformationFourm {

    JsonUtils testData;

    @Severity(SeverityLevel.CRITICAL)
    @Test
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

    @BeforeTest
    public void beforeClass() {
        testData = new JsonUtils("test-data");
    }

}
