package com.aibinong.backyard.web.module;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.pojo.App;
import com.aibinong.backyard.pojo.PushMessage;
import com.aibinong.backyard.service.AppService;
import com.aibinong.backyard.service.PushMessageService;

@IocBean
public class PushMessageModule extends BaseModule{
	@Inject
	private PushMessageService pushMessageService;
	@Inject
	private AppService appService;
	private static Logger logger = LoggerFactory.getLogger(PushMessageModule.class);
	@At("/pushlist")
	public View getPushMessageList(@Param("startTime") Date startTime,@Param("endTime") Date endTime,Integer page,HttpServletRequest request){
		
		if(page==null){
			page=1;
		}
		QueryResult result = 	pushMessageService.getPushMessageList(startTime, endTime, page);
		request.setAttribute("pushlist", result.getList());
		request.setAttribute("page", result.getPager());
		return new JspView("jsp/push/pushlist");
	}
	
	@At("/addPush")
	public View addPush(HttpServletRequest request){
		List<App> applist =appService.getAppList();
		request.setAttribute("applist", applist);
		return  new JspView("jsp/push/createPush");
	}
	@At("/insertPush")
	public void insertPush(@Param("dest")String[] dest,@Param("send_time")Date send_time,@Param("event_type")String event_type,
			@Param("target_id")String target_id,@Param("target_url")String target_url,@Param("content")String content,HttpServletRequest request,HttpServletResponse response){
		PushMessage push =new PushMessage();
		push.setContent(content);
		if(StringUtils.isNotEmpty(event_type)){
			push.setEvent_type(Integer.parseInt(event_type));
		}
		push.setTarget_id(target_id);
		push.setTarget_url(target_url);
		push.setType(Constants.DUOBAO_PUSH_MANY_CLIENT_ID_TYPE);
		push.setGmt_create(new Date());
		if(dest!=null && dest.length>0){
			StringBuffer dest_Str= new StringBuffer();
			for (String string : dest) {
				dest_Str.append(string).append(",");
			}
			push.setDest(dest_Str.toString().substring(0, dest_Str.toString().lastIndexOf(",")));
		}
		
		push.setStatus(0);
		push.setSend_time(send_time);
		push=pushMessageService.addMessage(push);
		logService.operateLog(getAccount(request).getId(), "/insertPush", "创建PUSH操作");
		try {
			response.sendRedirect("/pushDetail.html?pushId="+push.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@At("/pushEdit")
	public void pushEdit(@Param("pushId")Long pushId,@Param("dest")String dest,@Param("send_time")Date send_time,@Param("event_type")String event_type,
			@Param("target_id")String target_id,@Param("target_url")String target_url,@Param("content")String content,HttpServletRequest request,HttpServletResponse response){
		PushMessage push =pushMessageService.getMessageById(pushId);
		if(push!=null){
			if(StringUtils.isNotEmpty(content)){
				push.setContent(content);
			}
			if(StringUtils.isNotEmpty(event_type)){
				push.setEvent_type(Integer.parseInt(event_type));
			}
			if(StringUtils.isNotEmpty(target_id)){
				push.setTarget_id(target_id);
			}
			if(StringUtils.isNotEmpty(target_url)){
				push.setTarget_url(target_url);
			}
			if(StringUtils.isNotEmpty(dest)){
				push.setDest(dest);
			}
			if(send_time!=null){
				push.setSend_time(send_time);
			}
			
			pushMessageService.updateMessage(push);
			logService.operateLog(getAccount(request).getId(), "/pushEdit", "修改PUSH操作");
		}
		
		try {
			response.sendRedirect("/pushDetail.html?pushId="+push.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@At("/pushDetail")
	public View getPushDetail(@Param("pushId")Long pushId,HttpServletRequest request){
		List<App> applist = appService.getAppList();
		request.setAttribute("applist", applist);
		PushMessage push = pushMessageService.getMessageById(pushId);
		if (push != null) {
			request.setAttribute("push", push);
		}
		return  new JspView("jsp/push/pushDetail");
	}
	@At("/delPush")
	public Object delPush(@Param("id")Long pushId,HttpServletRequest request){
		PushMessage push =pushMessageService.getMessageById(pushId);
		Map<String,String> map  = new HashMap<String, String>();
		if(push!=null){
			pushMessageService.delPush(push);
			logService.operateLog(getAccount(request).getId(), "/delPush", "删除PUSH操作");
			map.put("code", "0");
		}
		
		return map;
	}
}
