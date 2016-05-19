package com.bailian.ta.driver;

import org.openqa.selenium.WebDriver;

import com.bailian.ta.log.LogUtil;

public class DriverSelector {
	
	public WebDriver setDriver(BrowserType browserType) {
		BrowserDriver browser = new BrowserDriver();
		WebDriver driver = null;
		try{
			switch (browserType) {
			case CHROME:
				driver = browser.chromeDriver();
				LogUtil.frameLog("Launch Chrome Browser Complete");
				return driver;
			case FIREFOX:
				driver = browser.fireFoxDriver();
				LogUtil.frameLog("Launch Firefox Browser Complete");
				return driver;
			case SAFARI:
				driver = browser.safariDriver();
				LogUtil.frameLog("Launch Browser Complete");
				return driver;
			case IE:
				driver = browser.ieDriver();
				LogUtil.frameLog("Launch Browser Complete");
				return driver;
			default:
				LogUtil.frameLog("Please Check the Type of Browser Driver");
				return driver;
			}
		}
		catch(Exception e){
			LogUtil.error("Launch Browser Failed\n");
		//	e.printStackTrace();
			return driver;
		}
	}

}
