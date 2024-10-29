package com.WNS_Project.testCases;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.pageObject.Status_Check;

public class TC_06_Logs extends BaseClass {

	@Test
	public void Status() throws IOException, InterruptedException, AWTException {

		Status_Check status = new Status_Check(driver);

		WorkerManageScreen();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.View_Button();
		status.LogTab();
		Thread.sleep(5000);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(15000);
	}
}