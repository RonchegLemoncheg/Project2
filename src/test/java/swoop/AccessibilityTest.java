package swoop;

import basetest.BaseTest;
import com.codeborne.selenide.Selenide;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.swoop.CommonSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccessibilityTest extends BaseTest {
    CommonSteps commonSteps;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        Selenide.open(Constants.SWOOP_LINK);
        commonSteps = new CommonSteps();
    }


    @Test
    public void AccessebilityTestSwoop(){
        commonSteps.checkAccessibility();
    }
}
