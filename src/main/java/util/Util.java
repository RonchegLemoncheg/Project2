package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


}
