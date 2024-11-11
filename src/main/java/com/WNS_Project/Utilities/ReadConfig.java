package com.WNS_Project.Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {

	private Properties pro = new Properties();

	public ReadConfig() {

		try (InputStream input = getClass().getClassLoader().getResourceAsStream("Configuration/config.properties")) {
			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
				return;
			}
			pro.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
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