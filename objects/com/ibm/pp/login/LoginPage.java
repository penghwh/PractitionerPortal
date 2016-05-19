package com.ibm.pp.login;

import org.openqa.selenium.By;

import com.bailian.ta.action.FrameAction;
import com.bailian.ta.base.TestBase;

public class LoginPage extends TestBase {
	
	public LoginPage(FrameAction action){
		this.action = action;
	}
	
	public void doLogin(String userName, String psw) {
		action.sendKeys(By.xpath("//input[@name='username']"), userName, "Enter Username:" + userName);
		action.sendKeys(By.xpath("//input[@name='password']"), psw, "Enter password:" + psw);
		action.submit(By.xpath("//button[@type='submit']"), "Click on Sign in button");
	}

}
