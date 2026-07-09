package com.test.selenium.selenide;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class CatalogPageTest {

    @Test
    @DisplayName("Search on catalog page using CatalogPage object")
    void searchUsingCatalogPageObject() {
        open("http://localhost:5173/catalog");

        CatalogPage catalogPage = page(CatalogPage.class);

        catalogPage
                .searchFor("headphones")
                .exploreResults()
                .shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}
