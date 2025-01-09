package ge.tbc.testautomation.data;

import org.testng.annotations.DataProvider;

public class DataSupplier {
    @DataProvider
    public static Object[][] dataSupplier(){
        return new Object[][] {
                {"ლაუნჯი", "mastodon"}
        };
    }

}