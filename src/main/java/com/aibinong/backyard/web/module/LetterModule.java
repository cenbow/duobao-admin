package com.aibinong.backyard.web.module;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.util.RedirectView;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ForwardView;
import org.nutz.mvc.view.JspView;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.pojo.Letter;
import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.service.AppService;
import com.aibinong.backyard.service.LetterService;

@IocBean
public class LetterModule extends BaseModule{
	@Inject
	private LetterService  letterService;
	@At("/letterList")
	public View getLetterList(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("page") Integer page,HttpServletRequest request){
		if (page == null) {
			page = 1;
		}
		if (startTime != null) {
			request.setAttribute("startTime", startTime);
		}
		if (endTime != null) {
			request.setAttribute("endTime", endTime);
		}
		QueryResult result = letterService.getLetterList(startTime, endTime,page);
		if (result != null) {
			request.setAttribute("letterlist", result.getList());
			request.setAttribute("page", result.getPager());
		}
		return new JspView("jsp/letter/letterlist");
	}
	
	@At("/createLetter")
	@Ok("redirect:/letterList")
	public void createLetter(@Param("title")String title,@Param("send_time")Date send_time,@Param("event_type")String event_type,
			@Param("target_id")String target_id,@Param("userId")String userId,@Param("target_url")String target_url,@Param("content")String content,HttpServletRequest request){
		Letter letter = new Letter();
		letter.setContent(content);
		letter.setTitle(title);
		if (StringUtils.isNotEmpty(userId)) {
			letter.setDest(userId);
		} else {
			letter.setDest(Constants.LETTER_DEFAULT_SEND_TYPE);
		}
		letter.setSend_time(send_time);
		if (StringUtils.isNotEmpty(event_type)) {
			letter.setEvent_type(Integer.parseInt(event_type));
		}
		if (StringUtils.isNotEmpty(target_url)) {
			letter.setTarget_url(target_url);
		}
		if (StringUtils.isNotEmpty(target_id)) {
			letter.setTarget_id(target_id);
		}
		letter.setGmt_create(new Date());
		letter.setGmt_modified(new Date());
		letterService.addLetter(letter);
		logService.operateLog(getAccount(request).getId(), "/createLetter", "创建站内信操作");
	}
	@At("/addletter")
	public View addletter(HttpServletRequest request){
		return new JspView("jsp/letter/createLetter");
	}
	
	@At("/letterDetail")
	public View getLetterDetail(@Param("letterId")Long letterId,HttpServletRequest request){
		Letter letter = letterService.getLetterDetail(letterId);
		request.setAttribute("letter", letter);
		return new JspView("jsp/letter/letterDetail");
	}
	
	@At("/updateLetter")
	public void updateLetter(@Param("id")Long id,@Param("title")String title,@Param("send_time")String send_time,@Param("event_type")String event_type,
			@Param("target_id")String target_id,@Param("userId")String userId,@Param("target_url")String target_url,@Param("content")String content,HttpServletRequest request,HttpServletResponse response){
		
		Letter letter = letterService.getLetterDetail(id);
		if (letter != null) {
			if (StringUtils.isNotEmpty(title)) {
				letter.setTitle(title);
			}
			if (StringUtils.isNotEmpty(userId)) {
				letter.setDest(userId);
			} else {
				letter.setDest(Constants.LETTER_DEFAULT_SEND_TYPE);
			}
			if (StringUtils.isNotEmpty(event_type)) {
				letter.setEvent_type(Integer.parseInt(event_type));
			}
			if (StringUtils.isNotEmpty(target_id)) {
				letter.setTarget_id(target_id);
			}
			if (StringUtils.isNotEmpty(target_url)) {
				letter.setTarget_url(target_url);
			}
			if (StringUtils.isNotEmpty(content)) {
				letter.setContent(content);
			}
			if (StringUtils.isNotEmpty(send_time)) {
				SimpleDateFormat formart = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				try {
					letter.setSend_time(formart.parse(send_time));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			letter.setGmt_modified(new Date());
			letterService.updateLetter(letter);
			logService.operateLog(getAccount(request).getId(), "/updateLetter", "修改站内信操作");
		}
		try {
			response.sendRedirect("/letterDetail.html?letterId="+id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
