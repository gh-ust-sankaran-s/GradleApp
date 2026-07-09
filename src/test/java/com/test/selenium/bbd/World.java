package com.test.selenium.bbd;

import com.test.selenium.pages.CartPage;
import com.test.selenium.pages.CatalogPage;
import com.test.selenium.pages.CheckoutPage;
import com.test.selenium.pages.LoginPage;
import com.test.selenium.pages.ProductPage;
import com.test.selenium.pages.components.Header;
import org.openqa.selenium.WebDriver;

public class World {
    public WebDriver driver;
    public CatalogPage catalog;
    public LoginPage login;
    public ProductPage product;
    public CartPage cart;
    public CheckoutPage checkout;

    public Header header()
    {
        return  new Header(driver);
    }
}
