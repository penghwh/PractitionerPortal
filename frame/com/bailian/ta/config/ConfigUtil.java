package com.bailian.ta.config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.bailian.ta.log.LogUtil;

public class ConfigUtil {
	
	/*************************************************************************************************
	 * Type: Method
	 * Author: Henry
	 * Parameter: path
	 * Return: Properties prop
	 * Create date: 2015/11/11
	 * Load the Configuration from config.properites, return a Properties
	 ************************************************************************************************/
	public static Properties loadConfig(String path){
		InputStream in = null;
		Properties prop = new Properties(); 
		try {
			in = new BufferedInputStream(new FileInputStream(path));
			try {
				prop.load(in);
			} catch (IOException e) {
				LogUtil.error("Read Configuration File Failed");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			LogUtil.error("Load Configuration File path: config/config.properties Not Found}");
		}   
		return prop;
	}
	
	/*************************************************************************************************
	 * Type: Method
	 * Author: Henry
	 * Parameter: Properties prop
	 * Return: Map<String, String> configMap
	 * Create date: 2015/11/11
	 * Put the key and value from prop into a hash map configMap, then return the map
	 ************************************************************************************************/
	public static Map<String,String> initConfig(Properties prop){
		Map<String, String> configMap = new HashMap<String, String>();
		if(prop.isEmpty()){
			LogUtil.error("Load Configuration Failed");
		}
		else{
			Enumeration<?> enu = prop.propertyNames();  
		    while (enu.hasMoreElements()) {
		    	Object key = enu.nextElement();  
		        configMap.put((String) key, prop.getProperty((String) key));
		    }
		    LogUtil.frameLog("Load Configuration Succeed");
		}
	    return configMap;
	}
}
