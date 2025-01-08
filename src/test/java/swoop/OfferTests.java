package swoop;

import basetest.BaseTest;
import ge.tbc.testautomation.data.DataSupplier;
import ge.tbc.testautomation.steps.swoop.CommonSteps;
import ge.tbc.testautomation.steps.swoop.EatAndDrinkSteps;
import ge.tbc.testautomation.steps.swoop.OfferSteps;
import ge.tbc.testautomation.steps.swoop.SearchSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Test(groups = {"SwoopRegression"})
public class OfferTests extends BaseTest {

    CommonSteps commonSteps;
    SearchSteps searchSteps;
    OfferSteps offerSteps;
    EatAndDrinkSteps eatAndDrinkSteps;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        open("https://swoop.ge");
        commonSteps = new CommonSteps();
        searchSteps = new SearchSteps();
        offerSteps = new OfferSteps();
        eatAndDrinkSteps = new EatAndDrinkSteps();
        commonSteps.acceptCookie();
    }

    @Test(dataProvider = "dataSupplier", dataProviderClass = DataSupplier.class)
    public void searchTest(String offerName, String Gibberish){
        commonSteps.search(offerName);
        searchSteps.validateQuery(offerName);
        commonSteps.search(Gibberish);
        searchSteps.validateNoOfferFound();
    }

    @Test
    public void paginationTest(){
        commonSteps.goToCategory("დასვენება","კახეთი");
        searchSteps.validateDifferentFromFirstPage("2")
                .validateDifferentFromFirstPage("3")
                .testNavigationSmoothness();
    }

    @Test
    public void offerLocationTest(){
        commonSteps.goToCategory("დასვენება","კახეთი");
        searchSteps.clickOffer(1);
        offerSteps.validateLocationButton();
    }

    @Test
    public void numberOfGuestsTest(){
        commonSteps.goToCategory("კვება");
        eatAndDrinkSteps.filterNumberOfGuests(45)
                .filterNumberOfGuests(57)
                .checkIfOffersAreWithinRange();
    }

    @Test
    public void changeLanguageTest(){
        commonSteps.changeLanguage()
                .checkLanguageIsEnglish();
    }

}
