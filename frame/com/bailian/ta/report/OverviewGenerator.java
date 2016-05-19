package com.bailian.ta.report;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.bailian.ta.report.model.CaseModel;
import com.bailian.ta.report.model.OverviewModel;
import com.bailian.ta.report.model.TestResult;
import com.bailian.ta.report.model.TestStatus;
import com.bailian.ta.util.TimeUtil;

public class OverviewGenerator {
	
	private List<CaseModel> caseList = new ArrayList<CaseModel>();
	
	public OverviewGenerator(List<CaseModel> cases){
		this.caseList = cases;
	}
	
	public OverviewModel getOverview(){
		OverviewModel overview = new OverviewModel();
		overview.setTime(TimeUtil.getDate("yyyy-MM-dd HH:mm:ss"));
		overview.setCaseNum(Integer.toString(getCaseNum()));
		overview.setCasePassNum(Integer.toString(getCasePassNum()));
		overview.setCaseFailNum(Integer.toString(getCaseFailNum()));
		overview.setCaseSkipNum(Integer.toString(getCaseSkipNum()));
		overview.setCasePassRate(getRate(getCasePassNum(), getCaseNum()));
		overview.setStatus(getStatus(getCasePassNum(), getCaseNum()));
		return overview;
	}
	
	private int getCaseNum(){
		return caseList.size();
	}
	
	private int getCasePassNum(){
		int rs = 0;
		for(int i = 0; i < caseList.size(); i++){
			if(caseList.get(i).getResult().equals(TestResult.Passed)){
				rs += 1;
			}
		}
		return rs;
	}
	
	private int getCaseFailNum(){
		int rs = 0;
		for(int i = 0; i < caseList.size(); i++){
			if(caseList.get(i).getResult().equals(TestResult.Failed)){
				rs += 1;
			}
		}
		return rs;
	}
	
	private int getCaseSkipNum(){
		int rs = 0;
		for(int i = 0; i < caseList.size(); i++){
			if(caseList.get(i).getResult().equals(TestResult.Skipped)){
				rs += 1;
			}
		}
		return rs;
	}
	
	private TestStatus getStatus(int n, int m){
		double percent = (double)n / (double)m;
		if(percent >= 1){
			return TestStatus.Prefect;
		}
		else if(percent >= 0.9 && percent <= 1){
			return TestStatus.Good;
		}
		else{
			return TestStatus.Bad;
		}
	}
	
	private String getRate(int n, int m){
		double percent = (double)n /(double)m;
		NumberFormat nt = NumberFormat.getPercentInstance();
		nt.setMinimumFractionDigits(2);
		return nt.format(percent);
	}
	
	private int getStepNum(){
		int rs = 0;
		for(int i = 0; i < caseList.size(); i ++){
			rs += caseList.get(i).getSteps().size();
		}
		return rs;
	}
	
	public int getRowNum(){
		int rs = 0;
		rs = getStepNum() + getCaseNum() * 2 ;
		return rs;
	}

}
