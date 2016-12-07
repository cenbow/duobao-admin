package com.aibinong.backyard.web.module;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletOutputStream;
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
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import com.aibinong.backyard.pojo.App;
import com.aibinong.backyard.pojo.AppDownload;
import com.aibinong.backyard.pojo.AppRecord;
import com.aibinong.backyard.pojo.Channel;
import com.aibinong.backyard.service.AppService;
import com.aibinong.backyard.service.ChannelService;
import com.aibinong.backyard.service.UploadService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * 包管理模块
 * 
 * @author wang.yan
 * @version 1.0
 */
@IocBean
public class AppModule extends BaseModule {

	@Inject
	private AppService appService;
	@Inject
	private ChannelService channelService;
	@Inject
	private UploadService uploadService;

	/**
	 * APP列表
	 * 
	 * @param clientId
	 * @param name
	 * @param version
	 * @param channel
	 * @param os
	 * @param status
	 * @param page
	 * @param request
	 * @return
	 */
	@At("/applist")
	public View applist(@Param("client_id") String clientId, @Param("name") String name,
			@Param("version") String version, @Param("channel") String channel, @Param("os") String os,
			@Param("status") Integer status, @Param("page") int page, HttpServletRequest request) {
		// 记录列表
		QueryResult result = this.appService.getAppList(page, clientId, name, version, channel, os, status);
		// 渠道列表
		List<Channel> channelList = this.channelService.getChannelList();

		request.setAttribute("applist", result.getList());
		request.setAttribute("pager", result.getPager());
		request.setAttribute("client_id", clientId);
		request.setAttribute("name", name);
		request.setAttribute("version", version);
		request.setAttribute("channel", channel);
		request.setAttribute("os", os);
		request.setAttribute("status", status);
		request.setAttribute("channel_list", channelList);

		return new JspView("jsp/app/app_list");
	}

	/**
	 * APP创建页面
	 * 
	 * @param request
	 * @return
	 */
	@At("/createAppPage")
	public View createAppPage(HttpServletRequest request) {
		List<Channel> channelList = this.channelService.getChannelList();
		request.setAttribute("channel_list", channelList);
		return new JspView("jsp/app/app_create");
	}

	/**
	 * 创建APP
	 * 
	 * @param app
	 * @param download
	 * @param record
	 * @param channels
	 * @param icon
	 * @param material
	 * @param installpack
	 * @param request
	 * @return
	 */
	@AdaptBy(type = UploadAdaptor.class)
	@At("/createApp")
	public View createApp(@Param("..") final App app, @Param("..") final AppDownload download,
			@Param("..") final AppRecord record, @Param("channels") final String[] channels, @Param("icon1") File icon,
			@Param("material") File material, @Param("installpack") final File installpack,
			final HttpServletRequest request) {
		try {
			// 渠道信息为空
			if (channels == null || channels.length == 0) {
				request.setAttribute("errmsg", "请选择渠道");
				return new JspView("jsp/error_msg");
			}
			// 判断ID是否已经存在
			if (this.appService.getApp(app.getClient_id()) != null) {
				request.setAttribute("errmsg", "该ID已经存在");
				return new JspView("jsp/error_msg");
			}
			// 上传ICON图
			if (icon != null) {
				String iconUrl = this.uploadService.uploadFile(icon);
				app.setIcon(iconUrl);
				record.setIcon(iconUrl);
			}
			// 上传素材包
			if (material != null) {
				String materialUrl = this.uploadService.uploadMaterial(material, app.getClient_id());
				app.setMaterial_url(materialUrl);
				record.setMaterial_url(materialUrl);
			}
			// 上传安装包
			String installpackUrl = null;
			if (installpack != null && App.ANDROID.equals(app.getOs())) {
				installpackUrl = this.uploadService.uploadFile(installpack);
			}
			final String url = installpackUrl;

			Trans.exec(new Atom() {
				public void run() {
					// 创建APP
					appService.createApp(app);
					// 创建广告关联
					appService.createAdApp(app.getClient_id());
					// 根据渠道创建版本信息
					for (String channel : channels) {
						// 判断该版本是否存在
						AppDownload version = appService.getDownloadByChannelVersion(app.getClient_id(), channel,
								download.getVersion_code());
						if (version == null) {
							download.setChannel(channel);
							download.setUrl(url);
							appService.createDownload(download);

							record.setChannel(channel);
							record.setUrl(url);
							appService.createRecord(record, app, download, getAccount(request).getAccount());
						}
					}
				}
			});
			this.logService.operateLog(this.getAccount(request).getId(), "/createApp", "创建APP操作");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errmsg", "创建失败，请稍后再试");
			return new JspView("jsp/error_msg");
		}
		return new ServerRedirectView("applist.html");
	}

	/**
	 * APP详情页面
	 * 
	 * @param clientId
	 * @param request
	 * @return
	 */
	@At("/appDetailPage")
	public View appDetailPage(@Param("id") String clientId, HttpServletRequest request) {
		App app = this.appService.getApp(clientId);
		if (app == null) {
			request.setAttribute("errmsg", "非法的ID参数");
			return new JspView("jsp/error_msg");
		}
		request.setAttribute("app", app);
		return new JspView("jsp/app/app_detail");
	}

	/**
	 * APP编辑页面
	 * 
	 * @param clientId
	 * @param request
	 * @return
	 */
	@At("/editAppPage")
	public View editAppPage(@Param("id") String clientId, HttpServletRequest request) {
		App app = this.appService.getApp(clientId);
		if (app == null) {
			request.setAttribute("errmsg", "非法的ID参数");
			return new JspView("jsp/error_msg");
		}
		request.setAttribute("app", app);
		return new JspView("jsp/app/app_edit");
	}

	/**
	 * 更新APP
	 * 
	 * @param app
	 * @param record
	 * @param icon
	 * @param material
	 * @param request
	 * @return
	 */
	@AdaptBy(type = UploadAdaptor.class)
	@At("/updateApp")
	public View updateApp(@Param("..") final App app, @Param("..") final AppRecord record, @Param("icon1") File icon,
			@Param("material") File material, final HttpServletRequest request) {
		try {
			// 上传ICON图
			if (icon != null) {
				String iconUrl = this.uploadService.uploadFile(icon);
				app.setIcon(iconUrl);
				record.setIcon(iconUrl);
			}
			// 上传素材包
			if (material != null) {
				String materialUrl = this.uploadService.uploadMaterial(material, app.getClient_id());
				app.setMaterial_url(materialUrl);
				record.setMaterial_url(materialUrl);
			}
			// 更新APP信息
			Trans.exec(new Atom() {
				public void run() {
					appService.updateApp(app);
					appService.createRecord(record, app, null, getAccount(request).getAccount());
				}
			});
			this.logService.operateLog(this.getAccount(request).getId(), "/updateApp", "修改APP操作");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errmsg", "创建失败，请稍后再试");
			return new JspView("jsp/error_msg");
		}
		return new ServerRedirectView("appDetailPage.html?id=" + app.getClient_id());
	}

	/**
	 * 新版本页面
	 * 
	 * @param request
	 * @return
	 */
	@At("/createAppVersionPage")
	public View createAppVersionPage(HttpServletRequest request) {
		// APP列表
		List<App> appList = this.appService.getAppList();
		// 渠道列表
		List<Channel> channelList = this.channelService.getChannelList();

		request.setAttribute("applist", appList);
		request.setAttribute("channel_list", channelList);

		return new JspView("jsp/app/version_create");
	}

	/**
	 * 创建新版本
	 * 
	 * @param download
	 * @param record
	 * @param installpack
	 * @param os
	 * @param request
	 * @return
	 */
	@AdaptBy(type = UploadAdaptor.class)
	@At("/createAppVersion")
	public View createAppVersion(@Param("..") final AppDownload download, @Param("..") final AppRecord record,
			@Param("installpack") File installpack, @Param("os") final String os, final HttpServletRequest request) {
		// 该版本是否存在
		AppDownload version = this.appService.getDownloadByChannelVersion(download.getClient_id(),
				download.getChannel(), download.getVersion_code());
		if (version != null) {
			request.setAttribute("errmsg", "该版本已经存在");
			return new JspView("jsp/error_msg");
		}
		try {
			// 上传安装包
			if (installpack != null && App.ANDROID.equals(os)) {
				String installpackUrl = this.uploadService.uploadFile(installpack);
				download.setUrl(installpackUrl);
			}
			// 保存版本信息
			Trans.exec(new Atom() {
				public void run() {
					if (App.IOS.equals(os)) {
						if (AppDownload.Status.PENDING.value() == download.getStatus()) {
							// 开启网页支付、添加支付按钮
							appService.createWebPay(download.getClient_id(), download.getVersion());
							appService.createWebPayRecharge(download.getClient_id(), download.getVersion());
							appService.createHomeBtn(download.getClient_id(), download.getVersion());
						} else {
							// 删除网页支付、支付按钮
							appService.deleteWebPay(download.getClient_id(), download.getVersion());
							appService.deleteWebPayRecharge(download.getClient_id(), download.getVersion());
							appService.deleteHomeBtn(download.getClient_id(), download.getVersion());
						}
					}
					appService.createDownload(download);
					appService.createRecord(record, null, download, getAccount(request).getAccount());
				}
			});
			this.logService.operateLog(this.getAccount(request).getId(), "/createAppVersion", "创建APP版本操作");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errmsg", "保存失败，请稍后再试");
			return new JspView("jsp/error_msg");
		}
		return new ServerRedirectView("applist.html");
	}

	/**
	 * 版本详情页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@At("/appVersionDetailPage")
	public View appVersionDetailPage(@Param("id") Long id, HttpServletRequest request) {
		AppDownload download = this.appService.getDownload(id);
		if (download == null) {
			request.setAttribute("errmsg", "非法的ID参数");
			return new JspView("jsp/error_msg");
		}
		Channel channel = this.channelService.getChannel(download.getChannel());
		if (channel != null) {
			request.setAttribute("channel", channel.getName());
		}
		request.setAttribute("download", download);
		return new JspView("jsp/app/version_detail");
	}

	/**
	 * 版本编辑页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@At("/appVersionEditPage")
	public View appVersionEditPage(@Param("id") Long id, HttpServletRequest request) {
		AppDownload download = this.appService.getDownload(id);
		if (download == null) {
			request.setAttribute("errmsg", "非法的ID参数");
			return new JspView("jsp/error_msg");
		}
		Channel channel = this.channelService.getChannel(download.getChannel());
		if (channel != null) {
			request.setAttribute("channel", channel.getName());
		}
		request.setAttribute("download", download);
		return new JspView("jsp/app/version_edit");
	}

	/**
	 * 更新版本信息
	 * 
	 * @param download
	 * @param record
	 * @param installpack
	 * @param os
	 * @param request
	 * @return
	 */
	@AdaptBy(type = UploadAdaptor.class)
	@At("/updateAppVersion")
	public View updateAppVersion(@Param("..") final AppDownload download, @Param("..") final AppRecord record,
			@Param("installpack") File installpack, @Param("os") final String os, final HttpServletRequest request) {
		try {
			// 上传安装包
			if (installpack != null && App.ANDROID.equals(os)) {
				String installpackUrl = this.uploadService.uploadFile(installpack);
				download.setUrl(installpackUrl);
			}
			// 非审核拒绝状态，原因清空
			if (AppDownload.Status.NOT_PASS.value() != download.getStatus()) {
				download.setReason(null);
				record.setReason(null);
			}
			// 更新数据
			Trans.exec(new Atom() {
				public void run() {
					// 支付配置
					if (App.IOS.equals(os)) {
						// 审核中状态：开启网页支付、支付按钮
						if (AppDownload.Status.PENDING.value() == download.getStatus()) {
							// 开启网页支付、添加支付按钮
							appService.createWebPay(download.getClient_id(), download.getVersion());
							appService.createWebPayRecharge(download.getClient_id(), download.getVersion());
							appService.createHomeBtn(download.getClient_id(), download.getVersion());
						} else {
							// 删除网页支付、支付按钮
							appService.deleteWebPay(download.getClient_id(), download.getVersion());
							appService.deleteWebPayRecharge(download.getClient_id(), download.getVersion());
							appService.deleteHomeBtn(download.getClient_id(), download.getVersion());
						}
						// 上线状态：清除低版本信息
						if (AppDownload.Status.ONLINE.value() == download.getStatus()) {
							appService.deleteDownload(download);
						}
					}
					appService.updateDownload(download);
					appService.createRecord(record, null, download, getAccount(request).getAccount());
				}
			});
			this.logService.operateLog(this.getAccount(request).getId(), "/updateAppVersion", "修改APP版本操作");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errmsg", "保存失败，请稍后再试");
			return new JspView("jsp/error_msg");
		}
		return new ServerRedirectView("applist.html");
	}

	/**
	 * 记录列表
	 * 
	 * @param clientId
	 * @param page
	 * @param request
	 * @return
	 */
	@At("/appVersionRecordList")
	public View appVersionRecordList(@Param("id") String clientId, @Param("page") int page,
			HttpServletRequest request) {
		QueryResult result = this.appService.getRecordList(clientId, page);

		request.setAttribute("record_list", result.getList());
		request.setAttribute("pager", result.getPager());
		request.setAttribute("client_id", clientId);

		return new JspView("jsp/app/record_list");
	}

	/**
	 * 生成二维码，输出文件流
	 * 
	 * @param appId
	 * @param response
	 */
	@Ok("raw")
	@At("/getDownloadQRCode")
	public void getDownloadQRCode(@Param("app_id") String appId, @Param("size") int size,
			HttpServletResponse response) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0);

		ServletOutputStream stream = null;
		try {
			stream = response.getOutputStream();
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix matrix = writer.encode("https://itunes.apple.com/cn/app/id" + appId, BarcodeFormat.QR_CODE, size,
					size, hints);
			MatrixToImageWriter.writeToStream(matrix, "png", stream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stream != null) {
					stream.flush();
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 下载素材文件
	 * 
	 * @param materialUrl
	 * @param response
	 */
	@Ok("raw")
	@At("/getAppMaterial")
	public void getAppMaterial(@Param("url") String materialUrl, HttpServletResponse response) {
		DataInputStream in = null;
		try {
			if (StringUtils.isBlank(materialUrl)) {
				return;
			}
			String filename = materialUrl.substring(materialUrl.lastIndexOf("/") + 1);

			URL url = new URL(materialUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			in = new DataInputStream(connection.getInputStream());

			OutputStream out = response.getOutputStream();
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

			byte[] buffer = new byte[4096];
			int count = 0;
			while ((count = in.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
