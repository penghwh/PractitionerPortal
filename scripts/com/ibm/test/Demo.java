package com.ibm.test;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.bailian.ta.action.FrameAssertion;
import com.bailian.ta.base.TestBase;
import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.driver.BrowserType;
//import com.ibm.pp.login.LoginPage;

public class Demo extends TestBase  {
//	private LoginPage login;
	public String userName;
	public String password;
	
	@BeforeClass
	public void setUp() {
		setBrowser(System.getProperty("testing_browser") == null ? BrowserType.FIREFOX
				: BrowserType.getType(System.getProperty("testing_browser")));
		data.loadData("testdata.xlsx", "demo");
		userName = data.getData("login", "username");
		password = data.getData("login", "password");
		initPage();
	}
	
	private void initPage(){
	//	login = new LoginPage(action);
	}

/*	@Test
	public void loginTest(){
		String url = FrameConfig.getInstance().getConfig("URL");
		action.open(url, "Login web application");
		login.doLogin(userName, password);
		String wlc_text = action.getText(By.xpath(".//*[@id='welcome']/h3[1]"), "Get Welcome message");
		FrameAssertion.contains(wlc_text, "Welcome", "Verify welcome message");
	}
	
	@Test(dependsOnMethods = {"loginTest"})
	public void selectSeats(){
		action.click(By.xpath("//a[contains(text(),'Information')]"), "Click on 'My information' link");
		String info_text = action.getText(By.xpath(".//*[@id='accountdetails']/p[1]"), "Get name");
		FrameAssertion.contains(info_text, userName, "Verify name");
		FrameAssertion.contains(info_text, userName, "Verify name");
	}*/
	
	
	@Test
	public void signUpTest(){
		
		String url = FrameConfig.getInstance().getConfig("URL");
		action.open(url, "Login web application");
		action.sendKeys(By.id("FirstName"), "testFirst", "Inpurt first name");
		action.sendKeys(By.id("LastName"), "testLast", "Inpurt last name");
		action.sendKeys(By.id("Company"), "IBM", "Inpurt Company name ");
		action.selectText(By.id("Mobile_Testing_Role__c"), "Development", "Select Role");
		
		
		
		
		
	}


}
