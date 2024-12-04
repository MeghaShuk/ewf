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

public class TC_02_Status_Check extends BaseClass {

	@Description("This TestCase will verify that the Node Health Status is \"Running - Waiting for Subscription\" once the node reaches the ready state. If the Node Health Status shows \"Stopped,\" an alarm will be triggered to alert the team to investigate the health of the services.")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Status_Check() throws Exception {

		Status_Check status = new Status_Check(driver);

		ScreenRecorderUtil.startRecord("Status");

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
		Thread.sleep(3000);
		status.View_Button();
		Thread.sleep(2000);
		waitForNodeStatus();
		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());
	}

	@AfterClass
	public void delayAfterTests() throws Exception {
		try {
			System.out.println("Adding a 5-seconds delay before running the next test class...");
			Thread.sleep(5000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ScreenRecorderUtil.stopRecord();
	}

	private boolean waitForNodeStatus() {
		WebElement nodestatus = driver.findElement(
				By.xpath("/html/body/div[2]/div/div/div[2]/div/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div"));
		String statuscheck = nodestatus.getText();

		if (statuscheck.equals("Running - Waiting for Subscription")) {
			System.out.println("Node Health Status is " + nodestatus.getText() + " and Node is Healthy");
			return true;

		} else {
			System.out.println(
					"Node Health Status is " + nodestatus.getText() + " and something is wrong with the services");
			return false;
		}
	}
}