package ge.tbc.testautomation.steps.saucedemo;

import ge.tbc.testautomation.data.DatabaseSteps;
import ge.tbc.testautomation.pages.saucedemo.SauceDemoLoginPage;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Objects;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;

public class SauceDemoLoginSteps {
    SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage();

    @Step("Log in with user '{user}'.")
    public SauceDemoLoginSteps loginWithUser(String user){
        String[] usernameAndPassword = DatabaseSteps.getCredentialsOfUser(user);
        sauceDemoLoginPage.usernameField.sendKeys(usernameAndPassword[0]);
        sauceDemoLoginPage.passwordField.sendKeys(usernameAndPassword[1]);
        sauceDemoLoginPage.loginButton.click();
        return this;
    }

    @Step("Validate that the error message is visible.")
    public SauceDemoLoginSteps validateErrorMessage(){
        sauceDemoLoginPage.errorMessage.shouldBe(visible);
        return this;
    }

    @Step("Validate that error icons are visible.")
    public SauceDemoLoginSteps validateErrorIcons(){
        sauceDemoLoginPage.redIcons.shouldHave(sizeGreaterThan(0))
                .forEach(icon -> icon.shouldBe(visible));
        return this;
    }

    @Step("Validate that the username and password inputs are empty.")
    public SauceDemoLoginSteps validateInputsAreEmpty(){
        Assert.assertTrue(Objects.requireNonNull(sauceDemoLoginPage.usernameField.val()).isEmpty()
                && Objects.requireNonNull(sauceDemoLoginPage.passwordField.val()).isEmpty());
        return this;
    }
}
