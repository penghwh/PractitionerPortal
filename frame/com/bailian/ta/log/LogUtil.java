package com.bailian.ta.log;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bailian.ta.report.FrameReporter;
import com.bailian.ta.util.TimeUtil;



public class LogUtil {
	
	public static  List<LogModel> loglist = new ArrayList<LogModel>();
	
	private static void log(String message, boolean isError){
		LogModel log = new LogModel(message, TimeUtil.getTimeMs());
		if(isError == false){
			System.out.println(log.getLogStep());
		}
		else{
			System.err.println(log.getLogStep());
		}
		loglist.add(log);
	}
	
	public static void info(String message){
		log(printSpace(5) + "[INFO]" + printSpace(1) + message, false);
	}
	
	public static void step(String...message){
		log(printSpace(5) + "[STEP]" + printSpace(1) + message[0], false);
		if(message.length == 1){
			FrameReporter.getInstance().addPassStep(message[0], message[0]);
		}
		else{
			FrameReporter.getInstance().addPassStep(message[1], message[0]);
		}
		
	}
	
	public static void frameLog(String message){
		log("[FRAME]" + printSpace(2) + message + "", false);
	}
	
	public static void error(String...message){
		log("[ERROR]"+ printSpace(1) + message[0] + "", true);
		if(message.length == 1){
			FrameReporter.getInstance().addFailStep(message[0], message[0]);
		}
		else{
			FrameReporter.getInstance().addFailStep(message[1], message[0]);
		}
	}
	
	public static void warn(String message){
		log("[WARN]" + printSpace(1) +  message +"", true);
	}
	
	public static void startCase(String caseName){
		log("\n" + printSpace(2) + "<TestCase> Start to run test method [" + caseName + "]", false);
	}
	
	public static void endCase(String caseName, String result){
		log(printSpace(2) + "<TestCase> [" + caseName + "] Test method " + result + "\n", false);
	}
	
	protected static String printSpace(int length){
		String rs =  StringUtils.repeat(" ", length);
		return rs;
	}
	
	protected static String printPoint(int length){
		String rs =  StringUtils.repeat(".", length);
		return rs;
	}
	
}
