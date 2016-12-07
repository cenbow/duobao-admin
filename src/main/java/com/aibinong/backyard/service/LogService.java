package com.aibinong.backyard.service;

import java.util.Map;

import com.aibinong.backyard.pojo.CustomerOperateLog;
import com.aibinong.backyard.pojo.OrderExpress;
import com.aibinong.backyard.pojo.Period;

public interface LogService {

	public void operateLog(Long user_id,String object,String oper_desc);
	
	public void customerOperateLog(CustomerOperateLog log);
	
	public Map<Long,CustomerOperateLog> getCustomerLogBySearch(Long periodId,String mobile);
	
	public void customerChangeOrder(CustomerOperateLog log,OrderExpress express);
	
	public Map<Long,CustomerOperateLog>  getCustomerLogByType(Integer type);
	
}
