package com.WNS_Project.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.ITestResult;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.WNS_Project.Utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected static final Logger logger = Logger.getLogger(BaseClass.class);

	ReadConfig readconfig = new ReadConfig();

	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	public String baseurl;
	public String workermanage = readconfig.getWorkerNodeManageURL();
	public String email = readconfig.getEmail();
	public String password = readconfig.getPassword();
	public String nodename = readconfig.getNodeName();
	public String importedkey = readconfig.getMnemonicKey();
	public String solution_whitelist = readconfig.getsolutionwhitelist();

	public static WebDriver driver;

	@BeforeClass
	public void setup() throws InterruptedException {

		WebDriverManager.firefoxdriver().setup();

		FirefoxOptions options = new FirefoxOptions();
		// options.addArguments("--headless");

		// Create a map to store Chrome preferences
		Map<String, Object> prefs = new HashMap<>();

		String downloadPath = System.getProperty("user.dir") + "\\downloadedFiles";
		prefs.put("download.default_directory", downloadPath);

		// Disable download prompt (so file is downloaded automatically)
		prefs.put("download.prompt_for_download", false);

		// Apply preferences to ChromeOptions
		//options.setExperimentalOption("prefs", prefs);
		options.addArguments("--headless"); 
		//options.addArguments("--no-sandbox");
		//options.addArguments("--disable-dev-shm-usage"); 
		//options.addArguments("--remote-debugging-port=9222");

		// Initialize WebDriver with the configured ChromeOptions
		driver = new FirefoxDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 100);
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("location.reload(true);");
		driver.navigate().refresh();
		Username(email);
		Password(password);
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("grecaptcha-badge")));
		js.executeScript("var badge = document.getElementsByClassName('grecaptcha-badge'); "
				+ "if (badge.length > 0) { badge[0].remove(); }");

		Submit();
		System.out.println("Login Completed");
		Thread.sleep(5000);
		Toggle();
		Thread.sleep(2000);
		System.out.println("Welcome to VaaS Platform");
		Thread.sleep(5000);
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

	public static FileInputStream getScreenshotAsFileInputStream() throws IOException {
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File src = ts1.getScreenshotAs(OutputType.FILE);
		return new FileInputStream(src);
	}

	// Method to determine shift based on the time of day
	protected String getShift() {
		int hour = new Date().getHours();
		if (hour >= 6 && hour < 14) {
			return "Morning Shift at " + getTimestamp();
		} else {
			return "Evening Shift at " + getTimestamp();
		}
	}

	// Method to get the current timestamp in a readable format
	protected String getTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date()); // Format the current date and time
	}
}