package com.WNS_Project.testCases;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.pageObject.Status_Check;

public class TC_03_Download_Summary extends BaseClass {

	@Test
	public void Status() throws IOException, InterruptedException, AWTException {

		Status_Check status = new Status_Check(driver);

		WorkerManageScreen();
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.View_Button();
		Thread.sleep(2000);
		status.Download_Button();
	}
}