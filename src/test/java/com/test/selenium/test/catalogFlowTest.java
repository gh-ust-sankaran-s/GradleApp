package com.test.selenium.test;//package com.test.selenium.test;
//
//import com.test.selenium.support.Config;
//import com.test.selenium.support.DriverFactory;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CatalogFlowTest {
//
//    private static final By SEARCH_INPUT =
//            By.cssSelector("[data-test='search-input']");
//
//    private static final By RESULT_INPUT =
//            By.cssSelector("[data-test='catalog-result-count']");
//
//    private static final By PRODUCT_CARD =
//            By.cssSelector("[data-test='product-card']");
//
//    private static final By PRODUCT_TITLE =
//            By.cssSelector("[data-test='product-title']");
//
//    private static final By PRODUCT_PRICE =
//            By.cssSelector("[data-test='product-price']");
//
//    private static final By EMPTY_SEARCH =
//            By.cssSelector("[data-test='empty-search']");
//
//    private static final By PRODUCT_LINK =
//            By.cssSelector("[data-test='product-card'] a");
//
//    private static final By DETAIL_NAME =
//            By.cssSelector("[data-test='detail-name']");
//
//    private static final By ADD_TO_CART =
//            By.cssSelector("[data-test='add-to-cart']");
//
//    private static final By CART_COUNT =
//            By.cssSelector("[data-test='cart-count']");
//
//    private static final By SORT_SELECT =
//            By.cssSelector("[data-test='sort-select']");
//
//    private static final By QUICK_VIEW =
//            By.cssSelector("[data-test='quick-view']");
//
//    private static final By SIZE_GUIDE =
//            By.cssSelector(".product-actions a");
//
//    private static final By CATEGORY =
//            By.cssSelector("#category-filter");
//
//    private static final By CHECKOUT =
//            By.cssSelector(".button.primary");
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    @BeforeEach
//    void setup() {
//        driver = DriverFactory.createChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
//    @DisplayName("Searching a Product")
//    void searching_a_product_checks_visibility() {
//
//        search("headphones", "Showing 1 product");
//
//        List<WebElement> cards = driver.findElements(PRODUCT_CARD);
//
//        for (WebElement card : cards) {
//
//            String title = card.findElement(PRODUCT_TITLE).getText();
//
//            assertAll(
//                    () -> assertTrue(
//                            title.toLowerCase().contains("headphones"),
//                            "Unrelated product: " + title
//                    ),
//                    () -> assertTrue(
//                            card.findElement(PRODUCT_PRICE).isDisplayed(),
//                            "Missing price for " + title
//                    )
//            );
//        }
//    }
//
//    @Test
//    @DisplayName("Searching a product which is not available")
//    void searching_a_product_not_available() {
//
//        search("no-product", "Showing 0 products");
//
//        WebElement emptyBox =
//                wait.until(ExpectedConditions.visibilityOfElementLocated(EMPTY_SEARCH));
//
//        assertAll(
//                () -> assertTrue(
//                        driver.findElements(PRODUCT_CARD).isEmpty(),
//                        "Cards should be hidden for empty search"
//                ),
//                () -> assertEquals(
//                        "No products found",
//                        emptyBox.findElement(By.tagName("h2")).getText()
//                )
//        );
//    }
//
//    @Test
//    @DisplayName("Open the product detail")
//    void open_product_detail() {
//
//        wait.until(
//                ExpectedConditions.visibilityOfElementLocated(PRODUCT_LINK)
//        ).click();
//
//        WebElement detailName =
//                wait.until(ExpectedConditions.visibilityOfElementLocated(DETAIL_NAME));
//
//        assertAll(
//                () -> assertTrue(driver.getCurrentUrl().contains("/product/")),
//                () -> assertFalse(detailName.getText().isBlank())
//        );
//
//        wait.until(
//                ExpectedConditions.visibilityOfElementLocated(ADD_TO_CART)
//        ).click();
//
//        wait.until(ExpectedConditions.urlContains("/cart"));
//        wait.until(ExpectedConditions.textToBe(CART_COUNT, "1"));
//    }
//
//    @Test
//    @DisplayName("Sort the Items")
//    void sorting_the_products() {
//
//        WebElement oldFirst =
//                wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_CARD));
//
//        new Select(driver.findElement(SORT_SELECT))
//                .selectByVisibleText("Price: Low to High");
//
//        wait.until(ExpectedConditions.stalenessOf(oldFirst));
//        wait.until(
//                ExpectedConditions.numberOfElementsToBeMoreThan(PRODUCT_CARD, 0)
//        );
//
//        List<Integer> prices = driver.findElements(PRODUCT_PRICE)
//                .stream()
//                .map(element ->
//                        Integer.parseInt(
//                                element.getText().replaceAll("[^0-9]", "")
//                        )
//                )
//                .toList();
//
//        assertEquals(
//                prices.stream().sorted().toList(),
//                prices
//        );
//    }
//
//    @Test
//    @DisplayName("Category Validation")
//    void category_validation() {
//
//        new Select(driver.findElement(CATEGORY))
//                .selectByVisibleText("Footwear");
//
//        wait.until(
//                ExpectedConditions.numberOfElementsToBeMoreThan(PRODUCT_CARD, 0)
//        );
//
//        assertFalse(driver.findElements(PRODUCT_CARD).isEmpty());
//    }
//
//    @Test
//    @DisplayName("Actions can hover and reveals in quick view marker")
//    void reveal_a_quick_view_marker() {
//
//        WebElement firstCard =
//                wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_CARD));
//
//        new Actions(driver)
//                .moveToElement(firstCard)
//                .perform();
//
//        wait.until(driver ->
//                firstCard.findElement(QUICK_VIEW).isDisplayed());
//
//        assertEquals(
//                "Quick view ready",
//                firstCard.findElement(QUICK_VIEW).getText()
//        );
//    }
//
//    @Test
//    @DisplayName("Go to New Tab")
//    void size_guide_page() {
//
//        wait.until(
//                ExpectedConditions.visibilityOfElementLocated(PRODUCT_LINK)
//        ).click();
//
//        WebElement heading =
//                wait.until(ExpectedConditions.visibilityOfElementLocated(DETAIL_NAME));
//
//        assertTrue(heading.getText().contains("Running"));
//
//        String originalWindow = driver.getWindowHandle();
//
//        wait.until(
//                ExpectedConditions.visibilityOfElementLocated(SIZE_GUIDE)
//        ).click();
//
//        for (String handle : driver.getWindowHandles()) {
//            if (!handle.equals(originalWindow)) {
//                driver.switchTo().window(handle);
//                break;
//            }
//        }
//
//        assertTrue(driver.getCurrentUrl().contains("/size-guide"));
//    }
//
//    private void search(String query, String expectedResult) {
//
//        WebElement searchBar =
//                wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
//
//        searchBar.clear();
//        searchBar.sendKeys(query);
//        searchBar.sendKeys(Keys.ENTER);
//
//        wait.until(
//                ExpectedConditions.textToBe(RESULT_INPUT, expectedResult)
//        );
//    }
//}