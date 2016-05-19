package com.bailian.ta.locator;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.ho.yaml.Yaml;
import org.openqa.selenium.By;
import com.bailian.ta.log.LogUtil;

public class WebElementLocator {

	private HashMap<String, HashMap<String, String>> _locatorMap ;
	
	private final static String _type = "type";
	private final static String _value = "value";
	
	public WebElementLocator(String yamlFileName){	
		_locatorMap = getLocatorMapFromYamlFile(yamlFileName);
	}	

	@SuppressWarnings("unchecked")
	private HashMap<String, HashMap<String, String>> getLocatorMapFromYamlFile(String fileName){		
		File yamlFile = new File("pageLocator/" + fileName + ".yaml");
		HashMap<String, HashMap<String, String>> locatorMap = new HashMap<String, HashMap<String, String>>();
		locatorMap.clear();

		if(!yamlFile.exists() || !yamlFile.canRead())
			return locatorMap;
		try {
			locatorMap = Yaml.loadType(
					new FileInputStream(yamlFile.getAbsolutePath()), HashMap.class);
		} catch (Exception e) {
			LogUtil.warn("WARNING: Fail to read yaml file: " + fileName
					+ ".yaml");
//			e.printStackTrace();			
		}
		return locatorMap;
	}
	
	public By getLocator(String key, ArrayList<String> locatorStringReplacement) {
		By locatorBy = null;
		if (_locatorMap == null)
			return null;
		if (_locatorMap.containsKey(key)) {
			String locatorString = _locatorMap.get(key).get(_value);
			if (locatorStringReplacement != null
					&& locatorStringReplacement.size() != 0) {
				for (String replaceItem : locatorStringReplacement) {
					locatorString = locatorString.replaceFirst("%s",replaceItem);
				}
			}

			if (_locatorMap.get(key).get(_type).equals("id"))
				locatorBy = By.id(locatorString);
			else if (_locatorMap.get(key).get(_type).equals("name"))
				locatorBy = By.name(locatorString);
			else if (_locatorMap.get(key).get(_type).equals("xpath"))
				locatorBy = By.xpath(locatorString);
			else if (_locatorMap.get(key).get(_type).equals("linkText"))
				locatorBy = By.linkText(locatorString);
			else if (_locatorMap.get(key).get(_type).equals("partialLinkText"))
				locatorBy = By.partialLinkText(locatorString);
			else if (_locatorMap.get(key).get(_type).equals("className"))
				locatorBy = By.className(locatorString);
			else if (_locatorMap.get(key).get(_type).equals("tagName"))
				locatorBy = By.tagName(locatorString);
			else if (_locatorMap.get(key).get(_type).equals("cssSelector"))
				locatorBy = By.cssSelector(locatorString);
		}
		else{
			LogUtil.error("The locator with specified name " + key + " does not exist.");
		}
		return locatorBy;
	}

	public By getLocator(String key) {
		return getLocator(key, null);
	}
	
	//tried to overload getLocator(String key, String replacement) but says it's ambiguous
	public By getDynLocator(String key, String singleReplace) {
		ArrayList<String> oneStringList = new ArrayList<String>();
		oneStringList.add(singleReplace);
		return getLocator(key, oneStringList);
	}
	
	public String getLocatorType(String key) {
		if (_locatorMap == null)
			return null;
		if (_locatorMap.containsKey(key)) {
			return _locatorMap.get(key).get(_type);
		} else {
			LogUtil.error("The locator with specified name " + key
					+ " does not exist.");
			return null;
		}
	}
	
	public String get(String key) {
		if (_locatorMap == null)
			return null;
		if (_locatorMap.containsKey(key)) {
			return _locatorMap.get(key).get(_value);
		} else {
			LogUtil.error("The locator with specified name " + key
					+ " does not exist.");
			return null;
		}
	}
	
	public static void main(String[] args){
		WebElementLocator PO;
		PO = new WebElementLocator("social");
		System.out.println(PO.get("渠道管理门户"));
		System.out.println(PO.getLocator("渠道管理门户"));
	}
}

