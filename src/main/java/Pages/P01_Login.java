package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static utilities.actions.ElementActions.*;


public class P01_Login {

    private final WebDriver driver;

    // Locators
    private final By userName = By.id("user-name");
    private final By Password = By.id("password");
    private final By loginButton = By.cssSelector("[type='submit']");


    //constructor
    public P01_Login(WebDriver driver) {

        this.driver = driver;
    }

    //Actions

    public P01_Login enterUserName(String username) {
        SendData(driver, userName, username);
        return this;

    }

    public P01_Login enterPassword(String password) {
        SendData(driver, Password, password);
        return this;

    }

    public P02_Landing clickLogin() {
        clickElement(driver, loginButton);
        return new P02_Landing(driver);
    }
}


