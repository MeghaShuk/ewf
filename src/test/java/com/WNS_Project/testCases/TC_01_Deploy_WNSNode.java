package com.WNS_Project.testCases;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.pageObject.Deploy_WNSNode;

public class TC_01_Deploy_WNSNode extends BaseClass {

	@Test
	public void DeployNode() throws IOException, InterruptedException, AWTException {

		Deploy_WNSNode deploy = new Deploy_WNSNode(driver);

		WorkerManageScreen();
		Thread.sleep(3000);
		deploy.Deploy_button();
		deploy.Node_Name(nodename);
		deploy.ChooseRegion();
		deploy.ChooseInstance();
		deploy.ChooseCloudAccount();
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		deploy.Continue_button();
		deploy.Public_Gateway();
		deploy.Continue_button();
		deploy.Generate_Key();
		deploy.Continue_button();
		deploy.Popup_Continue();
	}

	@AfterClass
	public void delayAfterTests() {
		try {
			System.out.println("Adding a 10-minute delay before running the next test class...");
			Thread.sleep(650000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}