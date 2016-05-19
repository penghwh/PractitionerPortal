package com.bailian.ta.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class SysUtil {
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @param int s
	 * 
	 *    The method will stop the system a period of time, the length of time is the parameter.
	 *    the unit is seconds.
	 * 
	************************************************************************************************/
	public static void sleep(int s){
		try{
			Thread.sleep(s * 1000);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @return ip
	 * 
	 *    The method will return the ip address of the machine running the framewor as string.
	 * 
	************************************************************************************************/
    public static String getIpAddress() {  
        InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}  
        return address.getHostAddress();  
    }  
    
    
    /*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @return ip
	 * 
	 *    The method will return the user name of the machine running the framewor as string.
	 * 
	************************************************************************************************/
    public static String getOSUser(){
    	String os = null;
    	Properties props = System.getProperties(); 
        os = props.getProperty("user.name");
    	return os;
    }
    
    /*************************************************************************************************
 	 * @author Ink
 	 * @date 2015/11/11
 	 * @return ip
 	 * 
 	 *    The method will return the operation system information of the computer running the framewor as string.
 	 *    The information contains the name of operation and version.
 	 * 
 	************************************************************************************************/
    public static String getOSInfo(){
    	Properties props=System.getProperties(); 
    	String osName = props.getProperty("os.name"); 
    	String osVersion = props.getProperty("os.version"); 
    	String rs = osName + "-v" + osVersion;
    	return rs;
    }
}
