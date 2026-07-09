package com.test.selenium.selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class CatalogPage {

    private final SelenideElement searchInput = $("[data-test='search-input']");
    private final ElementsCollection rows = $$("[data-test='product-card']");

    public CatalogPage searchFor(String query) {
        searchInput
                .shouldBe(visible)
                .setValue(query)
                .pressEnter();
        return this;
    }

    public ElementsCollection exploreResults() {
        return rows;
    }
}
