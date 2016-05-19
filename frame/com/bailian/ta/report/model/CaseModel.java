package com.bailian.ta.report.model;

import java.util.ArrayList;
import java.util.List;


public class CaseModel  implements Cloneable  {
	
	private String caseName;//record the test case's name
	private TestResult result;//record the result of the test case
	private String startTime;//record the time when the test case begin to execute
	private String endTime;// record the time when the test case complete execution
	private String duration;//the duration time
	private List<StepModel> steps;//the steps of the test case
	private String image;//the screen path of the test case
	
	
	/*************************************************************************************************
	 * @author Henry
	 * @date 2015/11/12
	 * 
	 *    The method is the constructor of the class, when call the method, it will instant a new 
	 *    array list, the list type is StepModel.
	 * 
	************************************************************************************************/
	public CaseModel(){
		steps = new ArrayList<StepModel>();
	}
	
	public void copySteps(CaseModel ac){
		for(int i = 0; i < ac.getSteps().size(); i++){
			this.steps.add((StepModel) ac.getSteps().get(i).clone());
		}
	}
	
	@Override 
	public Object clone() {
		Object o = null;
		try {
			o = (CaseModel) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
		return o;
	} 
	
	
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public TestResult getResult() {
		return result;
	}
	public void setResult(TestResult result) {
		this.result = result;
	}
	public List<StepModel> getSteps() {
		return steps;
	}
	public void setSteps(List<StepModel> steps) {
		this.steps = steps;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	

}
