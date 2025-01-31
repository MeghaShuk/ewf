package com.WNS_Project.testCases;

import java.time.ZoneId;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.Utilities.ScreenRecorderUtil;
import com.WNS_Project.pageObject.Status_Check;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.model.Label;

public class TC_08_DeleteWNS extends BaseClass {

	@Description("This TestCase will verify the proper deletion of a deployed worker node. After deletion, the node should no longer appear in the node list but should be visible under \"Pending Deployment\" with the \"Deploy\" button. If the deletion process fails, an alert should be raised to notify the team, enabling them to investigate and resolve any issues.")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Deletion() throws Exception {

		Status_Check status = new Status_Check(driver);

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

		ScreenRecorderUtil.startRecord("Delete");

		WorkerManageScreen();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.View_Button();
		Thread.sleep(5000);
		status.Delete();
		status.Confirm_Del();
		Thread.sleep(2000);
		driver.navigate().to(workermanage);
		Thread.sleep(2000);
		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());
		Thread.sleep(160000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", status);
		waitForNodeToBe();
		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());

		ScreenRecorderUtil.stopRecord();
	}

	private boolean waitForNodeToBe() {
		WebElement nodename1 = driver.findElement(By.xpath("//tr/td[1]/div"));
		String namecheck = nodename1.getText();

		if (namecheck.equals(nodename)) {
			System.out.println("NodeName " + nodename1.getText());
			return true;

		} else {
			System.out.println("NodeName " + nodename1.getText());
			return false;
		}
	}
}