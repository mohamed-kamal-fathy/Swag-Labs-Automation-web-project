package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import static utilities.actions.ElementActions.*;

public class P03_Cart extends P02_Landing {

    static float totalPriceInCartPage = 0;

    //locator
    private final By continueShopping = By.id("continue-shopping");
    private final By priceOfSelectedProductsLocator = By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class='inventory_item_price']");


    //Constructor
    ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private final WebDriver driver;

    public P03_Cart(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //actions

    public P03_Cart click_On_ContinueShopping() {
        clickElement(driver, continueShopping);
        return new P03_Cart(driver);

    }

    public String getTotalPriceOnCartPage() {
        try {
            List<WebElement> PriceOfSelectedProducts = driver.findElements(priceOfSelectedProductsLocator);
            for (int i = 1; i <= PriceOfSelectedProducts.size(); i++) {
                By elements = By.xpath("(//button[.=\"Remove\"]//preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String fullText = getText(driver, elements);
                totalPriceInCartPage += Float.parseFloat(fullText.replace("$", ""));
            }
            return String.valueOf(totalPriceInCartPage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean comparingPrices() {
        return getTotalPriceOnCartPage().equals(getTotalPriceOfSelectedProducts());
    }
}
