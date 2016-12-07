package com.aibinong.backyard.web;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.nutz.mvc.Mvcs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.service.RedpackService;
import com.aibinong.backyard.util.TemplateUtil;

/**
 * Created by ouwa on 16/2/16.
 */
public class MainSetup implements Setup {
	private final static Logger LOG = LoggerFactory.getLogger(MainSetup.class);

	private RedpackService redpackService = Mvcs.getIoc().get(RedpackService.class, "redpackService");

    public void init(NutConfig conf) {
		LOG.debug("MainSetup init().");
		TemplateUtil.initTemplate();

		// 定时任务启动
		this.startTimer();
    }

    public void destroy(NutConfig nutConfig) {
    }

	private void startTimer() {
		LOG.info("定时任务启动...");
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					redpackService.createRedpackTurn(null);
				} catch (Exception ex) {
					LOG.error("新建活动轮次失败", ex);
				}
			}
		}, 0, 5, TimeUnit.MINUTES);
	}
}
