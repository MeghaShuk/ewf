package com.WNS_Project.Utilities;

import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {

	static ExtentReports extent;

	public static ExtentReports extentReportGenerator() {
		String path = System.getProperty("user.dir") + "\\report-output\\extentreport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setDocumentTitle("Test Result");
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setTheme(Theme.DARK);
		try {
			reporter.loadXMLConfig("./extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Test Engineer", "Megha Shukla");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Project", "Extent Report - EW");

		return extent;
	}
}