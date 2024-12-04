package com.WNS_Project.testCases;

import java.time.ZoneId;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

public class TC_04_Create_NewKey extends BaseClass {

	@Description("This TestCase will verify that the \"Create New\" button is clickable and triggers the creation of a new worker node from the backend. After the node restarts and reaches the \"Ready\" state, the seed phrase should be updated, ensuring that the new worker account is correctly linked using the updated seed phrase.")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void CreateNewKey() throws Exception {

		Status_Check status = new Status_Check(driver);

		ScreenRecorderUtil.startRecord("NewKey");

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
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.View_Button();
		Thread.sleep(5000);
		status.SeedPhrase_Button();
		Thread.sleep(5000);
		SeedPhrase();
		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());
		Thread.sleep(4000);
		status.Create_Button();
		status.Confirm_Button();
		Thread.sleep(20000);
		status.View_Button();
		Thread.sleep(5000);
		status.SeedPhrase_Button();
		Thread.sleep(5000);
		SeedPhrase();
		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());
	}

	@AfterClass
	public void delayAfterTests() throws Exception {
		try {
			System.out.print("Adding 5-seconds delay before running the next test class...");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ScreenRecorderUtil.stopRecord();
	}

	private boolean SeedPhrase() {
		WebElement seedphrase = driver.findElement(By.xpath("//div[text() =\"Seed phrase\"]/../div/div"));
		String check = seedphrase.getText();

		System.out.println("Current Seed-Phrase of the worker node - " + seedphrase.getText());
		return true;
	}
}