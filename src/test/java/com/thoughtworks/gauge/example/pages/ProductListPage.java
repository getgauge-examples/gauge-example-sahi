package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.example.BrowserFactory;
import net.sf.sahi.client.Browser;

public class ProductListPage extends BasePage {
    private final Browser browser;

    private static String PRODUCTS_URL = adminUrl.concat("products/");

    public ProductListPage() {
        this.browser = BrowserFactory.getBrowser();
    }

    public void openFirstProduct() {
        browser.byXPath("//table/tbody/tr[1]/td[1]/a").click();
    }

    public void navigate() {
        browser.navigateTo(PRODUCTS_URL);
    }

    public String getProductId() {
        return browser.byXPath("//table/tbody/tr[1]/td").getText();
    }

    public void search(String title) {
        browser.byId("q_title").setValue(title);
        browser.submit("commit").click();
    }
}
