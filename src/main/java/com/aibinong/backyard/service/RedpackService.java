package com.aibinong.backyard.service;

import java.util.List;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.Redpack;

public interface RedpackService {

	/**
	 * 获取红包
	 * @author zhang_zg
	 * @param redpackId
	 * @return
	 */
	public Redpack getRedpack(long redpackId);

	/**
	 * 删除红包轮次
	 * @author zhang_zg
	 * @param redpackId
	 */
	public void deleteRedpackTurn(long redpackId);

	/**
	 * 是否有上线的红包活动
	 * @author zhang_zg
	 * @return
	 */
	public String getOnlineRedpackIds();

	/**
	 * 新建红包
	 * @author zhang_zg
	 * @param redpack
	 */
	public void createRedpack(Redpack redpack);

	/**
	 * 修改红包(只允许在下线状态下修改)
	 * @author zhang_zg
	 * @param redpack
	 */
	public void updateRedpack(Redpack redpack);

	/**
	 * 获取上线的红包活动列表
	 * @author zhang_zg
	 * @return
	 */
	public List<Redpack> getOnlineRedpackList();

	/**
	 * 获取红包活动列表
	 * @author zhang_zg
	 * @return
	 */
	public QueryResult getRedpackList(Long id, String name, String startDate, String endDate, Integer status, int pageNo, int pageSize);

	/**
	 * 获取红包计划列表
	 * @author zhang_zg
	 * @return
	 */
	public QueryResult getRedpackTurnList(long redpackId, String startDate, String endDate, int pageNo, int pageSize);

	/**
	 * 生成红包轮次
	 * @author zhang_zg
	 * @param dateString 为空 则默认创建明天的红包轮次, 否则生成指定日期的红包轮次
	 */
	public void createRedpackTurn(String dateString);

}
