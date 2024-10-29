package com.WNS_Project.testCases;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.pageObject.Status_Check;

public class TC_05_Import_Key extends BaseClass {

	@Test
	public void Status() throws IOException, InterruptedException, AWTException {

		Status_Check status = new Status_Check(driver);

		WorkerManageScreen();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.View_Button();
		Thread.sleep(2000);
		status.Import_Button();
		status.Key_Input(importedkey);
		status.Save_Button();
	}

	@AfterClass
	public void delayAfterTests() {
		try {
			System.out.print("Adding 20-sec delay before running the next test class...");
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}