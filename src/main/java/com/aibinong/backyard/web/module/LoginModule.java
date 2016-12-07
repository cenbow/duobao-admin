package com.aibinong.backyard.web.module;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.service.AccountService;
import com.aibinong.backyard.service.RoleService;
import com.aibinong.backyard.util.SessionUtil;

/**
 * Created by lvfugang on 16/6/29.
 */
@IocBean
public class LoginModule extends BaseModule {

	private static Logger logger = LoggerFactory.getLogger(LoginModule.class);

	@Inject
	protected AccountService accountService;
	@Inject
	protected RoleService roleService;

	@At("/login")
	@Ok("jsp:jsp.index")
	@Fail("jsp:jsp.login")
	public JspView login(@Param("username") String username, @Param("password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
				return new JspView("jsp/login");
			}
			ManageAccount account = accountService.findAccount(username, password);
			if (account != null) {
				if(account.getStatus()==Constants.DUOBAO_ACCOUNT_DELETED_STATUS){
					request.setAttribute("errmsg", "您的用户已被锁定请联系管理员");
					return new JspView("jsp/login");
				}
				logger.info("登录人员：" + account.getAccount() + ",登录成功！");
				
				String sessionId = SessionUtil.getSessionId(request);
				if (sessionId == null) {
					sessionId = SessionUtil.generateSession(response);
				}
				HttpSession session = (HttpSession) request.getSession();
				session.setAttribute(sessionId, account.getId());
				session.setAttribute(sessionId + "_account", account);
				session.setMaxInactiveInterval(60 * 60);
				request.setAttribute("account", account);
				
				logService.operateLog(account.getId(), "/login", "登录操作");
				return new JspView("jsp/index");
			} else {
				request.setAttribute("errmsg", "用户名或密码错误");
				return new JspView("jsp/login");
			}
		} catch (Exception e) {
			logger.error("登录失败", e);
			return new JspView("jsp/login");
		}
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @param response
	 */
	@At("/logout")
	public JspView logout(HttpServletRequest request, HttpServletResponse response) {
		SessionUtil.sessioninvalidate(request, response);
		return new JspView("jsp/login");
	}

}
