package ge.tbc.testautomation.steps.swoop;
import ge.tbc.testautomation.pages.swoop.OfferPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public class OfferSteps extends CommonSteps {
    OfferPage offerPage = new OfferPage();

    @Step("Validate the location button functionality")
    public OfferSteps validateLocationButton(){
        offerPage.location.should(exist).scrollIntoView("{block: 'center'}").click();
        offerPage.map.shouldBe(visible);
        return this;
    }
}
