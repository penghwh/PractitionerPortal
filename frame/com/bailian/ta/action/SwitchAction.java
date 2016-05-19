package com.bailian.ta.action;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bailian.ta.log.LogUtil;


public class SwitchAction {
	private WebDriver driver;
	
	public SwitchAction(WebDriver d){
		this.driver = d;
	}
	
	public void frameSwitchDefault(){
		try{
			driver.switchTo().defaultContent();
		}
		catch(Exception e){
			throw e;
		}
		
	}
	
	public void frameSwitch(By by){
		try{
			driver.switchTo().frame(driver.findElement(by));
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public void frameSwitchParent(){
		try{
			driver.switchTo().parentFrame();
		}
		catch(Exception e){
			throw e;
		}
	}

	public void pageSwitchByHandle(String description) {
		try {
			String winHandleBefore = driver.getWindowHandle();
			Set<String> winHandles = driver.getWindowHandles();
			Iterator<String> it = winHandles.iterator();
			while (it.hasNext()) {
				String win = it.next();
				if (!win.equals(winHandleBefore)) {
					driver.switchTo().window(win);
					LogUtil.step("Switch Window From " + winHandleBefore + " to " + win, description);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
