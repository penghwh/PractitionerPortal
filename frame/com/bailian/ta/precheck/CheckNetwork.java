package com.bailian.ta.precheck;

import java.util.ArrayList;
import java.util.List;

import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.log.LogUtil;
import com.bailian.ta.util.CommandUtil;

public class CheckNetwork {
	
	public void check(){
		if(getNetworkState()){
			LogUtil.frameLog("Check Network Passed");
		}
		else{
			LogUtil.warn("Check Network Failed");
		}
	}
	
	private boolean getNetworkState(){
		boolean state = true;
		List<String> urls = getURL();
		for(int i = 0; i < urls.size(); i++){
			LogUtil.frameLog("Check Network Access " + urls.get(i) + "...");
			if(getState(exePing(urls.get(i))) == false){
				state = false;
			}
		}
		return state;
	}
	
	private List<String> exePing(String url){
		return CommandUtil.exec("ping " + url + " -n 4");
	}
	
	private boolean getState(List<String> list){
		int timeOut = 0;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).contains("Request timed out")){
				timeOut ++;
			}
		}
		if(timeOut == 4){
			return false;
		}
		else{
			return true;
		}
	}
	
	private List<String> getURL(){
		List<String> urls = new ArrayList<String>();
		for(String key : FrameConfig.getInstance().getConfig().keySet()){
			if(key.toLowerCase().endsWith("url")){
				urls.add( FrameConfig.getInstance().getConfig(key).split("//")[1]);
			}
		}
		return urls;
	}

}
