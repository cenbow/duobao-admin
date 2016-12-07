package com.aibinong.backyard.sms;

public class SmsSendFactory {

	private static SmsSend smsSend;

	private static SmsSend webChineseSmsSend;
	
	public static SmsSend getSmsSend() {
		if (smsSend != null) {
			return smsSend;
		}
		synchronized (SmsSendFactory.class) {
			if (smsSend == null) {
				PrioritySmsSend ss = new PrioritySmsSend();
				smsSend = ss;
			}
		}
		return smsSend;
	}
	public static SmsSend getWebChineseSmsSend(){
		if (webChineseSmsSend != null) {
			return webChineseSmsSend;
		}
		synchronized (SmsSendFactory.class) {
			if (webChineseSmsSend == null) {
				webChineseSmsSend =new WebChineseSmsSend();
			}
		}
		return webChineseSmsSend;
	}
	

}
