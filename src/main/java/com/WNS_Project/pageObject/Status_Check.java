package com.WNS_Project.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.WNS_Project.Base.BaseClass;

public class Status_Check extends BaseClass {

	WebDriverWait wait = new WebDriverWait(driver, 120);

	public Status_Check(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "/html/body/div[2]/div/header/div[2]/div[1]/label/div")
	WebElement togglebutton;
	
	@FindBy(xpath = "//td[4]/div/a/button")
	WebElement view_button;

	public void Toggle_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(togglebutton));
		togglebutton.click();
	}
	
	public void View_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(view_button));
		view_button.click();
	}
}