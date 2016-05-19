package com.bailian.ta.base;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.bailian.ta.action.FrameAction;
import com.bailian.ta.action.FrameAssertion;
import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.data.TestData;
import com.bailian.ta.driver.BrowserType;
import com.bailian.ta.driver.DriverSelector;
import com.bailian.ta.log.FrameLog;
import com.bailian.ta.log.LogUtil;
import com.bailian.ta.precheck.PreCheck;
import com.bailian.ta.report.FrameReporter;
import com.bailian.ta.report.excel.ExcelReportGenerator;
import com.bailian.ta.report.html.HtmlReportGenerator;
import com.bailian.ta.report.model.CaseModel;

@Listeners(FrameListener.class)
public class TestBase {
	
	protected WebDriver driver;
	protected FrameAction action;
	protected TestData data;
	protected FrameAssertion assertion;
	
	protected TestBase(){
		this.data = new TestData();
	}
	
	public void setBrowser() {
		setBrowser(BrowserType.FIREFOX);
	}
	
	public void setBrowser(BrowserType browserType) {
		this.driver = new DriverSelector().setDriver(browserType);
		this.action = new FrameAction(this.driver);
		this.assertion = new FrameAssertion();
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	//Env selection for Jenkins
	public String getEnvURL(){
		String url = null;
		if (System.getProperty("env")!= null && System.getProperty("env").equalsIgnoreCase("PRE"))
			url = FrameConfig.getInstance().getConfig("PRE_OSP_URL");
		else 
			url = FrameConfig.getInstance().getConfig("OSP_URL");
		return url;
	}
	
	public HashMap<String,String> getEnvData(HashMap<String,String> test,HashMap<String,String> pre){
		if (System.getProperty("env")!= null && System.getProperty("env").equalsIgnoreCase("PRE"))
			return pre;
		else
			return test;
	}
	
	@BeforeTest
	public void bTest(){
		PreCheck.getInstance().preCheck();
	}
	
	@BeforeClass
	public void bClass(){
		LogUtil.info("-------Start to Run Class-----------");
	}
	
	@AfterClass
	public void aClass(){
		LogUtil.info("-------Finished Test Class-----------");
		if (action != null)
			action.tearDown("-------Finished Test Class-----------");
	}
	
	@AfterTest
	public void aTest(){
		List<CaseModel> tc = FrameReporter.getInstance().getTestCases();
		if (tc != null && !tc.isEmpty()) {
			new ExcelReportGenerator(tc).createExcelReport();
			new HtmlReportGenerator(tc).createHtmlReport();
			new FrameLog().createLogFile(LogUtil.loglist);
		}
	}
}
