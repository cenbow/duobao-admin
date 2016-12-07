package com.aibinong.backyard.web.module;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ForwardView;
import org.nutz.mvc.view.JspView;

import com.aibinong.backyard.dao.RedisDao;
import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.pojo.SysConfig;
import com.aibinong.backyard.service.ConfigService;

@IocBean
public class ConfigModule extends BaseModule{

	@Inject
	private ConfigService configService;
	@Inject
    protected RedisDao  redisDao;
	@At("/configlist")
	@Ok("jsp:jsp.config.configlist")
	public View  getConfigList(@Param("client_id")String client_id,@Param("k")String k,@Param("page")Integer page,HttpServletRequest request){
		if (page == null || page <= 1) {
			page = 1;
		}
		QueryResult result = configService.getConfiglist(client_id, k, page);

		if (result != null) {
			request.setAttribute("configlist", result.getList());
			request.setAttribute("pager", result.getPager());
		}
		return new JspView("jsp/config/configlist");
	}
	@At("/configdetail")
	public View getConfigDetail(@Param("configId")Long configId,HttpServletRequest request){
		 SysConfig config = configService.getConfigDetail(configId);
		 request.setAttribute("config", config);
		 return new JspView("jsp/config/configdetail");
	}
	@At("/configEdit")
	public View configEdit(@Param("..")final SysConfig config,HttpServletRequest request){
		if(config!=null && config.getId()!=null){
			configService.updateConfig(config);
			logService.operateLog(getAccount(request).getId(), "/configEdit", "编辑系统配置操作");
		}
		return new ForwardView("/configdetail.html?configId="+config.getId());
	}
	
	@At("/configIndex")
	public View configIndex(HttpServletRequest request){
		return new JspView("jsp/config/configadd");
	}
	@At("/addConfig")
	public View addConfig(@Param("..")final SysConfig config,HttpServletRequest request){
		configService.addConfig(config);
		logService.operateLog(getAccount(request).getId(), "/configEdit", "添加系统配置操作");
		return new ForwardView("/configlist");
	}
	
	@At("/configDel")
	public Object ConfigDel(@Param("configId")Long configId,HttpServletRequest request){
		SysConfig config = configService.getConfigDetail(configId);
		Map<String,String> map  = new HashMap<String, String>();
		if(config!=null){
			configService.delConfig(config);
			logService.operateLog(getAccount(request).getId(), "/configDel", "删除系统配置操作");
			map.put("code", "0");
		}
		return map;
	} 
	
	@At("/redisDel")
	public Object redisDel(@Param("key")String key,HttpServletRequest request){
		redisDao.del(key);
		logService.operateLog(getAccount(request).getId(), "/redisDel", "删除redis key键操作，key:"+key);
		Map<String,String> map  = new HashMap<String, String>();
		map.put("code", "0");
		return map;
	} 
	@At("/redisIndex")
	public View redisDelIndex(HttpServletRequest request){
		return new JspView("jsp/config/redisdel");
	} 
	
	
}
