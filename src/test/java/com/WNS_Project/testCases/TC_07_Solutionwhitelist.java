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
import com.WNS_Project.pageObject.Status_Check;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.model.Label;

public class TC_07_Solutionwhitelist extends BaseClass {

	@Description("This TestCase will verify that solutions are correctly added and removed from the solution whitelisting, with both scenarios involving the restart of the worker node. If the worker node does not properly update the whitelist or encounters any issues during the process, an alert should be triggered to notify the team. This will allow the team to check the service health and ensure the stability of the system.")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Solutionwhitelist() throws Exception {

		Status_Check status = new Status_Check(driver);

		ScreenRecorderUtil.startRecord("Whitelisting");

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
		Thread.sleep(40000);
		status.View_Button();
		Thread.sleep(2000);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		status.SW();
		Thread.sleep(2000);
		waitForSolutionWhitelistingAdded();
		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());
		Thread.sleep(2000);
		status.Remove_Sol();
		Thread.sleep(2000);
		status.Sol_Save_Button();
		Thread.sleep(40000);
		status.View_Button();
		Thread.sleep(2000);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		status.SW();
		Thread.sleep(2000);
		waitForSolutionWhitelistingRemove();
		Allure.addAttachment("Screenshot", getScreenshotAsFileInputStream());
	}

	@AfterClass
	public void delayAfterTests() throws Exception {
		try {
			System.out.println("Adding a 5-seconds delay before running the next test class...");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ScreenRecorderUtil.stopRecord();
	}

	private boolean waitForSolutionWhitelistingAdded() {
		WebElement solution_added = driver
				.findElement(By.xpath("(//div[@class=\"relative flex h-max w-full\"]/input)[1]"));
		String solution_name = solution_added.getAttribute("value");

		if (solution_name.equals("solution1")) {
			System.out.println(
					"Solution added for whitelisting is  " + solution_name + " and that is the correct user input");
			return true;

		} else {
			System.out.println(
					"Solution added for whitelisting is  " + solution_name + " and that is the not correct user input");
			return false;
		}
	}

	private boolean waitForSolutionWhitelistingRemove() {
		WebElement solution_added = driver.findElement(By.xpath("//div[@class=\"relative flex h-max w-full\"]/input"));
		String solution_name = solution_added.getAttribute("value");

		if (solution_name.equals("")) {
			System.out.println("Solution removed for whitelisting - " + solution_name);
			return true;

		} else {
			System.out.println("Solution not removed for whitelisting " + solution_name);
			return false;
		}
	}
}