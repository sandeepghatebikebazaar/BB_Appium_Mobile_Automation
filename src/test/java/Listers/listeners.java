package Listers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.ExtentReportNG;
import util.Testutil;

import java.io.IOException;

public class listeners extends Testutil implements ITestListener {
WebDriver driver;
    ExtentReports extent= ExtentReportNG.getreportobject();

    ExtentTest test;

    public listeners() throws IOException {
    }

    @Override
    public void onTestStart(ITestResult result) {
       test=extent.createTest(result.getMethod().getMethodName()).assignCategory("Regaression");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    test.log(Status.PASS,"Test pass"+"--->"+result.getTestClass().getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL,"Test Fail"+"--->"+result.getTestClass().getName());
       test.log(Status.FAIL,result.getThrowable());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            test.addScreenCaptureFromPath(getscreenshots(result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        try {
//            MediaEntityBuilder.createScreenCaptureFromPath(getscreenshots(result)).build();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }




}
