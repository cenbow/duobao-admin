package com.aibinong.backyard.sms;

public interface SmsSend {
	public void send(String mobile, String message,String title) throws SmsException;
}
