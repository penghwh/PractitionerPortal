package com.bailian.ta.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bailian.ta.log.LogUtil;

public class TimeUtil {
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @param String format
	 * @return String date
	 * 
	 *    The method will return the current time with the parameter's format, such as yyyy-MM-dd, 
	 *    yyyyMMdd-HHmmss and so on.
	 * 
	************************************************************************************************/
	public static String getDate(String format){
	    Date date = new Date();  
	    SimpleDateFormat sdf = new SimpleDateFormat(format);  
	    String dateNowStr = sdf.format(date);  
	    return dateNowStr;
	}
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @return String date
	 * 
	 *    The method will return the current time with the format yyyy-MM-dd.
	 * 
	************************************************************************************************/
	public static String getDate(){
	    Date date = new Date();  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateNowStr = sdf.format(date);  
	    return dateNowStr;
	}
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @return String date
	 * 
	 *    The method will return the current time with the format yyyyMMdd-HHmmss, the format not 
	 *    contain illegal character as file name.
	 * 
	************************************************************************************************/
	public static String getTimeforFile(){
		Date date = new Date();  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");  
	    String dateNowStr = sdf.format(date);  
	    return dateNowStr;
	}
	
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @return String date
	 * 
	 *    The method will return the current time with the format yyyy-MM-dd HH:mm:ss SSS
	 * 
	************************************************************************************************/
	public static String getTimeMs(){
		Date date = new Date();  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");  
	    String dateNowStr = sdf.format(date);  
	    return dateNowStr;
	}
	
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @return String date
	 * 
	 *    The method will return the current time with the format yyyy-MM-dd HH:mm:ss
	 * 
	************************************************************************************************/
	public static String getTime(){
	    Date date = new Date();  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateNowStr = sdf.format(date);  
	    return dateNowStr;
	}
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @return String date
	 * 
	 *    The method will return the current time with the format HH:mm:ss
	 * 
	************************************************************************************************/
	public static String getHour(){
		  Date date = new Date();  
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");  
		    String dateNowStr = sdf.format(date);  
		    return dateNowStr;
	}
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @param beginTime
	 * @return duration
	 * 
	 *    The method will return the duration time from begin time to current time. the begin time
	 *    is same as the parameter, and the fromat is yyyy-MM-dd HH:mm:ss SSS, so that the precision
	 *    is 'ms'.
	 * 
	************************************************************************************************/
	public static String getDuration(String beginTime){
		Date date = new Date();  
		String rs = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");  
	    String now = sdf.format(date);
		try {
			Date begin = sdf.parse(beginTime);
			Date end = sdf.parse(now);
			long between = (end.getTime()-begin.getTime());
			rs = Long.toString(between) + "ms";
		} catch (ParseException e) {
			LogUtil.error("Get Duration {" + beginTime +", Failed}");
		}
		return rs;
	}
	
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @param beginTime, endTime
	 * @return duration
	 * 
	 *    The method will return the duration time from begin time to end time. the begin time and end
	 *    time is same as the parameter, and the fromat is yyyy-MM-dd HH:mm:ss SSS, so that the precision
	 *    is 'ms'.
	 * 
	************************************************************************************************/
	public static String getDuration(String beginTime, String endTime){
		String rs = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");  
		try {
			Date begin = sdf.parse(beginTime);
			Date end = sdf.parse(endTime);
			long between = (end.getTime()-begin.getTime());
			rs = Long.toString(between) + "ms";
		} catch (ParseException e) {
			LogUtil.error("Get Duration {" + beginTime +", Failed}");
		}
		return rs;
	}
}
