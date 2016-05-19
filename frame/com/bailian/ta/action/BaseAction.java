package com.bailian.ta.action;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.log.LogUtil;
import com.bailian.ta.util.SysUtil;

public class BaseAction {
	
	private static int RETRY_TIME;
	private static int RETRY_WAIT;
	
	public BaseAction(){
		RETRY_TIME = Integer.parseInt(FrameConfig.getInstance().getConfig("RetryTime"));
		RETRY_WAIT = Integer.parseInt(FrameConfig.getInstance().getConfig("RetryWait"));
	}
	
	
	public List<String> getSelectOptions(WebElement we){
		List<String> val = new ArrayList<String>();
		List<WebElement> eleList;
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				Select select = new Select(we);
				eleList = select.getOptions();
				for(int j = 0; j < eleList.size(); j++){
					val.add(eleList.get(j).getText());
				}
				break;
			}
			catch(Exception e){
				LogUtil.warn("Select value Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return val;
	}
	
	public void selectValue(WebElement we, String value){
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				Select select = new Select(we);
				select.deselectByValue(value);
				break;
			}
			catch(Exception e){
				LogUtil.warn("Select value Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
	}
	
	
	public void selectIndex(WebElement we, int index){
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				Select select = new Select(we);
				select.selectByIndex(index);
				break;
			}
			catch(Exception e){
				LogUtil.warn("Select index Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
	}
	
	public void selectText(WebElement we, String text){
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				Select select = new Select(we);
				select.selectByVisibleText(text);
				break;
			}
			catch(Exception e){
				LogUtil.warn("Select text Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
	}
	
	
	public void sendkeys(WebElement we, String key){
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				we.sendKeys(key);
				break;
			}
			catch(Exception e){
				LogUtil.warn("Sendkeys to WebElement Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
	}
	
	public void click(WebElement we){
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				we.click();
				break;
			}
			catch(Exception e){
				LogUtil.warn("Click WebElement Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
	}
	
	public void clear(WebElement we){
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				we.clear();
				break;
			}
			catch(Exception e){
				LogUtil.warn("Clear WebElement Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
	}
	
	public String getAttribute(WebElement we, String attribute){
		String val = "";
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				val = we.getAttribute(attribute);
				break;
			}
			catch(Exception e){
				LogUtil.warn("Get Attribute Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return val;
	}
	
	public String getCssVaule(WebElement we, String css){
		String val = "";
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				val = we.getCssValue(css);
				break;
			}
			catch(Exception e){
				LogUtil.warn("Get Css Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return val;
	}
	
	public String getTagName(WebElement we){
		String val = "";
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				val = we.getTagName();
				break;
			}
			catch(Exception e){
				LogUtil.warn("Get Tag Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return val;
	}
	
	public String getText(WebElement we){
		String val = "";
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				val = we.getText();
				break;
			}
			catch(Exception e){
				LogUtil.warn("Get Text Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return val;
	}
	
	public boolean isDisplayed(WebElement we){
		boolean val = false;
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				val = we.isDisplayed();
				break;
			}
			catch(Exception e){
				LogUtil.warn("Get Display state Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return val;
	}
	
	public boolean isSelected(WebElement we){
		boolean val = false;
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				val = we.isSelected();
				break;
			}
			catch(Exception e){
				LogUtil.warn("Get Select state Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return val;
	}
	
	public boolean isEnabled(WebElement we){
		boolean val = false;
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				val = we.isEnabled();
				break;
			}
			catch(Exception e){
				LogUtil.warn("Get Enabled state Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return val;
	}
	
	public void submit(WebElement we){
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				we.submit();
				break;
			}
			catch(Exception e){
				LogUtil.warn("Submit WebElement Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
	}
	
	public int getXLoctaion(WebElement we){
		return we.getLocation().getX();
	}
	
	public int getYLocation(WebElement we){
		return we.getLocation().getY();
	}
	
	public int getHeight(WebElement we){
		return we.getSize().getHeight();
	}
	
	public int getWidth(WebElement we){
		return we.getSize().getWidth();
	}
	
}
