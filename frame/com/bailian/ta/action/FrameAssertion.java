package com.bailian.ta.action;

import java.util.Arrays;

import org.testng.asserts.Assertion;

import com.bailian.ta.log.LogUtil;

public class FrameAssertion{
	
	public static void fail(String...message){
		LogUtil.error(message);
		new Assertion().fail(message[0]);
	}
	
	public static void isTrue(boolean actual, String description){
		if(actual){
			LogUtil.step("Is True, Passed", "Checkpoint: " + description);
		}
		else{
			fail("Not True, Failed", "Checkpoint: " + description);
		}
	}
	
	public static void notTrue(boolean actual, String description){
		if(!actual){
			LogUtil.step("Not True, Passed", "Checkpoint: " + description);
		}
		else{
			fail("Is True, Failed", "Checkpoint: " + description);
		}
	}
	
	public static void notNull(Object o, String description){
		if(o == null){
			fail("Object is Null, Failed", "Checkpoint: " + description);
		}
		else{
			LogUtil.step("Object " + o.getClass().getName() + " is Not Null, Passed", "Checkpoint: " + description);
		}
	}
	
	public static void contains(String actual, String expect, String description){
		if(actual.contains(expect)){
			LogUtil.step("Actual String '" + actual + "' contains Expect String '" + expect + "', Passed",
					"Checkpoint: " + description);
		}
		else{
			fail("Actual String '" + actual + "' not contains Expect String '" + expect + "', Failed",
					"Checkpoint: " + description);
		}
	}
	
	public static void equalsOneOf(String actual, String[] expect, String description) {
		if (Arrays.asList(expect).contains(actual)) {
			LogUtil.step("Actual String '" + actual + "' equals Expect String, Passed", "Checkpoint: " + description);
		}
		else{
			fail("Actual String '" + actual + "' not equals Expect String '" + Arrays.toString(expect) + "', Failed",
					"Checkpoint: " + description);
		}
	}
	
	public static <T> void equals(T actual, T expect, String description) {
		if (actual.equals(expect)) {
			LogUtil.step("Actual Value '" + actual + "' equals Expect value '" + expect + "', Passed",
					"Checkpoint: " + description);
		} else {
			fail("Actual value '" + actual + "' not equals Expect value '" + expect + "', Failed",
					"Checkpoint: " + description);
		}
	}
	

	

}
