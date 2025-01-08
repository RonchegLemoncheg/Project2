package ge.tbc.testautomation.pages.saucedemo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SauceDemoLoginPage {
    public SelenideElement
            //errorMessage = $x("//h3[@data-test='error']"),
            errorMessage = $x("//h3[text()='Epic sadface: Sorry, this user has been locked out.']"),
            usernameField = $("#user-name"),
            passwordField = $("#password"),
            loginButton = $("#login-button");
    public ElementsCollection
            redIcons = $$x("//*[local-name()='path' and @fill='currentColor']");
}
