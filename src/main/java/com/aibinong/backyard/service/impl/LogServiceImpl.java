package com.aibinong.backyard.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.CustomerOperateLog;
import com.aibinong.backyard.pojo.Oper_Log;
import com.aibinong.backyard.pojo.OrderExpress;
import com.aibinong.backyard.service.LogService;
import com.aibinong.backyard.service.PeriodService;
@IocBean(name = "logService")
public class LogServiceImpl implements LogService {
	@Inject
    protected BasicDao basicDao;
	@Inject
    private PeriodService periodService;
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void operateLog(Long user_id, String object,String oper_desc) {
		// TODO Auto-generated method stub
		Oper_Log log = new Oper_Log();
		log.setOper_object(object);
		log.setOper_user(user_id);
		log.setOper_time(new Date());
		log.setOper_desc(oper_desc);
		log.setGmt_create(new Date());
		log.setGmt_modified(new Date());
		basicDao.save(log);
	}
	@Override
	public void customerOperateLog(CustomerOperateLog log) {
		// TODO Auto-generated method stub
		basicDao.save(log);
		
	}
	@Override
	public Map<Long,CustomerOperateLog> getCustomerLogBySearch(
			Long periodId, String mobile) {
		StringBuffer str = new StringBuffer();
		str.append(" select * from customer_operate_log  where");
		str.append("  mobile="+mobile);
		if(periodId!=null){
			str.append("  and period_id="+periodId);
		}
		Map<Long,CustomerOperateLog>  logMap = new HashMap<Long, CustomerOperateLog>();
		List<CustomerOperateLog> loglist = basicDao.searchBySql(CustomerOperateLog.class, str.toString());
		if(loglist!=null && !loglist.isEmpty()){
			for (CustomerOperateLog customerOperateLog : loglist) {
				logMap.put(customerOperateLog.getPeriod_id(), customerOperateLog);
			}
		}
		return logMap;
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void customerChangeOrder(CustomerOperateLog log, OrderExpress express) {
		customerOperateLog(log);
		periodService.initOrderExpress(express);
	}
	@Override
	public Map<Long, CustomerOperateLog> getCustomerLogByType(Integer type) {
		StringBuffer str = new StringBuffer();
		str.append(" select * from customer_operate_log  where  ");
			str.append("   type="+type);
		Map<Long,CustomerOperateLog>  logMap = new HashMap<Long, CustomerOperateLog>();
		List<CustomerOperateLog> loglist = basicDao.searchBySql(CustomerOperateLog.class, str.toString());
		if(loglist!=null && !loglist.isEmpty()){
			for (CustomerOperateLog customerOperateLog : loglist) {
				logMap.put(customerOperateLog.getPeriod_id(), customerOperateLog);
			}
		}
		return logMap;
	}

}
