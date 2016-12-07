package com.aibinong.backyard.service;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.SysConfig;

public interface ConfigService {

	/**
	 * 获取系统配置列表
	 * @param client_id
	 * @param k
	 * @param page
	 * @return
	 */
	public QueryResult getConfiglist(String client_id,String k,Integer page);
	/**
	 * 详情
	 * @param configId
	 * @return
	 */
	public SysConfig getConfigDetail(Long configId);
	/**
	 * 修改系统配置
	 * @param config
	 */
	public void updateConfig(SysConfig config);
	/**
	 * 添加配置
	 * @param config
	 */
	public void addConfig(SysConfig config);
	/**
	 * 删除
	 * @param config
	 */
	public void delConfig(SysConfig config);
	/**
	 * 根据k获取config
	 * @param k
	 * @return
	 */
	public SysConfig  getConfigByKey(String k);
}
