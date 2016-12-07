package com.aibinong.backyard.web.module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ForwardView;
import org.nutz.mvc.view.JspView;

import com.aibinong.backyard.pojo.Channel;
import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.service.ChannelService;

/**
 * Created by lvfugang on 16/6/29.
 */

@IocBean
public class ChannelModule extends BaseModule{
    @Inject
    private ChannelService channelService;
    @At("/channellist")
    public View channellist(HttpServletRequest request){
        List<Channel> channellist =  channelService.getChannelList();

        request.setAttribute("channellist",channellist);
        return new JspView("jsp/channel_list");
    }

    @At("/editChannel")
    @Ok("jsp:jsp.channel_edit")
    public View editChannel(@Param("code")String code, HttpServletRequest request){
		if (StringUtils.isEmpty(code)) {
			return new JspView("jsp/error");
		}
		Channel channel = channelService.getChannel(code);
		request.setAttribute("channel", channel);
        return new JspView("jsp/channel_edit");
    }
    
    @At("/updateChannel")
    public View updateChannel(@Param("code")String code,@Param("title")String title,HttpServletRequest request){
		if (StringUtils.isNotEmpty(code)) {
			Channel channel = channelService.getChannel(code);
			if (channel != null) {
				channel.setName(title);
				channelService.updateChannel(channel);
			}
			request.setAttribute("channel", channel);
		}
    	logService.operateLog(getAccount(request).getId(), "/updateChannel", "编辑渠道操作");
    	return new JspView("jsp/channel_edit");
    }
    @At("/saveChannel")
    public View saveChannel(@Param("code")String code,@Param("title")String title,HttpServletRequest request){
		
		if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(code)) {
			Channel channel = new Channel();
			channel.setCode(code);
			channel.setName(title);
			channel = channelService.saveChannel(channel);
			request.setAttribute("channel", channel);
		}
    	logService.operateLog(getAccount(request).getId(), "/saveChannel", "添加渠道操作");
    	return new JspView("jsp/channel_edit");
    }
    @At("/addChannel")
    public View addChannelIndex(HttpServletRequest request){
    	return new JspView("jsp/createChannel");
    }
}
