package com.WNS_Project.testCases;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.pageObject.Import_Key;

public class TC_05_Import_Key extends BaseClass {

	@Test(priority = 1)
	public void Status() throws IOException, InterruptedException, AWTException {

		TC_02_Status_Check test2 = new TC_02_Status_Check();
		test2.Status();
	}

	@Test(priority = 2)
	public void ImportKey() throws IOException, InterruptedException, AWTException {

		Import_Key importkey = new Import_Key(driver);
		importkey.Import_Button();
		importkey.Key_Input(importedkey);
		importkey.Save_Button();
	}
}