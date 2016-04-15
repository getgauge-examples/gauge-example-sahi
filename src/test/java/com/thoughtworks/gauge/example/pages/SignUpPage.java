package com.thoughtworks.gauge.example.pages;

import com.thoughtworks.gauge.example.BrowserFactory;
import net.sf.sahi.client.Browser;

public class SignUpPage extends BasePage {

    private String signUpUrl = url.concat("signup/");
    private final Browser browser;

    public SignUpPage() {
        this.browser = BrowserFactory.getBrowser();
    }

    public void search(String username) {
        browser.byId("user_username").setValue(username);
        browser.byId("user_email").setValue(username.concat("@domain.com"));
        browser.byId("user_password").setValue(username.concat("password"));
        browser.byId("user_password_confirmation").setValue(username.concat("password"));
        browser.submit("commit").click();
        storeStringToScenarioDataStore("currentUser", username);
    }

    public void navigate() {
        browser.navigateTo(signUpUrl);
    }
}
