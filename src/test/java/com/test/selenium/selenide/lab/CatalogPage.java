package com.test.selenium.selenide.lab;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class CatalogPage {

    private final SelenideElement searchInput = $("[data-test='search-input']");
    private final SelenideElement resultCount = $("[data-test='catalog-result-count']");
    private final ElementsCollection productCards = $$("[data-test='product-card']");
    private final ElementsCollection productTitles = $$("[data-test='product-title']");

    public CatalogPage searchFor(String query) {
        searchInput
                .shouldBe(visible)
                .setValue(query)
                .pressEnter();

        return this;
    }
    public SelenideElement result_Count() {
        return resultCount;
    }
    public ElementsCollection product_Cards() {
        return productCards;
    }
    public ElementsCollection product_Titles() {
        return productTitles;
    }
}
