package com.WNS_Project.testCases;

import java.io.File;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.WNS_Project.Base.BaseClass;
import com.WNS_Project.Utilities.ScreenRecorderUtil;
import com.WNS_Project.pageObject.Status_Check;

public class TC_03_Download_Summary extends BaseClass {

	@Test
	public void DownloadSummary() throws Exception {

		Status_Check status = new Status_Check(driver);

		ScreenRecorderUtil.startRecord("Download Summary");

		WorkerManageScreen();
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		status.View_Button();
		Thread.sleep(8000);
		status.Download_Button();
		Thread.sleep(2000);
		 String downloadDir = "C:\\Users\\Megha Shukla\\Downloads\\"; 
		 
		 File downloadedFile = waitForDownload(downloadDir);

         // Rename the file once it is detected
         if (downloadedFile != null) {
             File renamedFile = new File(downloadDir + "worker_node_download.json");
             boolean renamed = downloadedFile.renameTo(renamedFile);
             if (renamed) {
                 System.out.println("File renamed successfully.");
             } else {
                 System.out.println("File renaming failed.");
             }
         } else {
             System.out.println("Downloaded file not found.");
         }
	}
	
    public static File waitForDownload(String downloadDir) {
        File dir = new File(downloadDir);
        File[] files = dir.listFiles();
        File latestFile = null;

        if (files != null && files.length > 0) {
            long lastModified = Long.MIN_VALUE;

            for (File file : files) {
                if (file.isFile() && file.lastModified() > lastModified) {
                    lastModified = file.lastModified();
                    latestFile = file;
                }
            }
        }

        // Return the most recent file
        return latestFile;
    }

	@AfterClass
	public void delayAfterTests() throws Exception {
		try {
			System.out.println("Adding a 10-seconds delay before running the next test class...");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ScreenRecorderUtil.stopRecord();
	}
}