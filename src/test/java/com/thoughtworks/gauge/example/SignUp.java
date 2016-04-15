package com.thoughtworks.gauge.example;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.example.pages.SignUpPage;

import java.util.UUID;

public class SignUp {

    private final SignUpPage signUpPage;

    public SignUp() {
        this.signUpPage = new SignUpPage();
    }

    private String localPart() {
        return UUID.randomUUID().toString();
    }

    @Step("On signup page")
    public void navigateToSignUpPage() {
        signUpPage.navigate();
    }

    @Step("Fill in and send registration form")
    public void searchUser() {
        signUpPage.search(localPart());
    }
}
