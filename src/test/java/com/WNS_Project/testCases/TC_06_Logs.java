package com.WNS_Project.testCases;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.pageObject.Logs;

public class TC_06_Logs extends BaseClass {

	@Test(priority = 1)
	public void Status() throws IOException, InterruptedException, AWTException {

		TC_02_Status_Check test2 = new TC_02_Status_Check();
		test2.Status();
	}

	@Test(priority = 2)
	public void logs() throws IOException, InterruptedException, AWTException {

		Logs log = new Logs(driver);
		log.LogTab();
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(15000);
	}
}