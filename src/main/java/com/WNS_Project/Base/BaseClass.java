package com.WNS_Project.Base;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.WNS_Project.Utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected static final Logger logger = Logger.getLogger(BaseClass.class);

	ReadConfig readconfig = new ReadConfig();

	public String baseurl;
	public String workermanage = readconfig.getWorkerNodeManageURL();
	public String email = readconfig.getEmail();
	public String password = readconfig.getPassword();
	public String nodename = readconfig.getNodeName();
	public String importedkey = readconfig.getMnemonicKey();
	public String solution_whitelist = readconfig.getsolutionwhitelist();

	public static WebDriver driver;

	public void logInfo(String message) {
		logger.info(message);
	}

	public void logError(String message, Throwable throwable) {
		logger.error(message, throwable);
	}

	@BeforeClass
	public void setup() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

		ChromeOptions options = new ChromeOptions();

		// Create a map to store Chrome preferences
		Map<String, Object> prefs = new HashMap<>();

		String downloadPath = System.getProperty("user.dir") + "\\downloadedFiles";
		prefs.put("download.default_directory", downloadPath);

		// Disable download prompt (so file is downloaded automatically)
		prefs.put("download.prompt_for_download", false);

		// Apply preferences to ChromeOptions
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--headless"); // Run in headless mode
		options.addArguments("--no-sandbox"); // Disable sandbox for CI environments
		options.addArguments("--disable-dev-shm-usage"); // Avoid issues with shared memory
		options.addArguments("--remote-debugging-port=9222"); // Enable remote debugging

		// Initialize WebDriver with the configured ChromeOptions
		driver = new ChromeDriver(options);
		baseurl = readconfig.getApplicationURL();
		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(5000);
		driver.navigate().refresh();
		((JavascriptExecutor) driver).executeScript("window.open()");
		Thread.sleep(5000);
		String mainWindow = driver.getWindowHandle();

		Set<String> handles = driver.getWindowHandles();
		List<String> handleList = new ArrayList<>(handles);
		driver.navigate().refresh();
		// Switch to new window
		for (String handle : handleList) {
			if (!handle.equals(mainWindow)) {
				driver.switchTo().window(handle);
				driver.get(baseurl);
				driver.manage().window().maximize();
				System.out.println("BASE URL ----" + baseurl);

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
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(togglebutton));
		togglebutton.click();
	}

	// common method to login for WNS
	public void WorkerManageScreen() throws InterruptedException {
		System.out.println("Login Started");
		Username(email);
		Password(password);
		Thread.sleep(5000);

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("grecaptcha-badge")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("var badge = document.getElementsByClassName('grecaptcha-badge'); "
				+ "if (badge.length > 0) { badge[0].remove(); }");

		Thread.sleep(3000);
		Submit();
		System.out.println("Login Completed");
		Thread.sleep(10000);
		System.out.println("Welcome to Dashboard");
		Toggle();
		System.out.println("In VaaS Environment");
		Thread.sleep(10000);
		driver.navigate().to(workermanage);
	}

	// method to capture screenshot
	public String getScreenshotPath(String TestCaseName) throws IOException {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		String timestamp = now.format(formatter);

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destpath = System.getProperty("user.dir") + "\\report-output\\" + TestCaseName + ".png";

		File file = new File(destpath);
		FileUtils.copyFile(source, file);

		// Validate screenshot directory
		if (destpath == null || destpath.isEmpty()) {
			throw new IllegalStateException("Screenshot directory is not defined.");
		}
		return destpath;
	}
}