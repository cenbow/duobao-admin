package com.aibinong.backyard.web.module;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.aibinong.backyard.service.ReportService;
import com.aibinong.backyard.util.DateUtil;
import com.aibinong.backyard.util.NumberUtil;

/**
 * 数据报表
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年8月15日 下午12:55:31
 */
@IocBean
public class ReportModule extends BaseModule {

	@Inject
	private ReportService reportService;

	@At("/reportProduct")
	@Ok("jsp:jsp.report.product")
	public void getProductReport(HttpServletRequest request, @Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("productId") Long productId, @Param("page") Integer page) {

		if (page == null || page < 1) {
			page = 1;
		}

		if (StringUtils.isBlank(endDate)) {
			endDate = DateUtil.format(new Date(), DateUtil.SDF_SHORT);
		}

		if (StringUtils.isBlank(startDate)) {
			startDate = endDate;
		}

		QueryResult result = reportService.getProductReport(startDate, endDate, productId, page, 50);
		int total = reportService.getProductReportTotal(startDate, endDate, productId);

		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("productId", productId);
		request.setAttribute("pager", result.getPager());
		request.setAttribute("list", result.getList());
		request.setAttribute("total", NumberUtil.formatDouble(total, "#,##0.00"));
	}

	@At("/reportProductCharts")
	@Ok("jsp:jsp.report.product_charts")
	public void getProductReport(HttpServletRequest request, @Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("productId") Long productId) {
		if (StringUtils.isBlank(endDate)) {
			endDate = DateUtil.format(new Date(), DateUtil.SDF_SHORT);
		}

		if (StringUtils.isBlank(startDate)) {
			startDate = DateUtil.format(DateUtil.addDays(new Date(), -7), DateUtil.SDF_SHORT);
		}

		String json = reportService.getProductCharts(startDate, endDate, productId);

		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("productId", productId);
		request.setAttribute("productEcharts", json);
	}

	@At("/reportChannel")
	@Ok("jsp:jsp.report.channel")
	public void getChannelReport(HttpServletRequest request, @Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("clientId") String clientId, @Param("os") String os, @Param("channel") String channel, @Param("page") Integer page) {

		if (page == null || page < 1) {
			page = 1;
		}

		if (StringUtils.isBlank(endDate)) {
			endDate = DateUtil.format(new Date(), DateUtil.SDF_SHORT);
		}

		if (StringUtils.isBlank(startDate)) {
			startDate = endDate;
		}
		QueryResult result = reportService.getChannelReport(startDate, endDate, clientId, os, channel, page, 50);
		Record record = reportService.getChannelReportTotal(startDate, endDate, clientId, os, channel);

		long payAmount = NumberUtil.parseLong(record.getString("pay_amount"));
		long rechargeAmount = NumberUtil.parseLong(record.getString("recharge_amount"));
		long newPayAmount = NumberUtil.parseLong(record.getString("new_pay_amount"));
		long newRechargeAmount = NumberUtil.parseLong(record.getString("new_recharge_amount"));
		long payAmounts = payAmount + rechargeAmount;
		long newPayAmounts = newPayAmount + newRechargeAmount;

		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("clientId", clientId);
		request.setAttribute("os", os);
		request.setAttribute("channel", channel);
		request.setAttribute("pager", result.getPager());
		request.setAttribute("list", result.getList());
		request.setAttribute("pay_amount_total", NumberUtil.formatDouble(payAmount / 100d, "#,##0.00"));
		request.setAttribute("recharge_amount_total", NumberUtil.formatDouble(rechargeAmount / 100d, "#,##0.00"));
		request.setAttribute("new_pay_amount_total", NumberUtil.formatDouble(newPayAmount / 100d, "#,##0.00"));
		request.setAttribute("new_recharge_amount_total", NumberUtil.formatDouble(newRechargeAmount / 100d, "#,##0.00"));
		request.setAttribute("pay_amounts_total", NumberUtil.formatDouble(payAmounts / 100d, "#,##0.00"));
		request.setAttribute("new_pay_amounts_total", NumberUtil.formatDouble(newPayAmounts / 100d, "#,##0.00"));
		
		if (startDate.equals(endDate)) {
			@SuppressWarnings("unchecked")
			List<Record> list = (List<Record>) result.getList();
			int activateNumTotal = 0;
			int registerNumTotal = 0;
			int payNumTotal = 0;
			int newPayNumTotal = 0;
			for (Record e : list) {
				activateNumTotal += e.getInt("activate_num");
				registerNumTotal += e.getInt("register_num");
				payNumTotal += e.getInt("pay_num");
				newPayNumTotal += e.getInt("new_pay_num");
			}

			request.setAttribute("isSameDay", true);
			request.setAttribute("activate_num_total", activateNumTotal);
			request.setAttribute("register_num_total", registerNumTotal);
			request.setAttribute("activate_total_rate", NumberUtil.getPercent(registerNumTotal, activateNumTotal));
			request.setAttribute("pay_num_total", payNumTotal);
			request.setAttribute("new_pay_num_total", newPayNumTotal);
			request.setAttribute("new_pay_total_rate", NumberUtil.getPercent(newPayNumTotal, registerNumTotal));
		}
	}

	@At("/reportChannelCharts")
	@Ok("jsp:jsp.report.channel_charts")
	public void getChannelReportCharts(HttpServletRequest request, @Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("clientId") String clientId, @Param("os") String os, @Param("channel") String channel) {
		if (StringUtils.isBlank(endDate)) {
			endDate = DateUtil.format(new Date(), DateUtil.SDF_SHORT);
		}

		if (StringUtils.isBlank(startDate)) {
			startDate = DateUtil.format(DateUtil.addDays(new Date(), -7), DateUtil.SDF_SHORT);
		}

		Record record = reportService.getChannelReportCharts(startDate, endDate, clientId, os, channel);

		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("clientId", clientId);
		request.setAttribute("os", os);
		request.setAttribute("channel", channel);
		request.setAttribute("list", record.get("dataList"));
		request.setAttribute("channelConversionEcharts", record.getString("channelConversionEcharts"));
		request.setAttribute("channelPayerEcharts", record.getString("channelPayerEcharts"));
		request.setAttribute("channelAmountEcharts", record.getString("channelAmountEcharts"));
	}

	@At("/reportChannelHour")
	@Ok("jsp:jsp.report.channel_hour")
	public void getChannelChart(HttpServletRequest request, @Param("date") String date, @Param("clientId") String clientId, @Param("os") String os,
			@Param("channel") String channel) {
		try {
			date = date.replaceAll("-", "");
			Date curDate = DateUtil.parse(date, "yyyyMMdd");
			date = DateUtil.format(curDate, "yyyy-MM-dd");
		} catch (ParseException ex) {
		}

		List<Record> list = reportService.getChannelHourReport(date, clientId, os, channel);
		String json = reportService.getChannelHourCharts(list);
		Record record = reportService.getChannelHourReportTotal(date, clientId, os, channel);
		long payAmount = NumberUtil.parseLong(record.getString("pay_amount"));
		long rechargeAmount = NumberUtil.parseLong(record.getString("recharge_amount"));
		long newPayAmount = NumberUtil.parseLong(record.getString("new_pay_amount"));
		long newRechargeAmount = NumberUtil.parseLong(record.getString("new_recharge_amount"));
		long payAmounts = payAmount + rechargeAmount;
		long newPayAmounts = newPayAmount + newRechargeAmount;

		request.setAttribute("date", date);
		request.setAttribute("clientId", clientId);
		request.setAttribute("os", os);
		request.setAttribute("channel", channel);
		request.setAttribute("list", list);
		request.setAttribute("hourEcharts", json);
		request.setAttribute("pay_amount_total", NumberUtil.formatDouble(payAmount / 100d, "#,##0.00"));
		request.setAttribute("recharge_amount_total", NumberUtil.formatDouble(rechargeAmount / 100d, "#,##0.00"));
		request.setAttribute("new_pay_amount_total", NumberUtil.formatDouble(newPayAmount / 100d, "#,##0.00"));
		request.setAttribute("new_recharge_amount_total", NumberUtil.formatDouble(newRechargeAmount / 100d, "#,##0.00"));
		request.setAttribute("pay_amounts_total", NumberUtil.formatDouble(payAmounts / 100d, "#,##0.00"));
		request.setAttribute("new_pay_amounts_total", NumberUtil.formatDouble(newPayAmounts / 100d, "#,##0.00"));
	}

	@At("/reportUserPay")
	@Ok("jsp:jsp.report.user_pay")
	public void getUserPayReport(HttpServletRequest request, @Param("userId") Long userId, @Param("mobile") String mobile, @Param("page") Integer page) {
		if (page == null || page < 1) {
			page = 1;
		}

		QueryResult result = reportService.getUserPayReport(userId, mobile, page, 50);

		request.setAttribute("userId", userId);
		request.setAttribute("mobile", mobile);
		request.setAttribute("pager", result.getPager());
		request.setAttribute("list", result.getList());
	}

}
