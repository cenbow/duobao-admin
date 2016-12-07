package com.aibinong.backyard.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.dao.RedisDao;
import com.aibinong.backyard.service.SystemService;
@IocBean(name = "systemService")
public class SystemServiceImpl implements SystemService {
	private final static Logger LOG = LoggerFactory.getLogger(SystemServiceImpl.class);
	@Inject
	private BasicDao basicDao;

	@Inject
	private RedisDao redisDao;

	@Override
	public Map<String, String> serviceMonitor() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mysql", "OK");
		map.put("redis", "OK");
		// 监测MYSQL
		try {
			basicDao.executeSql("SELECT 1");
		} catch (Exception ex) {
			map.put("mysql", "error");
			LOG.error("mysql error", ex);
		}
		// 监测REDIS
		try {
			redisDao.get("check_service_key");
		} catch (Exception ex) {
			map.put("redis", "unNormal");
			LOG.error("redis unNormal", ex);
		}
		return map;
	}

}
