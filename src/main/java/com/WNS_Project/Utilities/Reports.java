package com.WNS_Project.Utilities;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports extends TestListenerAdapter {

	public ExtentReports extent;
	public ExtentTest logger;
	long now = 0;

	public void onStart(ITestContext testContext) {

		extent = new ExtentReports();

		ExtentSparkReporter spark = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/Report-output/ExtentReport.html");
		extent.attachReporter(spark);
		extent.setSystemInfo("Company Name", "EWF");
		extent.setSystemInfo("Environemnt", "Launchpad");
		extent.setSystemInfo("user", "QA Team");

		spark.config().setDocumentTitle("EWF Platform Automation");
		spark.config().setReportName(" WNS Functional Test Automation Report");
		spark.config().setTimeStampFormat("yyyyMMdd");
		spark.config().setTheme(Theme.DARK);
	}

	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		String fileName = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date(now));
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";

		File f = new File(screenshotPath);
		if (f.exists()) {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		}
	}

	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date(now));
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";

		File f = new File(screenshotPath);
		if (f.exists()) {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		}
	}

	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}