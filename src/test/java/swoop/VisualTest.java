package swoop;

import basetest.BaseTest;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.swoop.CommonSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.open;

public class VisualTest extends BaseTest {
    CommonSteps commonSteps;

    @BeforeMethod
    public void setup() {
        open(Constants.SWOOP_LINK);
        commonSteps = new CommonSteps();
    }

    @Test
    public void visualCookieTest(){
        // Firefox-ზე გავაკეთე ეს მხოლოდ.
        // Chrome-სა და Edge-ზე რომ ემუშავა ცალკე Screenshot-ები დამჭირდებოდა და ბარემ მარტო Firefox-ზე დავტოვე
        commonSteps.scrollToTheBottom()
                .acceptCookie()
                .checkCookieFunctionality();
    }
}
