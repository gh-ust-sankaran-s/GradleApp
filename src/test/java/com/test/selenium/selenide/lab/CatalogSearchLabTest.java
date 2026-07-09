package com.test.selenium.selenide.lab;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class CatalogSearchLabTest {

    @BeforeEach
    void setup() {
        open("http://localhost:5173/catalog");
    }

    @Test
    @DisplayName("Search catalog with Selenide page object")
    void searchCatalog() {

        CatalogPage catalogPage = page(CatalogPage.class)
                .searchFor("headphones");

        catalogPage.result_Count().shouldHave(text("Showing 1 product"));
        catalogPage.product_Cards()
                .shouldHave(CollectionCondition.size(1));
        catalogPage.product_Titles()
                .shouldHave(CollectionCondition.size(1))
                .first()
                .shouldHave(text("Headphones"));
    }
}
