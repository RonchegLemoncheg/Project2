package ge.tbc.testautomation.steps.swoop;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.testautomation.pages.swoop.SearchPage;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;
import util.Util;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class SearchSteps extends CommonSteps {
    SearchPage searchPage = new SearchPage();
    SoftAssert softAssert = new SoftAssert();

    @Step("Validate that all displayed offers contain the keyword: {keyword}")
    public SearchSteps validateQuery(String keyword){
        searchPage.everyOfferText.shouldHave(sizeGreaterThan(0)).forEach(
                offer -> offer.shouldHave(text(keyword))
        );
        return this;
    }

    @Step("Validate that no offers are found")
    public SearchSteps validateNoOfferFound(){
        searchPage.offerNotFoundErrorMessage.should(exist);
        return this;
    }

    @Step("Navigate to page: {pageNumber}")
    public SearchSteps goToPage(String pageNumber){
        String currentUrl = WebDriverRunner.url();
        try {
            searchPage.searchBarNumbers.shouldHave(sizeGreaterThan(0)).filterBy(text(pageNumber)).first().click();
            webdriver().shouldNotHave(url(currentUrl));
            return this;
        } catch (Exception e){
            throw new RuntimeException("Error: Page " + pageNumber + " does not exist.");
        }
    }


    @Step("Validate that the offers on page {pageNumber} are different from the first page")
    public SearchSteps validateDifferentFromFirstPage(String pageNumber){
        goToPage("1");
        List<String> firstPageOfferTexts = searchPage.everyOfferTitle.
                shouldHave(sizeGreaterThan(0)).
                stream().
                map(SelenideElement::getText)
                .toList();
        goToPage(pageNumber);
        List<String> pageOfferTexts = searchPage.everyOfferTitle.
                shouldHave(sizeGreaterThan(0)).
                stream().
                map(SelenideElement::getText)
                .toList();
        //checking that they have at least 1 diff element
        boolean isDifferent = pageOfferTexts.stream().anyMatch(item -> !firstPageOfferTexts.contains(item));
        softAssert.assertTrue(isDifferent);
        // არ არიო ეს საჭირო ალინამ და დავაკომენტარე, ისე მუშაობს
//        pageOfferTexts.forEach(
//                offerTitle -> {
//                    SelenideElement offer = $x("//img[normalize-space(@alt) = '" + offerTitle + "']");
//                    String currentUrl = WebDriverRunner.url();
//                    if(commonPage.cookieButton.exists())commonPage.cookieButton.click();
//                    offer.scrollTo().click();
//                    webdriver().shouldNotHave(url(currentUrl));
//                    $x("//a[contains(@class, 'text-nowrap')]//p[normalize-space(text())='" + keyword + "']")
//                            .should(exist);
//                    back();
//                }
//        );
        return this;
    }

    @Step("Test navigation smoothness between pages")
    public SearchSteps testNavigationSmoothness(){
        goToPage("1");
        int xPosition = $("h3").getLocation().getX();
        int yPosition = $("h3").getLocation().getY();
        searchPage.nextButton.scrollTo().click();


        String newUrl = WebDriverRunner.url();
        int newXPosition = $("h3").getLocation().getX();
        int newYPosition = $("h3").getLocation().getY();
        softAssert.assertTrue(newUrl.contains("page=2"));
        softAssert.assertEquals(xPosition,newXPosition);
        softAssert.assertEquals(yPosition,newYPosition);

        searchPage.previousButton.scrollTo().click();
        webdriver().shouldNotHave(url(newUrl));
        softAssert.assertTrue(WebDriverRunner.url().contains("page=1"));
        softAssert.assertAll();
        return this;
    }

    @Step("Click on offer at index: {number}")
    public SearchSteps clickOffer(int number) {
        String currentUrl = WebDriverRunner.url();
        if (number < 0 || number >= searchPage.everyOffer.shouldHave(sizeGreaterThan(0)).size()) {
            throw new RuntimeException("Invalid index: " + number + ". Must be between 0 and " + (searchPage.everyOffer.size() - 1));
        }
        searchPage.everyOffer.get(number).click();
        Util.waitForPageToLoad(currentUrl);
        return this;
    }
}
