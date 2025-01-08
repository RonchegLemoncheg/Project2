package ge.tbc.testautomation.listeners;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.screenshot;
import static io.qameta.allure.Allure.addAttachment;

public class TestListener implements ITestListener {
    private LocalDateTime testStartDate;
    private LocalDateTime testEndDate;
    private LocalDateTime methodStartDate;
    private LocalDateTime methodEndDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onTestStart(ITestResult result) {
        methodStartDate = LocalDateTime.now();
        String testName = result.getTestContext().getName();
        System.out.printf("Test Method '%s' (from Test '%s') has started execution at %s.%n",
                result.getMethod().getMethodName(), testName, methodStartDate.format(FORMATTER));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        methodEndDate = LocalDateTime.now();
        Duration duration = Duration.between(methodStartDate, methodEndDate);
        String testName = result.getTestContext().getName();
        System.out.printf("Test Method '%s' (from Test '%s') has successfully finished execution at %s.%n",
                result.getMethod().getMethodName(), testName, methodEndDate.format(FORMATTER));
        System.out.printf("Total time needed to finish the Test Method: %d ms.%n", duration.toMillis());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        methodEndDate = LocalDateTime.now();
        String testName = result.getTestContext().getName();
        String methodName = result.getMethod().getMethodName();
        Duration duration = Duration.between(methodStartDate, methodEndDate);
        System.out.printf("Test Method '%s' (from Test '%s') has failed execution at %s.%n",
                methodName, testName, methodEndDate.format(FORMATTER));
        System.out.printf("Total time needed to finish the Test Method: %d ms.%n", duration.toMillis());
        // es pirobidan rogorc gavige marto failis dros unda daesqrina
        String screenshotPath = screenshot(methodName + "_" + System.currentTimeMillis());
        System.out.printf("Screenshot for test '%s' saved at: %s.%n",
                methodName, screenshotPath);
        try (InputStream is = Files.newInputStream(Path.of(Objects.requireNonNull(screenshot(OutputType.FILE)).getAbsolutePath()))) {
            addAttachment("error_screen.png", is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        methodEndDate = LocalDateTime.now();
        String testName = result.getTestContext().getName();
        String methodName = result.getMethod().getMethodName();
        Duration duration = Duration.between(methodStartDate, methodEndDate);
        System.out.printf("Test Method '%s' (from Test '%s') was skipped at %s.%n",
                methodName, testName, methodEndDate.format(FORMATTER));
        System.out.printf("Total time needed to finish the Test Method: %d ms.%n", duration.toMillis());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onStart(ITestContext context) {
        testStartDate = LocalDateTime.now();
        System.out.printf("Test '%s' has started execution at %s.%n",
                context.getName(), testStartDate.format(FORMATTER));
    }

    @Override
    public void onFinish(ITestContext context) {
        testEndDate = LocalDateTime.now();
        Duration duration = Duration.between(testStartDate, testEndDate);
        System.out.printf("Test '%s' has finished execution at %s.%n",
                context.getName(), testEndDate.format(FORMATTER));
        System.out.printf("Total time needed to finish the Test: %d ms.%n", duration.toMillis());
    }


}