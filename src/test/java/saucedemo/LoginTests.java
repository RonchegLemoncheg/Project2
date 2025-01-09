package saucedemo;
import basetest.BaseTest;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.saucedemo.SauceDemoInventorySteps;
import ge.tbc.testautomation.steps.saucedemo.SauceDemoLoginSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.open;

@Epic("SauceDemo Tests")
@Feature("Login Functionality")
@Test(groups = {"SauceDemoLogin"})
public class LoginTests extends BaseTest {
    SauceDemoLoginSteps sauceDemoLoginSteps;
    SauceDemoInventorySteps sauceDemoInventorySteps;


    @BeforeMethod(alwaysRun = true)
    public void setup() {
        open(Constants.SAUCEDEMO_LINK);
        sauceDemoLoginSteps = new SauceDemoLoginSteps();
        sauceDemoInventorySteps = new SauceDemoInventorySteps();
    }

    @Test(priority = 1)
    @Description("Verifying that a standard user can log in successfully and product images are displayed on the inventory page.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Successful Login")
    public void successfulLoginTest(){
        sauceDemoLoginSteps.loginWithUser(Constants.STANDARD_USER);
        sauceDemoInventorySteps.validateImages();
    }

    @Test(priority = 3)
    @Description("Verify that a locked-out user cannot log in and receives the appropriate error message and error icons.")
    @Severity(SeverityLevel.MINOR)
    @Story("Banned User Login")
    public void bannedUserLoginTest(){
        sauceDemoLoginSteps.loginWithUser(Constants.LOCKED_OUT_USER)
                .validateErrorMessage()
                .validateErrorIcons();
    }

    @Test(priority = 2)
    @Description("Verify that a logged-in user can successfully log out and that the input fields on the login page are empty after logout.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Logout Functionality")
    public void logOutTest(){
        sauceDemoLoginSteps.loginWithUser(Constants.STANDARD_USER);
        sauceDemoInventorySteps.logOut();
        sauceDemoLoginSteps.validateInputsAreEmpty();
    }
}
