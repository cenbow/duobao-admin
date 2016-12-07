package com.aibinong.backyard.service;

import java.util.List;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.AlarmBalance;
import com.aibinong.backyard.pojo.AlarmConf;

public interface GoldenEyeService {

	/**
	 * 配置列表
	 * 
	 * @return
	 */
	List<AlarmConf> getConfigList();

	/**
	 * 配置详情
	 * 
	 * @param id
	 * @return
	 */
	AlarmConf getConfig(long id);

	/**
	 * 更新配置
	 * 
	 * @param config
	 */
	void updateConfig(AlarmConf config);

	/**
	 * 记录列表
	 * 
	 * @param type
	 * @param page
	 * @return
	 */
	QueryResult getRecordList(Integer type, int page);

	/**
	 * 余量列表
	 * 
	 * @return
	 */
	List<AlarmBalance> getBalanceList();

}
