package com.bailian.ta.util.httppost;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;

public class Common {

	/**
	 * 百联Http请求安全 密钥 DES
	 */
	public static final String BAILIAN_DES_KEY = "123ABCde";

	/**
	 * 服务响应时间超时时间为5秒
	 */
	public static final int HTTP_CONNECTION_TIMEOUT = 5000;

	/**
	 * 链接超时时间为30秒
	 */
	public static final int HTTP_SOCKET_TIMEOUT = 30000;

	public static final String encode_UTF8 = "UTF-8";
	public static StringBuffer content;
	public static final String client_id = "11125";
	// public static final String client_id = "10006";
	public static final String client_secret = "dda26438a3ae4177ba765ee41433a0b0";
	// public static final String client_secret
	// ="e256cdce093c28a5524e1d5bc518684e";
	// token
	public static final String ACCESS_TOKEN = "access_token";
	public static final int resultCode = 20;

	public static final String CHECK_PATH = "$(CHECK_PATH)";
	public static final String GEN_PATH = "$(GEN_PATH)";

	// public static final String CHECK_PATH =
	// "http://mapi.blemall.com/cashier/ddmx";
	// public static final String GEN_PATH =
	// "http://mapi.blemall.com/cashier/ddins";

	// public static final String CHECK_PATH =
	// "http://222.73.227.153/mapi/cashier/ddmx";
	// public static final String GEN_PATH =
	// "http://222.73.227.153/mapi/cashier/ddins";

	// 192.168.96.84

	public static boolean isEmpty(String str) {
		if (str == null || "null".equals(str) || "".equals(str)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param rateStr
	 * @return
	 */
	public static String formateRate2(String rateStr) {
		if ("null".equals(rateStr) || rateStr == null || rateStr.equals("")) {
			rateStr = "0";
		}
		Double d = Double.valueOf(rateStr);
		DecimalFormat df = new DecimalFormat("0.00");

		String str = df.format(d);
		return str;
	}

	public static String getData(String str) {
		String s = "";
		if (str.length() == 1) {
			s = "0" + str;
		} else if (str.length() == 2) {
			s = str;
		}
		return s;
	}

	/**
	 * 瑙ｅ瘑
	 * 
	 * @param str
	 * @return
	 */
	public static String Decode(String str) {
		String str1 = "";
		try {
			str1 = URLDecoder.decode(str, "utf-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(Base64Encoder.decode(str1));

	}
}

