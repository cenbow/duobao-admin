package com.aibinong.backyard.sms;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.Mvcs;

import com.aibinong.backyard.JedisUtils;
import com.aibinong.backyard.dao.RedisDao;
	

/**
 * 优先级
 * 
 * @author lea
 *
 */
public class PrioritySmsSend implements SmsSend {
	private final static int LIVE_TIME = 120;
	private List<SmsSend> smsSendList = new ArrayList<SmsSend>();
	public PrioritySmsSend(){	
		//addSmsSend(new ShiyuanSmsSend());
		addSmsSend(new EmaySmsSend());
		//addSmsSend(new RdSmsSend());
		//addSmsSend(new WebChineseSmsSend());
	}
	
	public void addSmsSend(SmsSend smsSend){
		smsSendList.add(smsSend);
	}
	public void removeSmsSend(SmsSend smsSend){
		smsSendList.remove(smsSend);
	}
	
	@Override
	public void send(String mobile, String message,String title) throws SmsException {
		String key = "smssend_index_" + mobile;
		RedisDao jedisBO=JedisUtils.getJedis();
		String smsSend_Idx = jedisBO.get(key);
		Integer smsSendIdx=null;
		if(StringUtils.isNotEmpty(smsSend_Idx)){
			smsSendIdx = Integer.parseInt(smsSend_Idx);
		}
		
		if (smsSendIdx == null) {
			if (smsSendList == null || smsSendList.isEmpty()) {
				throw new SmsException("可用短信网关为空");
			}
			smsSendIdx = 0;
			SmsSend smsSend = smsSendList.get(smsSendIdx);
			smsSend.send(mobile, message,title);
			jedisBO.set(key, smsSendIdx, LIVE_TIME);
			return;
		}
		if (smsSendIdx >= smsSendList.size()) {
			smsSendIdx = 0;
		}
		
		
		SmsSend smsSend = smsSendList.get(smsSendIdx);
		smsSendIdx++;
		jedisBO.set(key, smsSendIdx, LIVE_TIME);
		smsSend.send(mobile, message,title);
	}

}
