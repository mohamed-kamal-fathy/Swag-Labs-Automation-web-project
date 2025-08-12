package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.util.Arrays;

public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        System.out.println("Test Started : " + result.getTestName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test ---- Success : " + result.getTestName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test ---- Failure : " + result.getTestName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test ---- Skipped : " + result.getTestName());
    }


    public void onStart(ITestContext context) {

        System.out.println(Arrays.toString(context.getAllTestMethods()));
    }

    public void onFinish(ITestContext context) {

        System.out.println(" Finished test class: " );
    }
}
