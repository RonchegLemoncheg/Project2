package ge.tbc.testautomation.pages.saucedemo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SauceDemoInventoryPage {
    public SelenideElement
                menuButton = $("#react-burger-menu-btn"),
                logoutButton = $("#logout_sidebar_link");
    public ElementsCollection
                inventory = $$("div.inventory_item");
}
