package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.example.BrowserFactory;
import net.sf.sahi.client.Browser;

import java.util.List;

public class CreateProductPage extends BasePage {

    private final Browser browser;
    private String newProductUrl = Url.concat("products/new/");


    public CreateProductPage() {
        this.browser = BrowserFactory.getBrowser();
    }

    public void create(String title, String desc, String author, String price) {
        browser.byId("product_title").setValue(title);
        browser.byId("product_description").setValue(desc);
        browser.byId("product_author").setValue(author);
        browser.byId("product_price").setValue(price);
        browser.byId("product_image_file_name").setValue("not available");
        browser.byId("product_submit").click();
    }


    @Step("Create a product <table>")
    public void CreateProduct(Table table) {
        List<List<String>> rows = table.getRows();
        for (List<String> row : rows) {
            openNewProductsPage();
            create(row.get(0), row.get(1), row.get(2), row.get(3));
        }
    }

    @Step("On new products page")
    public void openNewProductsPage() {
        browser.navigateTo(newProductUrl);
    }
}
