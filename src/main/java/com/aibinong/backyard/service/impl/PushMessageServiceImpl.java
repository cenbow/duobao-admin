package com.aibinong.backyard.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.PushMessage;
import com.aibinong.backyard.service.PushMessageService;
@IocBean(name = "pushMessageService")
public class PushMessageServiceImpl implements PushMessageService {

	@Inject
    protected BasicDao basicDao;
	@Override
	public QueryResult getPushMessageList(Date startTime, Date endTime,Integer page) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer str = new StringBuffer();
		str.append("select * from push_message where type=3");
		
		if(startTime!=null){
			str.append(" and send_time>='"+format.format(startTime)+"'");
		}
		if(startTime!=null){
			str.append(" and send_time<='"+format.format(endTime)+"'");
		}
		str.append(" order by gmt_create desc");
		QueryResult queryResult = basicDao.querySqlResult(str.toString(), page, Constants.DEFAULT_PAGE_SIZE);
	    return queryResult;
	}

	@Override
	public PushMessage getMessageById(Long messageId) {
		// TODO Auto-generated method stub
		return basicDao.find(messageId, PushMessage.class);
	}

	@Override
	public PushMessage addMessage(PushMessage message) {
		// TODO Auto-generated method stub
		message=basicDao.save(message);
		return message;
	}

	@Override
	public void updateMessage(PushMessage message) {
		// TODO Auto-generated method stub
		basicDao.update(message);
	}

	@Override
	public void delPush(PushMessage message) {
		// TODO Auto-generated method stub
		basicDao.delById(message.getId(), PushMessage.class);
	}

}
