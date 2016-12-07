package com.aibinong.backyard.sms;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 亿美软通
 * 
 * @author lea
 *
 */
public class EmaySmsSend implements SmsSend {
	public static final String AIBINONG_SMS_URL = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/sendsms.action";
	public static final String AIBINONG_SMS_USERNAME = "8SDK-EMY-6699-RIVLO";
	public static final String AIBINONG_SMS_PASSWORD = "795741";

	@Override
	public void send(String mobile, String message,String title) throws SmsException {
		String mes_title="一元够精彩";
		if(StringUtils.isNotEmpty(title)){
			mes_title=title;
		}
		message = "【"+mes_title+"】" + message;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(AIBINONG_SMS_URL);
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("cdkey", AIBINONG_SMS_USERNAME), new NameValuePair("password", AIBINONG_SMS_PASSWORD),
				new NameValuePair("phone", mobile), new NameValuePair("message", message) };
		post.setRequestBody(data);
		try {
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			String response = new String(post.getResponseBodyAsString().getBytes("utf-8"));
			String result=parseResponse(response);
			if (result != null && result.equals("-2")) {
				throw new SmsException("客户端异常");
			}
			if (result != null && result.equals("-9000")) {
				throw new SmsException("数据格式错误,数据超出数据库允许范围");
			}
			if (result != null && result.equals("0")) {
				return;
			}
			if (result != null && result.equals("-104")) {
				throw new SmsException("请求超过限制");
			}
			if (result != null && result.equals("-1105")) {
				throw new SmsException("注册号状态异常, 未用");
			}
			if (result != null) {
				throw new SmsException("服务端错误,return code :" + result);
			}

		} catch (HttpException e) {
			throw new SmsException(e);
		} catch (IOException e) {
			throw new SmsException(e);
		} catch (ParserConfigurationException e) {
			throw new SmsException(e);
		} catch (SAXException e) {
			throw new SmsException(e);
		} finally {
			post.releaseConnection();
		}

	}
	private String parseResponse(String response) throws ParserConfigurationException, SAXException, IOException{
		BufferedReader br=new BufferedReader(new StringReader(response));
		StringBuffer sb=new StringBuffer();
		for(String line=br.readLine();line!=null;line=br.readLine()){
			sb.append(line.trim());
		}
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		ByteArrayInputStream bis = new ByteArrayInputStream(sb.toString().getBytes("utf-8"));
		Document document = db.parse(bis);
		Element root=document.getDocumentElement();
		if(root!=null){
			NodeList list=root.getElementsByTagName("error");
			if(list!=null&&list.getLength()>0){
				Node error=list.item(0);
				return error.getTextContent();
			}
		}
		return null;
	}
	public static void main(String[] a) throws SmsException {
		EmaySmsSend ws = new EmaySmsSend();
		ws.send("15957140404", "亲爱的用户，很感谢您关注我们的APP。因为涉及到支付通道方的问题，所以耽搁了几天，请您谅解。您在5月14日付款10元，支付通道方未通知到我们，造成您的损失，我们将返还您价值10元的榴莲币，额外又赠送您价值10元的榴莲币到您的账户里。","一元够精彩");
	}
}
