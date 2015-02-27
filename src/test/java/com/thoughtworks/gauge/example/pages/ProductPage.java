package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.example.BrowserFactory;
import net.sf.sahi.client.Browser;
import org.junit.Assert;

public class ProductPage extends BasePage {
    private final Browser browser;

    public ProductPage() {
        this.browser = BrowserFactory.getBrowser();
    }

    public void verifyAuthor(String name) {
        Assert.assertEquals(browser.cell(3).getText(), name);
    }

    @Step("Verify product author as <author>")
    public void verifyProductTitle(String author) {
        verifyAuthor(author);
    }

    @Step("Delete this product")
    public void deleteProduct() {
        browser.link("Delete Product");
    }
}
