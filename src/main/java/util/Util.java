package util;

import com.codeborne.selenide.Condition;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class Util {
    public static boolean isNumberInRange(int number, String rangeText) {
        String[] parts = rangeText.split(" ");
        String[] range = parts[0].split("-");
        int min = Integer.parseInt(range[0]);
        int max = Integer.parseInt(range[1]);
        return number >= min && number <= max;
    }

    public static boolean containsGeorgianText(String text) {
        String georgianRegex = "[\\u10A0-\\u10FF]";
        Pattern pattern = Pattern.compile(georgianRegex);
        return pattern.matcher(text).find();
    }

    public static List<Integer> extractNumbers(String text) {
        List<Integer> numbers = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\d+").matcher(text);
        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        return numbers;
    }

    public static String getBaselineScreenshotPath(String browserName) {
        return switch (browserName.toLowerCase()) {
            case "chrome" -> "screenshots/chrome/baseline.png";
            case "firefox" -> "screenshots/firefox/baseline.png";
            case "edge" -> "screenshots/edge/baseline.png";
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };
    }


    public static void waitForPageToLoad(String currentUrl){
        $("html").shouldHave(Condition.cssClass("nprogress-busy"));
        webdriver().shouldNotHave(url(currentUrl));
        $("html").shouldNotHave(Condition.cssClass("nprogress-busy"));
    }

    public static boolean compareScreenshots(File currentScreenshot, File baselineScreenshot) {
        try {
            BufferedImage currentImage = ImageIO.read(currentScreenshot);
            BufferedImage baselineImage = ImageIO.read(baselineScreenshot);

            if (currentImage.getWidth() != baselineImage.getWidth() || currentImage.getHeight() != baselineImage.getHeight()) {
                return false;
            }

            for (int x = 0; x < currentImage.getWidth(); x++) {
                // აქ 235 იმიტომ მიწერია რომ ზევითა მენიუს ვადარებ, დაბლა სურათები ინტერნეტის მიხედვით ხან იცვლებოდა ხან არა
                // და მარტო მენიუზე დავტოვე შემოწმება და headerებზე
                for (int y = 0; y < 235; y++) {
                    if (currentImage.getRGB(x, y) != baselineImage.getRGB(x, y)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
