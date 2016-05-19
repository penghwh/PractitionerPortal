package com.bailian.ta.report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.log.LogUtil;
import com.bailian.ta.util.FileUtil;

public class ScreenShot {
	
	private String getImagePath(String imageName){
		String path = FrameConfig.getInstance().getConfig("SnapshotPath") + "/" + NameUtil.getFolderName();
		FileUtil.createPath(path);
		path = FileUtil.getAbsolutelyPath(path + "/" + imageName);
		return path;
	}
	
	public String takeScreenShot(WebDriver driver, String method){
		if (driver == null) {
			return null;
		}
		String imageName = NameUtil.getImageName(method);
		String path = getImagePath(imageName);
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(path));
			LogUtil.frameLog("Screen shot be taken: " + path);
		} catch (IOException e) {
			LogUtil.error("Screen shot error: " + path);
		}
		return imageName;
	}

}
