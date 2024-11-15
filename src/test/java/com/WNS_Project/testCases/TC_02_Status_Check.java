package com.WNS_Project.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.Utilities.ScreenRecorderUtil;
import com.WNS_Project.pageObject.Status_Check;

public class TC_02_Status_Check extends BaseClass {

	@Test
	public void Status_Check() throws Exception {

		Status_Check status = new Status_Check(driver);

		ScreenRecorderUtil.startRecord("Status");

		WorkerManageScreen();
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.View_Button();
	}

	@AfterClass
	public void delayAfterTests() throws Exception {
		try {
			System.out.println("Adding a 10-seconds delay before running the next test class...");
			Thread.sleep(10000);

			boolean isStatusVisible = waitForNodeStatus();
			System.out.println(isStatusVisible);

			if (!isStatusVisible) {
				System.out.println("Test failed: Node Status is not visible");
			}

			else {
				System.out.println("Test Passed: Node Status is visible");
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ScreenRecorderUtil.stopRecord();
	}

	private boolean waitForNodeStatus() {
		WebElement nodestatus = driver
				.findElement(By.xpath("//div/div[text() = \"Running - Waiting for Subscription\"]"));
		String statuscheck = nodestatus.getText();

		if (statuscheck.equals("Running - Waiting for Subscription")) {
			System.out.println("Status printed " + nodestatus.getText());
			return true;

		} else {
			System.out.println("Statusprinted" + nodestatus.getText());
			return false;
		}
	}
}