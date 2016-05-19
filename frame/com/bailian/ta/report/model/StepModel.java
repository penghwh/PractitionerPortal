package com.bailian.ta.report.model;

public class StepModel implements Cloneable  {
	
	private String operation;//record the operation information of this step
	private String detail;//record the detail information of this step
	private String time;//record the execute time of this step
	private TestResult result;//record the result

	@Override 
	public Object clone() {
		Object o = null;
		try {
			o = (StepModel) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
		return o;
	} 
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String btime) {
		this.time = btime;
	}
	public TestResult getResult() {
		return result;
	}
	public void setResult(TestResult result) {
		this.result = result;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	

}
