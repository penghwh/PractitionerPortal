package com.bailian.ta.report.model;

import com.bailian.ta.util.SysUtil;

public class OverviewModel {
	
	private String time;//record the execute time
	private String pcName;//record the computer name
	private String pcIp;// record the IP address of computer
	private String pcOs;//record the operation system information of the computer
	private String caseNum;//record the total test case number
	private String casePassNum;//record the pass test case number
	private String caseFailNum;//record the fail test case number
	private String caseSkipNum;// record the skip test case number
	private String casePassRate;//record the pass rate of this testing
	private TestStatus status;// record the status of this testing, includes
								// perfect, good, bad
	
	public OverviewModel(){
		this.pcName = SysUtil.getOSUser();
		this.pcIp = SysUtil.getIpAddress();
		this.pcOs = SysUtil.getOSInfo();
		this.caseNum = "0";
		this.casePassNum = "0";
		this.caseFailNum = "0";
		this.casePassRate = "0";
		this.caseSkipNum = "0";
	}
	
	
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getPcName() {
		return pcName;
	}
	
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	
	public String getPcIp() {
		return pcIp;
	}
	
	public void setPcIp(String pcIp) {
		this.pcIp = pcIp;
	}
	
	public String getPcOs() {
		return pcOs;
	}
	public void setPcOs(String pcOs) {
		this.pcOs = pcOs;
	}
	
	public String getCaseNum() {
		return caseNum;
	}
	
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	
	public String getCasePassNum() {
		return casePassNum;
	}
	public void setCasePassNum(String casePassNum) {
		this.casePassNum = casePassNum;
	}
	
	public String getCaseFailNum() {
		return caseFailNum;
	}
	
	public void setCaseFailNum(String caseFailNum) {
		this.caseFailNum = caseFailNum;
	}
	
	public String getCasePassRate() {
		return casePassRate;
	}
	
	public void setCasePassRate(String casePassRate) {
		this.casePassRate = casePassRate;
	}
	public TestStatus getStatus() {
		return status;
	}

	public void setStatus(TestStatus status) {
		this.status = status;
	}
	
	public String getCaseSkipNum() {
		return caseSkipNum;
	}

	public void setCaseSkipNum(String caseSkipNum) {
		this.caseSkipNum = caseSkipNum;
	}
}
