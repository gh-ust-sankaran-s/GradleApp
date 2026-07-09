package com.test.selenium.pages;

import com.test.selenium.support.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final By EMAIL = By.id("email");
    private static final By PASSWORD = By.id("password");
    private static final By SIGNIN = By.cssSelector("button.form-submit");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(Config.baseUrl() + "/login");
        visible(EMAIL);
        return this;
    }

    public CatalogPage login(String email, String password) {
        type(EMAIL, email);
        type(PASSWORD, password);
        click(SIGNIN);
        urlchecking("/home");
        return new CatalogPage(driver);
    }
}
