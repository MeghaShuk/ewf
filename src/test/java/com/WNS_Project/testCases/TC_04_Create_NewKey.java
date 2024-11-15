package com.WNS_Project.testCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.Utilities.ScreenRecorderUtil;
import com.WNS_Project.pageObject.Status_Check;

public class TC_04_Create_NewKey extends BaseClass {

	@Test
	public void CreateNewKey() throws Exception {

		Status_Check status = new Status_Check(driver);

		ScreenRecorderUtil.startRecord("NewKey");

		WorkerManageScreen();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.View_Button();
		Thread.sleep(2000);
		status.Create_Button();
		status.Confirm_Button();
	}

	@AfterClass
	public void delayAfterTests() throws Exception {
		try {
			System.out.print("Adding 15-seconds delay before running the next test class...");
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ScreenRecorderUtil.stopRecord();
	}
}