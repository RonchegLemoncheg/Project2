package ge.tbc.testautomation.pages.swoop;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

// Search Page-ს იმიტომ ვაექსტენდებ რომ ძალიან ბევრი საერთო აქვთ
// და როგორც დაიწერა ამის უფლება გვაქ
public class EatAndDrinkPage extends SearchPage {
    public ElementsCollection
            labels = $$x("//label[starts-with(@for, 'radio-სტუმრების რაოდენობა-')]"),
            labelsAfterFiltering = $$x("//p[@weight='medium' and contains(text(), 'სტუმარი')]");
}
