package com.xu.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;

public class SoapUtilities {
	public static String sendRequest(String wsurl, String method, String... params) {
		String resultstr="";
		int timeout = 10000;
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<soap:Envelope " + "xmlns:api='http://webservice.xu.com' "
				+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
				+ "xmlns:xsd='http://www.w3.org/2001/XMLSchema' "
				+ "xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>");
		sb.append("<soap:Body>");
		sb.append("<api:"+method+ ">");
		for(int i=0; i<params.length;i++){
			sb.append("<arg"+i+ ">"+params[i]+ "</arg0>");
		}
		sb.append("</api:"+method+ ">");
		sb.append("</soap:Body>");
		sb.append("</soap:Envelope>");

		
		try {
			// HttpClient发送SOAP请求
			System.out.println("HttpClient 发送SOAP请求");
			HttpClient client = new HttpClient();
			PostMethod postMethod = new PostMethod(wsurl);
			// 设置连接超时
			client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			// 设置读取时间超时
			client.getHttpConnectionManager().getParams().setSoTimeout(timeout);
			// 然后把Soap请求数据添加到PostMethod中
			RequestEntity requestEntity;
			requestEntity = new StringRequestEntity(sb.toString(), "text/xml", "UTF-8");
			// 设置请求头部，否则可能会报 “no SOAPAction header” 的错误
			postMethod.setRequestHeader("SOAPAction", "");
			// 设置请求体
			postMethod.setRequestEntity(requestEntity);
			int status = client.executeMethod(postMethod);
			// 打印请求状态码
			System.out.println("status:" + status);
			// 获取响应体输入流
			
			InputStream is = postMethod.getResponseBodyAsStream();
			// 获取请求结果字符串
			String result = IOUtils.toString(is, "utf-8");//.toString(is);
			System.out.println("result: " + result);
			is.close();
			///////////////////////////////////////
			
			// HttpURLConnection 发送SOAP请求
			System.out.println("HttpURLConnection 发送SOAP请求");
			URL url = new URL(wsurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			//重要，不添加会报错 The endpoint reference (EPR) for the Operation not found
			conn.addRequestProperty("SOAPAction", "");
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);

			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			dos.write(sb.toString().getBytes("utf-8"));
			dos.flush();
			BufferedReader reader;
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line = null;
			StringBuffer strBuf = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				strBuf.append(line);
			}

			System.out.println(strBuf.toString());
			resultstr=strBuf.toString();
			dos.close();
			reader.close();
			
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------------------------------");
		System.out.println(resultstr);
		return resultstr;
	}

}
