package com.bailian.ta.log;


public class LogModel {
	
	private String logStep;
	private String time;
	
	public LogModel(String step, String time){
		if(step.equals("")){
			this.logStep = step;
			this.time = "";
		}
		else{
			this.logStep = step;
			this.time = time;
		}
	}
	
	public String getLogStep() {
		return logStep;
	}
	public void setLogStep(String logStep) {
		this.logStep = logStep;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
