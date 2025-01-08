package ge.tbc.testautomation.steps.swoop;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.testautomation.pages.swoop.CommonPage;
import org.openqa.selenium.Keys;
import util.Util;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class CommonSteps {
    CommonPage commonPage = new CommonPage();

    public CommonSteps acceptCookie(){
        commonPage.cookieButton.click();
        return this;
    }
    public CommonSteps search(String keyword){
        String currentUrl = WebDriverRunner.url();
//        commonPage.searchBar.clear();
//        commonPage.searchBar.shouldHave(Condition.value(""));
        // ამის გარეშე ცოცხალი თავით არ მუშაობდა
        commonPage.searchBar.sendKeys(Keys.CONTROL + "a");
        commonPage.searchBar.sendKeys(Keys.BACK_SPACE);
        commonPage.searchBar.sendKeys(keyword);
        commonPage.searchBar.sendKeys(Keys.ENTER);
        webdriver().shouldNotHave(url(currentUrl));
        $("html").shouldNotHave(Condition.cssClass("nprogress-busy"));
        return this;
    }

    public CommonSteps goToCategory(String mainCategory, String subCategory){
        String currentUrl = WebDriverRunner.url();
        commonPage.categories.click();
        $(byTagAndText("h4", mainCategory)).hover();
        $(byTagAndText("h4", subCategory)).click();
        webdriver().shouldNotHave(url(currentUrl));
        $("html").shouldNotHave(Condition.cssClass("nprogress-busy"));
        return this;
    }

    public CommonSteps goToCategory(String mainCategory){
        String currentUrl = WebDriverRunner.url();
        commonPage.categories.click();
        $(byTagAndText("h4", mainCategory)).parent().click();
        webdriver().shouldNotHave(url(currentUrl));
        $("html").shouldNotHave(Condition.cssClass("nprogress-busy"));
        return this;
    }

    public CommonSteps checkLanguageIsEnglish(){
        $$("body *").stream()
                .filter(SelenideElement::isDisplayed)
                .map(element -> element.getText().trim())
                .filter(text -> !text.isEmpty())
                .forEach(text -> {
                    if (Util.containsGeorgianText(text)) {
                        throw new AssertionError("Found Georgian characters in text: " + text);
                    }
                });
        return this;
    }

    public CommonSteps changeLanguage(){
        String currentUrl = WebDriverRunner.url();
        commonPage.languageButton.click();
        if(currentUrl.contains("/en/")) commonPage.georgianLanguage.click();
        else commonPage.englishLanguage.click();
        webdriver().shouldNotHave(url(currentUrl));
        $("html").shouldNotHave(Condition.cssClass("nprogress-busy"));
        return this;
    }


}
