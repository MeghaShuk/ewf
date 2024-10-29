package com.WNS_Project.testCases;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.pageObject.Download_Summary;

public class TC_03_Download_Summary extends BaseClass {

	@Test(priority = 1)
	public void Status() throws IOException, InterruptedException, AWTException {

		TC_02_Status_Check test2 = new TC_02_Status_Check();
		test2.Status();
	}

	@Test(priority = 2)
	public void Download() throws IOException, InterruptedException, AWTException {

		Download_Summary download = new Download_Summary(driver);
		download.Download_Button();
	}
}