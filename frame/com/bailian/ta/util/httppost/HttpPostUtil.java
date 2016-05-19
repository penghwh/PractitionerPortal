package com.bailian.ta.util.httppost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpHeaders;

import com.bailian.ta.action.FrameAssertion;

public class HttpPostUtil {

	public static String doPost(String url, String postMessage) throws Exception {
		String response = null;
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.setRequestHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
		method.setRequestHeader(HttpHeaders.USER_AGENT, "Jakarta Commons-HttpClient/3.1");
		HttpMethodParams params = method.getParams();
		params.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

		NameValuePair[] data = { new NameValuePair("RecContentType", "application/json"),
				new NameValuePair("Resource", "0"), new NameValuePair("data", postMessage) };

		method.setRequestBody(data);

		// 采取http post方式调用服务
		try {
			HttpConnectionManagerParams connParams = client.getHttpConnectionManager().getParams();
			// 服务响应时间超时时间
			connParams.setConnectionTimeout(Common.HTTP_CONNECTION_TIMEOUT);
			// 链接超时时间为30000毫秒
			connParams.setSoTimeout(Common.HTTP_SOCKET_TIMEOUT);

			client.executeMethod(method);

			if (method.getStatusCode() == HttpStatus.SC_OK) {
				// response = method.getResponseBodyAsString();
				StringBuffer contentBuffer = new StringBuffer();
				InputStream in = method.getResponseBodyAsStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, method.getResponseCharSet()));
				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {
					contentBuffer.append(inputLine);
				}
				response = contentBuffer.toString();
				in.close();
			} else {
				FrameAssertion.fail("API call fail: stuatus=" + method.getStatusCode());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return response;
	}


}
