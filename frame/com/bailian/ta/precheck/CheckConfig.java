package com.bailian.ta.precheck;

import java.util.ArrayList;
import java.util.List;

import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.log.LogUtil;

public class CheckConfig {
	private String[] needCheck;
	private List<String> passList = new ArrayList<String>();
	private List<String> failList = new ArrayList<String>();
	
	
	
	public CheckConfig(){
		needCheck = new String[] {"IEDriverPath", "ChromeDriverPath", "LogPath", "LogName", 
				                  "ReportPath", "SnapshotPath", "RetryTime", "RetryWait", "Report_title"};
	}
	
	public void check(){
		runCheck();
		if(failList.size() > 0){
			LogUtil.warn("Check Configuration Completed" + getString(failList) + " Failed");
		}
		else{
			LogUtil.frameLog("Check Configuration Completed" + getString(passList) + " Passed");
		}
	}
	
	private boolean isEmpty(String key){
		return FrameConfig.getInstance().getConfig(key).isEmpty();
	}
	
	private void runCheck(){
		for(int i = 0; i < needCheck.length; i++){
			if(isEmpty(needCheck[i])){
				failList.add(needCheck[i]);
			}
			else{
				passList.add(needCheck[i]);
			}
		}
	}
	
	private String getString(List<String> list){
		String rs = "";
		for(int i = 0; i < list.size(); i++){
			rs = rs + ", '" + list.get(i) + "'"; 
		}
		return rs;
	}

}
