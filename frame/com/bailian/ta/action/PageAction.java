package com.bailian.ta.action;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bailian.ta.log.LogUtil;

public class PageAction {
	private WebDriver driver;
	
	public PageAction(WebDriver d){
		this.driver = d;
	}
	
	public void openPage(String url){
		try{
			driver.navigate().to(url);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public void forward(){
		try{
			driver.navigate().forward();
			LogUtil.step("Forward Page: {Succeed}");
		}
		catch(Exception e){
			LogUtil.error("Forward Page {Failed}");
			throw e;
		}
	}
	
	public void back(){
		try{
			driver.navigate().back();
			LogUtil.step("Back Page: {Succeed}");
		}
		catch(Exception e){
			LogUtil.error("Back Page {Failed}");
			throw e;
		}
	}
	
	public void refresh(){
		try{
			driver.navigate().refresh();
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public String alertGetText() {
		String rs = "";
		try{
			rs = driver.switchTo().alert().getText();
			return rs;
		}
		catch(Exception e){
			throw e;
		}
	}

	public void alertSendkey(String keyword) {
		try{
			driver.switchTo().alert().sendKeys(keyword);
		}
		catch(Exception e){
			throw e;
		}
	}

	public void alertAccept() {
		try{
			driver.switchTo().alert().accept();
		}
		catch(Exception e){
			throw e;
		}
	}

	public void alertCancel() {
		try{
			driver.switchTo().alert().dismiss();
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public void removeReadOnly(WebElement we){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try{
			js.executeScript("arguments[0].removeAttribute('readonly');", we);
		}
		catch(Exception e){
			throw e;
		}
		
	}
	
	public void removeReadOnly(String id) {
		String script = "var setDate = document.getElementById(\"" + id + "\");setDate.removeAttribute('readonly');";
		JavascriptExecutor removeAttribute = (JavascriptExecutor) driver;
		try{
			removeAttribute.executeScript(script);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public void tearDown(){
		try{
			driver.manage().deleteAllCookies();
			driver.quit();
		}
		catch(Exception e){
			throw e;
		}
	}
}
