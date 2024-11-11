package com.WNS_Project.Base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.WNS_Project.Utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	public String baseurl;
	public String workermanage = readconfig.getWorkerNodeManageURL();
	public String email = readconfig.getEmail();
	public String password = readconfig.getPassword();
	public String nodename = readconfig.getNodeName();
	public String importedkey = readconfig.getMnemonicKey();
	public String solution_whitelist = readconfig.getsolutionwhitelist();

	public static WebDriver driver;

	public WebDriver startbrowser(String browser) {
		if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		return driver;
	}

	@BeforeClass
	public void setup() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		baseurl = readconfig.getApplicationURL();
		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(5000);
		driver.navigate().refresh();
		((JavascriptExecutor) driver).executeScript("window.open()");
		Thread.sleep(5000);
		String mainWindow = driver.getWindowHandle();

		Set<String> handles = driver.getWindowHandles();
		List<String> handleList = new ArrayList<>(handles);

		// Switch to new window
		for (String handle : handleList) {
			if (!handle.equals(mainWindow)) {
				driver.switchTo().window(handle);
				driver.get(baseurl);
				driver.manage().window().maximize();
				break;
			}
		}
	}

	@AfterClass
	public void teardown() {
		// driver.quit();
	}

	// method to give Username for Login
	public void Username(String login_email) {
		WebElement Id = driver.findElement(By.xpath("//input[@name=\"email\"]"));
		Id.sendKeys(login_email);
	}

	// method to give Password for Login
	public void Password(String login_password) {
		WebElement pass = driver.findElement(By.xpath("//input[@name =\"password\"]"));
		pass.sendKeys(login_password);
	}

	// method to click Sign in button
	public void Submit() {
		driver.findElement(By.xpath("//button[contains(text(), \"Sign in\")]")).click();
	}

	// toggle button for VaaS
	public void Toggle() {
		WebElement togglebutton = driver.findElement(By.xpath("/html/body/div[2]/div/header/div[2]/div[1]/label/div"));
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(togglebutton));
		togglebutton.click();
	}

	public void WorkerManageScreen() throws InterruptedException {
		Thread.sleep(3000);
		Username(email);
		Password(password);
		Thread.sleep(2000);
		Submit();
		Thread.sleep(2000);
		Toggle();
		driver.navigate().to(workermanage);
	}

	public String getScreenshotPath(String TestCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destpath = System.getProperty("user.dir") + "\\report-output\\" + TestCaseName + ".png";
		File file = new File(destpath);
		FileUtils.copyFile(source, file);
		return destpath;
	}
}