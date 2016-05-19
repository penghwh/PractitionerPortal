package com.bailian.ta.driver;


public enum BrowserType {
	CHROME,FIREFOX,SAFARI, IE;
	
	public static BrowserType getType(String type) {
		switch (type.trim().toLowerCase()) {
		case "firefox":
			return FIREFOX;
		case "ie":
			return IE;
		case "chrome":
			return CHROME;
		case "safari":
			return SAFARI;
		default:
			return FIREFOX;
		}
	}
}
