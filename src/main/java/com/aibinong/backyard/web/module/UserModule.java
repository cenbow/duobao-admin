package com.aibinong.backyard.web.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;

import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.pojo.Period;
import com.aibinong.backyard.pojo.User;
import com.aibinong.backyard.pojo.UserAccountRecord;
import com.aibinong.backyard.pojo.UserDuobaoRecord;
import com.aibinong.backyard.service.AccountService;
import com.aibinong.backyard.service.UserService;
import com.aibinong.backyard.util.MD5Util;

/**
 * Created by ouwa on 16/6/28.
 */
@IocBean
public class UserModule extends BaseModule{
    @Inject
    private UserService userService;
    @Inject
    protected AccountService accountService;
    @At("/listUser")
    public View listUser(
                     @Param("mobile")String mobile,
                     @Param("user_id")String user_id,
                     @Param("page")int page,
                     HttpServletRequest request) {

        if(page <= 1){
            page = 1;
        }

        QueryResult result= userService.getUserList(mobile,user_id,page);

        request.setAttribute("userList", result.getList());
        request.setAttribute("mobile", mobile);
        request.setAttribute("user_id", user_id);
        request.setAttribute("pager", result.getPager());
        return new JspView("jsp/user_list");
    }

    @At("/listUserRecharge")
    public View listUserRecharge(@Param("userId")Long userId,
                     HttpServletRequest request) {
        if(userId != null) {
        	 User user = userService.getUserById(userId);
        	 List<UserAccountRecord> userAccountRecordList =	userService.getAccountRecordList(userId);

            request.setAttribute("userAccountRecordList", userAccountRecordList);
            request.setAttribute("user", user);
        }
        return new JspView("jsp/user_recharge_list");
    }

    @At("/listUserDuobao")
    @Ok("jsp:jsp.user_duobao_list")
    public View listUserDuobao(@Param("userId")Long userId,HttpServletRequest request) {
        if(userId != null) {
            User user =userService.getUserById(userId);

            List<Record> list = userService.getUserDuobaoRecord(userId,null);
            Map<Long, Period> periodMap = new HashMap<Long, Period>();
            Map<Long, List<UserDuobaoRecord>> userDuobaoMap = new HashMap<Long, List<UserDuobaoRecord>>();
            for (Record record : list) {
                Long period_id = (Long)record.get("period_id");
                String list_img = record.getString("list_img");
                String title = record.getString("title");
                Integer status = record.getInt("status");
                Date start_time = (Date)record.get("start_time");
                Date award_time = (Date)record.get("award_time");
                String lucky_code = record.getString("lucky_code");

                Long user_id = (Long)record.get("user_id");
               // String user_nick = record.getString("nick");
                Integer buy_count = record.getInt("y_count");
                String buy_code = record.getString("buy_code");
                Integer lucky = record.getInt("lucky");
                Date buy_time = (Date)record.get("buy_time");

                Period period = new Period();
                period.setId(period_id);
                period.setList_img(list_img);
                period.setTitle(title);
                period.setStatus(status);
                period.setStart_time(start_time);
                period.setAward_time(award_time);
                period.setLucky_code(lucky_code);
                if(periodMap.get(period_id) == null){
                    periodMap.put(period_id, period);
                }


                UserDuobaoRecord userDuobaoRecord = new UserDuobaoRecord();
                userDuobaoRecord.setUser_id(user_id);
               // userDuobaoRecord.setUser_nick(user_nick);
                userDuobaoRecord.setBuy_count(buy_count);
                userDuobaoRecord.setBuy_code(buy_code);
                userDuobaoRecord.setLucky(lucky);
                userDuobaoRecord.setGmt_create(buy_time);

                if(userDuobaoMap.get(period_id) != null){
                    userDuobaoMap.get(period_id).add(userDuobaoRecord);
                }else{
                    List<UserDuobaoRecord> userDuobaoRecordList = new ArrayList<UserDuobaoRecord>();
                    userDuobaoRecordList.add(userDuobaoRecord);
                    userDuobaoMap.put(period_id, userDuobaoRecordList);
                }
            }
            request.setAttribute("user", user);
            request.setAttribute("periodMap", periodMap);
            request.setAttribute("userDuobaoMap", userDuobaoMap);
        }
        return new JspView("jsp/user_duobao_list");
    }
    
    @At("/getBuyRecord")
    public View getBuyRecord(@Param("id")Long id,@Param("userId")Long userId,HttpServletRequest request){
    	List<Record> list= userService.getUserDuobaoLuckyByPeriod(id,userId);
    	StringBuffer str = new StringBuffer();
    	String[] buy_code_str=null;
    	if(list!=null){
    		for (Record record : list) {
				str.append(record.getString("buy_code")).append(",");
			}
    		if(StringUtils.isNotEmpty(str.toString())){
    			buy_code_str=str.toString().split(",");
    		}
    		request.setAttribute("buy_code_list", buy_code_str);
    	}
    	return new JspView("jsp/codelist");
    }
    
    @At("/changePassWord")
    public JspView changePassWord(HttpServletRequest request){
    	
     return new JspView("jsp/password");
    }
    
    
    @At("/updatePassWord")
    public JspView updatePassWord(@Param("accountId")Long accountId, @Param("oldPass") String oldPass,
    		@Param("newPass") String newPass,HttpServletRequest request){
    	if(accountId==null){
    		request.setAttribute("msg", "参数错误");
    		return new JspView("jsp/password");
    	}
    	if(StringUtils.isEmpty(oldPass)){
    		request.setAttribute("msg", "请填写旧密码");
    		return new JspView("jsp/password");
    	}
    	if(StringUtils.isEmpty(newPass)){
    		request.setAttribute("msg", "请填写新密码");
    		return new JspView("jsp/password");
    	}
    	if(oldPass.equals(newPass)){
    		request.setAttribute("msg", "新密码不能跟旧密码一样");
    		return new JspView("jsp/password");
    	}
    	
    	ManageAccount account=accountService.getAccountById(accountId);
    	if(account!=null){
    		if(!(MD5Util.getMD5String(oldPass).toUpperCase()).equals(account.getPassword().toUpperCase())){
    			request.setAttribute("msg", "老密码不正确请确认");
        		return new JspView("jsp/password");
    		}
    		account.setPassword(MD5Util.getMD5String(newPass));
    		accountService.updateAccount(account);
    	    return new JspView("jsp/login");
    	}else{
    		request.setAttribute("msg", "用户不存在");
    		return new JspView("jsp/password");
    	}
    	
    }
}
