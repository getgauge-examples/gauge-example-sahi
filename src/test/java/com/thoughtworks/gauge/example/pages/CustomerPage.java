package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.example.BrowserFactory;
import net.sf.sahi.client.Browser;

public class CustomerPage extends BasePage {
    private final Browser browser;
    private String customerUrl = adminUrl.concat("customers/");

    public CustomerPage() {
        this.browser = BrowserFactory.getBrowser();
    }

    public void navigate() {
        browser.navigateTo(customerUrl);
    }

    public void search(String username) {
        browser.textbox("q[username_contains]").setValue(username);
        browser.submit("commit").click();
    }

    public String getUserName() {
        return browser.cell("col col-username").getText();
    }
}
