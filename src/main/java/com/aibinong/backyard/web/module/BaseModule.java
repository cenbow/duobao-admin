package com.aibinong.backyard.web.module;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.service.AccountService;
import com.aibinong.backyard.service.LogService;
import com.aibinong.backyard.util.SessionUtil;

@IocBean
public class BaseModule {
	@Inject
    protected AccountService accountService;
	@Inject
	protected LogService logService;
	private ManageAccount account;
	public ManageAccount getAccount(HttpServletRequest request){
		String sessionId = SessionUtil.getSessionId(request);
		if(StringUtils.isNotEmpty(sessionId)){
			if(request.getSession().getAttribute(sessionId)!=null){
			    account = (ManageAccount)request.getSession().getAttribute(sessionId+"_account");
				return account;
			}
		}
		return null;
	}
	
	public ManageAccount getAccount() {
		return account;
	}
	public void setAccount(ManageAccount account) {
		this.account = account;
	}
	
}
