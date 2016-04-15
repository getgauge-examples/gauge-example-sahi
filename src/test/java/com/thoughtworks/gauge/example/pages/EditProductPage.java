package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.example.BrowserFactory;
import net.sf.sahi.client.Browser;

public class EditProductPage extends BasePage {
    private final Browser browser;

    public EditProductPage() {
        this.browser = BrowserFactory.getBrowser();
    }

    public void updateProductValue(String specifier, String newValue) {
        browser.byId("product_" + specifier).setValue(newValue);
        browser.submit("commit").click();
    }

    public void navigate() {
        String id = fetchStringFromScenarioDataStore("productId");
        String url = adminUrl.concat("products/" + id) + "/edit";
        browser.navigateTo(url);
    }
}
