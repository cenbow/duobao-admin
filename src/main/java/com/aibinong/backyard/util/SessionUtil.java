package com.aibinong.backyard.util;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.RedisDao;
import com.aibinong.backyard.session.Session;

public class SessionUtil {
	@Inject
    private static RedisDao redisDao;
	 private static int liveTime = 24 * 3600;
    /**
     * 创建cookie
     * @param response
     * @param account
     * @param passward
     * @return
     */
	public static String generateSession(HttpServletResponse response) {
	        String uuid = UUID.randomUUID().toString();
	        Cookie c_httpCookie = new Cookie(Constants.COOKIE_SESSION_ID,uuid);
	       // c_httpCookie.setDomain(domain);
	        c_httpCookie.setPath("/");
	        c_httpCookie.setMaxAge(-1);
	        response.addCookie(c_httpCookie);
	        return uuid;
	    }
	
	public static String getSessionId(HttpServletRequest request){
		 Cookie[] cookie = request.getCookies();
	        if(cookie==null){
	        	return null;
	        }
	        for (Cookie c : cookie) {
	            if (c.getName().startsWith(Constants.COOKIE_SESSION_ID)) {
	                return c.getValue();
	            }
	        }
	        return null;
	}
    /**
     * 清楚cookie
     * @param request
     * @param response
     * @param account
     * @param password
     */
	public static void sessioninvalidate(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = (HttpSession) request.getSession();
		String sessionId = SessionUtil.getSessionId(request);
		Set<String> sessionIds = new HashSet<String>();
		Cookie[] cookies = request.getCookies();
		if (StringUtils.isNotEmpty(sessionId)&& session.getAttribute(sessionId) != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getValue().equals(sessionId)) {
					sessionIds.add(cookie.getValue());
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
			session.removeAttribute(sessionId);
			session.removeAttribute(sessionId + "_account");
		}
    			
		
	}
}
