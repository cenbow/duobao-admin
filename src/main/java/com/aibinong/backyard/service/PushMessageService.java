package com.aibinong.backyard.service;

import java.util.Date;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.PushMessage;

public interface PushMessageService {

	/**
	 * push列表
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public QueryResult getPushMessageList(Date startTime,Date endTime,Integer page);
	/**
	 * 获取某个messge
	 * @param messageId
	 * @return
	 */
	public PushMessage getMessageById(Long messageId);
	/**
	 * 添加push
	 * @param message
	 */
	public PushMessage addMessage(PushMessage message);
	/**
	 * 修改push
	 * @param message
	 */
	public void updateMessage(PushMessage message);
	/**
	 * 删除push
	 * @param message
	 */
	public void delPush(PushMessage message);
}
