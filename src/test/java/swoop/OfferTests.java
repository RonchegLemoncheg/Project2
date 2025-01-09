package swoop;

import basetest.BaseTest;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.DataSupplier;
import ge.tbc.testautomation.steps.swoop.CommonSteps;
import ge.tbc.testautomation.steps.swoop.EatAndDrinkSteps;
import ge.tbc.testautomation.steps.swoop.OfferSteps;
import ge.tbc.testautomation.steps.swoop.SearchSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.open;

@Epic("Swoop Automation")
@Test(groups = {"SwoopRegression"})
public class OfferTests extends BaseTest {

    CommonSteps commonSteps;
    SearchSteps searchSteps;
    OfferSteps offerSteps;
    EatAndDrinkSteps eatAndDrinkSteps;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        open(Constants.SWOOP_LINK);
        commonSteps = new CommonSteps();
        searchSteps = new SearchSteps();
        offerSteps = new OfferSteps();
        eatAndDrinkSteps = new EatAndDrinkSteps();
        commonSteps.acceptCookie();
    }

    @Test(dataProvider = "dataSupplier", dataProviderClass = DataSupplier.class, priority = 2)
    @Description("Test to perform search with valid and invalid keywords and validate results.")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Search Test")
    @Story("Searching for offers and validating search results with valid and invalid keywords.")
    public void searchTest(String offerName, String Gibberish){
        commonSteps.search(offerName);
        searchSteps.validateQuery(offerName);
        commonSteps.search(Gibberish);
        searchSteps.validateNoOfferFound();
    }

    @Test(priority = 3)
    @Description("Test pagination functionality by navigating through multiple pages.")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Page Functionality Test")
    @Story("Navigating through the pagination and verifying correct behavior on each page.")
    public void paginationTest(){
        commonSteps.goToCategory(Constants.HOLIDAY,Constants.KAXETI);
        searchSteps.validateDifferentFromFirstPage("2")
                .validateDifferentFromFirstPage("3")
                .testNavigationSmoothness();
    }

    @Test(priority = 5)
    @Description("Test location button functionality on offer details page.")
    @Severity(SeverityLevel.MINOR)
    @Feature("Offer Page Test")
    @Story("Validating that the location button on the offer page redirects to the map correctly.")
    public void offerLocationTest(){
        commonSteps.goToCategory(Constants.HOLIDAY,Constants.KAXETI);
        searchSteps.clickOffer(1);
        offerSteps.validateLocationButton();
    }

    @Test(priority = 4)
    @Description("Test the filtering functionality with the 'Number of Guests' filter.")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Page Functionality Test")
    @Story("Filtering offers based on the number of guests and validating the results.")
    public void numberOfGuestsTest(){
        commonSteps.goToCategory(Constants.EAT_AND_DRINK);
        eatAndDrinkSteps.filterNumberOfGuests(45)
                .filterNumberOfGuests(57)
                .checkIfOffersAreWithinRange();
    }

    @Test(priority = 1)
    @Description("Test language change functionality between English and Georgian.")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Language Test")
    @Story("Switching between English and Georgian languages and checking if UI text updates accordingly.")
    public void changeLanguageTest(){
        commonSteps.changeLanguage()
                .checkLanguageIsEnglish();
    }



}
