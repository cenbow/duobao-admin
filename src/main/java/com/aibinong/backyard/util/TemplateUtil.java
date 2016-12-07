package com.aibinong.backyard.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * FreeMarker模板处理工具类
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年6月29日 下午4:09:18
 */
public class TemplateUtil {
	private final static Logger LOG = LoggerFactory.getLogger(TemplateUtil.class);

	private final static String TEMPLATE_DIR_NAME = "template";
	private static File templateFiles;
	private static String templateDir;
	private static Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

	private TemplateUtil() {
	}

	/**
	 * 初始化模板入口
	 * @author zhang_zg
	 */
	public static void initTemplate() {
		String tplDir = TemplateUtil.class.getClassLoader().getResource("/").getPath() + TEMPLATE_DIR_NAME;
		templateDir = tplDir;
		templateFiles = new File(templateDir);
		// 初始化Configuration
		try {
			cfg.setDirectoryForTemplateLoading(templateFiles);
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
			cfg.setNumberFormat("computer"); // like in someNumber?c
			cfg.setClassicCompatible(true); // handle undefined expressions gracefully.
		} catch (Exception ex) {
			LOG.error("init Configuration error, template directory is '{}'.", templateDir, ex);
		}
	}

	/**
	 * 获取渲染后的模板文件
	 * @author zhang_zg
	 * @param tplPath 模板的相对路径 即：templateDir 后面的路径
	 * @param data 需要渲染的数据
	 */
	public static String format(String tplPath, Map<String, Object> data) {
		String outString = null;

		Writer out = new StringWriter();
		try {
			Template template = cfg.getTemplate(tplPath);
			template.process(data, out);
			outString = out.toString();
		} catch (Exception ex) {
			LOG.error("template of '{}' render error!", tplPath, ex);
			throw new RuntimeException(ex);
		} finally {
			try {
				out.close();
			} catch (IOException ex) {
				LOG.error("StringWriter close error", ex);
				throw new RuntimeException(ex);
			}
		}

		return outString;
	}

	/**
	 * 添加属性到data节点里面
	 * @author zhang_zg
	 * @param tplPath
	 * @param data
	 * @param appendData
	 * @return
	 */
	public static String append2Data(String jsonString, Map<String, Object> appendData) {
		JSONObject jsonObject = JSON.parseObject(jsonString);
		JSONObject jsonData = jsonObject.getJSONObject("data");
		if (appendData != null) {
			for (String key : appendData.keySet()) {
				jsonData.put(key, appendData.get(key));
			}
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 添加节点到根节点
	 * @author zhang_zg
	 * @param tplPath
	 * @param data
	 * @param appendData
	 * @return
	 */
	public static String append2Root(String jsonString, Map<String, Object> appendData) {
		JSONObject jsonObject = JSON.parseObject(jsonString);
		for (String key : appendData.keySet()) {
			jsonObject.put(key, appendData.get(key));
		}
		return jsonObject.toJSONString();
	}
}
