package com.WNS_Project.testCases;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.pageObject.Status_Check;

public class TC_02_Status_Check extends BaseClass {

	@Test
	public void Status() throws IOException, InterruptedException, AWTException {

		Status_Check status = new Status_Check(driver);

		Thread.sleep(7000);
		driver.navigate().refresh();
		Username(email);
		Password(password);
		Thread.sleep(3000);
		Submit();
		Thread.sleep(3000);
		status.Toggle_Button();
		driver.navigate().to(workermanage);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		status.View_Button();
		Thread.sleep(7000);
	}
}