package com.WNS_Project.pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.WNS_Project.Base.BaseClass;

public class Deploy_WNSNode extends BaseClass {

	Actions action = new Actions(driver);
	WebDriverWait wait = new WebDriverWait(driver, 150);

	public Deploy_WNSNode(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//../../div/div[2]/div[1]/div[2]/img[@alt=\"AWS Logo\"]/../../../../button")
	WebElement deploy_button_aws;

	@FindBy(xpath = "//../../div/div[2]/div[1]/div[2]/img[@alt=\"zeeve-managed Logo\"]/../../../../button")
	WebElement deploy_button_managed;

	@FindBy(xpath = "//input[@name=\"name\"]")
	WebElement nodename;

	@FindBy(xpath = "//div/input[@id=\"react-select-:rl:-input\"]")
	WebElement aws_region;

	@FindBy(xpath = "//div/input[@id=\"react-select-:rj:-input\"]")
	WebElement managed_region;

	@FindBy(xpath = "//div/input[@id=\"react-select-:rj:-input\"]")
	WebElement instancetype;

	@FindBy(xpath = "//div/input[@id=\"react-select-:rn:-input\"]")
	WebElement cloudaccount;

	@FindBy(xpath = "//div/button[contains(text(), \"Continue\")]")
	WebElement continuebutton;

	@FindBy(xpath = "//div[@class=\"flex flex-row gap-2 lg:gap-4\"]/label[1]/div[1]")
	WebElement public_gateway;

	@FindBy(xpath = "//div[@class=\"flex flex-row gap-2 lg:gap-4\"]/label[1]/div[1]")
	WebElement generate_key;

	@FindBy(xpath = "//div[@id=\"modal-:r8:\"]/div[2]/button[contains(text(),\"Continue\")]")
	WebElement popup_continue;

	@FindBy(xpath = "//div[@id=\"modal-:r8:\"]/div[2]/button[contains(text(),\"Cancel\")]")
	WebElement popup_cancel;

	public void AWS_Deploy_button() {
		wait.until(ExpectedConditions.elementToBeClickable(deploy_button_aws));
		deploy_button_aws.click();
	}

	public void Managed_Deploy_button() {
		wait.until(ExpectedConditions.elementToBeClickable(deploy_button_managed));
		deploy_button_managed.click();
	}

	public void Node_Name(String name) {
		wait.until(ExpectedConditions.elementToBeClickable(nodename));
		nodename.sendKeys(name);
	}

	public void ChooseAWSRegion() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(aws_region));
		for (int i = 0; i < 3; i++) {
			action.moveToElement(aws_region).click();
			Thread.sleep(2000);
			aws_region.sendKeys(Keys.ARROW_DOWN);
		}
		aws_region.sendKeys(Keys.ENTER);
	}

	public void ChooseManagedRegion() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(managed_region));
		for (int i = 0; i < 3; i++) {
			action.moveToElement(managed_region).click();
			Thread.sleep(2000);
			managed_region.sendKeys(Keys.ARROW_DOWN);
		}
		managed_region.sendKeys(Keys.ENTER);
	}

	public void ChooseInstance() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(instancetype));
		for (int i = 0; i < 3; i++) {
			action.moveToElement(instancetype).click();
			Thread.sleep(2000);
			instancetype.sendKeys(Keys.ARROW_DOWN);
		}
		instancetype.sendKeys(Keys.ENTER);
	}

	public void ChooseCloudAccount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(cloudaccount));
		for (int i = 0; i < 1; i++) {
			action.moveToElement(cloudaccount).click();
			Thread.sleep(2000);
			cloudaccount.sendKeys(Keys.ARROW_DOWN);
		}
		cloudaccount.sendKeys(Keys.ENTER);
	}

	public void Continue_button() {
		wait.until(ExpectedConditions.elementToBeClickable(continuebutton));
		continuebutton.click();
	}

	public void Public_Gateway() {
		public_gateway.click();
	}

	public void Generate_Key() {
		generate_key.click();
	}

	public void Popup_Continue() {
		wait.until(ExpectedConditions.elementToBeClickable(popup_continue));
		popup_continue.click();
	}

	public void Popup_Cancel() {
		popup_cancel.click();
	}
}