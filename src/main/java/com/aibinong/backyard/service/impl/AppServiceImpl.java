package com.aibinong.backyard.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.App;
import com.aibinong.backyard.pojo.AppDownload;
import com.aibinong.backyard.pojo.AppRecord;
import com.aibinong.backyard.pojo.SysConfig;
import com.aibinong.backyard.service.AppService;
import com.aibinong.backyard.service.ConfigService;
import com.alibaba.fastjson.JSON;

@IocBean(name = "appService")
public class AppServiceImpl implements AppService {

	@Inject
	protected BasicDao basicDao;
	@Inject
	protected ConfigService configService;

	@Override
	public App getApp(String clientId) {
		return this.basicDao.findByCondition(App.class, Cnd.where("client_id", "=", clientId));
	}

	@Override
	public List<App> getAppList() {
		return this.basicDao.search(App.class, "gmt_create");
	}

	@Override
	public QueryResult getAppList(int page, String clientId, String name, String version, String channel, String os,
			Integer status) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT t1.client_id, t1.icon, t1.name, t1.os, t2.id, t3.name channel, t2.version, t2.url, t2.status, t2.gmt_modified\n");
		sql.append("FROM app t1 RIGHT JOIN app_download t2\n");
		sql.append("ON t1.client_id = t2.client_id\n");
		sql.append("LEFT JOIN channel t3\n");
		sql.append("ON t2.channel = t3.code\n");
		sql.append("WHERE t2.deleted = 0\n");
		if (StringUtils.isNotBlank(clientId)) {
			sql.append("AND t1.client_id = '").append(clientId).append("'\n");
		}
		if (StringUtils.isNotBlank(name)) {
			sql.append("AND t1.name like '%").append(name).append("%'\n");
		}
		if (StringUtils.isNoneBlank(os)) {
			sql.append("AND t1.os = '").append(os).append("'\n");
		}
		if (StringUtils.isNotBlank(version)) {
			sql.append("AND t2.version = '").append(version.trim()).append("'\n");
		}
		if (StringUtils.isNotBlank(channel)) {
			sql.append("AND t2.channel = '").append(channel.trim()).append("'\n");
		}
		if (status != null && status.intValue() > 0) {
			sql.append("AND t2.status = ").append(status.intValue()).append("\n");
		}
		sql.append("ORDER BY t2.id DESC");

		if (page <= 0) {
			page = 1;
		}
		return this.basicDao.querySqlResult(sql.toString(), page, Constants.DEFAULT_PAGE_SIZE);
	}

	@Aop(TransAop.READ_COMMITTED)
	@Override
	public void createApp(App app) {
		app.setSecret_key("4)d)qj+f");
		app.setGmt_create(new Date());
		app.setGmt_modified(new Date());

		this.basicDao.save(app);
	}

	@Aop(TransAop.READ_COMMITTED)
	@Override
	public void updateApp(App app) {
		Chain chain = Chain.make("name", app.getName());
		if (StringUtils.isNotBlank(app.getIcon())) {
			chain.add("icon", app.getIcon());
		}
		if (StringUtils.isNotBlank(app.getMaterial_url())) {
			chain.add("material_url", app.getMaterial_url());
		}
		chain.add("sub_title", app.getSub_title());
		chain.add("keyword", app.getKeyword());
		chain.add("description", app.getDescription());
		chain.add("ios_account", app.getIos_account());
		chain.add("ios_appid", app.getIos_appid());
		chain.add("ios_bundleid", app.getIos_bundleid());
		chain.add("android_package", app.getAndroid_package());
		chain.add("ios_umeng_appid", app.getIos_umeng_appid());
		chain.add("android_umeng_appid", app.getAndroid_umeng_appid());
		chain.add("getui_d_appid", app.getGetui_d_appid());
		chain.add("getui_d_appkey", app.getGetui_d_appkey());
		chain.add("getui_d_appmaster", app.getGetui_d_appmaster());
		chain.add("getui_d_appsecret", app.getGetui_d_appsecret());
		chain.add("getui_r_appid", app.getGetui_r_appid());
		chain.add("getui_r_appkey", app.getGetui_r_appkey());
		chain.add("getui_r_appmaster", app.getGetui_r_appmaster());
		chain.add("getui_r_appsecret", app.getGetui_r_appsecret());
		chain.add("watermark", app.getWatermark());
		chain.add("gmt_modified", new Date());

		this.basicDao.update(App.class, chain, Cnd.where("client_id", "=", app.getClient_id()));
	}

	@Aop(TransAop.READ_COMMITTED)
	@Override
	public void createDownload(AppDownload download) {
		download.setDeleted(AppDownload.NOT_DELETE);
		download.setGmt_create(new Date());
		download.setGmt_modified(new Date());

		this.basicDao.save(download);
	}

	@Override
	public AppDownload getDownload(Long id) {
		AppDownload download = this.basicDao.find(id, AppDownload.class);
		if (download != null) {
			App app = this.getApp(download.getClient_id());
			download.setApp(app);
		}
		return download;
	}

	@Override
	public AppDownload getDownloadByChannelVersion(String clientId, String channel, String versionCoce) {
		Cnd cnd = Cnd.where("client_id", "=", clientId);
		cnd.and("channel", "=", channel);
		cnd.and("version_code", "=", versionCoce);

		return this.basicDao.findByCondition(AppDownload.class, cnd);
	}

	@Override
	public AppDownload getDownloadByLast(String clientId) {
		AppDownload download = this.basicDao.findByCondition(AppDownload.class,
				Cnd.where("client_id", "=", clientId).orderBy("version_code", "desc"));
		return download;
	}

	@Aop(TransAop.READ_COMMITTED)
	@Override
	public void updateDownload(AppDownload download) {
		Chain chain = Chain.make("channel", download.getChannel());
		chain.add("version_code", download.getVersion_code());
		chain.add("version", download.getVersion());
		chain.add("url", download.getUrl());
		chain.add("status", download.getStatus());
		chain.add("reason", download.getReason());
		chain.add("gmt_modified", new Date());

		this.basicDao.update(AppDownload.class, chain, Cnd.where("id", "=", download.getId()));
	}

	@Aop(TransAop.READ_COMMITTED)
	@Override
	public void deleteDownload(AppDownload download) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE app_download SET deleted = 1\n");
		sql.append("WHERE client_id = '").append(download.getClient_id()).append("'\n");
		sql.append("AND channel = '").append(download.getChannel()).append("'\n");
		sql.append("AND version_code < ").append(download.getVersion_code());
		
		this.basicDao.executeSql(sql.toString());
	}

	@Aop(TransAop.READ_COMMITTED)
	@Override
	public void createRecord(AppRecord record, App app, AppDownload download, String adminName) {
		if (app == null) {
			app = this.getApp(record.getClient_id());
			record.setName(app.getName());
			record.setOs(app.getOs());
			record.setSub_title(app.getSub_title());
			record.setDescription(app.getDescription());
			record.setKeyword(app.getKeyword());
			record.setIcon(app.getIcon());
			record.setMaterial_url(app.getMaterial_url());
			record.setWatermark(app.getWatermark());
		}
		record.setAdmin_name(adminName);
		record.setGmt_create(new Date());
		record.setGmt_modified(new Date());

		this.basicDao.save(record);
	}

	@Override
	public QueryResult getRecordList(String clientId, int page) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT t1.id, t1.name, t1.os, t1.keyword, t1.icon, t1.material_url, t2.name channel, t1.version, t1.version_code, t1.url, t1.status, t1.admin_name, t1.gmt_create\n");
		sql.append("FROM app_record t1 LEFT JOIN channel t2\n");
		sql.append("ON t1.channel = t2.code\n");
		sql.append("WHERE t1.client_id = '").append(clientId).append("'\n");
		sql.append("ORDER BY t1.gmt_create ASC");

		if (page <= 0) {
			page = 1;
		}
		return this.basicDao.querySqlResult(sql.toString(), page, Constants.DEFAULT_PAGE_SIZE);
	}

	@Aop(TransAop.READ_COMMITTED)
	@Override
	public void createAdApp(String clientId) {
		String delete = "DELETE FROM ad_app WHERE client_id = '" + clientId + "'";
		this.basicDao.executeSql(delete);

		String insert = "INSERT INTO ad_app (ad_id,client_id) SELECT id, '" + clientId + "' FROM ads WHERE deleted = 0";
		this.basicDao.executeSql(insert);
	}

	@Aop(TransAop.READ_COMMITTED)
	@Override
	public void createWebPay(String clientId, String version) {
		Cnd cnd = Cnd.where("client_id", "=", clientId);
		cnd.and("version", "=", version);
		cnd.and("os", "=", "ios");
		cnd.and("k", "=", "webPay");

		SysConfig config = this.basicDao.findByCondition(SysConfig.class, cnd);
		if (config == null) {
			config = new SysConfig();
			config.setClient_id(clientId);
			config.setVersion(version);
			config.setOs("ios");
			config.setK("webPay");
			config.setV("http://jyapi.1yuantaohuo.com/order/wap/create_order");

			this.configService.addConfig(config);
		}
	}

	@Aop(TransAop.READ_COMMITTED)
	@Override
	public void createWebPayRecharge(String clientId, String version) {
		Cnd cnd = Cnd.where("client_id", "=", clientId);
		cnd.and("version", "=", version);
		cnd.and("os", "=", "ios");
		cnd.and("k", "=", "recharge");

		SysConfig config = this.basicDao.findByCondition(SysConfig.class, cnd);
		if (config == null) {
			config = new SysConfig();
			config.setClient_id(clientId);
			config.setVersion(version);
			config.setOs("ios");
			config.setK("recharge");
			config.setV("0");

			this.configService.addConfig(config);
		}
	}

	@Override
	public void deleteWebPay(String clientId, String version) {
		Sql sql = Sqls
				.create("DELETE FROM sys_config WHERE k = 'webPay' AND client_id = @client_id AND version = @version");
		sql.params().set("client_id", clientId);
		sql.params().set("version", version);
		this.basicDao.getDao().execute(sql);
	}

	@Override
	public void deleteWebPayRecharge(String clientId, String version) {
		Sql sql = Sqls.create(
				"DELETE FROM sys_config WHERE k = 'recharge' AND client_id = @client_id AND version = @version");
		sql.params().set("client_id", clientId);
		sql.params().set("version", version);
		this.basicDao.getDao().execute(sql);
	}

	@Override
	public void createHomeBtn(String clientId, String version) {
		Cnd cnd = Cnd.where("client_id", "=", clientId);
		cnd.and("version", "=", version);
		cnd.and("os", "=", "ios");
		cnd.and("k", "=", "homeBtn");

		SysConfig config = this.basicDao.findByCondition(SysConfig.class, cnd);
		
		if (config == null) {
			config = new SysConfig();
			config.setClient_id(clientId);
			config.setVersion(version);
			config.setOs("ios");
			config.setK("homeBtn");
		String homeString  = "[{ 'title': '分类','status': '1', 'event': {'data': {'url': 'xxx' },"
				+ " 'needLogin': '0',  'messageType': '30' },'img': 'http://new-duobao-oss.oss-cn-beijing.aliyuncs.com/img/icon/classify.png',"
				+ "'type': 'classify'},{'title': '精彩活动','status': '1', 'event': {'data': {  'url': 'http://jyapi.1yuantaohuo.com/discover/discoverlist.html' },"
				+ "'needLogin': '0', 'messageType': '9' },'img': 'http://new-duobao-oss.oss-cn-beijing.aliyuncs.com/img/icon/activities.png','type': 'activities'},"
				+ "{'title': '常见问题','status': '1',  'event': {'data': {'url': 'http://fengkuaangimg.oss-cn-beijing.aliyuncs.com/huodong/customer.html'},"
				+ "'needLogin': '0', 'messageType': '9' }, 'img': 'http://new-duobao-oss.oss-cn-beijing.aliyuncs.com/img/icon/questions.png','type': 'play'}]";
			   homeString = homeString.replace("'", "\"");
			config.setV(homeString);
			this.configService.addConfig(config);
		}
	}

	@Override
	public void deleteHomeBtn(String clientId, String version) {
		Sql sql = Sqls
				.create("DELETE FROM sys_config WHERE k = 'homeBtn' AND client_id = @client_id AND version = @version");
		sql.params().set("client_id", clientId);
		sql.params().set("version", version);
		this.basicDao.getDao().execute(sql);
	}

}
