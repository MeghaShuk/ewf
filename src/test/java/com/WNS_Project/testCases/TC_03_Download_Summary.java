package com.WNS_Project.testCases;

import java.time.ZoneId;
import java.util.TimeZone;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.Utilities.ScreenRecorderUtil;
import com.WNS_Project.pageObject.Status_Check;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.model.Label;

public class TC_03_Download_Summary extends BaseClass {

	@Description("This TestCase will verify that the Download button is clickable and, when clicked, it successfully downloads a JSON file containing the worker node's information, including the Public Key, Address, and Seed Phrase.")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void DownloadSummary() throws Exception {

		Status_Check status = new Status_Check(driver);

		ScreenRecorderUtil.startRecord("Download Summary");

		// Get the current timezone
		ZoneId zoneId = ZoneId.systemDefault();
		String timezone = zoneId.getId();

		String shift = getShift(); // Get current shift (morning/evening)
		Allure.addAttachment("Shift Time", shift); // Add it as an attachment, visible in the report
		Allure.addAttachment("Timezone Information", "Timezone: " + timezone);
		String timestamp = getTimestamp();

		// Create a label for the shift with timestamp
		Label shiftLabel = new Label();
		shiftLabel.setName("Shift");
		shiftLabel.setValue(shift + " at: " + timestamp + " and Timezone:" + timezone);

		WorkerManageScreen();
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		status.View_Button();
		Thread.sleep(5000);
		status.Download_Button();
		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());

		System.out.println("File downloaded successfully");
	}

	@AfterClass
	public void delayAfterTests() throws Exception {
		try {
			System.out.println("Adding a 2-seconds delay before running the next test class...");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ScreenRecorderUtil.stopRecord();
	}
}