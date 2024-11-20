package com.WNS_Project.Utilities;

import java.io.IOException;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.WNS_Project.Base.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseClass implements ITestListener {

	ExtentReports extent = ExtentReporterNG.extentReportGenerator();
	ExtentTest test;
	private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {

		extentTest.get().log(Status.PASS, "Successful");

		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName()),
					result.getMethod().getMethodName());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		extentTest.get().fail(result.getThrowable());
		Object testObject = result.getInstance();
		Class clazz = result.getTestClass().getRealClass();
		try {
			driver = (WebDriver) clazz.getDeclaredField("driver").get(testObject);
		} catch (IllegalArgumentException e1) {

			e1.printStackTrace();
		} catch (Exception e1) {

		}
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName()),
					result.getMethod().getMethodName());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}
}