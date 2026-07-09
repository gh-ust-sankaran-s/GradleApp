package com.test.selenium.selenide;



import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.junit.jupiter.api.Assertions.*;

public class CatalogFlowSelenideTest {

    private static final String SEARCH_INPUT = "[data-test='search-input']";
    private static final String PRODUCT_CARD = "[data-test='product-card']";
    private static final String PRODUCT_TITLE = "[data-test='product-title']";
    private static final String PRODUCT_PRICE = "[data-test='product-price']";
    private static final String PRODUCT_LINK = "[data-test='product-card'] a";
    private static final String DETAIL_NAME = "[data-test='detail-name']";
    private static final String ADD_TO_CART = "[data-test='add-to-cart']";
    private static final String CART_COUNT = "[data-test='cart-count']";
    private static final String SORT_SELECT = "[data-test='sort-select']";
    private static final String QUICK_VIEW = "[data-test='quick-view']";
    private static final String RESULT_COUNT = "[data-test='catalog-result-count']";
    private static final String EMPTY_SEARCH = "[data-test='empty-search']";

    @BeforeEach
    void setup() {
        open("http://localhost:5173/catalog");
    }

    @Test
    void searchShowsMatchingCards() {

        search("headphones", "Showing 1 product");

        $$(PRODUCT_CARD).shouldHave(size(1));

        for (SelenideElement card : $$(PRODUCT_CARD)) {

            String title = card.$(PRODUCT_TITLE).text();

            assertAll(
                    () -> assertTrue(title.toLowerCase().contains("headphone")),
                    () -> card.$(PRODUCT_PRICE).shouldBe(visible)
            );
        }
    }

    @Test
    void searchShowsEmptyStateForUnknownProduct() {

        search("ssssssssss - no product", "Showing 0 products");

        $(EMPTY_SEARCH)
                .shouldBe(visible)
                .$("h2")
                .shouldHave(text("No products found"));

        $$(PRODUCT_CARD).shouldHave(size(0));
    }

    @Test
    void detailAddToCartUpdateBadge() {

        $(PRODUCT_LINK).click();

        webdriver().shouldHave(urlContaining("/product"));

        $(DETAIL_NAME)
                .shouldBe(visible)
                .shouldNotBe(empty);

        $(ADD_TO_CART).click();

        webdriver().shouldHave(urlContaining("/cart"));

        $(CART_COUNT).shouldHave(text("1"));
    }

    @Test
    void sortLowToHighShowAscendingPrices() {

        $(SORT_SELECT).selectOption("Price: Low to High");

        List<Integer> prices = $$(PRODUCT_PRICE)
                .texts()
                .stream()
                .map(s -> Integer.parseInt(s.replaceAll("[^0-9]", "")))
                .toList();

        assertEquals(prices.stream().sorted().toList(), prices);
    }

    @Test
    void hoverRevealsQuickViewMarker() {

        SelenideElement firstCard = $(PRODUCT_CARD);

        firstCard.hover();

        firstCard.$(QUICK_VIEW)
                .shouldBe(visible)
                .shouldHave(text("Quick view ready"));
    }

    private void search(String query, String expectedResult) {

        $(SEARCH_INPUT)
                .setValue(query)
                .pressEnter();

        $(RESULT_COUNT)
                .shouldHave(text(expectedResult));
    }
}