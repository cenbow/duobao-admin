package com.aibinong.backyard.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.dao.RedisDao;
import com.aibinong.backyard.pojo.OrderExpress;
import com.aibinong.backyard.pojo.Period;
import com.aibinong.backyard.pojo.Product;
import com.aibinong.backyard.pojo.RechargeRecord;
import com.aibinong.backyard.pojo.User;
import com.aibinong.backyard.service.PeriodService;
@IocBean(name = "periodService")
public class PeriodServiceImpl implements PeriodService {
	private static Logger logger = LoggerFactory.getLogger(PeriodServiceImpl.class);
	@Inject
    protected BasicDao basicDao;
	@Inject
	protected RedisDao redisDao;
	@Override
	public QueryResult periodlist(Integer toPage,Long periodId, String mobile,
			String productName, String startDate, String endDate,
			Integer expressStatus,Integer objtype) {
		String str =getSqlStr(periodId,  mobile,productName,  startDate,  endDate, expressStatus ,objtype);
		QueryResult queryResult = basicDao.querySqlResult(str.toString(), toPage, Constants.DEFAULT_PAGE_SIZE);
        return queryResult;
	}
	@Override
	public List<Period> getPeriodListByStatus(Integer status) {
		Condition con = Cnd.where("status", "=", status);
		List<Period> periodlist = basicDao.search(Period.class, con);
		return periodlist;
	}
	@Override
	public OrderExpress getOrderExperiod(Long id) {
		return basicDao.find(id,OrderExpress.class);
	}
	@Override
	public Period getPeriodById(Long periodId) {
		return basicDao.find(periodId, Period.class);
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void updateOrderExpress(OrderExpress express) {
		basicDao.update(express);
	}
	@Override
	public QueryResult notLuckShowList(Integer toPage, Long periodId,
			String productName,Integer isAI) {
		StringBuffer str = new StringBuffer();
		str.append("  select d.* ");
		
		if(isAI==Constants.DUOBAO_NOTAI_STATUS){
			str.append("  ,u.nick,u.mobile ");
		}
		str.append("  from period d ");
		if(isAI==Constants.DUOBAO_NOTAI_STATUS){
			str.append(" ,users u");
		}
		str.append("  where d.status=2  ");
		if(isAI==Constants.DUOBAO_NOTAI_STATUS){
			str.append(" And d.lucky_user_id=u.id");
		}
		str.append("  And d.ai=").append(isAI);
		str.append("  and d.id not in (select period_id from lucky_show )");
		if (periodId != null) {
			str.append(" and d.id=").append(periodId);
		}
		if (StringUtils.isNotEmpty(productName)) {
			List<Record> recordlist = basicDao.select("select * from product where title like'%" + productName + "%'");
			StringBuffer prostr = new StringBuffer();
			if (recordlist != null && !recordlist.isEmpty()) {
				for (Record record : recordlist) {
					prostr.append(record.get("id")).append(",");
				}
			}
			if (StringUtils.isEmpty(prostr.toString())) {
				return null;
			}
			str.append(" and d.product_id in (")
					.append(prostr.toString().substring(0, prostr.length() - 1))
					.append(")");
		}
			str.append(" order by d.award_time desc,d.id desc");
			logger.info("search  sql:"+str.toString());
		QueryResult queryResult = basicDao.querySqlResult(str.toString(),toPage, Constants.DEFAULT_PAGE_SIZE);
        return queryResult;
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void createPeriod(Product product) {
		if(product.getStatus()==Product.Status.GROUNDING.value()&& product.getNumbers() > 0){
            Period newPeriod = new Period();
            newPeriod.setProduct_id(product.getId());
            newPeriod.setSpu_id(product.getSpu_id());
            newPeriod.setType(product.getType());
            newPeriod.setTitle(product.getTitle());
            newPeriod.setSub_title(product.getSub_title());
            newPeriod.setList_img(product.getList_img());
            newPeriod.setLoop_img(product.getLoop_img());
            newPeriod.setRemark(product.getRemark());
            newPeriod.setReal_cost(product.getReal_cost());
            newPeriod.setNumbers(product.getNumbers());
            newPeriod.setTotal_count(product.getTotal_count());
            newPeriod.setDefault_count(product.getDefault_count());
            newPeriod.setLimit_count(product.getLimit_count());
            newPeriod.setDuobao_type(product.getDuobao_type());
            newPeriod.setIs_hot(product.getIs_hot());
            newPeriod.setIs_new(product.getIs_new());
            newPeriod.setWeight(product.getWeight());
            newPeriod.setStart_time(new Date());
            newPeriod.setStatus(Constants.PERIOD_STATUS_NORMAL);
            newPeriod.setCur_count(Constants.PERIOD_DEFAULT_CUR_COUNT);
            newPeriod.setGmt_create(new Date());
            newPeriod.setGmt_modified(new Date());

            basicDao.save(newPeriod);

            //将夺宝号放进redis中
            List<String> codeList = new ArrayList<String>();
            String code = null;
            for(int i = 0; i < newPeriod.getTotal_count(); i++) {
                code = String.valueOf(Constants.PERIOD_CODE_BASE + i);
                codeList.add(code);
            }
            Collections.shuffle(codeList);
            String  redis_key = Constants.REDIS_KEY_PERIOD_CODE_LIST_PREFIX + newPeriod.getId();
            redisDao.lpush(redis_key, codeList.toArray(new String[]{}));
        }
		
		
	}
	@Override
	public List<Period> getNewPeriodByProduct(Long productId) {
		Condition condition = Cnd.wrap("where status !="+Period.Status.OPEN_LOTTERY.value()+" and product_id ="+productId);//Cnd.where("status", "=", Period.Status.UNDER_WAY.value()).and("product_id", "=", productId).and("total_count", ">", "cur_count");
		
		return basicDao.search(Period.class, condition);
	}
	@Override
	public QueryResult rechargePeriodList(Integer toPage, Long periodId,
			String mobile, String startDate, String endDate, String status) {
		
		String str = "";
		if(StringUtils.isNotEmpty(status) && String.valueOf(Constants.ORDER_EXPRESS_DEFAULT_STATUS).equals(status)){
			str = getNotConfirmMobileStr(periodId,mobile,startDate,endDate,status);
		}else{
			 str =getRechargeStr(periodId,mobile,startDate,endDate,status);
		}
		QueryResult queryResult = basicDao.querySqlResult(str, toPage, Constants.DEFAULT_PAGE_SIZE);
        return queryResult;
	}
	@Override
	public RechargeRecord getRechargeRecordById(Long recordId) {
		return basicDao.find(recordId, RechargeRecord.class);
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void updateRecharge(RechargeRecord record) {
		basicDao.update(record);
	}
	
	private String getSqlStr(Long periodId, String mobile,
			String productName, String startDate, String endDate,
			Integer expressStatus,Integer objtype){

		StringBuffer str = new StringBuffer();
		str.append("SELECT es.*,");
		str.append("	   d.title,");
		str.append("	   d.list_img,");
		str.append("	   d.spu_id,");
		str.append("	   d.close_time,");
		str.append("	   d.award_time,");
		str.append("	   d.lucky_code,");
		str.append("	   d.lucky_user_id");
		str.append("  FROM  order_express es");
		str.append("    , period d where es.period_id = d.id");
		if (periodId != null) {
			str.append(" and es.period_id=" + periodId);
		}
		if (StringUtils.isNotEmpty(mobile)) {
			Condition con = Cnd.where("mobile", "=", mobile);
			User user = basicDao.findByCondition(User.class, con);
			if (user == null) {
				str.append(" and es.user_id =''");
			}else{
				str.append(" and es.user_id =" + user.getId());
			}
		}
		if (StringUtils.isNotEmpty(productName)) {
			List<Record> recordlist = basicDao
					.select("select * from product where title like'%"
							+ productName + "%'");
			StringBuffer prostr = new StringBuffer();
			if (recordlist != null && !recordlist.isEmpty()) {
				for (Record record : recordlist) {
					prostr.append(record.get("id")).append(",");
				}
			}
			if (StringUtils.isEmpty(prostr.toString())) {
				return null;
			}
			str.append(" and es.product_id in (")
					.append(prostr.toString().substring(0, prostr.length() - 1))
					.append(")");
		}

		if (objtype != null && objtype != -1) {
			str.append(" and d.type=" + objtype);
		}
		if (expressStatus != null && expressStatus != -1) {
			str.append(" and es.status=" + expressStatus);
		}
		if (expressStatus != null && expressStatus != -1
				&& expressStatus != Constants.ORDER_EXPRESS_DEFAULT_STATUS) {

				if (StringUtils.isNotEmpty(startDate)) {
					str.append(" and commit_address_time >=").append("'")
							.append(startDate).append("'");
				}
				if (StringUtils.isNotEmpty(endDate)) {
					str.append(" and commit_address_time <=").append("'")
							.append(endDate).append("'");
				}
		} else {
			if (StringUtils.isNotEmpty(startDate)) {
				str.append(" and es.gmt_create >=").append("'")
						.append(startDate).append("'");
			}
			if (StringUtils.isNotEmpty(endDate)) {
				str.append(" and es.gmt_create <=").append("'").append(endDate)
						.append("'");
			}
		}
		str.append(" order by es.product_id,es.gmt_create desc");
		return str.toString();
	}
	
	private String getRechargeStr(Long periodId,
			String mobile, String startDate, String endDate, String status){


		StringBuffer str = new StringBuffer();
		str.append("SELECT es.id,es.user_id,");
		str.append("	   es.order_id,");
		str.append("	   es.period_id,");
		str.append("	   es.name,");
		str.append("	   es.mobile,");
		str.append("	   d.amount,");
		str.append("	   d.status,");
		str.append("	   d.times,");
		str.append("	   d.remark ,");
		str.append("       u.mobile as res_mobile");
		str.append("  FROM  order_express es");
		str.append("    , recharge_record d ,users u where es.recharge_record_id = d.id  And es.user_id=u.id");
		if(periodId!=null){
			str.append(" and es.period_id="+periodId);
		}
		if(StringUtils.isNotEmpty(mobile)){
			Condition con = Cnd.where("mobile","=",mobile);
			User user =basicDao.findByCondition(User.class, con);
			if(user==null){
				str.append(" and es.user_id =''");
			}else{
				str.append(" and es.user_id ="+user.getId());
			}
		}
		if(StringUtils.isNotEmpty(status)&& !"-1".equals(status)){
			str.append(" and d.status in ("+status+")");
		}
		if(StringUtils.isNotEmpty(startDate)){
			str.append(" and es.gmt_create >=").append("'").append(startDate).append("'");
		}
		if(StringUtils.isNotEmpty(endDate)){
			str.append(" and es.gmt_create <=").append("'").append(endDate).append("'");
		}
		
		str.append(" order by es.id,es.gmt_create desc");
		
		return str.toString();
	}
	
	private String getNotConfirmMobileStr(Long periodId,String mobile,String startDate, String endDate, String orderStatus){
		StringBuffer str = new StringBuffer();
		str.append("  select es.id,es.user_id,es.order_id,es.product_id,es.period_id,es.mobile,");
		str.append("  es.status as order_status,es.remark,es.name, u.mobile as res_mobile from order_express es ,");
		str.append("  period d , users u where d.type = 1");
        str.append("  AND d.status = 2 And es.period_id=d.id   And es.user_id=u.id ");
        if(periodId!=null){
			str.append(" and es.period_id="+periodId);
		}
        if(StringUtils.isNotEmpty(mobile)){
			Condition con = Cnd.where("mobile","=",mobile);
			User user =basicDao.findByCondition(User.class, con);
			if(user==null){
				str.append(" and es.user_id =''");
			}else{
				str.append(" and es.user_id ="+user.getId());
			}
		}
        if(StringUtils.isNotEmpty(orderStatus)){
			str.append(" and es.status ="+orderStatus);
		}
        if(StringUtils.isNotEmpty(startDate)){
			str.append(" and es.gmt_create >=").append("'").append(startDate).append("'");
		}
		if(StringUtils.isNotEmpty(endDate)){
			str.append(" and es.gmt_create <=").append("'").append(endDate).append("'");
		}
		return str.toString();
	}
	@Override
	public List<Record> getExcelDataByObj(Long periodId, String mobile,
			String productName, String startDate, String endDate,
			Integer expressStatus, Integer objtype) {
		String str =getSqlStr(periodId,  mobile,productName,  startDate,  endDate, expressStatus ,objtype);
		List<Record> recordlist =basicDao.select(str);
		return recordlist;
	}
	@Override
	public List<Record> getRechargeExcelDataByObj(Long periodId, String mobile,
			String startDate, String endDate, String status,String orderStatus) {
		String str = "";
		if(StringUtils.isNotEmpty(orderStatus)){
			str = getNotConfirmMobileStr(periodId,mobile,startDate,endDate,orderStatus);
		}else{
			 str =getRechargeStr(periodId,mobile,startDate,endDate,status);
		}
		
		List<Record> recordlist =basicDao.select(str);
		return recordlist;
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void initOrderExpress(OrderExpress express) {
		StringBuffer str = new StringBuffer();
		str.append("  update order_express set status=0,");
		str.append("  recharge_record_id=null,gmt_modified=now() ");
		str.append("  where id=" + express.getId());
		basicDao.executeSql(str.toString());
		Period period = basicDao.find(express.getPeriod_id(), Period.class);
		if (period != null
				&& period.getType() == Constants.PERIOD_TYPE_RECHARGE_VALUE) {
			Condition con = Cnd.where("order_express_id", "=", express.getId()).and("period_id", "=", period.getId());
			RechargeRecord record = basicDao.findByCondition(RechargeRecord.class, con);
			if (record != null&& (record.getStatus() == Constants.RECHARGE_RECORD_STATUS_CANNOT_RECOVER ||
					record.getStatus() == Constants.RECHARGE_RECORD_STATUS_COLSED)) {
				basicDao.delete("recharge_record", con);
			}
		}
	}
	@Override
	public Map<Long, OrderExpress> getExpressByUser(Long periodId,Long userId) {
		StringBuffer str = new StringBuffer();
		str.append("SELECT ");
		str.append("  es.*, re.status AS recharge_status");
		str.append(" FROM");
		str.append(" order_express es");
		str.append("   LEFT JOIN");
		str.append("  recharge_record re ON es.recharge_record_id = re.id");
		str.append("   AND es.user_id = re.user_id");
		str.append("  WHERE");
		str.append("  es.user_id = "+userId);
		if(periodId!=null){
			str.append("   and es.period_id="+periodId);
		}
		List<Record> recordlist = basicDao.select(str.toString());
		List<OrderExpress> expresslist = new ArrayList<OrderExpress>();
		if(recordlist!=null){
			for (Record record : recordlist) {
				  Long period_id = (Long)record.get("period_id");
				  String mobile= record.getString("mobile");
				  Integer status = record.getInt("status");
				  Long id = (Long)record.get("id");
				  Long user_id = (Long)record.get("user_id");
				  Object recharge_status = record.get("recharge_status");
				  Object commit_address_time = record.get("commit_address_time");
				  Object express_time = record.get("express_time");
				  OrderExpress express = new OrderExpress();
				  if(recharge_status!=null){
					  express.setRechargeStatus((Integer)recharge_status);
				  }else{
					  express.setRechargeStatus(null);
				  }
				  if(express_time!=null){
					  express.setExpress_time((Date)express_time);
				  }else{
					  express.setExpress_time(null);
				  }
				  if(commit_address_time!=null){
					  express.setCommit_address_time((Date)commit_address_time);
				  }else{
					  express.setCommit_address_time(null);
				  }
				  express.setId(id);
				  express.setMobile(mobile);
				  express.setStatus(status);
				  express.setUser_id(user_id);
				 
				  express.setPeriod_id(period_id);
				  expresslist.add(express);
			}
		}
		 Map<Long, OrderExpress> expressMap = new HashMap<Long, OrderExpress>();
		if(expresslist!=null && !expresslist.isEmpty()){
			for (OrderExpress orderExpress : expresslist) {
				expressMap.put(orderExpress.getPeriod_id(), orderExpress);
			}
		}
		return expressMap;
	}
}
