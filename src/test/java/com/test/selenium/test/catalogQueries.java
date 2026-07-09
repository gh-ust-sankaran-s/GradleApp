package com.test.selenium.test;//package com.test.selenium.test;
//
//import com.test.selenium.pages.CatalogPage;
//import com.test.selenium.support.Config;
//import com.test.selenium.support.DriverFactory;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebDriver;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class catalogQueries {
//
//    private WebDriver driver;
//
//    @BeforeEach
//    void setup() {
//        driver = DriverFactory.createChromeDriver();
//        driver.get(Config.Catalog());
//    }
//
//    @AfterEach
//    void cleanup() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    @Test
//    @DisplayName("Search finds only headphones")
//    void searchFindsOnlyHeadphones() {
//        CatalogPage catalog = new CatalogPage(driver);
//
//        catalog.search("Noise Canceling Headphones");
//
//        List<String> titles = catalog.titles();
//        System.out.println(titles);
//        List<Integer> prices = catalog.prices();
//
//        assertFalse(titles.isEmpty(), "Expected at least one result");
//        assertFalse(prices.isEmpty(), "Expected at least one price");
//
//        assertTrue(
//                titles.stream()
//                        .allMatch(title -> title.toLowerCase().contains("noise canceling headphones")),
//                "Search returned unrelated products"
//        );
//
//        assertTrue(
//                prices.stream().allMatch(price -> price > 0),
//                "Product prices should be positive"
//        );
//    }
//}
//
