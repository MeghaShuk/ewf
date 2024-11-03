package com.WNS_Project.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public ReadConfig() {
		File src = new File("./Configuration/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("The excetption is: " + e.getMessage());
		}
	}

	// Application URL
	public String getApplicationURL() {
		String url = pro.getProperty("baseURL");
		return url;
	}

	// Worker Node Manage URL
	public String getWorkerNodeManageURL() {
		String url = pro.getProperty("workernode_manage");
		return url;
	}

	// Login Email
	public String getEmail() {
		String Email = pro.getProperty("login_email");
		return Email;
	}

	// Login Password
	public String getPassword() {
		String Password = pro.getProperty("login_password");
		return Password;
	}

	// Node Name
	public String getNodeName() {
		String name = pro.getProperty("NodeName");
		return name;
	}

	public String getMnemonicKey() {
		String key = pro.getProperty("Mnemonic");
		return key;
	}

	public String getsolutionwhitelist() {
		String sol = pro.getProperty("solution");
		return sol;
	}
}