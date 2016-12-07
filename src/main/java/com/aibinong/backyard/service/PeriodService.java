package com.aibinong.backyard.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;

import com.aibinong.backyard.pojo.OrderExpress;
import com.aibinong.backyard.pojo.Period;
import com.aibinong.backyard.pojo.Product;
import com.aibinong.backyard.pojo.RechargeRecord;

public interface PeriodService {
	/**
	 * 获取期次列表
	 * @param periodId
	 * @param mobile
	 * @param productName
	 * @param startDate
	 * @param endDate
	 * @param expressStatus
	 * @return
	 */
	public QueryResult periodlist(Integer toPage,Long periodId,String mobile,String productName,String startDate,String endDate,Integer expressStatus,Integer objtype);
	/**
	 * 实物数据导出
	 * @param periodId
	 * @param mobile
	 * @param productName
	 * @param startDate
	 * @param endDate
	 * @param expressStatus
	 * @param objtype
	 * @return
	 */
	public List<Record> getExcelDataByObj(Long periodId,String mobile,String productName,String startDate,String endDate,Integer expressStatus,Integer objtype);
	/**
	 * 虚拟充值导出
	 * @param periodId
	 * @param mobile
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @return
	 */
	public List<Record> getRechargeExcelDataByObj(Long periodId,String mobile,String startDate,String endDate,String status,String orderStatus);
	
	public QueryResult rechargePeriodList(Integer toPage,Long periodId,String mobile,String startDate,String endDate,String status);
	
	
	
	public QueryResult notLuckShowList(Integer toPage,Long periodId,String productName,Integer isAI);
	
	/**
	 * 获取已经揭晓的期次
	 * @param status
	 * @return
	 */
	public List<Period> getPeriodListByStatus(Integer status);
	/**
	 * 获取发货信息
	 * @param id
	 * @return
	 */
	public OrderExpress getOrderExperiod(Long id);
	/**
	 * 获取某一期
	 * @param periodId
	 * @return
	 */
	public Period getPeriodById(Long periodId);
	/**
	 * 
	 * @param express
	 */
	public void updateOrderExpress(OrderExpress express);
	
	/**
	 * 
	 * @param express
	 */
	public void initOrderExpress(OrderExpress express);
	
	
	/**
	 * 创建新的一期
	 * @param product
	 */
	public void createPeriod(Product product);
	/**
	 * 根据productId获取正在进行的期次
	 * @param productId
	 * @return
	 */
	public List<Period>  getNewPeriodByProduct(Long productId);
	/**
	 * 获取充值订单
	 * @param recordId
	 * @return
	 */
	public RechargeRecord getRechargeRecordById(Long recordId);
	/**
	 * 修改充值订单
	 * @param record
	 */
	public void updateRecharge(RechargeRecord record);
	
	public Map<Long,OrderExpress> getExpressByUser(Long periodId,Long userId);
}
