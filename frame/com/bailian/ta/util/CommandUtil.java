package com.bailian.ta.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bailian.ta.log.LogUtil;


public class CommandUtil {
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/12
	 * @param String command line
	 * @return String execute result
	 * 
	 *    The method will execute the command and return the execute result.
	 * 
	************************************************************************************************/
	public static List<String> exec(String commandLine){
		List<String> rs = new ArrayList<String>();
		String line;
		try{    
			Process process = Runtime.getRuntime().exec(commandLine);			
			InputStreamReader isr = new InputStreamReader(process.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				rs.add(line);
				continue;
			}	
		}
		catch (Exception e){
			LogUtil.frameLog("Execute {Command: " + commandLine + ", Failed}");
		} 
		return rs;
	}

}
