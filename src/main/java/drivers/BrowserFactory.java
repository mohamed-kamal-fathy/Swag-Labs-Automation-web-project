package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    public static WebDriver getBrowser (String browserName)
    {
        switch (browserName.toLowerCase())
        {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-notifications");
                options.addArguments("--remote-allow-origins=*");
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                return new ChromeDriver(options);
            case   "firefox":
                FirefoxOptions options1 = new FirefoxOptions();
                options1.addArguments("--start-maximized");
                options1.addArguments("--disable-extensions");
                options1.addArguments("--disable-infobars");
                options1.addArguments("--disable-notifications");
                options1.addArguments("--remote-allow-origins=*");
                options1.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "edge":
                EdgeOptions Edgeoptions = new EdgeOptions();
                Edgeoptions.addArguments("--start-maximized");
                Edgeoptions.addArguments("--disable-extensions");
                Edgeoptions.addArguments("--disable-infobars");
                Edgeoptions.addArguments("--disable-notifications");
                Edgeoptions.addArguments("--remote-allow-origins=*");  // Fixed argument name
                Edgeoptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver(Edgeoptions);
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserName);

        }
    }
}
