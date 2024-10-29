package com.WNS_Project.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.WNS_Project.Base.BaseClass;

public class Generate_NewKey extends BaseClass {

	Actions action = new Actions(driver);
	WebDriverWait wait = new WebDriverWait(driver, 120);

	public Generate_NewKey(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button/span[contains(text(), \"Create new\")]")
	WebElement create_button;

	@FindBy(xpath = "//button[contains(text(), \"Confirm\")]")
	WebElement confirm;

	@FindBy(xpath = "//button[contains(text(), \"Cancel\")]")
	WebElement cancel;

	public void Create_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(create_button));
		create_button.click();
	}

	public void Confirm_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(confirm));
		confirm.click();
	}

	public void Cancel_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(cancel));
		cancel.click();
	}
}