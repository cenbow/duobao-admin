package com.aibinong.backyard.web.module;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.pojo.CustomerOperateLog;
import com.aibinong.backyard.pojo.Express;
import com.aibinong.backyard.pojo.OrderExpress;
import com.aibinong.backyard.pojo.Period;
import com.aibinong.backyard.pojo.RechargeRecord;
import com.aibinong.backyard.pojo.User;
import com.aibinong.backyard.pojo.UserAccountRecord;
import com.aibinong.backyard.pojo.UserDuobaoRecord;
import com.aibinong.backyard.service.ExpressService;
import com.aibinong.backyard.service.PeriodService;
import com.aibinong.backyard.service.ProductService;
import com.aibinong.backyard.service.UserService;
import com.aibinong.backyard.sms.SmsException;
import com.aibinong.backyard.util.SendUtils;
import com.aibinong.backyard.web.Excel.EntityModel;
import com.aibinong.backyard.web.Excel.ExcelUtil;
import com.aibinong.backyard.web.Excel.RechargeModel;

@IocBean
public class PeriodModule extends BaseModule{

	private static Logger logger = LoggerFactory.getLogger(PeriodModule.class);
	@Inject
    private PeriodService periodService;
	@Inject
    private ProductService productService;
	@Inject
	private UserService userService;
	@Inject
    private ExpressService expressService;
	/**
	 * 实物期次列表
	 * @param page
	 * @param periodId
	 * @param mobile
	 * @param productName
	 * @param startTime
	 * @param endTime
	 * @param expressStatus
	 * @param objtype
	 * @param request
	 * @return
	 */
	@At("/periodlist")
	public View getPeriodlist(@Param("page")Integer page,@Param("periodId")Long periodId,@Param("mobile") String mobile,
			@Param("productName")String productName,@Param("startTime") String startTime, @Param("endTime")String endTime,
			@Param("expressStatus")Integer expressStatus,HttpServletRequest request){
		if(page ==null || page <= 1){
            page = 1;
        }
		if(periodId!=null){
        	request.setAttribute("periodId", periodId);
        }
        if(StringUtils.isNotEmpty(mobile)){
        	request.setAttribute("mobile", mobile);
        }
        if(StringUtils.isNotEmpty(productName)){
        	request.setAttribute("productName", productName);
        }
        if(startTime!=null){
        	request.setAttribute("startTime", startTime);
        }
        if(endTime!=null){
        	request.setAttribute("endTime", endTime);
        }
        if(expressStatus!=null){
        	request.setAttribute("expressStatus", expressStatus);
        }
        
		QueryResult result  =periodService.periodlist(page, periodId, mobile, productName, startTime, endTime,
				expressStatus,Constants.PERIOD_TYPE_OBJECT_VALUE);
		if(result==null){
			return new JspView("jsp/period/periodlist");
		}
		Map<Long, Integer>  userMap =userService.getUserList();
		request.setAttribute("userMap",userMap);
		request.setAttribute("periodlist", result.getList());
        request.setAttribute("pager", result.getPager());
		return new JspView("jsp/period/periodlist");
	} 
	
	/**
	 * 充值期次列表
	 * @param page
	 * @param periodId
	 * @param mobile
	 * @param productName
	 * @param startTime
	 * @param endTime
	 * @param expressStatus
	 * @param objtype
	 * @param request
	 * @return
	 */
	@At("/rechargePeriodlist")
	public View rechargePeriodlist(@Param("page")Integer page,@Param("periodId")Long periodId,@Param("mobile") String mobile,
			@Param("productName")String productName,@Param("startTime") String startTime, @Param("endTime")String endTime,
			@Param("status")String status,@Param("orderStatus")String orderStatus,HttpServletRequest request){
		if(page ==null || page <= 1){
            page = 1;
        }
		if(periodId!=null){
        	request.setAttribute("periodId", periodId);
        }
        if(StringUtils.isNotEmpty(mobile)){
        	request.setAttribute("mobile", mobile);
        }
        if(StringUtils.isNotEmpty(productName)){
        	request.setAttribute("productName", productName);
        }
        if(StringUtils.isNotEmpty(status)){
        	request.setAttribute("status", status);
        }
        if(startTime!=null){
        	request.setAttribute("startTime", startTime);
        }
        if(endTime!=null){
        	request.setAttribute("endTime", endTime);
        }
        if(StringUtils.isNotEmpty(orderStatus)){
        	request.setAttribute("orderStatus", orderStatus);
        }
		QueryResult result  =periodService.rechargePeriodList(page,periodId,mobile,startTime,endTime,status);
		if(result==null){
			return new JspView("jsp/period/recharge_periodlist");
		}
		request.setAttribute("periodlist", result.getList());
        request.setAttribute("pager", result.getPager());
		return new JspView("jsp/period/recharge_periodlist");
	} 
	
	@At("/periodDetail")
	public View getPeriodExpress(@Param("periodExpressId")Long periodExpressId,HttpServletRequest request){
		OrderExpress express = periodService.getOrderExperiod(periodExpressId);
		if(express==null){
			request.setAttribute("msg", "物流信息不存在");
		}
		Period period = periodService.getPeriodById(express.getPeriod_id());
		if(period!=null){
			request.setAttribute("period", period);
			User user = userService.getUserById(period.getLucky_user_id());
			request.setAttribute("user", user);
		}
		request.setAttribute("express", express);
		List<Express> expressllist = expressService.getExpressList();
		request.setAttribute("expresslist", expressllist);
		return new JspView("jsp/period/periodDetail");
	}
	@At("/expressOrder")
	public View expressOrder(@Param("periodExpressId")Long periodExpressId,@Param("express_id") Long express_id,
			@Param("express_code")String express_code,HttpServletRequest request){
		logService.operateLog(getAccount(request).getId(), "/expressOrder", "发货操作");
		OrderExpress express = periodService.getOrderExperiod(periodExpressId);
		if(express!=null){
			express.setExpress_id(express_id);
			express.setExpress_code(express_code);
			express.setStatus(OrderExpress.Status.DELIVERED.value());
			express.setExpress_time(new Date());
			periodService.updateOrderExpress(express);
			Period period = periodService.getPeriodById(express.getPeriod_id());
			Express ess =expressService.getExpressById(express_id);
			if(period!=null){
				request.setAttribute("period", period);
				User user = userService.getUserById(period.getLucky_user_id());
				String content = "您购买的"+period.getId()+"期夺宝奖品已安排发货，快递公司："+ess.getTitle()+" 快递单号："+express.getExpress_code()+".请登录快递公司官网查询货运状态，收货后请第一时间晒单，感谢您的支持！";
				try {
					SendUtils.sendMesage(user.getMobile(), content, "一元夺宝");
				} catch (SmsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("user", user);
			}
		}
		
		request.setAttribute("express", express);
		List<Express> expressllist = expressService.getExpressList();
		request.setAttribute("expresslist", expressllist);
		return new JspView("jsp/period/periodDetail");
	}
	
	@At("/getRecharegOrderDetail")
	public View getRecharegOrderDetail(@Param("express_id") Long express_id,HttpServletRequest request){
		OrderExpress express =periodService.getOrderExperiod(express_id);
		if(express!=null){
			RechargeRecord record=	periodService.getRechargeRecordById(express.getRecharge_record_id());
			request.setAttribute("record", record);
		}
		request.setAttribute("express", express);
		return new JspView("jsp/period/rechargeDetail");
	}
	
	@At("/closeRecharge")
	public View closeRecharge(@Param("record_id")Long record_id,@Param("content")String content,HttpServletRequest request){
		logService.operateLog(getAccount(request).getId(), "/closeRecharge", "关闭充值操作");
		RechargeRecord record=	periodService.getRechargeRecordById(record_id);
		if(record!=null){
			record.setStatus(9);
			if(StringUtils.isNotEmpty(content)){
				record.setRemark(content);
			}
			periodService.updateRecharge(record);
			OrderExpress express=	periodService.getOrderExperiod(Long.parseLong(record.getOrder_express_id()));
			request.setAttribute("record", record);
			request.setAttribute("express", express);
		}
		
		return new JspView("jsp/period/rechargeDetail");
	}
	
	/**
	 * 实物导出
	 * @param periodId
	 * @param mobile
	 * @param productName
	 * @param startTime
	 * @param endTime
	 * @param expressStatus
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@At("/excelImport")
	public Object periodExcelImport(@Param("periodId")Long periodId,@Param("mobile") String mobile,
			@Param("productName")String productName,@Param("startTime") String startTime, @Param("endTime")String endTime,
			@Param("expressStatus")Integer expressStatus,HttpServletRequest request,HttpServletResponse response)throws IOException{
		logService.operateLog(getAccount(request).getId(), "/excelImport", "实物订单导出操作");
		Map<String,String> map  = new HashMap<String, String>();
		if(periodId!=null){
        	request.setAttribute("periodId", periodId);
        }
        if(StringUtils.isNotEmpty(mobile)){
        	request.setAttribute("mobile", mobile);
        }
        if(StringUtils.isNotEmpty(productName)){
        	request.setAttribute("productName", productName);
        }
        if(startTime!=null){
        	request.setAttribute("startTime", startTime);
        }
        if(endTime!=null){
        	request.setAttribute("endTime", endTime);
        }
        if(expressStatus!=null){
        	request.setAttribute("expressStatus", expressStatus);
        }
        
		List<Record> result  =periodService.getExcelDataByObj(periodId, mobile, productName, startTime, endTime, expressStatus,0);
		

        String[] headers = {"期次","商品ID","商品名称","中奖号码","用户手机号","用户名","地址","开奖时间","确认地址时间"};
        Collection<Object> dataset=new ArrayList<Object>();
        map.put("code", "1");
        if(result!=null && !result.isEmpty()){
        	for (Record record : result) {
        		Long period = null;
        		Long product_id=null;
        		if(record.get("period_id")!=null){
        			period=(Long)record.get("period_id");
        		}
        		if(record.get("product_id")!=null){
        			product_id=(Long)record.get("product_id");
        		}
        		String title=record.getString("title");
        		String lucky_code=record.getString("lucky_code");
        		String user_mobile=record.getString("mobile");
        		String name=record.getString("name");
        		String province=record.getString("province");
        		String city=record.getString("city");
        		String district=record.getString("district");
        		String address= record.getString("address");
        		String award_time ="";
        		String commit_address_time="";
        		if(record.get("award_time")!=null){
        			award_time=DateFormatUtils.format((Date)record.get("award_time"), "yyyy-MM-dd HH:mm:ss");
        		}
        		if(record.get("commit_address_time")!=null){
        			commit_address_time= DateFormatUtils.format((Date)record.get("commit_address_time"), "yyyy-MM-dd HH:mm:ss");
        		}
        		 dataset.add(new EntityModel(period+"",product_id+"",title,lucky_code,user_mobile,name,province+city+district+address,
        				 award_time,commit_address_time));
			}
        	String fileName="duobao_fahuo"+ DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        	response.reset();
       		response.setContentType("application/msexcel");// 定义输出类型 
       		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
       		OutputStream out = response.getOutputStream();
       		ExcelUtil.exportExcel(headers, dataset, out);
       		out.flush();
       		out.close();
        }
       return null;
	}
	
	/**
	 * 虚拟导出
	 * @param periodId
	 * @param mobile
	 * @param productName
	 * @param startTime
	 * @param endTime
	 * @param expressStatus
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@At("/rechargeExcelImport")
	public Object rechargeExcelImport(@Param("periodId")Long periodId,@Param("mobile") String mobile,@Param("startTime") String startTime, @Param("endTime")String endTime,
			@Param("status")String status,@Param("orderStatus")String orderStatus,HttpServletRequest request,HttpServletResponse response)throws IOException{
		logService.operateLog(getAccount(request).getId(), "/rechargeExcelImport", "充值订单导出操作");
		logger.info("充值数据导出");
		 Map<String,String> map  = new HashMap<String, String>();
			if(periodId!=null){
	        	request.setAttribute("periodId", periodId);
	        }
	        if(StringUtils.isNotEmpty(mobile)){
	        	request.setAttribute("mobile", mobile);
	        }
	        if(StringUtils.isNotEmpty(status)){
	        	request.setAttribute("status", status);
	        }
	        if(startTime!=null){
	        	request.setAttribute("startTime", startTime);
	        }
	        if(endTime!=null){
	        	request.setAttribute("endTime", endTime);
	        }
	        
	        if(StringUtils.isNotEmpty(orderStatus)){
	        	request.setAttribute("orderStatus", orderStatus);
	        }
	        
			List<Record> result  =periodService.getRechargeExcelDataByObj(periodId,mobile,startTime,endTime,status,orderStatus);
			
	
	        String[] headers = {"期次","商品名称","充值金额","用户手机号","用户名","开奖时间","状态"};
	        Collection<Object> dataset=new ArrayList<Object>();
	        map.put("code", "1");
	        if(result!=null && !result.isEmpty()){
	        	for (Record record : result) {
	        		Long period = null;
	        		if(record.get("period_id")!=null){
	        			period=(Long)record.get("period_id");
	        		}
	        		Integer amount=record.getInt("amount");
	        		String user_mobile=record.getString("mobile");
	        		String name=record.getString("name");
	        		Integer user_status = record.getInt("status");
	        		String award_time ="";
	        		String status_str="";
	        		if(user_status==0 || user_status==-1){
	        			status_str="待充值";
	        		}
	        		if(user_status==2){
	        			status_str="充值成功";
	        		}
	        		if(user_status==-9){
	        			status_str="充值失败";
	        		}
	        		if(user_status==9){
	        			status_str="充值已经关闭";
	        		}
	        		if(record.get("award_time")!=null){
	        			award_time=DateFormatUtils.format((Date)record.get("award_time"), "yyyy-MM-dd HH:mm:ss");
	        		}
	        		String amountstr = String.valueOf(amount/100)+"元";
	        		 dataset.add(new RechargeModel(period+"","充值卡",amountstr,user_mobile,name,award_time,status_str+""));
				}
	        	   String fileName="duobao_recharge"+ DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	        	   response.reset();
		       		response.setContentType("application/msexcel");// 定义输出类型 
		       		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
		       		OutputStream out = response.getOutputStream();
		       		ExcelUtil.exportExcel(headers, dataset, out);
		       		out.flush();
		       		out.close();    
	        }  
	        return null;
	}
	/**
	 * 恢复订单状态
	 * @return
	 */
	@At("/updateExpress")
	public Object updateExpress(@Param("expressId")Long expressId,HttpServletRequest request){
		logService.operateLog(getAccount(request).getId(), "/updateExpress", "恢复订单操作");
		Map<String,String> map = new HashMap<String, String>();
		OrderExpress express =periodService.getOrderExperiod(expressId);
		if(express!=null){
			periodService.initOrderExpress(express);
			map.put("code", "0");
		}
		return map;
		
	}
	/**
	 * 客服查询订单跳转
	 * @param request
	 * @return
	 */
	@At("/customerPeriodList")
	public JspView customerPeriodList(HttpServletRequest request){
		
		return new JspView("jsp/period/customerPeriodList");
	}
	/**
	 * 客服查询订单
	 * @param periodId
	 * @param mobile
	 * @param request
	 * @return
	 */
	@At("/periodlistByCustomer")
	public View periodlistByCustomer(@Param("periodId")Long periodId,@Param("mobile") String mobile,HttpServletRequest request,HttpServletResponse response){
		if(periodId==null &&  StringUtils.isEmpty(mobile)){
			return new JspView("jsp/period/customerPeriodList");
		}
		
		if(StringUtils.isNotEmpty(mobile)){
			request.setAttribute("mobile", mobile);
			if(periodId!=null){
				request.setAttribute("periodId", periodId);
			}
			User user =userService.getUserByMobile(mobile);
			if(user!=null){
				//充值记录
				 List<UserAccountRecord> userAccountRecordList =	userService.getAccountRecordList(user.getId());

		         request.setAttribute("userAccountRecordList", userAccountRecordList);
		         List<Record> list = userService.getUserDuobaoRecord(user.getId(),periodId);
		         Map<Long, Period> periodMap = new HashMap<Long, Period>();
		         Map<Long, List<UserDuobaoRecord>> userDuobaoMap = new HashMap<Long, List<UserDuobaoRecord>>();
		         for (Record record : list) {
		             Long period_id = (Long)record.get("period_id");
		             String list_img = record.getString("list_img");
		             Integer type = record.getInt("type");
		             String title = record.getString("title");
		             Integer status = record.getInt("status");
		             Date start_time = (Date)record.get("start_time");
		             Date award_time = (Date)record.get("award_time");
		             String lucky_code = record.getString("lucky_code");

		             Long user_id = (Long)record.get("user_id");
		            // String user_nick = record.getString("nick");
		             Integer buy_count = record.getInt("y_count");
		             String buy_code = record.getString("buy_code");
		             Integer lucky = record.getInt("lucky");
		             Date buy_time = (Date)record.get("buy_time");

		             Period period = new Period();
		             period.setId(period_id);
		             period.setList_img(list_img);
		             period.setTitle(title);
		             period.setStatus(status);
		             period.setStart_time(start_time);
		             period.setAward_time(award_time);
		             period.setLucky_code(lucky_code);
		             period.setType(type);
		             if(periodMap.get(period_id) == null){
		                 periodMap.put(period_id, period);
		             }
		             UserDuobaoRecord userDuobaoRecord = new UserDuobaoRecord();
		             userDuobaoRecord.setUser_id(user_id);
		            // userDuobaoRecord.setUser_nick(user_nick);
		             userDuobaoRecord.setBuy_count(buy_count);
		             userDuobaoRecord.setBuy_code(buy_code);
		             userDuobaoRecord.setLucky(lucky);
		             userDuobaoRecord.setGmt_create(buy_time);

		             if(userDuobaoMap.get(period_id) != null){
		                 userDuobaoMap.get(period_id).add(userDuobaoRecord);
		             }else{
		                 List<UserDuobaoRecord> userDuobaoRecordList = new ArrayList<UserDuobaoRecord>();
		                 userDuobaoRecordList.add(userDuobaoRecord);
		                 userDuobaoMap.put(period_id, userDuobaoRecordList);
		             }
		         }
		         request.setAttribute("user", user);
		         request.setAttribute("periodMap", periodMap);
		         request.setAttribute("userDuobaoMap", userDuobaoMap);
		         
		         Map<Long,OrderExpress>  orderMap=  periodService.getExpressByUser(periodId,user.getId());
		         request.setAttribute("orderMap", orderMap);
		         Map<Long,CustomerOperateLog> logMap=  logService.getCustomerLogBySearch(periodId,mobile);
		         request.setAttribute("logMap", logMap);
			}
		}
			
         
		QueryResult result  =periodService.periodlist(1, periodId, mobile, null, null, null,
				null,Constants.PERIOD_TYPE_OBJECT_VALUE);
		if(result==null){
			return new JspView("jsp/period/recharge_periodlist");
		}
		request.setAttribute("periodlist", result.getList());
		return new JspView("jsp/period/customerPeriodList");
	}
	@At("/customerLog")
	public Map customerLog(@Param("expressId")Long expressId,@Param("mobile") String mobile,@Param("type") Integer type,@Param("remark") String remark,HttpServletRequest request){
		Map<String,String> map = new HashMap<String, String>();
		OrderExpress express =periodService.getOrderExperiod(expressId);
		if(express!=null){
			CustomerOperateLog log = new CustomerOperateLog();
			log.setGmt_create(new Date());
			log.setGmt_modified(new Date());
			log.setMobile(mobile);
			log.setPeriod_id(express.getPeriod_id());
			log.setRemark(remark);
			log.setType(type);
			log.setUser_id(express.getUser_id());
			log.setOperate_id(getAccount(request).getId());
			logService.customerChangeOrder(log, express);
			map.put("code", "0");
			map.put("period_id", express.getPeriod_id()+"");
		}else{
			map.put("code", "1");
		}
		return  map;
	}
	@At("/closeRecharge")
	public Map closeRecharge(@Param("expressId")Long expressId,HttpServletRequest request ){
		Map<String,String> map = new HashMap<String, String>();
		OrderExpress express =periodService.getOrderExperiod(expressId);
		if(express!=null){
			express.setStatus(OrderExpress.Status.DELIVERED.value());
			express.setGmt_modified(new Date());
			periodService.updateOrderExpress(express);
			RechargeRecord record = periodService.getRechargeRecordById(express.getRecharge_record_id());
			if(record !=null){
				record.setStatus(RechargeRecord.Status.SUCCESS.value());
				periodService.updateRecharge(record);
				map.put("code", "0");
			}
		}else{
				map.put("code", "1");
		}
		return map;
	}
}
