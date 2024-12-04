package com.WNS_Project.testCases;

import java.time.ZoneId;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.Utilities.ScreenRecorderUtil;
import com.WNS_Project.pageObject.Deploy_WNSNode;
import com.WNS_Project.pageObject.Status_Check;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.model.Label;

public class TC_01_Deploy_ManagedWNSNode extends BaseClass {

	WebElement status;

	@Description("Deploying a worker node involves a series of steps to ensure proper setup, secure connectivity, and efficient integration into the network. After the node is deployed, it will retrieve a Node-RED flow from IPFS (SmartFlow Storage). This TestCase will validate the deployment process, including the 9-minute wait time required for the worker node to reach a ready state, ensuring it is fully operational and ready to contribute to the network.After a successful deployment, the node should appear in the Node List on the Manage Screen. If it does not, an alert will be triggered to notify the team for further investigation and resolution.")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void DeployNode() throws Exception {

		Deploy_WNSNode deploy = new Deploy_WNSNode(driver);

		ScreenRecorderUtil.startRecord("DeployNode");

		// Get the current timezone
		ZoneId zoneId = ZoneId.systemDefault();
		String timezone = zoneId.getId();

		String shift = getShift(); // Get current shift (morning/evening)
		Allure.addAttachment("Shift Time", shift); // Add it as an attachment, visible in the report
		Allure.addAttachment("Timezone Information", "Timezone: " + timezone);
		String timestamp = getTimestamp();

		// Create a label for the shift with timestamp
		Label shiftLabel = new Label();
		shiftLabel.setName("Shift");
		shiftLabel.setValue(shift + " at: " + timestamp + " and Timezone:" + timezone);

		WorkerManageScreen();
		Thread.sleep(3000);
		deploy.Managed_Deploy_button();
		System.out.println("Node Creation started");
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
		Thread.sleep(20000);
		driver.navigate().to(workermanage);
		Thread.sleep(6000);
		waitForNodeToBe();
		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());
	}

	@AfterClass
	public void delayAfterTests() throws Exception {

		System.out.println("Adding a 19-minutes delay before running the next test class...");

		Thread.sleep(1140000);

		waitForNodeStatusToBe();

		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());

		Status_Check status = new Status_Check(driver);

		status.View_Button();
		Thread.sleep(2000);

		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());

		ScreenRecorderUtil.stopRecord();
	}

	private boolean waitForNodeStatusToBe() {
		WebElement status = driver.findElement(By.xpath("//tr/td[2]/div"));
		String statuscheck = status.getText();

		if (statuscheck.equals("READY")) {
			System.out.println("Node Status is " + status.getText() + " and that is the end requirement of the user");
			return true;

		} else {
			System.out.println("Node Status is " + status.getText() + " and something is wrong with the services");
			return false;
		}
	}

	private boolean waitForNodeToBe() {
		WebElement nodename1 = driver.findElement(By.xpath("//tr/td[1]/div"));
		String namecheck = nodename1.getText();

		if (namecheck.equals(nodename)) {
			System.out.println("Node Name is " + nodename1.getText() + " provided by the user");
			return true;

		} else {
			System.out.println("Node Name is " + nodename1.getText() + " and something is wrong with the services");
			return false;
		}
	}
}