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

public class TC_05_Import_Key extends BaseClass {

	@Description("This TestCase will verify that when the user initiates the \"Import Key\" process and provides a 12-word Seed Phrase as input, the node will restart. After the restart, once the node reaches the \"Ready\" state, the seed phrase should be updated accordingly. This ensures that the provided seed phrase is correctly applied to the worker node.")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void Import_Key() throws Exception {

		Status_Check status = new Status_Check(driver);

		ScreenRecorderUtil.startRecord("ImportKey");

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
		Thread.sleep(3000);
		status.View_Button();
		Thread.sleep(7000);
		status.SeedPhrase_Button();
		Thread.sleep(3000);
		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());
		status.Import_Button();
		status.Key_Input(importedkey);
		status.Save_Button();
		Thread.sleep(20000);
		status.View_Button();
		Thread.sleep(7000);
		status.SeedPhrase_Button();
		Thread.sleep(2000);
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

		if (check.equals(importedkey)) {
			System.out.println("Seed Phrase entered by user for this Worker Node- " + seedphrase.getText());
			return true;

		} else {
			System.out.println("Seed Phrase entered by user for this Worker Node " + seedphrase.getText()
					+ " and it is incorrect");
			return false;
		}
	}

}