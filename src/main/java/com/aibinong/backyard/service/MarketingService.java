package com.aibinong.backyard.service;

import java.util.Date;
import java.util.List;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.AwardPlan;
import com.aibinong.backyard.pojo.LadderDiscount;
import com.aibinong.backyard.pojo.MarketingActivity;

public interface MarketingService {
	/**
	 * 获取活动列表
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	public QueryResult loadMarketingList(String name,Date startTime,Date endTime,Integer page);
	
	/**
	 * 创建活动
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @param limit_amount
	 * @param reduce_amount
	 * @param discount
	 */
	public void createMarketing(String name,Date startTime,Date endTime,String[] limit_amount,String[] reduce_amount,String[] discount);
	/**
	 * 修改活动
	 * @param activity
	 */
	public void updateMarket(MarketingActivity activity);
	/**
	 * 活动详情
	 * @param id
	 * @return
	 */
	public MarketingActivity getMarketDetail(Long id);
	/**
	 * 获取某活动折扣列表
	 * @param marketingId
	 * @return
	 */
	public List<LadderDiscount>  getDisCountListByMarket(Long marketingId);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public LadderDiscount  getLadderDisCount(Long id);
	/**
	 * 创建红包计划
	 * @param disCountId
	 * @param interval_date
	 * @param face_value
	 * @param validity_date
	 * @param award_data
	 */
	public void createPlan(LadderDiscount dis,String[] interval_date,String[] face_value,String[] validity_date,String[] award_data);
	/**
	 * 获取红包计划
	 * @param disCountId
	 * @return
	 */
	public List<AwardPlan> getPlanList(Long disCountId,Long marketing_id);
}
