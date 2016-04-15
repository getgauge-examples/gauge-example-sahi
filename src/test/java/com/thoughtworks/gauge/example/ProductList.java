package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.example.pages.ProductListPage;

public class ProductList {
    private final ProductListPage listPage;

    public ProductList() {
        this.listPage = new ProductListPage();
    }

    @Step("On product page")
    public void openProductsPage() {
        listPage.navigate();
    }

    @Step("Search for product <name>")
    public void searchProduct(String title) {
        listPage.search(title);
    }

    @Step("Open description for product <name>")
    public void viewProductDescription(String name) {
        listPage.openFirstProduct();
    }

}
