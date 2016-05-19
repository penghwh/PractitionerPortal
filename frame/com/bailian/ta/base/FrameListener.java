package com.bailian.ta.base;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.bailian.ta.log.LogUtil;
import com.bailian.ta.report.FrameReporter;
import com.bailian.ta.report.ScreenShot;

public class FrameListener extends TestListenerAdapter {
	
	@Override
	public void onStart(ITestContext testContext) {
		LogUtil.info("----------------------------------------" );
		LogUtil.info("Start to run " + testContext.getName());
		LogUtil.info("----------------------------------------\n");
	}
	
	@Override
	public void onFinish(ITestContext tc){
	//	FrameReporter.getInstance().print();
	}

	@Override
	public void onTestStart(ITestResult tr) {
		LogUtil.startCase(tr.getName());
		FrameReporter.getInstance().addNewCase(tr.getName());
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		LogUtil.endCase(tr.getName(), "Failed");
		TestBase tb = (TestBase) tr.getInstance();
		String img = new ScreenShot().takeScreenShot(tb.getDriver(), tr.getName());
		FrameReporter.getInstance().setCaseFailed(img);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		LogUtil.endCase(tr.getName(), "Skipped");
		FrameReporter.getInstance().addNewCase(tr.getName());
		FrameReporter.getInstance().setCaseSkipped();
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		LogUtil.endCase(tr.getName(), "Passed");
		FrameReporter.getInstance().setCasePassed();
	}

}
