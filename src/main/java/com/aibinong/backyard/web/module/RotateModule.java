package com.aibinong.backyard.web.module;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.pojo.Rotate;
import com.aibinong.backyard.pojo.RotateItem;
import com.aibinong.backyard.pojo.RotateRecord;
import com.aibinong.backyard.service.RotateService;
import com.aibinong.backyard.service.UploadService;
import com.aibinong.backyard.util.DateUtil;
import com.alibaba.fastjson.JSON;

/**
 * 幸运大转盘
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年9月21日 下午12:17:18
 */
@IocBean
public class RotateModule extends BaseModule {

	@Inject
	private RotateService rotateService;

	@Inject
	private UploadService uploadService;

	/**
	 * 活动列表
	 * @author zhang_zg
	 * @param rotateId
	 * @param rotateName
	 * @param status
	 * @param page
	 * @param request
	 */
	@At("/rotateList")
	@Ok("jsp:/WEB-INF/jsp/rotate/rotate_list.jsp")
	public void rotateList(@Param("rotate_id") Long rotateId, @Param("rotate_name") String rotateName, @Param("status") Integer status,
			@Param("page") int page, HttpServletRequest request) {
		if (page <= 0) {
			page = 1;
		}

		QueryResult queryResult = rotateService.getRotateList(rotateId, rotateName, status, page, Constants.DEFAULT_PAGE_SIZE);

		request.setAttribute("rotate_id", rotateId);
		request.setAttribute("rotate_name", rotateName);
		request.setAttribute("status", status);

		request.setAttribute("list", queryResult.getList());
		request.setAttribute("pager", queryResult.getPager());
	}

	/**
	 * 活动奖品选项
	 * @author zhang_zg
	 * @param rotateId
	 * @param request
	 */
	@At("/rotateItems")
	@Ok("jsp:/WEB-INF/jsp/rotate/rotate_items.jsp")
	public void rotateItems(@Param("rotate_id") Long rotateId, HttpServletRequest request) {
		List<RotateItem> list = rotateService.getRotateItems(rotateId);

		request.setAttribute("rotate_id", rotateId);
		request.setAttribute("list", list);
	}

	/**
	 * 活动中奖纪录
	 * @author zhang_zg
	 * @param rotateId
	 * @param type
	 * @param userId
	 * @param page
	 * @param request
	 * @return
	 */
	@At("/rotateRecords")
	public View rotateRecords(@Param("rotate_id") Long rotateId, @Param("type") Integer type, @Param("mobile") String mobile, @Param("page") int page,
			HttpServletRequest request) {
		if (page <= 0) {
			page = 1;
		}

		if (rotateId == null) {
			request.setAttribute("errmsg", "活动不存在" + rotateId);
			return new JspView("jsp/error_msg");
		}

		QueryResult queryResult = rotateService.getRotateRecords(rotateId, type, mobile, page, Constants.DEFAULT_PAGE_SIZE);

		request.setAttribute("rotate_id", rotateId);
		request.setAttribute("type", type);
		request.setAttribute("mobile", mobile);

		request.setAttribute("list", queryResult.getList());
		request.setAttribute("pager", queryResult.getPager());
		return new JspView("jsp/rotate/rotate_records");
	}

	/**
	 * 编辑活动
	 * @author zhang_zg
	 */
	@At("/rotateEdit")
	public View rotateEdit(@Param("rotate_id") Long rotateId, HttpServletRequest request) {
		if (rotateId != null) {
			Rotate rotate = rotateService.getRotateById(rotateId);
			if (rotate == null) {
				request.setAttribute("errmsg", "活动不存在" + rotateId);
				return new JspView("jsp/error_msg");
			}
			request.setAttribute("rotate", rotate);
		}
		return new JspView("jsp/rotate/rotate_edit");
	}

	/**
	 * 保存活动编辑
	 * @author zhang_zg
	 * @param rotateId
	 * @param rotateName
	 * @param startTime
	 * @param endTime
	 * @param participate_count
	 * @param status
	 * @param request
	 * @return
	 */
	@At("/saveRotateEdit")
	public View saveRotateEdit(@Param("rotate_id") Long rotateId, @Param("rotate_name") String rotateName, @Param("start_time") String startTime,
			@Param("end_time") String endTime, @Param("participate_count") Integer participateCount, @Param("status") Integer status, HttpServletRequest request) {

		if (StringUtils.isBlank(rotateName)) {
			request.setAttribute("errmsg", "活动名称不能为空");
			return new JspView("jsp/error_msg");
		}

		if (StringUtils.isBlank(startTime)) {
			request.setAttribute("errmsg", "活动开始时间不能为空");
			return new JspView("jsp/error_msg");
		}

		if (StringUtils.isBlank(endTime)) {
			request.setAttribute("errmsg", "活动结束时间不能为空");
			return new JspView("jsp/error_msg");
		}

		Date start = null;
		Date end = null;
		try {
			start = DateUtil.parse(startTime, DateUtil.SDF_FORMAT);
			end = DateUtil.parse(endTime, DateUtil.SDF_FORMAT);
		} catch (Exception ex) {
			request.setAttribute("errmsg", "活动结束时间格式错误");
			return new JspView("jsp/error_msg");
		}

		if (start.compareTo(end) > 0) {
			request.setAttribute("errmsg", "活动结束时间不能小于活动开始时间");
			return new JspView("jsp/error_msg");
		}

		if (participateCount == null || participateCount <= 0) {
			request.setAttribute("errmsg", "每天参与次数必须大于0");
			return new JspView("jsp/error_msg");
		}

		if (status == null) {
			request.setAttribute("errmsg", "状态不能为空");
			return new JspView("jsp/error_msg");
		}

		// 上线活动，先判断当前是否有上线的
		if (status == 1) {
			// 新增
			if (rotateId == null) {
				request.setAttribute("errmsg", "请先添加活动选项再选择上线");
				return new JspView("jsp/error_msg");
			}
			// 修改
			else {
				String rotateIds = rotateService.getOnlineRotateIds();
				if (StringUtils.isNotBlank(rotateIds)) {
					// 如果不是同一个
					if (!Lang.contains(rotateIds.split(","), String.valueOf(rotateId))) {
						request.setAttribute("errmsg", "当前存在已上线的活动" + rotateIds + "，请先下线再上线");
						return new JspView("jsp/error_msg");
					}
				}

				// 检查上线的选项是否足量
				String rotateItemIds = rotateService.getOnlineRotateItemIds(rotateId);
				if (StringUtils.isBlank(rotateItemIds) || rotateItemIds.split(",").length < 3) {
					request.setAttribute("errmsg", "活动的选项个数不能少于3个吧");
					return new JspView("jsp/error_msg");
				}
			}
		}

		// 新增
		if (rotateId == null) {
			Rotate rotate = new Rotate();
			rotate.setName(rotateName);
			rotate.setStartTime(start);
			rotate.setEndTime(end);
			rotate.setParticipateCount(participateCount);
			rotate.setStatus(status);
			rotate.setGmtCreate(new Date());
			rotate.setGmtModified(new Date());
			rotateService.addRotate(rotate);
		}
		// 修改
		else {
			Rotate rotate = new Rotate();
			rotate.setId(rotateId);
			rotate.setName(rotateName);
			rotate.setStartTime(start);
			rotate.setEndTime(end);
			rotate.setParticipateCount(participateCount);
			rotate.setStatus(status);
			rotate.setGmtModified(new Date());
			rotateService.updateRotate(rotate);
		}

		return new ServerRedirectView("rotateList.html");
	}

	/**
	 * 编辑活动选项
	 * @author zhang_zg
	 */
	@At("/rotateItemEdit")
	public View rotateItemEdit(@Param("rotate_id") Long rotateId, @Param("item_id") Long itemId, HttpServletRequest request) {
		if (rotateId == null) {
			request.setAttribute("errmsg", "活动不存在" + rotateId);
			return new JspView("jsp/error_msg");
		}

		if (itemId != null) {
			RotateItem rotateItem = rotateService.getRotateItemById(itemId);
			if (rotateItem == null) {
				request.setAttribute("errmsg", "活动选项不存在" + itemId);
				return new JspView("jsp/error_msg");
			}
			request.setAttribute("rotate_item", rotateItem);
		}
		request.setAttribute("rotate_id", rotateId);
		return new JspView("jsp/rotate/rotate_item_edit");
	}

	/**
	 * 保存活动选项编辑
	 * @author zhang_zg
	 * @param id
	 * @param rotateId
	 * @param name
	 * @param type
	 * @param image
	 * @param image_path
	 * @param amount 数量 夺宝币单位:个; 红包单位:元
	 * @param count 当type时红包的时候，红包拆分个数
	 * @param expire_days 当type时红包的时候,过期天数
	 * @param inventory
	 * @param weight
	 * @param status
	 * @param flag
	 * @param request
	 * @return
	 */
	@At("/saveRotateItemEdit")
	@AdaptBy(type = UploadAdaptor.class)
	public View saveRotateItemEdit(@Param("id") Long id, @Param("rotate_id") Long rotateId, @Param("name") String name, @Param("type") Integer type,
			@Param("image") File image, @Param("image_path") String imagePath, @Param("amount") Integer amount, @Param("part_count") Integer partCount,
			@Param("expire_days") Integer expireDays, @Param("inventory") Integer inventory, @Param("weight") Integer weight, @Param("status") Integer status,
			@Param("flag") Integer flag, HttpServletRequest request) {
		if (rotateId == null) {
			request.setAttribute("errmsg", "活动ID不能为空");
			return new JspView("jsp/error_msg");
		}

		if (StringUtils.isBlank(name)) {
			request.setAttribute("errmsg", "奖品名称不能为空");
			return new JspView("jsp/error_msg");
		}

		if (type == null) {
			request.setAttribute("errmsg", "奖品类型不能为空");
			return new JspView("jsp/error_msg");
		}
		
		if (inventory == null || inventory < 0) {
			request.setAttribute("errmsg", "奖品库存必须大于0");
			return new JspView("jsp/error_msg");
		}

		if (weight == null || weight < 0) {
			request.setAttribute("errmsg", "奖品权重必须大于0");
			return new JspView("jsp/error_msg");
		}

		if (status == null) {
			request.setAttribute("errmsg", "状态不能为空");
			return new JspView("jsp/error_msg");
		}

		if (flag == null) {
			request.setAttribute("errmsg", "保底必中不能为空");
			return new JspView("jsp/error_msg");
		}

		// 奖品类型为夺宝币和红包的时候需要指定数量
		if (Lang.contains(new Integer[] { 1, 2 }, type)) {
			if (amount == null || amount <= 0) {
				request.setAttribute("errmsg", "奖品数量不能为空");
				return new JspView("jsp/error_msg");
			}
		}

		String data = null;
		// 奖品类型为红包
		if (type == 1) {
			// 指定拆分个数
			if (partCount == null || partCount <= 0) {
				request.setAttribute("errmsg", "红包拆分个数必须大于0");
				return new JspView("jsp/error_msg");
			}

			if (amount % partCount != 0) {
				request.setAttribute("errmsg", "红包总额不能整除拆分个数");
				return new JspView("jsp/error_msg");
			}

			// 指定过期时间
			if (expireDays == null || expireDays <= 0) {
				request.setAttribute("errmsg", "红包过期天数必须大于0");
				return new JspView("jsp/error_msg");
			}

			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("amount", amount * 100);
			dataMap.put("partCount", partCount);
			dataMap.put("expireDays", expireDays);
			data = JSON.toJSONString(dataMap);
		}
		// 奖品类型为夺宝币
		else if (type == 2) {
			data = String.valueOf(amount);
		}

		if (image != null) {
			try {
				imagePath = uploadService.uploadFile(image);
			} catch (Exception ex) {
				request.setAttribute("errmsg", "奖品图标上传失败");
				return new JspView("jsp/error_msg");
			}
		}

		if (StringUtils.isBlank(imagePath)) {
			request.setAttribute("errmsg", "奖品图标不能为空");
			return new JspView("jsp/error_msg");
		}

		// 新增
		if (id == null) {
			RotateItem item = new RotateItem();
			item.setRotateId(rotateId);
			item.setName(name);
			item.setType(type);
			item.setImage(imagePath);
			item.setData(data);
			item.setInventory(inventory);
			item.setWeight(weight);
			item.setStatus(status);
			item.setFlag(flag);
			item.setGmtCreate(new Date());
			item.setGmtModified(new Date());
			rotateService.addRotateItem(item);

			// 将其他选项设置为不保底
			if (flag == 1) {
				rotateService.updateRotateFlag(rotateId, item.getId());
			}
		}
		// 修改
		else {
			RotateItem item = new RotateItem();
			item.setId(id);
			item.setName(name);
			item.setRotateId(rotateId);
			item.setType(type);
			item.setImage(imagePath);
			item.setData(data);
			item.setInventory(inventory);
			item.setWeight(weight);
			item.setStatus(status);
			item.setFlag(flag);
			item.setGmtModified(new Date());
			rotateService.updateRotateItem(item);

			// 将其他选项设置为不保底
			if (flag == 1) {
				rotateService.updateRotateFlag(rotateId, item.getId());
			}
		}

		return new ServerRedirectView("rotateItems.html?rotate_id=" + rotateId);
	}

	/**
	 * 编辑中奖纪录
	 * @author zhang_zg
	 */
	@At("/rotateRecordEdit")
	public View rotateRecordEdit(@Param("id") Long id, HttpServletRequest request) {
		if (id == null) {
			request.setAttribute("errmsg", "中奖ID不能为空");
			return new JspView("jsp/error_msg");
		}

		RotateRecord record = rotateService.getRotateRecordById(id);
		if (record == null) {
			request.setAttribute("errmsg", "中奖纪录不存在" + id);
			return new JspView("jsp/error_msg");
		}

		request.setAttribute("record", record);
		return new JspView("jsp/rotate/rotate_record_edit");
	}
	
	/**
	 * 保存编辑中奖纪录
	 * @author zhang_zg
	 */
	@At("/saveRotateRecordEdit")
	public View saveRotateRecordEdit(@Param("id") Long id, @Param("rotate_id") Long rotateId, @Param("status") Integer status, @Param("remark") String remark,
			HttpServletRequest request) {
		if (id == null) {
			request.setAttribute("errmsg", "中奖ID不能为空");
			return new JspView("jsp/error_msg");
		}

		if (status == null) {
			request.setAttribute("errmsg", "兑换状态不能为空");
			return new JspView("jsp/error_msg");
		}

		if (StringUtils.isBlank(remark)) {
			request.setAttribute("errmsg", "备注不能为空");
			return new JspView("jsp/error_msg");
		}

		RotateRecord record = new RotateRecord();
		record.setId(id);
		record.setStatus(status);
		record.setRemark(remark);

		rotateService.updateRotateRecord(record);

		request.setAttribute("record", record);
		return new ServerRedirectView("rotateRecords.html?rotate_id=" + rotateId);
	} 

}
