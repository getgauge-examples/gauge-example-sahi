package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.example.BrowserFactory;
import net.sf.sahi.client.Browser;

public class ProductListPage extends BasePage {
    private final Browser browser;
    private String productsUrl = Url.concat("products/");

    public ProductListPage() {
        this.browser = BrowserFactory.getBrowser();
    }

    @Step("On product page")
    public void openProductsPage() {
        browser.navigateTo(productsUrl);
    }

    @Step("Search for product <name>")
    public void searchProduct(String title) {
        browser.byId("q_title").setValue(title);
        browser.byId("q_submit").click();
    }

    @Step("Open description for product <name>")
    public void viewProductDescription(String name) {
        openFirstProduct();
    }

    private void openFirstProduct() {
        browser.image("Not available").click();
    }

}
