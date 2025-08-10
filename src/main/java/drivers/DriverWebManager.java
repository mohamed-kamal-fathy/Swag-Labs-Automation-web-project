package drivers;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utilities.logs.LogsUtil;

import static org.testng.Assert.fail;

public class DriverWebManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


    private DriverWebManager()
    {
        super();
    }


    @Step("Create Driver instance on: {browserName}")
    public static WebDriver createInstance(String browserName)
    {
        WebDriver D= BrowserFactory.getBrowser(browserName);
        LogsUtil.info("Driver Created On", browserName);
        setDriver(D);
        return getDriver();
    }

    public static WebDriver getDriver ()
    {

        if(driverThreadLocal.get()==null)
        {
            fail("Driver is Null");
        }
        return driverThreadLocal.get();
    }
    public static void setDriver (WebDriver D)
    {
        driverThreadLocal.set(D);
    }
}
