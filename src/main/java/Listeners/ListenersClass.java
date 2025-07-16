package Listeners;

import ExtentReporting.ExtentReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ListenersClass implements ITestListener {
    ExtentReports extentReports;
    ExtentTest extentTest;
    String testName;
    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReport.generateExtentReport();
    }
    @Override
    public void onTestStart(ITestResult result) {
        testName = result.getName().replace(" ", "");
        extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO, testName + " Started Executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, testName + " Executed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.PASS, testName + " Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, testName + " SKIPPED");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        File reportiingFile = new File("ExtentReports/APITesting.html");
        try{
            Desktop.getDesktop().browse(reportiingFile.toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
