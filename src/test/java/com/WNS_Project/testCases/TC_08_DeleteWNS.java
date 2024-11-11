package com.WNS_Project.testCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.Utilities.ScreenRecorderUtil;
import com.WNS_Project.pageObject.Status_Check;

public class TC_08_DeleteWNS extends BaseClass {

	@Test
	public void Deletion() throws Exception {

		Status_Check status = new Status_Check(driver);

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
		Thread.sleep(5000);

		ScreenRecorderUtil.stopRecord();
	}
}