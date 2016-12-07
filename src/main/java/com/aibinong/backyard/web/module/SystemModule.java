package com.aibinong.backyard.web.module;

import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.aibinong.backyard.service.SystemService;
@IocBean
public class SystemModule {
	@Inject
	private SystemService systemService;
	@At("/checkService")
	@Ok("json")
	public Map checkService(){
		return systemService.serviceMonitor(); 
	}
	
}
