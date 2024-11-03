package com.WNS_Project.testCases;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.pageObject.Status_Check;

public class TC_07_Solutionwhitelist extends BaseClass {

	@Test
	public void Solutionwhitelist() throws IOException, InterruptedException, AWTException {

		Status_Check status = new Status_Check(driver);

		WorkerManageScreen();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.View_Button();
		Thread.sleep(5000);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		status.SW();
		Thread.sleep(8000);
		status.SolutionName(solution_whitelist);
		Thread.sleep(2000);
		status.Sol_Save_Button();
		Thread.sleep(20000);
		status.View_Button();
		Thread.sleep(2000);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.SW();
		Thread.sleep(2000);
		status.Remove_Sol();
		status.Sol_Save_Button();
		Thread.sleep(20000);
	}
}