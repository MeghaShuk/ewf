package com.WNS_Project.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.Utilities.ScreenRecorderUtil;
import com.WNS_Project.pageObject.Deploy_WNSNode;

public class TC_01_Deploy_WNSNode extends BaseClass {

	@Test
	public void DeployNode() throws Exception {

		Deploy_WNSNode deploy = new Deploy_WNSNode(driver);

		ScreenRecorderUtil.startRecord("DeployNode");

		WorkerManageScreen();
		Thread.sleep(2000);
		deploy.Managed_Deploy_button();
		// deploy.AWS_Deploy_button();
		deploy.Node_Name(nodename);
		Thread.sleep(3000);
		deploy.ChooseManagedRegion();
		// deploy.ChooseAWSRegion();
		// deploy.ChooseInstance();
		// deploy.ChooseCloudAccount();
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		deploy.Continue_button();
		deploy.Public_Gateway();
		deploy.Continue_button();
		deploy.Generate_Key();
		deploy.Continue_button();
		deploy.Popup_Continue();

		logger.info("Login test completed");
	}

	@AfterClass
	public void delayAfterTests() throws Exception {

		// System.out.println("Adding a 9-minutes delay before running the next test
		// class...");

		Thread.sleep(240000);

		boolean isNameVisible = waitForNodeToBe();
		System.out.println(isNameVisible);

		if (!isNameVisible) {
			System.out.println("Test failed: Node Name is not visible");
		}

		else {
			System.out.println("Test Passed: Node Name is visible");
		}

		boolean isStatusChanged = waitForNodeStatusToBe();
		System.out.println(isStatusChanged);

		if (!isStatusChanged) {
			System.out.println("Test failed: Node is still in provisioning state.");
		}

		else {
			System.out.println("Test Passed: Node is in Ready state.");
		}

		ScreenRecorderUtil.stopRecord();
	}

	private boolean waitForNodeStatusToBe() {
		WebElement status = driver.findElement(By.xpath("//tr/td[2]/div"));
		String statuscheck = status.getText();

		if (statuscheck.equals("READY")) {
			System.out.println("True Status printed " + status.getText());
			return true;

		} else {
			System.out.println("False Status printed " + status.getText());
			return false;
		}
	}

	private boolean waitForNodeToBe() {
		WebElement nodename = driver.findElement(By.xpath("//tr/td[1]/div"));
		String namecheck = nodename.getText();

		if (namecheck.equals("newnode01")) {
			System.out.println("True Name printed " + nodename.getText());
			return true;

		} else {
			System.out.println("False Name printed" + nodename.getText());
			return false;
		}
	}
}