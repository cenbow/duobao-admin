package com.aibinong.backyard.web.module;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ForwardView;
import org.nutz.mvc.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.pojo.AwardPlan;
import com.aibinong.backyard.pojo.LadderDiscount;
import com.aibinong.backyard.pojo.MarketingActivity;
import com.aibinong.backyard.service.MarketingService;

@IocBean
public class MarketingModule extends BaseModule{
	private static Logger logger = LoggerFactory.getLogger(MarketingModule.class);
	
	@Inject
	private MarketingService marketingService;
	@At("/marketlist")
	public View getMarketingList(@Param("name")String name,@Param("startTime")Date startTime,
			@Param("endTime")Date endTime,@Param("page")Integer page,HttpServletRequest request){
		if(page ==null || page <= 1){
            page = 1;
        }
		QueryResult result =marketingService.loadMarketingList(name, startTime, endTime, page);
		
		request.setAttribute("marketlist", result.getList());
		request.setAttribute("page", result.getPager());
		return new JspView("jsp/marketing/marketinglist");
	}
	
	@At("/createMarketing")
	public View createMarketing(HttpServletRequest request){
		return new JspView("jsp/marketing/createMarketing");
	}
	
	@At("/createMarket")
	@Ok("redirect:/marketlist.html")
	public void createMarket(@Param("name")String name,@Param("startTime")Date startTime,@Param("endTime")Date endTime,
			@Param("limit_amount")String[] limit_amount,@Param("reduce_amount")String[] reduce_amount,
			@Param("discount")String[] discount,HttpServletRequest request){
			marketingService.createMarketing(name,startTime,endTime,limit_amount,reduce_amount,discount);
			logService.operateLog(getAccount(request).getId(), "/createMarket", "创建营销活动操作");
	}
	
	@At("/updateMarket")
	public void updateMarket(@Param("marketId")Long marketId,@Param("name")String name,@Param("startTime")String startTime,
			@Param("endTime")String endTime,HttpServletRequest request,HttpServletResponse response){
		logService.operateLog(getAccount(request).getId(), "/updateMarket","修改营销活动操作");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		MarketingActivity market = marketingService.getMarketDetail(marketId);
		try {
			if (market != null) {
				if (StringUtils.isNotEmpty(name)) {
					market.setName(name);
				}
				if (startTime != null) {
					market.setGmt_validity_start(format.parse(startTime));
				}
				if (endTime != null) {
					market.setGmt_validity_end(format.parse(endTime));
				}
				marketingService.updateMarket(market);
			}
		} catch (Exception e) {
			request.setAttribute("msg", "操作失败");
		}
		try {
			response.sendRedirect("/marketDetail.html?id=" + marketId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@At("/marketDetail")
	public View getMarketDetail(@Param("id")Long id,HttpServletRequest request){
		MarketingActivity marketing =marketingService.getMarketDetail(id);
		if(marketing!=null){
			List<LadderDiscount> discountlist = marketingService.getDisCountListByMarket(marketing.getId());
			request.setAttribute("discountlist", discountlist);
		}
		request.setAttribute("marketing", marketing);
		return new JspView("jsp/marketing/marketingDetail");
	}
	@At("/disCountDetail")
	public View getDisCountDetail(@Param("id")Long id,HttpServletRequest request){
		LadderDiscount discount = marketingService.getLadderDisCount(id);
		request.setAttribute("discount", discount);
		if (discount != null) {
			List<AwardPlan> planlist = marketingService.getPlanList(discount.getId(), discount.getMarketing_id());
			if (planlist != null && !planlist.isEmpty()) {
				for (AwardPlan awardPlan : planlist) {
					StringBuffer str = new StringBuffer();
					String[] award = awardPlan.getAward_data().split(",");
					if (award != null && award.length > 0) {
						for (int i = 0; i < award.length; i++) {
							str.append(Integer.parseInt(award[i]) / 100).append(",");
						}
						awardPlan.setAward_data(str.toString().substring(0,str.toString().length() - 1));
					}
				}
				request.setAttribute("planlist", planlist);
			}
		}
		return new JspView("jsp/marketing/createPlan");
	}
	
	@At("/createPlan")
	public void createPlan(@Param("limit_amount")String limit_amount,@Param("reduce_amount")String reduce_amount,
			@Param("discount")String discount,@Param("id")Long id,@Param("interval_date")String[] interval_date,
			@Param("face_value")String[] face_value,@Param("validity_date")String[] validity_date,@Param("award_data")String[] award_data,
			HttpServletRequest request,HttpServletResponse response){
			logService.operateLog(getAccount(request).getId(), "/createPlan","创建红包计划操作");
			LadderDiscount dis = marketingService.getLadderDisCount(id);
			try{
			if (interval_date == null || face_value == null
					|| validity_date == null || award_data == null) {
				request.setAttribute("msg", "请求错误");
				response.sendRedirect("/disCountDetail.html?id="+id);			
				}
			if (StringUtils.isNotEmpty(limit_amount)) {
				dis.setLimit_amount(Integer.parseInt(limit_amount)*100);
			}
			if (StringUtils.isNotEmpty(reduce_amount)) {
				dis.setReduce_amount(Integer.parseInt(reduce_amount)*100);
			}
			if (StringUtils.isNotEmpty(discount)) {
				dis.setDiscount(Double.parseDouble(discount));
			}
			if (discount != null) {
				marketingService.createPlan(dis, interval_date, face_value,
						validity_date, award_data);
			}
				response.sendRedirect("/disCountDetail.html?id="+id);		
			}catch(Exception e){
				try {
					logger.info("创建计划异常：",e);
					response.sendRedirect("/disCountDetail.html?id="+id);
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
	}
	@At("/marketUp")
	public Object marketUp(@Param("marketId")Long marketId,@Param("status") Integer status,HttpServletRequest request){
		logService.operateLog(getAccount(request).getId(), "/marketUp","营销活动上架操作");
		MarketingActivity activity = marketingService.getMarketDetail(marketId);
		Map<String, String> map = new HashMap<String, String>();
		if (activity != null) {
			activity.setStatus(status);
			marketingService.updateMarket(activity);
			map.put("code", "0");
		}
		return map;
	}
}
