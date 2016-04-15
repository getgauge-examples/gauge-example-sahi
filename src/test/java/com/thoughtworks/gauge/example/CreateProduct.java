package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.example.pages.CreateProductPage;

import java.util.List;

public class CreateProduct {

    private final CreateProductPage createProductPage;


    public CreateProduct() {
        this.createProductPage = new CreateProductPage();
    }

    @Step("Create a product <table>")
    public void createProduct(Table table) {
        List<List<String>> rows = table.getRows();
        for (List<String> row : rows) {
            openNewProductsPage();
            createProductPage.create(row.get(0), row.get(1), row.get(2), row.get(3));
        }
    }

    @Step("On new products page")
    public void openNewProductsPage() {
        createProductPage.navigate();
    }
}
