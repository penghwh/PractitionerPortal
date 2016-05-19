package com.bailian.ta.report;

import java.util.ArrayList;
import java.util.List;

import com.bailian.ta.report.model.CaseModel;
import com.bailian.ta.report.model.StepModel;
import com.bailian.ta.report.model.TestResult;
import com.bailian.ta.util.TimeUtil;




public class FrameReporter {
	
	private List<CaseModel> caseList = new ArrayList<CaseModel>();	
	private static FrameReporter instance = null;

	public static synchronized FrameReporter getInstance() {
		if (instance == null) {
			instance = new FrameReporter();
		}
		return instance;
	}
	
	public List<CaseModel> getTestCases(){
		return this.caseList;
	}
	
	public void addNewCase(String caseName){
		CaseModel ca = new CaseModel();
		ca.setCaseName(caseName);
		ca.setStartTime(TimeUtil.getTimeMs());
		caseList.add(ca);
	}
	
	public void setCaseFailed(String image){
		getCurrentCase().setResult(TestResult.Failed);
		getCurrentCase().setEndTime(TimeUtil.getTimeMs());
		getCurrentCase().setImage(image);
	}
	
	public void setCasePassed(){
		getCurrentCase().setResult(TestResult.Passed);
		getCurrentCase().setEndTime(TimeUtil.getTimeMs());
	}
	
	public void setCaseSkipped(){
		getCurrentCase().setResult(TestResult.Skipped);
	}
	
	public CaseModel getCurrentCase(){
		CaseModel ncase = new CaseModel();
		if(!caseList.isEmpty()){
			ncase = caseList.get(caseList.size() - 1);
		}
		return ncase;
	}
	
	public void addPassStep(String operation, String detail){
		StepModel st = new StepModel();
		st.setOperation(operation);
		st.setDetail(detail);
		st.setTime(TimeUtil.getTimeMs());
		st.setResult(TestResult.Passed);
		getCurrentCase().getSteps().add(st);
	}
	
	public void addFailStep(String operation, String detail){
		StepModel st = new StepModel();
		st.setOperation(operation);
		st.setDetail(detail);
		st.setTime(TimeUtil.getTimeMs());
		st.setResult(TestResult.Failed);
		getCurrentCase().getSteps().add(st);
	}

}
