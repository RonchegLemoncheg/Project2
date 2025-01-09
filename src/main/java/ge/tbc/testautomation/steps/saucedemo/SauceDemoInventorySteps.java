package ge.tbc.testautomation.steps.saucedemo;

import ge.tbc.testautomation.pages.saucedemo.SauceDemoInventoryPage;
import io.qameta.allure.Step;
import org.testng.Assert;

public class SauceDemoInventorySteps {
    SauceDemoInventoryPage sauceDemoInventoryPage = new SauceDemoInventoryPage();

    @Step("Validate all product images are loaded and not empty")
    public SauceDemoInventorySteps validateImages(){
        sauceDemoInventoryPage.inventory.stream()
                .map(inv -> inv.$("img"))
                .forEach(img -> Assert.assertFalse(img.getAttribute("src") == null && img.getAttribute("src").isEmpty()));
        return this;
    }

    @Step("Log out from the Sauce Demo site.")
    public SauceDemoInventorySteps logOut(){
        sauceDemoInventoryPage.menuButton.click();
        sauceDemoInventoryPage.logoutButton.click();
        return this;
    }
}
