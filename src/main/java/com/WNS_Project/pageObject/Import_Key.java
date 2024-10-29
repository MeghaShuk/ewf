package com.WNS_Project.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.WNS_Project.Base.BaseClass;

public class Import_Key extends BaseClass {

	Actions action = new Actions(driver);
	WebDriverWait wait = new WebDriverWait(driver, 120);

	public Import_Key(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button/span[contains(text(),\"Import new\")]")
	WebElement import_button;

	@FindBy(xpath = "//input[@id=\"input-:r16:\"]")
	WebElement key_input;

	@FindBy(xpath = "//div[@class=\"flex items-center gap-4 px-6 py-4 justify-end\"]/button[contains(text(), \"Save\")]")
	WebElement save;

	public void Import_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(import_button));
		import_button.click();
	}

	public void Key_Input(String key) {
		wait.until(ExpectedConditions.elementToBeClickable(key_input));
		key_input.sendKeys(key);
	}

	public void Save_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(save));
		save.click();
	}
}