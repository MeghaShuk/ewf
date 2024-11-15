package com.WNS_Project.testCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.Utilities.ScreenRecorderUtil;
import com.WNS_Project.pageObject.Status_Check;

public class TC_05_Import_Key extends BaseClass {

	@Test
	public void Import_Key() throws Exception {

		Status_Check status = new Status_Check(driver);

		ScreenRecorderUtil.startRecord("ImportKey");

		WorkerManageScreen();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(3000);
		status.View_Button();
		Thread.sleep(3000);
		status.Import_Button();
		status.Key_Input(importedkey);
		Thread.sleep(2000);
		status.Save_Button();
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