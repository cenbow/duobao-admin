package com.aibinong.backyard.web.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.JspView;

import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.util.SessionUtil;

public class SecurityFilter implements ActionFilter{
    public View match(ActionContext actionContext) {
		HttpServletRequest request = actionContext.getRequest();
		String requestURI = request.getRequestURI();
		if (requestURI.contains("/login.html") || requestURI.contains("/index.html") || requestURI.contains("/logout.html")) {
			return null;
		}

		String sessionId = SessionUtil.getSessionId(request);
		if (StringUtils.isNotEmpty(sessionId)) {
			if (request.getSession().getAttribute(sessionId) != null) {
				ManageAccount account = (ManageAccount) request.getSession().getAttribute(sessionId + "_account");
				if (account != null) {
					request.setAttribute("account", account);
					return null;
				}
			}
		}
		return new JspView("jsp/login");
	}
}
