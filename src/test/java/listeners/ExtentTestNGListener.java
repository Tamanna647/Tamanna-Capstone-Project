package listeners;

import org.testng.*;
import com.aventstack.extentreports.*;
import utils.ExtentManager;

public class ExtentTestNGListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final ExtentReports extent = ExtentManager.getInstance();

    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
