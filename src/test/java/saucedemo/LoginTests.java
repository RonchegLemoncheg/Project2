package saucedemo;
import basetest.BaseTest;
import ge.tbc.testautomation.steps.saucedemo.SauceDemoInventorySteps;
import ge.tbc.testautomation.steps.saucedemo.SauceDemoLoginSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Test(groups = {"SauceDemoLogin"})
public class LoginTests extends BaseTest {
    SauceDemoLoginSteps sauceDemoLoginSteps;
    SauceDemoInventorySteps sauceDemoInventorySteps;


    @BeforeMethod(alwaysRun = true)
    public void setup() {
        open("https://saucedemo.com");
        sauceDemoLoginSteps = new SauceDemoLoginSteps();
        sauceDemoInventorySteps = new SauceDemoInventorySteps();
    }

    @Test
    public void successfulLoginTest(){
        sauceDemoLoginSteps.loginWithUser("standard_user");
        sauceDemoInventorySteps.validateImages();
    }

    @Test
    public void bannedUserLoginTest(){
        sauceDemoLoginSteps.loginWithUser("locked_out_user")
                .validateErrorMessage()
                .validateErrorIcons();
    }

    @Test
    public void logOutTest(){
        sauceDemoLoginSteps.loginWithUser("standard_user");
        sauceDemoInventorySteps.logOut();
        sauceDemoLoginSteps.validateInputsAreEmpty();
    }
}
