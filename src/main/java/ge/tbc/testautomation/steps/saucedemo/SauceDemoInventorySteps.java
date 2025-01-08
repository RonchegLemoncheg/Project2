package ge.tbc.testautomation.steps.saucedemo;

import ge.tbc.testautomation.pages.saucedemo.SauceDemoInventoryPage;
import org.testng.Assert;

public class SauceDemoInventorySteps {
    SauceDemoInventoryPage sauceDemoInventoryPage = new SauceDemoInventoryPage();

    public SauceDemoInventorySteps validateImages(){
        sauceDemoInventoryPage.inventory.stream()
                .map(inv -> inv.$("img"))
                .forEach(img -> Assert.assertFalse(img.getAttribute("src") == null && img.getAttribute("src").isEmpty()));
        return this;
    }

    public SauceDemoInventorySteps logOut(){
        sauceDemoInventoryPage.menuButton.click();
        sauceDemoInventoryPage.logoutButton.click();
        return this;
    }
}
