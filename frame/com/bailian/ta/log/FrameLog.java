package com.bailian.ta.log;

import java.util.List;

import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.util.FileUtil;

public class FrameLog {
	
	
	/*************************************************************************************************
	 * Type: Method
	 * Author: Henry
	 * Create date: 2015/11/11
	 * Write log information to log file, the information will add to the end of the file, the file
	 * name and path read from configuration
	************************************************************************************************/
	public void createLogFile(List<LogModel> loglist){
		String logMessage = null;
		try{
			String configPath = FrameConfig.getInstance().getConfig("LogPath");
			FileUtil.createPath(configPath);
			String absolutePath = FileUtil.getAbsolutelyPath(configPath, FrameConfig.getInstance().getConfig("LogName"));
			for(int i = 0; i < loglist.size(); i++){
				if(loglist.get(i).getTime() == null){
					logMessage = loglist.get(i).getLogStep() + "\n";
				}
				else{
					logMessage =  loglist.get(i).getTime() + LogUtil.printPoint(3) + loglist.get(i).getLogStep() + System.getProperty("line.separator");
				}
				FileUtil.writeFile(absolutePath, logMessage, true);
			}
			LogUtil.frameLog("Log has been Wrote to " + absolutePath);
		}
		catch(Exception e){
			LogUtil.error("No Log Path or Name Found in Config, Please Check");
		}
	}
	

}
