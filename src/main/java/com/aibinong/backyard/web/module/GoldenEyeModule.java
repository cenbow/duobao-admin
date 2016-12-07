package com.aibinong.backyard.web.module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;

import com.aibinong.backyard.pojo.AlarmBalance;
import com.aibinong.backyard.pojo.AlarmConf;
import com.aibinong.backyard.service.GoldenEyeService;

/**
 * 黄金眼监控模块
 * 
 * @author wang.yan
 * @version 1.0
 */
@IocBean
public class GoldenEyeModule extends BaseModule {

	@Inject
	private GoldenEyeService goldenEyeService;

	/**
	 * 配置列表
	 * 
	 * @param request
	 * @return
	 */
	@At("/alarmConfigList")
	public View alarmConfigList(HttpServletRequest request) {
		List<AlarmConf> configList = this.goldenEyeService.getConfigList();
		request.setAttribute("config_list", configList);
		return new JspView("jsp/goldeneye/config_list");
	}

	/**
	 * 配置信息编辑页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@At("/alarmConfigEditPage")
	public View alarmConfigEditPage(@Param("id") long id, HttpServletRequest request) {
		AlarmConf config = this.goldenEyeService.getConfig(id);
		request.setAttribute("config", config);
		return new JspView("jsp/goldeneye/config_edit");
	}

	/**
	 * 更新配置信息
	 * 
	 * @param config
	 * @param request
	 * @return
	 */
	@At("/updateAlarmConfig")
	public View updateAlarmConfig(@Param("..") AlarmConf config, HttpServletRequest request) {
		try {
			this.goldenEyeService.updateConfig(config);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errmsg", "修改失败，请稍后再试");
			return new JspView("jsp/error_msg");
		}
		return new ServerRedirectView("alarmConfigList.html");
	}
	
	/**
	 * 记录列表
	 * 
	 * @param type
	 * @param page
	 * @param request
	 * @return
	 */
	@At("/alarmRecordList")
	public View alarmRecordList(@Param("type") Integer type, @Param("page") int page, HttpServletRequest request) {
		QueryResult result = this.goldenEyeService.getRecordList(type, page);

		List<AlarmConf> configList = this.goldenEyeService.getConfigList();

		request.setAttribute("record_list", result.getList());
		request.setAttribute("pager", result.getPager());
		request.setAttribute("type", type);
		request.setAttribute("page", page);
		request.setAttribute("config_list", configList);

		return new JspView("jsp/goldeneye/record_list");
	}

	/**
	 * 余量列表
	 * 
	 * @param request
	 * @return
	 */
	@At("/alarmBalanceList")
	public View balanceList(HttpServletRequest request) {
		List<AlarmBalance> balanceList = this.goldenEyeService.getBalanceList();
		request.setAttribute("balance_list", balanceList);
		return new JspView("jsp/goldeneye/balance_list");
	}

}
