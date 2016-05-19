package com.bailian.ta.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.bailian.ta.log.LogUtil;


public class FileUtil {
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @param String path
	 * 
	 *    The method will create the dif base on the value of the parameter if the dir is not exist.
	 * 
	************************************************************************************************/
	public static void createPath(String path){
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
			LogUtil.frameLog("Create Path : " + path + "");
		}else{
			LogUtil.frameLog("Exist path: " + path);
		}
	}
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @param String filePath, String message, boolean notOverride
	 * 
	 *    The method will wirte the message to the filePath, the filePath includes two parts: path + fileName.
	 *    If the notOverride is true, the method will write the message to end of the file, otherwise,
	 *    it will be override.
	 * 
	************************************************************************************************/
	public static void writeFile(String filePath, String message, boolean notOverride){
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(filePath, notOverride);
			fileWriter.write(message);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			LogUtil.error("Write to Path " + filePath);
		}
	}
	

	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @param String path, String fileName
	 * @return String filPath
	 * 
	 *    The method will get the absolutely path and return it.
	 * 
	************************************************************************************************/
	public static String getAbsolutelyPath(String path, String fileName){
		File dir = new File(path);
		String filePath = dir.getAbsolutePath() + "\\" + fileName;
		return filePath;
	}
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @param String path
	 * @return String filPath
	 * 
	 *    The method will get the absolutely path and return it. the parameter path must contains the
	 *    path and file name.
	 * 
	************************************************************************************************/
	public static String getAbsolutelyPath(String path){
		File dir = new File(path);
		String filePath = dir.getAbsolutePath();
		return filePath;
	}
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/11
	 * @param String rpath, String dpath
	 * 
	 *    The method will copy the file from rpath to dpath, both rpath and dpath must contain the 
	 *    file name.
	 * 
	************************************************************************************************/
	@SuppressWarnings("resource")
	public static void copyFile(String rpath, String dpath){
		try{
			FileInputStream input = new FileInputStream(rpath);
			FileOutputStream output = new FileOutputStream(dpath);
			int in = input.read();
			while(in != -1){
				output.write(in);
			    in = input.read();
			}
			LogUtil.frameLog("Copy File from "+ rpath + " to " + dpath);
		}catch (IOException e){
			LogUtil.error("Failed Copy File from " + rpath + " to " + dpath);
		}
	}
	
	public static String getImageName(String methodName){
		String screenName =  methodName + "_" + String.valueOf(new Date().getTime()) + ".jpg";
		return screenName;
	}
}
