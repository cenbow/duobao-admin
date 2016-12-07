package com.aibinong.backyard.service;

import java.util.List;

import com.aibinong.backyard.pojo.Channel;

public interface ChannelService {
	/**
	 * 获取渠道
	 * @return
	 */
	public List<Channel> getChannelList();
	/**
	 * 获取某个Channel
	 * @param code
	 * @return
	 */
	public Channel getChannel(String code);
	/**
	 * 修改
	 * @param channel
	 */
	public void updateChannel(Channel channel);
	/**
	 * save
	 * @param channel
	 */
	public Channel saveChannel(Channel channel);
}
