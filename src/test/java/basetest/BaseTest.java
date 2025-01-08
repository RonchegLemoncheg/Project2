package basetest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import util.ModdedAllureSelenide;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    @Parameters("browserType")
    public void setup(@Optional("firefox") String browserType) {
        switch (browserType.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                WebDriverRunner.setWebDriver(new FirefoxDriver());
                Configuration.browser="firefox";
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                WebDriverRunner.setWebDriver(new ChromeDriver());
                Configuration.browser="chrome";
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                WebDriverRunner.setWebDriver(new EdgeDriver());
                Configuration.browser="edge";
                break;
            default:
                throw new IllegalArgumentException();
        }
        Configuration.timeout = 6000;
        SelenideLogger.addListener("AllureSelenide", new ModdedAllureSelenide());
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}