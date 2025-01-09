package ge.tbc.testautomation.pages.swoop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage extends CommonPage {

    public SelenideElement
            offerNotFoundErrorMessage = $x("//h2[text()='შეთავაზება არ მოიძებნა']"),
            nextButton = $x("//img[@alt='right arrow']").parent(),
            previousButton = $x("//img[@alt='left arrow']").parent();
    public ElementsCollection
            searchBarNumbers = $$("div.justify-start.gap-1 div.text-md"),
            everyOffer = $$("div.grid-flow-row a"),
            everyOfferText = $$("a.gap-3 div.line-clamp-1"),
            everyOfferTitle = $$("div.grid-flow-row a h4.font-tbcx-medium");
}
