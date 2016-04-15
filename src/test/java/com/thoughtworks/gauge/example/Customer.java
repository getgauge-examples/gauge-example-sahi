package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.example.pages.CustomerPage;
import org.junit.Assert;

public class Customer {
    private final CustomerPage customerPage;

    public Customer() {
        this.customerPage = new CustomerPage();
    }

    @Step("On the customer page")
    public void navigateToCustomersPage() {
        customerPage.navigate();
    }

    @Step("Search for customer <customer_name>")
    public void searchUser(String username) {
        customerPage.search(username);
    }

    @Step("The customer <name> is listed")
    public void verifyUserIsListed(String username) {
        Assert.assertEquals(customerPage.getUserName(), username);
    }

    @Step("Search for customers <table>")
    public void verifyCustomers(Table table) {
        for (String username : table.getColumnValues(0))
            searchUser(username);
    }

    @Step("Just registered customer is listed")
    public void verifyJustRegisteredCustomerListed() {
        verifyUserIsListed(customerPage.fetchStringFromScenarioDataStore("currentUser"));
    }
}
