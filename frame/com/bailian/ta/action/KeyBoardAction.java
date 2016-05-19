package com.bailian.ta.action;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class KeyBoardAction {
	
	private WebDriver driver;
	private Actions keyAction;
	
	public KeyBoardAction(WebDriver driver){
		this.driver = driver;
		this.keyAction = new Actions(this.driver);
	}
	
	public void pressKeyEnter(){
		try{
			keyAction.sendKeys(Keys.RETURN).perform();
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public void pressKeyEnter(WebElement we){
		try{
			keyAction.sendKeys(we, Keys.RETURN).perform();
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public void presskeySpace(){
		keyAction.sendKeys(Keys.SPACE).perform();
	}
	
	public void pressKeyBackSpace(){
		keyAction.sendKeys(Keys.BACK_SPACE).perform();
	}
	
	
	
	

}
