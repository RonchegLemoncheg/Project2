package ge.tbc.testautomation.steps.swoop;

import com.codeborne.selenide.*;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import ge.tbc.testautomation.pages.swoop.CommonPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import util.Util;

import java.io.File;
import java.net.URI;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class CommonSteps {
    CommonPage commonPage = new CommonPage();

    @Step("Accept the cookie popup.")
    public CommonSteps acceptCookie(){
        // ამან გამამწარა და ყველა ტესტის წინ ვაჭერ
        commonPage.cookieButton.click();
        return this;
    }

    @Step("Perform a search with keyword: {keyword}.")
    public CommonSteps search(String keyword){
        String currentUrl = WebDriverRunner.url();
//        commonPage.searchBar.clear();
//        commonPage.searchBar.shouldHave(Condition.value(""));
        // ამის გარეშე ცოცხალი თავით არ მუშაობდა
        commonPage.searchBar.sendKeys(Keys.CONTROL + "a");
        commonPage.searchBar.sendKeys(Keys.BACK_SPACE);
        commonPage.searchBar.sendKeys(keyword);
        commonPage.searchBar.sendKeys(Keys.ENTER);
        Util.waitForPageToLoad(currentUrl);
        return this;
    }

    @Step("Navigate to category: {mainCategory} -> {subCategory}.")
    public CommonSteps goToCategory(String mainCategory, String subCategory){
        String currentUrl = WebDriverRunner.url();
        commonPage.categories.click();
        $(byTagAndText("h4", mainCategory)).hover();
        $(byTagAndText("h4", subCategory)).click();
        Util.waitForPageToLoad(currentUrl);
        return this;
    }

    @Step("Navigate to category: {mainCategory}.")
    public CommonSteps goToCategory(String mainCategory){
        String currentUrl = WebDriverRunner.url();
        commonPage.categories.click();
        $(byTagAndText("h4", mainCategory)).parent().click();
        Util.waitForPageToLoad(currentUrl);
        return this;
    }

    @Step("Validate that the language is English using screenshot comparison.")
    public CommonSteps checkLanguageIsEnglish(){
        // ეს უშუალოდ ყველა ტექსტს ამოწმებდა რომ ინგლისურად იყო
        // ეს დავაკომენტარე და ეხლა Screenshot-ით ვამოწმებ რომ ნამდვილად იცვლება ინგლისურზე
//        $$("body *").stream()
//                .filter(SelenideElement::isDisplayed)
//                .map(element -> element.getText().trim())
//                .filter(text -> !text.isEmpty())
//                .forEach(text -> {
//                    if (Util.containsGeorgianText(text)) {
//                        throw new AssertionError("Found Georgian characters in text: " + text);
//                    }
//                });
        String currentScreenshotPath = screenshot("current_language");
        URI screenshotURI = URI.create(currentScreenshotPath);
        File currentScreenshotFile = new File(screenshotURI);

        String browserName = Configuration.browser;
        String baselinePath = Util.getBaselineScreenshotPath(browserName);
        File baselineScreenshotFile = new File(baselinePath);

        if (!Util.compareScreenshots(currentScreenshotFile,baselineScreenshotFile)) {
            throw new AssertionError("Screenshots do not match.");
        }
        return this;
    }

    @Step("Change the language on the website.")
    public CommonSteps changeLanguage(){
        String currentUrl = WebDriverRunner.url();
        commonPage.languageButton.click();
        if(currentUrl.contains("/en/")) commonPage.georgianLanguage.click();
        else commonPage.englishLanguage.click();
        Util.waitForPageToLoad(currentUrl);
        return this;
    }

    @Step("Check the Accessebility on the website.")
    public CommonSteps checkAccessibility(){
        AxeBuilder axe = new AxeBuilder();
        Results results = axe.analyze(WebDriverRunner.getWebDriver());
        if (results.getViolations().isEmpty()) {
            System.out.println("No accessibility violations found.");
        } else {
            System.out.println("Accessibility Violations:");
            results.getViolations().forEach(violation -> {
                System.out.println("Violation ID: " + violation.getId());
                System.out.println("Description: " + violation.getDescription());
                System.out.println("Impact: " + violation.getImpact());
                System.out.println("-------------------------------");
            });
        }
        return this;
    }

    @Step("Scrolling to the bottom of the page.")
    public CommonSteps scrollToTheBottom(){
        Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
        return this;
    }

    @Step("Checking that accepting the cookie makes it disappear.")
    public CommonSteps checkCookieFunctionality(){
        String currentScreenshotPath = screenshot("cookiestuff");
        URI screenshotURI = URI.create(currentScreenshotPath);
        File currentScreenshotFile = new File(screenshotURI);

        String baselinePath = "screenshots/firefox/Screenshot_without_cookie.png";
        File baselineScreenshotFile = new File(baselinePath);

        if (!Util.compareScreenshots(currentScreenshotFile,baselineScreenshotFile)) {
            throw new AssertionError("Screenshots do not match.");
        }
        return this;
    }


}
