package com.aibinong.backyard.pojo;

import java.util.Date;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("app")
public class App {

	public static final String IOS = "ios";
	public static final String ANDROID = "android";

	@Column
	private String client_id;
	@Column
	private String secret_key;
	@Column
	private String name;
	@Column
	private String os;
	@Column
	private String sub_title;
	@Column
	private String keyword;
	@Column
	private String description;
	@Column
	private String icon;
	@Column
	private String material_url;
	@Column
	private String comp_icon;
	@Column
	private String comp_remark;
	@Column
	private String ios_account;
	@Column
	private String ios_appid;
	@Column
	private String ios_bundleid;
	@Column
	private String android_package;
	@Column
	private String ios_umeng_appid;
	@Column
	private String android_umeng_appid;
	@Column
	private String wechat_name;
	@Column
	private String wechat_appid;
	@Column
	private String wechat_app_secret;
	@Column
	private String getui_d_appid;
	@Column
	private String getui_d_appkey;
	@Column
	private String getui_d_appmaster;
	@Column
	private String getui_d_appsecret;
	@Column
	private String getui_r_appid;
	@Column
	private String getui_r_appkey;
	@Column
	private String getui_r_appmaster;
	@Column
	private String getui_r_appsecret;
	@Column
	private String watermark;
	@Column
	private Date gmt_create;
	@Column
	private Date gmt_modified;

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getSecret_key() {
		return secret_key;
	}

	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMaterial_url() {
		return material_url;
	}

	public void setMaterial_url(String material_url) {
		this.material_url = material_url;
	}

	public String getComp_icon() {
		return comp_icon;
	}

	public void setComp_icon(String comp_icon) {
		this.comp_icon = comp_icon;
	}

	public String getComp_remark() {
		return comp_remark;
	}

	public void setComp_remark(String comp_remark) {
		this.comp_remark = comp_remark;
	}

	public String getIos_account() {
		return ios_account;
	}

	public void setIos_account(String ios_account) {
		this.ios_account = ios_account;
	}

	public String getIos_appid() {
		return ios_appid;
	}

	public void setIos_appid(String ios_appid) {
		this.ios_appid = ios_appid;
	}

	public String getIos_bundleid() {
		return ios_bundleid;
	}

	public void setIos_bundleid(String ios_bundleid) {
		this.ios_bundleid = ios_bundleid;
	}

	public String getAndroid_package() {
		return android_package;
	}

	public void setAndroid_package(String android_package) {
		this.android_package = android_package;
	}

	public String getIos_umeng_appid() {
		return ios_umeng_appid;
	}

	public void setIos_umeng_appid(String ios_umeng_appid) {
		this.ios_umeng_appid = ios_umeng_appid;
	}

	public String getAndroid_umeng_appid() {
		return android_umeng_appid;
	}

	public void setAndroid_umeng_appid(String android_umeng_appid) {
		this.android_umeng_appid = android_umeng_appid;
	}

	public String getWechat_name() {
		return wechat_name;
	}

	public void setWechat_name(String wechat_name) {
		this.wechat_name = wechat_name;
	}

	public String getWechat_appid() {
		return wechat_appid;
	}

	public void setWechat_appid(String wechat_appid) {
		this.wechat_appid = wechat_appid;
	}

	public String getWechat_app_secret() {
		return wechat_app_secret;
	}

	public void setWechat_app_secret(String wechat_app_secret) {
		this.wechat_app_secret = wechat_app_secret;
	}

	public String getGetui_d_appid() {
		return getui_d_appid;
	}

	public void setGetui_d_appid(String getui_d_appid) {
		this.getui_d_appid = getui_d_appid;
	}

	public String getGetui_d_appkey() {
		return getui_d_appkey;
	}

	public void setGetui_d_appkey(String getui_d_appkey) {
		this.getui_d_appkey = getui_d_appkey;
	}

	public String getGetui_d_appmaster() {
		return getui_d_appmaster;
	}

	public void setGetui_d_appmaster(String getui_d_appmaster) {
		this.getui_d_appmaster = getui_d_appmaster;
	}

	public String getGetui_d_appsecret() {
		return getui_d_appsecret;
	}

	public void setGetui_d_appsecret(String getui_d_appsecret) {
		this.getui_d_appsecret = getui_d_appsecret;
	}

	public String getGetui_r_appid() {
		return getui_r_appid;
	}

	public void setGetui_r_appid(String getui_r_appid) {
		this.getui_r_appid = getui_r_appid;
	}

	public String getGetui_r_appkey() {
		return getui_r_appkey;
	}

	public void setGetui_r_appkey(String getui_r_appkey) {
		this.getui_r_appkey = getui_r_appkey;
	}

	public String getGetui_r_appmaster() {
		return getui_r_appmaster;
	}

	public void setGetui_r_appmaster(String getui_r_appmaster) {
		this.getui_r_appmaster = getui_r_appmaster;
	}

	public String getGetui_r_appsecret() {
		return getui_r_appsecret;
	}

	public void setGetui_r_appsecret(String getui_r_appsecret) {
		this.getui_r_appsecret = getui_r_appsecret;
	}

	public String getWatermark() {
		return watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

	public Date getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}

	public Date getGmt_modified() {
		return gmt_modified;
	}

	public void setGmt_modified(Date gmt_modified) {
		this.gmt_modified = gmt_modified;
	}
}
