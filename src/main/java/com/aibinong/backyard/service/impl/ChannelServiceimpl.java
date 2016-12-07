package com.aibinong.backyard.service.impl;

import java.util.List;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.Channel;
import com.aibinong.backyard.service.ChannelService;
@IocBean(name = "channelService")
public class ChannelServiceimpl implements ChannelService {
	@Inject
    protected BasicDao basicDao;
	@Override
	public List<Channel> getChannelList() {
		// TODO Auto-generated method stub
		 List<Channel> channellist =  basicDao.search(Channel.class, Cnd.orderBy().asc("code"));
		return channellist;
	}
	@Override
	public Channel getChannel(String code) {
		// TODO Auto-generated method stub
		 Condition con = Cnd.where("code","=",code);
         Channel channel= basicDao.findByCondition(Channel.class, con);
         return channel;
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void updateChannel(Channel channel) {
		// TODO Auto-generated method stub
		Condition con = Cnd.where("code","=",channel.getCode());
		basicDao.update(Channel.class,Chain.make("name", channel.getName()),con);
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public Channel saveChannel(Channel channel) {
		// TODO Auto-generated method stub
		channel=basicDao.save(channel);
		return channel;
	}

}
