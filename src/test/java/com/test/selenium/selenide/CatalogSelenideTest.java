package com.test.selenium.selenide;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CatalogSelenideTest {

    @BeforeEach
    void setup() {
        open("http://localhost:5173/catalog");
    }

    @Test
    @DisplayName("Search headphones with Selenide")
    void searchForHeadphonesShowsResults() {

        $("[data-test='search-input']")
                .shouldBe(visible)
                .setValue("headphones")
                .pressEnter();

        $$("[data-test='product-card']")
                .shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}