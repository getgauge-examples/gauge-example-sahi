package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.example.BrowserFactory;
import net.sf.sahi.client.Browser;
import net.sf.sahi.client.ElementStub;
import org.junit.Assert;

public class ProductPage extends BasePage {
    private static final String TITLE = "//table/tbody/tr[2]/td";
    private static final String DESCRIPTION = "//table/tbody/tr[3]/td";
    private static final String AUTHOR = "//table/tbody/tr[4]/td";
    private static final String PRICE = "//table/tbody/tr[5]/td";
    private final Browser browser;

    public ProductPage() {
        this.browser = BrowserFactory.getBrowser();
    }

    public void verifyAuthor(String name) {
        Assert.assertEquals(browser.cell(3).getText(), name);
    }

    public void deleteProduct() {
        browser.link("Delete Product");
    }

    public ElementStub getElementByName(String elementName) {
        switch (elementName) {
            case "title":
                return browser.byXPath(TITLE);
            case "description":
                return browser.byXPath(DESCRIPTION);
            case "author":
                return browser.byXPath(AUTHOR);
            case "price":
                return browser.byXPath(PRICE);
        }
        return null;
    }
}
