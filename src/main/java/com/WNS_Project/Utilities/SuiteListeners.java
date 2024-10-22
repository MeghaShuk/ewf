package com.WNS_Project.Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import com.WNS_Project.Base.BaseClass;

public class SuiteListeners implements ITestListener, IAnnotationTransformer {

	@Override
	public void onTestFailure(ITestResult result) {

		String filename = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator
				+ result.getMethod().getMethodName();
		File file = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(file, new File(filename + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		String filename = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator
				+ result.getMethod().getMethodName();
		File file = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(file, new File(filename + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	@Override
	public void onFinish(ITestContext context) {

	}
}