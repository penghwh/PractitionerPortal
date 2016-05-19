package com.bailian.ta.action;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.log.LogUtil;
import com.bailian.ta.util.SysUtil;

public class Finder {
	private WebDriver driver;
	private static int RETRY_TIME;
	private static int RETRY_WAIT;
	
	public Finder(WebDriver d){
		this.driver = d;
		RETRY_TIME = Integer.parseInt(FrameConfig.getInstance().getConfig("RetryTime"));
		RETRY_WAIT = Integer.parseInt(FrameConfig.getInstance().getConfig("RetryWait"));
	}
	
	public boolean isExist(By by) {
		WebElement we = null;
		try {
			we = driver.findElement(by);
		} catch (Exception e) {
			return false;
		}
		return we != null;
	}

	public WebElement find(By by){
		WebElement element = null;
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				element = driver.findElement(by);
				return element;
			}
			catch(NoSuchElementException ne){
				LogUtil.warn("No WebElement Find " + by.toString() + ", Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw ne;
				}
			}
			catch(UnhandledAlertException ue){
				LogUtil.warn("Unhandled Alert Find" + by.toString() + ", Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw ue;
				}
			}
		}
		return element;
	}
	
	public WebElement find(By by, int index){
		WebElement element = null;
		List<WebElement> elements = null;
		elements = finds(by);
		try{
			element = elements.get(index);
			return element;
		}
		catch(Exception e){
			throw e;
		}
	}
	
	
	public List<WebElement> finds(By by){
		List<WebElement> elements = null;
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				elements = driver.findElements(by);
				return elements;
			}
			catch(NoSuchElementException ne){
				LogUtil.warn("No WebElements Find " + by.toString() + ", Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw ne;
				}
			}
			catch(UnhandledAlertException ue){
				LogUtil.warn("Unhandled Alert Find " + by.toString() + ", Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw ue;
				}
			}
		}
		return elements;
	}
}
