package com.aibinong.backyard.web;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.aibinong.backyard.web.filter.SecurityFilter;

/**
 * Created by ouwa on 16/2/16.
 */

@IocBean
@SetupBy(value=MainSetup.class)
@IocBy(type = ComboIocProvider.class,
        args = {"*json",
                "ioc/",
                "*annotation",
                 "com.aibinong.backyard",
                "*org.nutz.integration.quartz.QuartzIocLoader",
                "*org.nutz.aop.interceptor.ioc.TransIocLoader"
        })
@Ok("json:full")
@Modules(scanPackage=true)
@Localization("msg")
@Filters({@By(type = SecurityFilter.class)})
public class MainModule {
    private static final Log log = Logs.get();

    @Inject
    protected Dao dao;

    @At("/index")
    @Ok("jsp:jsp.login") // 真实路径是 /WEB-INF/jsp/index.jsp
    public void index(HttpServletRequest request) {
    }
}
