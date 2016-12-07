package com.aibinong.backyard.web.module;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;
import org.nutz.mvc.view.ForwardView;
import org.nutz.mvc.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.pojo.AdApp;
import com.aibinong.backyard.pojo.Ads;
import com.aibinong.backyard.pojo.App;
import com.aibinong.backyard.service.AdsService;
import com.aibinong.backyard.service.AppService;
import com.aibinong.backyard.service.UploadService;

/**
 * Created by lvfugang on 16/7/5.
 */
@IocBean
public class AdsModule extends BaseModule{
	@Inject
	private AdsService adsService;
	@Inject
	private UploadService uploadService;
	@Inject
	private AppService appService;
	private static Logger logger = LoggerFactory.getLogger(AdsModule.class);

	/**
	 * 广告列表
	 * @param client_id
	 * @param page
	 * @param request
	 */
	@At("/adslist")
	public View adsList(@Param("client_id")String client_id,@Param("page") Integer page,HttpServletRequest request){
		List<App> applist = appService.getAppList();
		request.setAttribute("applist", applist);
		if (page == null || page <= 1) {
			page = 1;
		}
		if (StringUtils.isNotEmpty(client_id)
				&& Constants.PARAM_SYS_DEFAULT_VALUE.equals(client_id)) {
			client_id = null;
		}
		QueryResult result = adsService.getAdsList(client_id, page);
		request.setAttribute("adslist", result.getList());
		request.setAttribute("pager", result.getPager());
		if (StringUtils.isNotEmpty(client_id)) {
			request.setAttribute("clientId", client_id);
		}
		return new JspView("jsp/ads/adslist");
	}

	/**
	 * 添加广告跳转
	 * @param client_id
	 * @param request
	 */
	@At("/adsinsert")
	public View insertAdsIndex(@Param("client_id") String client_id,HttpServletRequest request){
		List<App> applist = appService.getAppList();
		request.setAttribute("applist", applist);
		return new JspView("jsp/ads/ads_add");
	}

	/**
	 * 添加广告
	 * @param img
	 * @param click_type
	 * @param target_id
	 * @param client_id
	 * @param target_url
	 * @param weight
	 * @return
	 */
	@At("/adsadd")
	@AdaptBy(type = UploadAdaptor.class) 
	@Ok("redirect:/adslist.html")
	public void adsadd(@Param("img")File img,@Param("click_type")String click_type,@Param("pid")String pid,@Param("target_id")String target_id,@Param("client_id")String[] client_id,@Param("target_url")String target_url,@Param("weight")String weight,HttpServletRequest request){
		try {
			String path = uploadService.uploadFile(img);
			Ads ads = new Ads();
			ads.setMessage_type(Integer.parseInt(click_type));
			if (Constants.DUOBAO_ADS_EVENT_CLICK_LOCATION.equals(click_type)) {
				ads.setTarget_url(target_url);
			} 
			if (Constants.DUOBAO_ADS_EVENT_CLICK_DETAI.equals(click_type)) {
				ads.setTarget_id(target_id);
			}
			if (client_id.length > 0) {
				ads.setClientIds(client_id);
			}
			ads.setImg(path);
			if (StringUtils.isEmpty(weight)) {
				weight = Constants.BACKYARD_DEFAULT_WEIGHT + "";
			}
			ads.setPid(pid);
			ads.setWeight(Integer.parseInt(weight));
			ads.setDeleted(Constants.DUOBAO_ADS_NORMAL_STATUS);
			ads.setGmt_create(new Date());
			ads.setGmt_modified(new Date());
			ads.setTarget_need_login(Constants.NEED_LOGIN_TARGET);
			adsService.insertAd(ads);
			logService.operateLog(getAccount(request).getId(), "/adsadd","添加广告操作");
			logger.info("添加广告成功，操作人员：" + getAccount(request).getAccount());
		} catch (Exception e) {
			logger.info("添加广告异常:"+e);
		}
	}

	/**
	 * 编辑广告--跳转
	 * @param adsId
	 * @param request
	 */
	@At("/adsEdit")
	public View adsEdit(@Param("adsId") Long adsId,HttpServletRequest request){
		Ads ads = adsService.getAdById(adsId);
		if (ads != null) {
			request.setAttribute("ads", ads);
			List<AdApp> adslist = adsService.getAppAdList(adsId);
			Map<String, AdApp> map = new HashMap<String, AdApp>();
			if (adslist != null && !adslist.isEmpty()) {
				for (AdApp appds : adslist) {
					map.put(appds.getClient_id(), appds);
				}
				request.setAttribute("appMap", map);
			}
		}
		List<App> applist = appService.getAppList();
		request.setAttribute("applist", applist);
		return new JspView("jsp/ads/ads_edit");
	}

	/**
	 * 编辑广告
	 * @param img
	 * @param click_type
	 * @param target_id
	 * @param client_id
	 * @param target_url
	 * @param weight
	 * @param id
	 * @return
	 */
	@At("/adsupdate")
	@AdaptBy(type = UploadAdaptor.class)  
	public void adsUpdate(@Param("img")File img,@Param("click_type")String click_type,@Param("target_id")String target_id,
			@Param("client_id")String[] client_id,@Param("target_url")String target_url,@Param("pid")String pid,@Param("weight")String weight,@Param("id")Long id,HttpServletRequest request,HttpServletResponse response){

		Ads ads = adsService.getAdById(id);
		try {
			if (ads != null) {
				if (StringUtils.isNotEmpty(click_type)) {
					ads.setMessage_type(Integer.parseInt(click_type));
					if (Constants.DUOBAO_ADS_EVENT_CLICK_LOCATION.equals(click_type)) {
						if (StringUtils.isNotEmpty(target_url)) {
							ads.setTarget_url(target_url);
							ads.setTarget_id("");
						}
					} else {
						if (StringUtils.isNotEmpty(target_id)) {
							ads.setTarget_id(target_id);
							ads.setTarget_url("");
						}
					}
				}
				if (StringUtils.isNotEmpty(weight)) {
					ads.setWeight(Integer.parseInt(weight));
				}
				if (client_id != null) {
					ads.setClientIds(client_id);
				}
				if (img != null) {
					String path = uploadService.uploadFile(img);
					ads.setImg(path);
				}
				if (StringUtils.isNotEmpty(pid)) {
					ads.setPid(pid);
				}

				adsService.updateAd(ads);
				logService.operateLog(getAccount(request).getId(),"/adsupdate", "编辑广告操作");
				logger.info("编辑广告成功，操作人员：" + getAccount(request).getAccount());
			}
			response.sendRedirect("/adsEdit.html?adsId=" + id);
		} catch (Exception e) {
			logger.info("修改广告异常：" + e);
			try {
				response.sendRedirect("/adsEdit.html?adsId=" + id);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	/**
	 * 删除广告
	 * @param id
	 * @return
	 */
	@At("/adsDel")
	@Ok("json")
	public Object adsDel(@Param("id")Long id,HttpServletRequest request){
		Ads ads = adsService.getAdById(id);
		Map<String,String> map  = new HashMap<String, String>();
		if(ads!=null){
			ads.setDeleted(Constants.DUOBAO_ADS_DELETE_STATUS);
			adsService.adsDel(ads);
			logger.info("删除广告操作，操作人员："+getAccount(request).getAccount());
			map.put("code", "0");
			logService.operateLog(getAccount(request).getId(), "/adsDel", "删除广告操作");
		}
		return map;
	}
	
}
