package swaglabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.P03_CartPage;
import org.testng.annotations.Test;

public class TC012_EmptyInfoPage {


    @Test
    public void emptyInfoPage()
    {
     new P03_CartPage(DriverManager.getDriver()).clickCheckoutButton()
             .fillInformationFourm("","","")
             .clickContinueButton()
             .assertErrorMSQuestion();
    }
}
