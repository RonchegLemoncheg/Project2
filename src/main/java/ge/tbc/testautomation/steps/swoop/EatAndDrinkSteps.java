package ge.tbc.testautomation.steps.swoop;

import org.testng.Assert;
import util.Util;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.swoop.EatAndDrinkPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

public class EatAndDrinkSteps extends CommonSteps {
    EatAndDrinkPage eatAndDrinkPage = new EatAndDrinkPage();

    public EatAndDrinkSteps filterNumberOfGuests(int number) {
        eatAndDrinkPage.labels.shouldHave(sizeGreaterThan(0));
        for (SelenideElement label : eatAndDrinkPage.labels) {
            String spanText = label.$("span").getText();
            if (Util.isNumberInRange(number, spanText)) {
                label.$("input").click();
                return this;
            }
        }
        throw new RuntimeException("Not an acceptable number of guests: " + number);
    }

    public EatAndDrinkSteps checkIfOffersAreWithinRange() {
        List<int[]> acceptableNumbers = new ArrayList<>();
        eatAndDrinkPage.labelsAfterFiltering.stream().forEach(
                label -> {
                    String labelText = label.getText().trim();
                    String[] parts = labelText.split(" ")[0].split("-");
                    int num1 = Integer.parseInt(parts[0]);
                    int num2 = Integer.parseInt(parts[1]);
                    acceptableNumbers.add(new int[]{num1, num2});
                }
        );

        eatAndDrinkPage.everyOfferTitle.stream().forEach(offer -> {
            String offerText = offer.getText();
            List<Integer> offerNumbers = Util.extractNumbers(offerText);

            boolean containsValidNumber = offerNumbers.stream().anyMatch(number ->
                    acceptableNumbers.stream().anyMatch(range ->
                            number >= range[0] && number <= range[1]
                    )
            );
            Assert.assertTrue(containsValidNumber);
        });
        return this;
    }
}
