package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.example.BrowserFactory;
import net.sf.sahi.client.Browser;
import org.junit.Assert;

import java.util.List;

public class CustomerPage extends BasePage {
    private final Browser browser;
    public String customerUrl = Url.concat("customers/");

    public CustomerPage() {
        this.browser = BrowserFactory.getBrowser();
    }

    @Step("On the customer page")
    public void navigateToCustomersPage() {
        browser.navigateTo(customerUrl);
    }

    @Step("Search for customer <customer_name>")
    public void searchUser(String username) {
        browser.textbox("q[username_contains]").setValue(username);
        browser.submit("Filter").click();
    }

    @Step("The customer <name> is listed")
    public void verifyUserIsListed(String username) {
        Assert.assertEquals(browser.cell("user").getText(), username);
    }

    @Step("Search for customers <table>")
    public void verifyCustomers(Table table) {
        List<List<String>> rows = table.getRows();
        System.out.println(rows.size());
        for (List<String> row : rows) {
            searchUser(row.get(0));
            System.out.println(row.get(0));
        }
    }
}
