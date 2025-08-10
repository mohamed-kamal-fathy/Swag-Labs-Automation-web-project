package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

import static utilities.actions.ElementActions.*;
import static utilities.dataReader.FilesUtils.generateUniqueNumber;

public class P02_Landing {

    private final WebDriver driver;
    private static final Logger log = LogManager.getLogger(P02_Landing.class);
    private List<WebElement> all_Products;
    private List<WebElement> selected_Products;
    public float totalPriceLandingPage = 0;

    //locators_add_buttons
    private final By SauceLabs_Backpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By SauceLabs_BikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private final By SauceLabs_BoltTShirt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private final By SauceLabs_FleeceJacket = By.id("add-to-cart-sauce-labs-fleece-jacket");
    private final By SauceLabs_Onesie = By.id("add-to-cart-sauce-labs-onesie");
    private final By TShirt_Red = By.id("add-to-cart-test.allthethings()-t-shirt-(red)");

    //locators_remove_buttons
    private final By SauceLabsBackpack_remove = By.id("remove-sauce-labs-backpack");
    private final By SauceLabsBikeLight_remove = By.id("remove-sauce-labs-bike-light");
    private final By SauceLabsBoltTShirt_remove = By.id("remove-sauce-labs-bolt-t-shirt");
    private final By SauceLabsFleeceJacket_remove = By.id("remove-sauce-labs-fleece-jacket");
    private final By SauceLabsOnesie_remove = By.id("remove-sauce-labs-onesie");
    private final By TShirtRed_remove = By.id("remove-test.allthethings()-t-shirt-(red)");


    private final By addTOCartForALLProducts = By.xpath("//button[@class]");
    private final By cartNumbersOfProductsOnCart = By.className("shopping_cart_badge");
    private final By NumberOfSelectedProducts = By.xpath("//button[.='Remove']");
    private final By cartProducts = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    private final By priceOfSelectedProductsLocator = By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class='inventory_item_price']");
    private final By menu = By.id("react-burger-menu-btn");
    private final By logOut = By.id("logout_sidebar_link");
    private final By resetAppItem = By.id("reset_sidebar_link");


    public P02_Landing(WebDriver driver) {
        this.driver = driver;

    }


    //actions

    public P03_Cart addToCart(String button) {
        switch (button) {
            case "SauceLabs_Backpack":
                clickElement(driver, SauceLabs_Backpack);
                break;

            case "SauceLabs_BikeLight":
                clickElement(driver, SauceLabs_BikeLight);
                break;
            case "SauceLabs_BoltTShirt":
                clickElement(driver, SauceLabs_BoltTShirt);
                break;

            case "SauceLabs_FleeceJacket":
                clickElement(driver, SauceLabs_FleeceJacket);
                break;
            case "SauceLabs_Onesie":
               clickElement(driver, SauceLabs_Onesie);
                break;

            case "TShirt_Red":
                clickElement(driver, TShirt_Red);
                break;

        }
        return new P03_Cart(driver);

    }

    public P02_Landing removeFromCart(String button) {
        switch (button) {
            case "SauceLabsBackpack_remove":
                clickElement(driver, SauceLabsBackpack_remove);
                break;

            case "SauceLabsBikeLight_remove":
                clickElement(driver, SauceLabsBikeLight_remove);
                break;
            case "SauceLabsBoltTShirt_remove":
                clickElement(driver, SauceLabsBoltTShirt_remove);
                break;

            case "SauceLabsFleeceJacket_remove":
                clickElement(driver, SauceLabsFleeceJacket_remove);
                break;
            case "SauceLabsOnesie_remove":
                clickElement(driver, SauceLabsOnesie_remove);
                break;

            case "TShirtRed_remove":
                clickElement(driver, TShirtRed_remove);
                break;

        }
        return new P02_Landing(driver);

    }

    public P02_Landing addAllProductsToCart() {
        all_Products = driver.findElements(addTOCartForALLProducts);
        // dynamic locator
        for (int i = 1; i <= all_Products.size(); i++) {
            By addTOCartForALLProducts = By.xpath("(//button[@class])[" + i + "]");
            clickElement(driver, addTOCartForALLProducts);

        }
        return this;
    }

    public String getNumberOfProductsOnCart() {
        try {
            return getText(driver, cartNumbersOfProductsOnCart);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "0";
        }
    }

    public String getNumberOfSelectedProducts() {
        try {
            selected_Products = driver.findElements(NumberOfSelectedProducts);
            return String.valueOf(selected_Products.size());
        } catch (Exception e) {

            log.error(e.getMessage());
            return "0";
        }
    }

    public boolean comparingNumberOfSelectedProductsWithCart() {
        return getNumberOfProductsOnCart().equals(getNumberOfSelectedProducts());

    }

    public P02_Landing addRandomProducts(int numberOfProductsNeeded, int totalNumberOfProducts) {

        Set<Integer> randomNumbers = generateUniqueNumber(numberOfProductsNeeded, totalNumberOfProducts);
        for (int random : randomNumbers) {
            By addTOCartForALLProducts = By.xpath("(//button[@class])[" + random + "]");
            clickElement(driver, addTOCartForALLProducts);
        }
        return this;
    }

    public P03_Cart clickCartICon() {
        clickElement(driver, cartProducts);
        return new P03_Cart(driver);
    }

    public String getTotalPriceOfSelectedProducts() {
        try {
            List<WebElement> PriceOfSelectedProducts = driver.findElements(priceOfSelectedProductsLocator);
            for (int i = 1; i <= PriceOfSelectedProducts.size(); i++) {
                By elements = By.xpath("(//button[.=\"Remove\"]//preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String fullText = getText(driver, elements);
                totalPriceLandingPage += Float.parseFloat(fullText.replace("$", ""));
            }
            return String.valueOf(totalPriceLandingPage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public P02_Landing click_On_Menu() {

        clickElement(driver, menu);
        return this;
    }

    public P02_Landing click_On_LogOUt() {

        clickElement(driver, logOut);
        return this;
    }

    public P02_Landing click_On_RestAppItem() {
        clickElement(driver, resetAppItem);
        return this;
    }

}


