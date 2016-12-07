package com.aibinong.backyard.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;

import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.Product;
import com.aibinong.backyard.service.ReportService;
import com.aibinong.backyard.util.DateUtil;
import com.aibinong.backyard.util.NumberUtil;
import com.aibinong.backyard.util.NutzUtil;
import com.aibinong.backyard.util.TemplateUtil;
import com.aibinong.backyard.web.MainModule;

@IocBean(name = "reportService")
public class ReportServiceImpl implements ReportService {

	@Inject
	private BasicDao basicDao;

	@Override
	public QueryResult getProductReport(String startDate, String endDate, Long productId, int pageNo, int pageSize) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT date, product_id, title, list_img, numbers, buy_count\n");
		sb.append("FROM product_report\n");
		sb.append("WHERE 1 = 1\n");
		sb.append("AND date >= '" + startDate.replaceAll("-", "") + "'");
		sb.append("AND date <= '" + endDate.replaceAll("-", "") + "'");
		if (productId != null) {
			sb.append("AND product_id = " + productId + "\n");
		}
		sb.append("ORDER BY date, buy_count DESC");

		QueryResult result = basicDao.querySqlResult(sb.toString(), pageNo, pageSize);

		@SuppressWarnings("unchecked")
		List<Record> list = (List<Record>) result.getList();
		if (list != null && list.size() > 0) {
			for (Record record : list) {
				int buyCount = record.getInt("buy_count");
				record.put("amount_fmt", NumberUtil.formatDouble(buyCount, "#,##0.00"));
			}
		}
		return result;
	}

	@Override
	public String getProductCharts(String startDate, String endDate, long productId) {
		Product product = basicDao.find(productId, Product.class);

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t1.date, t2.product_id, IFNULL(t2.buy_count, 0) AS buy_count\n");
		sb.append("FROM (" + this.getDatesView(startDate, endDate, DateUtil.SDF_SHORT) + ") t1\n");
		sb.append("LEFT OUTER JOIN product_report t2\n");
		sb.append("ON (t1.date = t2.date AND t2.product_id = " + productId + ")\n");
		sb.append("ORDER BY t1.date");

		List<Record> list = basicDao.select(sb.toString());

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("title", product.getTitle());
		data.put("list", list);
		return TemplateUtil.format("/report/echarts_product_sales.ftl", data);
	}

	@Override
	public int getProductReportTotal(String startDate, String endDate, Long productId) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT IFNULL(SUM(buy_count), 0) AS total\n");
		sb.append("FROM product_report\n");
		sb.append("WHERE 1 = 1\n");
		sb.append("AND date >= '" + startDate.replaceAll("-", "") + "'");
		sb.append("AND date <= '" + endDate.replaceAll("-", "") + "'");
		if (productId != null) {
			sb.append("AND product_id = " + productId + "\n");
		}
		return basicDao.fetchInt(sb.toString());
	}

	@Override
	public QueryResult getChannelReport(String startDate, String endDate, String clientId, String os, String channel, int pageNo, int pageSize) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t1.date, t1.client_id, t1.os, t1.channel, t1.activate_num, t1.activity_num, t1.register_num,\n");
		sb.append("t1.pay_num, t1.new_pay_num, t1.pay_amount, t1.recharge_amount, t1.new_pay_amount, t1.new_recharge_amount, t2.icon\n");
		sb.append("FROM channel_daily_report t1, app t2\n");
		sb.append("WHERE t1.client_id = t2.client_id\n");
		sb.append("AND t1.date >= '" + startDate.replaceAll("-", "") + "'\n");
		sb.append("AND t1.date <= '" + endDate.replaceAll("-", "") + "'\n");
		if (StringUtils.isNotBlank(clientId)) {
			sb.append("AND t1.client_id = '" + clientId + "'\n");
		}
		if (StringUtils.isNotBlank(os)) {
			sb.append("AND t1.os = '" + os + "'\n");
		}
		if (StringUtils.isNotBlank(channel)) {
			sb.append("AND t1.channel = '" + channel + "'\n");
		}
		sb.append("ORDER BY t1.date, t1.client_id, t1.channel, t1.os\n");

		QueryResult result = basicDao.querySqlResult(sb.toString(), pageNo, pageSize);

		@SuppressWarnings("unchecked")
		List<Record> list = (List<Record>) result.getList();
		if (list != null && list.size() > 0) {
			for (Record record : list) {
				int activateNum = record.getInt("activate_num");
				int registerNum = record.getInt("register_num");
				int payNum = record.getInt("pay_num");
				int newPayNum = record.getInt("new_pay_num");
				long payAmount = NumberUtil.parseLong(record.getString("pay_amount"));
				long rechargeAmount = NumberUtil.parseLong(record.getString("recharge_amount"));
				long newPayAmount = NumberUtil.parseLong(record.getString("new_pay_amount"));
				long newRechargeAmount = NumberUtil.parseLong(record.getString("new_recharge_amount"));
				double arppu = payNum > 0 ? NumberUtil.div(payAmount + rechargeAmount, payNum * 100) : 0;
				record.put("new_pay_rate", NumberUtil.getPercent(newPayNum, registerNum));
				record.put("register_rate", NumberUtil.getPercent(registerNum, activateNum));
				record.put("arppu", NumberUtil.formatDouble(arppu, "#,##0.00")); // 付费金额 / 付费人数
				record.put("pay_amount_fmt", NumberUtil.formatDouble(payAmount / 100d, "#,##0.00"));
				record.put("recharge_amount_fmt", NumberUtil.formatDouble(rechargeAmount / 100d, "#,##0.00"));
				record.put("new_pay_amount_fmt", NumberUtil.formatDouble(newPayAmount / 100d, "#,##0.00"));
				record.put("new_recharge_amount_fmt", NumberUtil.formatDouble(newRechargeAmount / 100d, "#,##0.00"));

				record.put("pay_amounts_fmt", NumberUtil.formatDouble((payAmount + rechargeAmount) / 100d, "#,##0.00"));
				record.put("new_pay_amounts_fmt", NumberUtil.formatDouble((newPayAmount + newRechargeAmount) / 100d, "#,##0.00"));
			}
		}
		return result;
	}

	@Override
	public Record getChannelReportTotal(String startDate, String endDate, String clientId, String os, String channel) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT IFNULL(SUM(pay_amount), 0) AS pay_amount, IFNULL(SUM(recharge_amount), 0) AS recharge_amount,\n");
		sb.append("	IFNULL(SUM(new_pay_amount), 0) AS new_pay_amount, IFNULL(SUM(new_recharge_amount), 0) AS new_recharge_amount\n");
		sb.append("FROM channel_daily_report\n");
		sb.append("WHERE 1 = 1\n");
		sb.append("AND date >= '" + startDate.replaceAll("-", "") + "'\n");
		sb.append("AND date <= '" + endDate.replaceAll("-", "") + "'\n");
		if (StringUtils.isNotBlank(clientId)) {
			sb.append("AND client_id = '" + clientId + "'\n");
		}
		if (StringUtils.isNotBlank(clientId)) {
			sb.append("AND client_id = '" + clientId + "'\n");
		}
		if (StringUtils.isNotBlank(os)) {
			sb.append("AND os = '" + os + "'\n");
		}
		if (StringUtils.isNotBlank(channel)) {
			sb.append("AND channel = '" + channel + "'\n");
		}
		return basicDao.selectOne(sb.toString());
	}

	@Override
	public Record getChannelReportCharts(String startDate, String endDate, String clientId, String os, String channel) {
		Record result = new Record();

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT date, SUM(activate_num) AS activate_num, SUM(register_num) AS register_num,\n");
		sb.append("	SUM(pay_num) AS pay_num, SUM(new_pay_num) AS new_pay_num, SUM(pay_amount) AS pay_amount,\n");
		sb.append("	SUM(recharge_amount) AS recharge_amount, SUM(new_pay_amount) AS new_pay_amount, SUM(new_recharge_amount) AS new_recharge_amount\n");
		sb.append("FROM channel_daily_report\n");
		sb.append("WHERE date >= '" + startDate.replaceAll("-", "") + "'\n");
		sb.append("AND date <= '" + endDate.replaceAll("-", "") + "'\n");
		if (StringUtils.isNotBlank(clientId)) {
			sb.append("AND client_id = '" + clientId + "'");
		}
		if (StringUtils.isNotBlank(os)) {
			sb.append("AND os = '" + os + "'");
		}
		if (StringUtils.isNotBlank(channel)) {
			sb.append("AND channel = '" + channel + "'");
		}
		sb.append("GROUP BY date\n");
		sb.append("ORDER BY date\n");
		List<Record> list = basicDao.select(sb.toString());
		if (list != null && list.size() > 0) {
			for (Record record : list) {
				int activateNum = record.getInt("activate_num");
				int registerNum = record.getInt("register_num");
				int payNum = record.getInt("pay_num");
				int newPayNum = record.getInt("new_pay_num");
				long payAmount = NumberUtil.parseLong(record.getString("pay_amount"));
				long rechargeAmount = NumberUtil.parseLong(record.getString("recharge_amount"));
				long newPayAmount = NumberUtil.parseLong(record.getString("new_pay_amount"));
				long newRechargeAmount = NumberUtil.parseLong(record.getString("new_recharge_amount"));
				double arppu = payNum > 0 ? NumberUtil.div(payAmount + rechargeAmount, payNum * 100) : 0;
				record.put("new_pay_rate", NumberUtil.getPercent(newPayNum, registerNum));
				record.put("register_rate", NumberUtil.getPercent(registerNum, activateNum));
				record.put("arppu", NumberUtil.formatDouble(arppu, "#,##0.00")); // 付费金额 / 付费人数
				record.put("pay_amount_fmt", NumberUtil.formatDouble(payAmount / 100d, "#,##0.00"));
				record.put("recharge_amount_fmt", NumberUtil.formatDouble(rechargeAmount / 100d, "#,##0.00"));
				record.put("pay_amounts_fmt", NumberUtil.formatDouble((payAmount + rechargeAmount) / 100d, "#,##0.00"));
				record.put("new_pay_amount_fmt", NumberUtil.formatDouble(newPayAmount / 100d, "#,##0.00"));
				record.put("new_recharge_amount_fmt", NumberUtil.formatDouble(newRechargeAmount / 100d, "#,##0.00"));
				record.put("new_pay_amounts_fmt", NumberUtil.formatDouble((newPayAmount + newRechargeAmount) / 100d, "#,##0.00"));

				// eCharts数据格式化
				double payAmounts = NumberUtil.div(payAmount + rechargeAmount, 100, 2); // 付费金额(单位元)
				double newPayAmounts = NumberUtil.div(newPayAmount + newRechargeAmount, 100, 2); // 新增付费金额(单位元)
				record.put("pay_amounts", payAmounts);
				record.put("new_pay_amounts", newPayAmounts);
			}
		}
		result.put("dataList", list);

		// eCharts数据
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);
		String channelConversionEcharts = TemplateUtil.format("/report/echarts_channel_conversion_trends.ftl", data);
		String channelPayerEcharts = TemplateUtil.format("/report/echarts_channel_payer_trends.ftl", data);
		String channelAmountEcharts = TemplateUtil.format("/report/echarts_channel_amount_trends.ftl", data);
		result.put("channelConversionEcharts", channelConversionEcharts);
		result.put("channelPayerEcharts", channelPayerEcharts);
		result.put("channelAmountEcharts", channelAmountEcharts);
		return result;
	}

	@Override
	public List<Record> getChannelHourReport(String date, String clientId, String os, String channel) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT time, activate_num, activity_num, register_num, pay_num, new_pay_num,\n");
		sb.append(" pay_amount, recharge_amount, new_pay_amount, new_recharge_amount\n");
		sb.append("FROM channel_hour_report\n");
		sb.append("WHERE 1 = 1\n");
		sb.append("AND date = '" + date.replaceAll("-", "") + "'\n");
		sb.append("AND client_id = '" + clientId + "'\n");
		sb.append("AND client_id = '" + clientId + "'\n");
		sb.append("AND os = '" + os + "'\n");
		sb.append("AND channel = '" + channel + "'\n");
		sb.append("ORDER BY time");

		Map<String, Record> hourMap = new HashMap<String, Record>();

		List<Record> list = basicDao.select(sb.toString());
		if (list != null && list.size() > 0) {
			for (Record record : list) {
				String time = record.getString("time");
				String hour = time.substring(8);
				hourMap.put(hour, record);
			}
		}

		int timePoint = 24;
		String todayString = DateUtil.format(new Date(), "yyyy-MM-dd");
		if (todayString.equals(date)) {
			try {
				timePoint = (int) (DateUtil.diffDate(DateUtil.parse(todayString, "yyyy-MM-dd"), new Date()) / 60 / 60);
				if (timePoint < 24) {
					timePoint++;
				}
			} catch (ParseException ex) {
			}
		}

		List<Record> result = new ArrayList<Record>();
		for (int i = 0; i < timePoint; i++) {
			String hourString = i < 10 ? "0" + i : String.valueOf(i);
			if (hourMap.containsKey(hourString)) {
				Record record = hourMap.get(hourString);
				record.put("hour", hourString);
				record.put("time", (i) + "~" + (i + 1) + "点");
				result.add(record);
			} else {
				Record record = new Record();
				record.put("hour", hourString);
				record.put("time", (i) + "~" + (i + 1) + "点");
				record.put("activate_num", 0);
				record.put("activity_num", 0);
				record.put("register_num", 0);
				record.put("pay_num", 0);
				record.put("new_pay_num", 0);
				record.put("pay_amount", 0);
				record.put("recharge_amount", 0);
				record.put("new_pay_amount", 0);
				record.put("new_recharge_amount", 0);
				result.add(record);
			}
		}

		for (Record record : result) {
			int activateNum = record.getInt("activate_num");
			int registerNum = record.getInt("register_num");
			int payNum = record.getInt("pay_num");
			int newPayNum = record.getInt("new_pay_num");
			long payAmount = NumberUtil.parseLong(record.getString("pay_amount"));
			long rechargeAmount = NumberUtil.parseLong(record.getString("recharge_amount"));
			long newPayAmount = NumberUtil.parseLong(record.getString("new_pay_amount"));
			long newRechargeAmount = NumberUtil.parseLong(record.getString("new_recharge_amount"));
			double arppu = payNum > 0 ? NumberUtil.div(payAmount + rechargeAmount, payNum * 100) : 0;
			record.put("new_pay_rate", NumberUtil.getPercent(newPayNum, registerNum));
			record.put("register_rate", NumberUtil.getPercent(registerNum, activateNum));
			record.put("arppu", NumberUtil.formatDouble(arppu, "#,##0.00")); // 付费金额 / 付费人数
			record.put("pay_amount_fmt", NumberUtil.formatDouble(payAmount / 100d, "#,##0.00"));
			record.put("recharge_amount_fmt", NumberUtil.formatDouble(rechargeAmount / 100d, "#,##0.00"));
			record.put("new_pay_amount_fmt", NumberUtil.formatDouble(newPayAmount / 100d, "#,##0.00"));
			record.put("new_recharge_amount_fmt", NumberUtil.formatDouble(newRechargeAmount / 100d, "#,##0.00"));

			record.put("pay_amounts_fmt", NumberUtil.formatDouble((payAmount + rechargeAmount) / 100d, "#,##0.00"));
			record.put("new_pay_amounts_fmt", NumberUtil.formatDouble((newPayAmount + newRechargeAmount) / 100d, "#,##0.00"));
		}
		return result;
	}

	@Override
	public Record getChannelHourReportTotal(String date, String clientId, String os, String channel) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT IFNULL(SUM(pay_amount), 0) AS pay_amount, IFNULL(SUM(recharge_amount), 0) AS recharge_amount,\n");
		sb.append("	IFNULL(SUM(new_pay_amount), 0) AS new_pay_amount, IFNULL(SUM(new_recharge_amount), 0) AS new_recharge_amount\n");
		sb.append("FROM channel_hour_report\n");
		sb.append("WHERE 1 = 1\n");
		sb.append("AND date = '" + date.replaceAll("-", "") + "'\n");
		if (StringUtils.isNotBlank(clientId)) {
			sb.append("AND client_id = '" + clientId + "'\n");
		}
		if (StringUtils.isNotBlank(clientId)) {
			sb.append("AND client_id = '" + clientId + "'\n");
		}
		if (StringUtils.isNotBlank(os)) {
			sb.append("AND os = '" + os + "'\n");
		}
		if (StringUtils.isNotBlank(channel)) {
			sb.append("AND channel = '" + channel + "'\n");
		}
		return basicDao.selectOne(sb.toString());
	}

	@Override
	public String getChannelHourCharts(List<Record> channelHourList) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", channelHourList);
		return TemplateUtil.format("/report/echarts_channel_pay_num.ftl", data);
	}

	@Override
	public QueryResult getUserPayReport(Long userId, String mobile, int pageNo, int pageSize) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t1.user_id, (t1.pay_amount+t1.recharge_amount) AS pay_amounts,\n");
		sb.append("t2.client_id, t2.channel, t2.os, t2.mobile, t2.nick, t2.portrait, t2.gmt_create\n");
		sb.append("FROM user_report t1, users t2\n");
		sb.append("WHERE t1.user_id = t2.id\n");
		if (userId != null) {
			sb.append("AND t1.user_id = " + userId + "\n");
		}
		if (StringUtils.isNotBlank(mobile)) {
			sb.append("AND t2.mobile = '" + mobile + "'\n");
		}
		sb.append("ORDER BY pay_amounts DESC\n");

		QueryResult result = basicDao.querySqlResult(sb.toString(), pageNo, pageSize);

		@SuppressWarnings("unchecked")
		List<Record> list = (List<Record>) result.getList();
		if (list != null && list.size() > 0) {
			for (Record record : list) {
				int payAmounts = record.getInt("pay_amounts");
				record.put("pay_amounts_fmt", NumberUtil.formatDouble(payAmounts / 100d, "#,##0.00"));
				Date gmtCreate = record.getTimestamp("gmt_create");
				if (gmtCreate != null) {
					record.put("gmt_create_fmt", DateUtil.format(gmtCreate, DateUtil.SDF_FORMAT));
				}
			}
		}
		return result;
	}

	/**
	 * 返回一个dates视图，方便补全数据
	 * @author zhang_zg
	 * @param startDateString
	 * @param endDateString
	 * @param sdf
	 * @return
	 */
	private String getDatesView(String startDateString, String endDateString, String sdf) {
		List<String> dates = new ArrayList<String>();
		try {
			Date startDate = DateUtil.parse(startDateString, sdf);
			Date endDate = DateUtil.parse(endDateString, sdf);
			Date tempDate = startDate;
			while (!tempDate.after(endDate)) {
				String ds = DateUtil.format(tempDate, "yyyyMMdd");
				dates.add(ds);
				tempDate = DateUtil.addDays(tempDate, 1);
			}
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < dates.size(); i++) {
				sb.append("SELECT '").append(dates.get(i)).append("' AS date\n");
				if(i < (dates.size() - 1)) {
					sb.append("UNION ALL\n");
				}
			}
			return sb.toString();
		} catch (Exception ex) {
		}
		return null;
	}

	public static void main(String[] args) {
		NutzUtil.init(MainModule.class);
		ReportService service = Mvcs.getIoc().get(ReportService.class, "reportService");
		//System.out.println(service.getProductReport("20160811", "20160811", null, 1, 10));
		System.out.println(service.getProductCharts("2016-08-17", "2016-08-22", 1294));
	}
}
