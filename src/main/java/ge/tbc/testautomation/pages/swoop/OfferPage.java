package ge.tbc.testautomation.pages.swoop;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class OfferPage extends CommonPage {
    public SelenideElement
            location = $x("//p[text()='მდებარეობა']"),
            map = $("div.leaflet-grab");
}
