package com.aibinong.backyard.service;

import java.util.List;

import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;

public interface ReportService {

	/**
	 * 商品销售额列表
	 * @author zhang_zg
	 * @param startDate
	 * @param endDate
	 * @param productId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public QueryResult getProductReport(String startDate, String endDate, Long productId, int pageNo, int pageSize);

	/**
	 * 商品销售额总计
	 * @author zhang_zg
	 * @param startDate
	 * @param endDate
	 * @param productId
	 * @return
	 */
	public int getProductReportTotal(String startDate, String endDate, Long productId);

	/**
	 * 商品销售额趋势
	 * @author zhang_zg
	 * @param startDate
	 * @param endDate
	 * @param productId
	 * @return
	 */
	public String getProductCharts(String startDate, String endDate, long productId);
	
	/**
	 * 渠道日报
	 * @author zhang_zg
	 * @param startDate
	 * @param endDate
	 * @param clientId
	 * @param os
	 * @param channel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public QueryResult getChannelReport(String startDate, String endDate, String clientId, String os, String channel, int pageNo, int pageSize);

	/**
	 * 渠道日报总计
	 * @author zhang_zg
	 * @param startDate
	 * @param endDate
	 * @param clientId
	 * @param os
	 * @param channel
	 * @return
	 */
	public Record getChannelReportTotal(String startDate, String endDate, String clientId, String os, String channel);

	/**
	 * 渠道日报趋势
	 * @author zhang_zg
	 * @param startDate
	 * @param endDate
	 * @param clientId
	 * @param os
	 * @param channel
	 * @return
	 */
	public Record getChannelReportCharts(String startDate, String endDate, String clientId, String os, String channel);

	/**
	 * 渠道时报
	 * @author zhang_zg
	 * @param date
	 * @param clientId
	 * @param os
	 * @param channel
	 * @return
	 */
	public List<Record> getChannelHourReport(String date, String clientId, String os, String channel);

	/**
	 * 渠道时报总计
	 * @author zhang_zg
	 * @param date
	 * @param clientId
	 * @param os
	 * @param channel
	 * @return
	 */
	public Record getChannelHourReportTotal(String date, String clientId, String os, String channel);

	/**
	 * 渠道时报趋势
	 * @author zhang_zg
	 * @param channelHourList
	 * @return
	 */
	public String getChannelHourCharts(List<Record> channelHourList);

	/**
	 * 用户消耗报表
	 * @author zhang_zg
	 * @param userId
	 * @param mobile
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public QueryResult getUserPayReport(Long userId, String mobile, int pageNo, int pageSize);
}
