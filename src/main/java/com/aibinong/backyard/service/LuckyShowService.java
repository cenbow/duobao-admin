package com.aibinong.backyard.service;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.LuckyShow;

public interface LuckyShowService {

	/**
	 * 获取晒单列表
	 * @param toPage
	 * @param period
	 * @param spuId
	 * @return
	 */
	public QueryResult getLuckShowList(Integer toPage,Long period,String productName,Long spuId);
	/**
	 * 获取晒单详情
	 * @param luckyShowId
	 * @return
	 */
	public LuckyShow getLuckyShowDetail(Long luckyShowId);
	/**
	 * 修改晒单
	 * @param luckyShow
	 */
	public void updateLuckyShow(LuckyShow luckyShow);
	
	/**
	 * 根据期次获取晒单
	 * @param periodId
	 * @return
	 */
	public LuckyShow getLuckShowByPeriod(Long periodId);
	/**
	 * 保存
	 * @param luckyShow
	 * @return
	 */
	public LuckyShow saveLuckyShow(LuckyShow luckyShow);
}
