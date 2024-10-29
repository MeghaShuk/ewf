package com.WNS_Project.testCases;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.pageObject.Generate_NewKey;

public class TC_04_Generate_NewKey extends BaseClass {

	@Test(priority = 1)
	public void Status() throws IOException, InterruptedException, AWTException {

		TC_02_Status_Check test2 = new TC_02_Status_Check();
		test2.Status();
	}

	@Test(priority = 2)
	public void Generate_Key() throws IOException, InterruptedException, AWTException {

		Generate_NewKey generate = new Generate_NewKey(driver);
		generate.Create_Button();
		generate.Confirm_Button();
		Thread.sleep(15000);
	}
}