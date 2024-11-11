package com.WNS_Project.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro = new Properties();;

	public ReadConfig() {

		pro = new Properties();
        // Retrieve the file path from the system property (set in pom.xml or as an environment variable)
        String configFilePath = System.getProperty("configFile", "src/main/resources/Configuration/config.properties");
        
        try (FileInputStream input = new FileInputStream(configFilePath)) {
            pro.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	// Application URL
	public String getApplicationURL() {
		String url = System.getenv("baseURL");
		return url;
	}

	// Worker Node Manage URL
	public String getWorkerNodeManageURL() {
		String url = System.getenv("workernode_manage");
		return url;
	}

	// Login Email
	public String getEmail() {
		String Email = System.getenv("login_email");
		return Email;
	}

	// Login Password
	public String getPassword() {
		String Password = System.getenv("login_password");
		return Password;
	}

	// Node Name
	public String getNodeName() {
		String name = System.getenv("NodeName");
		return name;
	}

	public String getMnemonicKey() {
		String key = System.getenv("Mnemonic");
		return key;
	}

	public String getsolutionwhitelist() {
		String sol = System.getenv("solution");
		return sol;
	}
}