package com.test.selenium.test;

import com.test.selenium.support.Config;
import com.test.selenium.support.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmokeTest {
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
    @DisplayName("Product Catalog")
    void Catalog_Test(){
        driver.get(Config.Catalog());

        By heading = By.cssSelector("[data-test='catalog-title']");
        assertAll(
                ()->assertTrue(driver.getTitle().contains("Catalog")),
                ()->assertTrue(driver.findElement(heading).isDisplayed())

        );
    }
}
