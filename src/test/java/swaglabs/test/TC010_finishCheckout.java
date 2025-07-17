package swaglabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.P04_InformationPage;
import com.swaglabs.utils.JsonUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC010_finishCheckout {

    JsonUtils testData;

    @Test
    public void finishCheckout()
    {
        new P04_InformationPage(DriverManager.getDriver())
                .clickContinueButton()
                .clickFinishButton()
                .assertConfirmationMessage(testData.getJsonData("confirmation-message"));
    }


    @BeforeTest
    public void beforeClass() {
        testData = new JsonUtils("test-data");
    }

}
