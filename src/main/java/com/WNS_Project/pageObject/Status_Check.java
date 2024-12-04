package com.WNS_Project.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	@FindBy(xpath = "//button/img[@alt=\"download\"]")
	WebElement download;

	@FindBy(xpath = "//div[text()=\"Seed phrase\"]/../div[2]/div/following-sibling::*")
	WebElement seed_phrase_click;

	@FindBy(xpath = "//button/span[contains(text(), \"Create new\")]")
	WebElement create_button;

	@FindBy(xpath = "//button[contains(text(), \"Confirm\")]")
	WebElement confirm;

	@FindBy(xpath = "//button[contains(text(), \"Cancel\")]")
	WebElement cancel;

	@FindBy(xpath = "//button/span[contains(text(),\"Import new\")]")
	WebElement import_button;

	@FindBy(xpath = "//input[@type=\"text\"]")
	WebElement key_input;

	@FindBy(xpath = "//div[@class=\"flex items-center gap-4 px-6 py-4 justify-end\"]/button[contains(text(), \"Save\")]")
	WebElement save;

	@FindBy(xpath = "//button[contains(text(),\"Logs\")]")
	WebElement logs_tab;

	@FindBy(xpath = "//button[contains(text(), \"Solution Whitelist\")]")
	WebElement sw;

	@FindBy(xpath = "(//div[@class=\"relative flex h-max w-full\"]/input)[1]")
	WebElement solution_name;

	@FindBy(xpath = "//div[@id=\"accordion-0-content\"]/div[1]/div[1]/div/div")
	WebElement remove_solution;

	@FindBy(xpath = "//div[@class=\"mt-auto flex justify-end\"]/button[contains(text(),\"Save\")]")
	WebElement save_sol;

	@FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div[1]/div/button")
	WebElement delete;

	@FindBy(xpath = "//button[contains(text(), \"Confirm\")]")
	WebElement confirm_del;

	public void Toggle_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(togglebutton));
		togglebutton.click();
	}

	public void View_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(view_button));
		view_button.click();
	}

	public void Download_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(download));
		download.click();
	}

	public void SeedPhrase_Button() throws InterruptedException {
		// wait.until(ExpectedConditions.visibilityOf(seed_phrase_click));
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(seed_phrase_click).click();
	}

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

	public void LogTab() {
		wait.until(ExpectedConditions.elementToBeClickable(logs_tab));
		logs_tab.click();
	}

	public void SW() {
		wait.until(ExpectedConditions.elementToBeClickable(sw));
		sw.click();
	}

	public void SolutionName(String name) {
		solution_name.sendKeys(name);
	}

	public void Sol_Save_Button() {
		wait.until(ExpectedConditions.elementToBeClickable(save_sol));
		save_sol.click();
	}

	public void Remove_Sol() {
		wait.until(ExpectedConditions.elementToBeClickable(remove_solution));
		remove_solution.click();
	}

	public void Delete() {
		wait.until(ExpectedConditions.elementToBeClickable(delete));
		delete.click();
	}

	public void Confirm_Del() {
		wait.until(ExpectedConditions.elementToBeClickable(confirm_del));
		confirm_del.click();
	}
}