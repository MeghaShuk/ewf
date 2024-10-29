package com.WNS_Project.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.WNS_Project.Base.BaseClass;

public class Download_Summary extends BaseClass {

	Actions action = new Actions(driver);
	WebDriverWait wait = new WebDriverWait(driver, 120);

	public Download_Summary(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//button/img[@alt=\"download\"])[1]")
	WebElement download;

	public void Download_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(download));
		download.click();
	}
}