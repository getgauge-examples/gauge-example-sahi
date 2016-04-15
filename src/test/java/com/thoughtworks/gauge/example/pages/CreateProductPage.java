package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.example.BrowserFactory;
import net.sf.sahi.client.Browser;

public class CreateProductPage extends BasePage {

    private final Browser browser;
    private String newProductUrl = adminUrl.concat("products/new/");

    public CreateProductPage() {
        this.browser = BrowserFactory.getBrowser();
    }

    public void create(String title, String desc, String author, String price) {
        browser.byId("product_title").setValue(title);
        browser.byId("product_description").setValue(desc);
        browser.byId("product_author").setValue(author);
        browser.byId("product_price").setValue(price);
        browser.byId("product_image_file_name").setValue("not available");
        browser.submit("commit").click();
    }

    public void navigate() {
        browser.navigateTo(newProductUrl);
    }
}
