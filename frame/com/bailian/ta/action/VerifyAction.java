package com.bailian.ta.action;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.log.LogUtil;
import com.bailian.ta.util.SysUtil;

public class VerifyAction {
	
	private WebDriver driver;
	private static int RETRY_TIME;
	private static int RETRY_WAIT;
	
	public VerifyAction(WebDriver d){
		this.driver = d;
		RETRY_TIME = Integer.parseInt(FrameConfig.getInstance().getConfig("RetryTime"));
		RETRY_WAIT = Integer.parseInt(FrameConfig.getInstance().getConfig("RetryWait"));
	}
	
	public boolean verifyLoad(By by) throws Exception{
		boolean val = false;
		Finder finder = new Finder(driver);
		if(finder.finds(by).size() > 0){
			val = true;
		}
		else{
			val = false;
			throw new Exception();
		}
		return val;
	}
	
	public boolean verifyDisappear(By by) throws Exception{
		boolean val = false;
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				if(driver.findElements(by).size() > 0){
					val = false;
					LogUtil.warn("WebElement Existed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
					SysUtil.sleep(RETRY_WAIT);
				}
				else{
					val = true;
				}
			}
			catch(Exception e){
				val = true;
			}
		}
		if(val == false){
			throw new Exception();
		}
		return val;
	}

}
