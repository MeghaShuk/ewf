package com.WNS_Project.testCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.Utilities.ScreenRecorderUtil;
import com.WNS_Project.pageObject.Status_Check;

public class TC_07_Solutionwhitelist extends BaseClass {

	@Test
	public void Solutionwhitelist() throws Exception {

		Status_Check status = new Status_Check(driver);

		ScreenRecorderUtil.startRecord("Whitelisting");

		WorkerManageScreen();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.View_Button();
		Thread.sleep(5000);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		status.SW();
		Thread.sleep(4000);
		status.SolutionName(solution_whitelist);
		Thread.sleep(2000);
		status.Sol_Save_Button();
		Thread.sleep(22000);
		status.View_Button();
		Thread.sleep(2000);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.SW();
		Thread.sleep(2000);
		status.Remove_Sol();
		Thread.sleep(2000);
		status.Sol_Save_Button();
		Thread.sleep(22000);
	}

	@AfterClass
	public void delayAfterTests() throws Exception {
		try {
			System.out.println("Adding a 15-seconds delay before running the next test class...");
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ScreenRecorderUtil.stopRecord();
	}
}