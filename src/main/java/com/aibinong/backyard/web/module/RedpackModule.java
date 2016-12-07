package com.aibinong.backyard.web.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.pojo.Redpack;
import com.aibinong.backyard.pojo.TurnConf;
import com.aibinong.backyard.service.RedpackService;
import com.aibinong.backyard.util.DateUtil;

@IocBean
public class RedpackModule extends BaseModule {

	@Inject
	private RedpackService redpackService;

	@At("/redpackCreateTurn")
	@Ok("json")
	public Object createTurn(@Param("date") String dateString) {
		redpackService.createRedpackTurn(dateString);
		return "OK";
	}

	/**
	 * 红包活动列表
	 * @author zhang_zg
	 * @param id
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @param page
	 * @param request
	 */
	@At("/redpackList")
	@Ok("jsp:/WEB-INF/jsp/redpack/redpack_list.jsp")
	public void redpackList(@Param("id") Long id, @Param("name") String name, @Param("start_date") String startDate, @Param("end_date") String endDate,
			@Param("status") Integer status, @Param("page") int page, HttpServletRequest request) {
		if (page <= 0) {
			page = 1;
		}

		QueryResult queryResult = redpackService.getRedpackList(id, name, startDate, endDate, status, page, Constants.DEFAULT_PAGE_SIZE);

		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("start_date", startDate);
		request.setAttribute("end_date", endDate);
		request.setAttribute("status", status);

		request.setAttribute("list", queryResult.getList());
		request.setAttribute("pager", queryResult.getPager());
	}

	/**
	 * 红包轮次列表
	 * @author zhang_zg
	 * @param redpackId
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param request
	 */
	@At("/redpackTurnList")
	@Ok("jsp:/WEB-INF/jsp/redpack/redpack_turn_list.jsp")
	public void redpackTurnList(@Param("redpack_id") Long redpackId, @Param("start_date") String startDate, @Param("end_date") String endDate,
			@Param("page") int page, HttpServletRequest request) {
		if (page <= 0) {
			page = 1;
		}

		QueryResult queryResult = redpackService.getRedpackTurnList(redpackId, startDate, endDate, page, Constants.DEFAULT_PAGE_SIZE);

		request.setAttribute("redpack_id", redpackId);
		request.setAttribute("start_date", startDate);
		request.setAttribute("end_date", endDate);

		request.setAttribute("list", queryResult.getList());
		request.setAttribute("pager", queryResult.getPager());
	}

	/**
	 * 编辑红包活动
	 * @author zhang_zg
	 */
	@At("/redpackEdit")
	public View redpackEdit(@Param("redpack_id") Long redpackId, HttpServletRequest request) {
		if (redpackId != null) {
			Redpack redpack = redpackService.getRedpack(redpackId);
			if (redpack == null) {
				request.setAttribute("errmsg", "红包活动不存在" + redpackId);
				return new JspView("jsp/error_msg");
			}
			request.setAttribute("redpack", redpack);
		}
		return new JspView("jsp/redpack/redpack_edit");
	}

	/**
	 * 保存红包活动
	 * @author zhang_zg
	 */
	@At("/saveredpackEdit")
	public View saveredpackEdit(@Param("id") Long id, @Param("name") String name, @Param("start_date") String startDate, @Param("end_date") String endDate,
			@Param("range_min") Integer rangeMin, @Param("range_max") Integer rangeMax, @Param("range_avg") Integer rangeAvg,
			@Param("end_minute") Integer endMinute, @Param("participate_count") Integer participateCount, @Param("clock") String[] clock,
			@Param("count") Integer[] count, @Param("status") Integer status, HttpServletRequest request) {
		if (StringUtils.isBlank(name)) {
			request.setAttribute("errmsg", "红包活动名称不能为空");
			return new JspView("jsp/error_msg");
		}

		if (StringUtils.isBlank(startDate)) {
			request.setAttribute("errmsg", "活动开始日期不能为空");
			return new JspView("jsp/error_msg");
		}

		if (StringUtils.isBlank(endDate)) {
			request.setAttribute("errmsg", "活动结束日期不能为空");
			return new JspView("jsp/error_msg");
		}

		Date start = null;
		Date end = null;
		try {
			start = DateUtil.parse(startDate, DateUtil.SDF_SHORT);
			end = DateUtil.parse(endDate, DateUtil.SDF_SHORT);
		} catch (Exception ex) {
			request.setAttribute("errmsg", "活动日期格式错误");
			return new JspView("jsp/error_msg");
		}

		if (start.compareTo(end) > 0) {
			request.setAttribute("errmsg", "活动结束日期不能小于活动开始日期");
			return new JspView("jsp/error_msg");
		}

		if (rangeMin == null || rangeMin <= 0) {
			request.setAttribute("errmsg", "随机金额最小值必须大于0");
			return new JspView("jsp/error_msg");
		}

		if (rangeMax == null || rangeMax < rangeMin) {
			request.setAttribute("errmsg", "随机金额最大值不能小于随机金额最小值");
			return new JspView("jsp/error_msg");
		}

		if (rangeAvg == null || rangeAvg < rangeMin || rangeAvg > rangeMax) {
			request.setAttribute("errmsg", "随机金额平均值必须在最大值和最小值之间");
			return new JspView("jsp/error_msg");
		}

		if (endMinute == null || endMinute <= 0) {
			request.setAttribute("errmsg", "随机结束分钟数必须大于0");
			return new JspView("jsp/error_msg");
		}

		if (participateCount == null || participateCount <= 0) {
			request.setAttribute("errmsg", "每天参与次数必须大于0");
			return new JspView("jsp/error_msg");
		}

		if (clock == null || clock.length < 1) {
			request.setAttribute("errmsg", "请填写轮次时间。格式 '0800'");
			return new JspView("jsp/error_msg");
		}

		if (count == null || count.length < 1) {
			request.setAttribute("errmsg", "请填写轮次红包个数。");
			return new JspView("jsp/error_msg");
		}

		for (int i : count) {
			if (i < 1) {
				request.setAttribute("errmsg", "红包个数必须大于0，请检查后提交");
				return new JspView("jsp/error_msg");
			}
		}

		if (status == null) {
			request.setAttribute("errmsg", "状态不能为空");
			return new JspView("jsp/error_msg");
		}

		List<TurnConf> turns = new ArrayList<TurnConf>();
		for (int i = 0; i < clock.length; i++) {
			if (StringUtils.isBlank(clock[i]) || clock[i].length() != 4 || !StringUtils.isNumeric(clock[i])) {
				request.setAttribute("errmsg", "红包轮次时间格式错误。格式 '0800'");
				return new JspView("jsp/error_msg");
			}

			TurnConf turn = new TurnConf();
			turn.setClock(clock[i]);
			turn.setCount(count[i]);
			turns.add(turn);
		}

		Collections.sort(turns);

		String onlineIds = redpackService.getOnlineRedpackIds();
		Redpack redpack = null;
		if (id == null) {
			if (status == 1 && StringUtils.isNotBlank(onlineIds)) {
				request.setAttribute("errmsg", "有正在上线的红包活动，请先下线。");
				return new JspView("jsp/error_msg");
			}

			redpack = new Redpack();
			redpack.setName(name);
			redpack.setStartDate(startDate);
			redpack.setEndDate(endDate);
			redpack.setEndMinute(endMinute);
			redpack.setParticipateCount(participateCount);
			redpack.setRangeMax(rangeMax * 100);
			redpack.setRangeMin(rangeMin * 100);
			redpack.setRangeAvg(rangeAvg * 100);
			redpack.setTurnConf(Json.toJson(turns));
			redpack.setStatus(status);
			redpack.setGmtCreate(new Date());
			redpack.setGmtModified(new Date());
			redpackService.createRedpack(redpack);
		} else {
			if (status == 1 && StringUtils.isNotBlank(onlineIds)) {
				if (!Lang.contains(onlineIds.split(","), String.valueOf(id))) {
					request.setAttribute("errmsg", "有正在上线的红包活动，请先下线。");
					return new JspView("jsp/error_msg");
				}
			}

			redpack = new Redpack();
			redpack.setId(id);
			redpack.setName(name);
			redpack.setStartDate(startDate);
			redpack.setEndDate(endDate);
			redpack.setEndMinute(endMinute);
			redpack.setParticipateCount(participateCount);
			redpack.setRangeMax(rangeMax * 100);
			redpack.setRangeMin(rangeMin * 100);
			redpack.setRangeAvg(rangeAvg * 100);
			redpack.setTurnConf(Json.toJson(turns));
			redpack.setStatus(status);
			redpack.setGmtModified(new Date());
			redpackService.updateRedpack(redpack);
		}

		// 上线创建新轮次
		if (status == 1) {
			String dateString = DateUtil.format(new Date(), "yyyyMMdd");
			redpackService.deleteRedpackTurn(redpack.getId());
			redpackService.createRedpackTurn(dateString);
		}

		return new ServerRedirectView("redpackList.html");
	}
}
