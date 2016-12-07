package com.aibinong.backyard.service;

import java.util.List;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.App;
import com.aibinong.backyard.pojo.AppDownload;
import com.aibinong.backyard.pojo.AppRecord;

public interface AppService {

	/**
	 * APP对象
	 * 
	 * @param clientId
	 * @return
	 */
	App getApp(String clientId);

	/**
	 * APP列表
	 * 
	 * @return
	 */
	List<App> getAppList();

	/**
	 * APP列表
	 * 
	 * @param page
	 * @param clientId
	 * @param name
	 * @param version
	 * @param channel
	 * @param os
	 * @param status
	 * @return
	 */
	QueryResult getAppList(int page, String clientId, String name, String version, String channel, String os,
			Integer status);

	/**
	 * 创建APP
	 * 
	 * @param app
	 */
	void createApp(App app);

	/**
	 * 更新APP
	 * 
	 * @param app
	 */
	void updateApp(App app);

	/**
	 * 获取版本信息
	 * 
	 * @param id
	 * @return
	 */
	AppDownload getDownload(Long id);

	/**
	 * 根据版本号和渠道获取数据是否存在
	 * 
	 * @param clientId
	 * @param channel
	 * @param versionCoce
	 * @return
	 */
	AppDownload getDownloadByChannelVersion(String clientId, String channel, String versionCoce);

	/**
	 * 获取最新版本信息
	 * 
	 * @param clientId
	 * @return
	 */
	AppDownload getDownloadByLast(String clientId);

	/**
	 * 创建版本信息
	 * 
	 * @param download
	 */
	void createDownload(AppDownload download);

	/**
	 * 更新版本信息
	 * 
	 * @param download
	 */
	void updateDownload(AppDownload download);

	/**
	 * 删除低版本信息
	 * 
	 * @param download
	 */
	void deleteDownload(AppDownload download);

	/**
	 * 创建操作记录
	 * 
	 * @param record
	 * @param app
	 * @param download
	 * @param adminName
	 */
	void createRecord(AppRecord record, App app, AppDownload download, String adminName);

	/**
	 * 记录列表
	 * 
	 * @param clientId
	 * @param page
	 * @return
	 */
	public QueryResult getRecordList(String clientId, int page);

	/**
	 * 创建广告关联
	 * 
	 * @param clientId
	 */
	void createAdApp(String clientId);

	/**
	 * 创建网页支付
	 * 
	 * @param clientId
	 * @param version
	 */
	void createWebPay(String clientId, String version);
	/**
	 * 创建首页按钮
	 * @param clientId
	 * @param version
	 */
	void createHomeBtn(String clientId, String version);
	/**
	 * 创建充值按钮
	 * 
	 * @param clientId
	 * @param version
	 */
	void createWebPayRecharge(String clientId, String version);

	/**
	 * 删除网页支付
	 * 
	 * @param clientId
	 * @param version
	 */
	void deleteWebPay(String clientId, String version);

	/**
	 * 删除充值按钮
	 * 
	 * @param clientId
	 * @param version
	 */
	void deleteWebPayRecharge(String clientId, String version);
	/**
	 * 删除某版本首页按钮
	 * @param clientId
	 * @param version
	 */
	void deleteHomeBtn(String clientId, String version);
}
