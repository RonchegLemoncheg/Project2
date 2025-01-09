package ge.tbc.testautomation.pages.swoop;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CommonPage {
    public SelenideElement
            languageButton = $("img[src*='language']"),
            englishLanguage = $x("//button[p[text()='English']]"),
            georgianLanguage = $x("//button[p[text()='Georgian']]"),
            cookieButton = $x("//p[text()='ვეთანხმები']"),
            searchBar = $("input.font-tbcx-regular"),
            categories = $x("//p[text()='კატეგორიები']");

}
