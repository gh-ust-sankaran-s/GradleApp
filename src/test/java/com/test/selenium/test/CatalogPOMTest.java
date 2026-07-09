package com.test.selenium.test;


import com.test.selenium.pages.CartPage;
import com.test.selenium.pages.CatalogPage;
import com.test.selenium.pages.ProductPage;
import com.test.selenium.support.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogPOMTest {

    private WebDriver driver;


    @BeforeEach
    void setup(){
        driver = DriverFactory.createChromeDriver();
    }

    @AfterEach
    void cleanup(){
        if(driver!=null)
            driver.quit();
    }

    @Test
    @DisplayName("POM search query returns only matching catalog titles")
    void searchFindOnlyMatchiongProducts(){
        CatalogPage catalogPage = new CatalogPage(driver)
                .open()
                .searchFor("headphones","Showing 1 product");

        List<String> titles= catalogPage.titles();

        assertAll(
                ()->assertFalse(titles.isEmpty(),"search returned no products"),
                ()->assertTrue(titles.stream().allMatch((title)->title.toLowerCase().contains("headphones")),"search result not related to headphones")
        );

        System.out.println(titles);
    }

    @Test
    @DisplayName("POM sort query based on the price")
    void sortProductsBasedOnthePrice(){
        CatalogPage catalogPage = new CatalogPage(driver)
                .open()
                .sortby("Price: Low to High");

        List<Integer> prices = catalogPage.prices();
        assertEquals(prices.stream().sorted().toList(),prices);

    }

    @Test
    @DisplayName("POM full journey")
    void full_journey_of_all_stages(){
        CatalogPage catalogPage = new CatalogPage(driver)
                .open()
                .searchFor("headphones");

        ProductPage productPage = catalogPage.openFirstProduct();
        assertTrue(productPage.name().toLowerCase().contains("headphones"));

        CartPage cartPage = productPage.addToCart();
        cartPage.header().cartBadge().expectedCount(1);

        assertAll(
                ()->assertEquals(1,cartPage.lineCount()),
                ()->assertFalse(cartPage.total().isBlank())
        );
        cartPage.switchBackToCatalog().openFirstProduct().addToCart();

        assertAll(
                ()->assertEquals(2,cartPage.lineCount()),
                ()->assertFalse(cartPage.total().isBlank())
        );

        cartPage.header().cartBadge().expectedCount(2);
        String Confirmation = cartPage.proceed().placeOrder().confirmationText();

        assertTrue(Confirmation.toLowerCase().contains("confirmed"));


    }
}
