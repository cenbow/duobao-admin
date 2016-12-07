package com.aibinong.backyard.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;

import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.Rotate;
import com.aibinong.backyard.pojo.RotateItem;
import com.aibinong.backyard.pojo.RotateRecord;
import com.aibinong.backyard.service.RotateService;
import com.aibinong.backyard.util.DateUtil;
import com.aibinong.backyard.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@IocBean(name = "rotateService")
public class RotateServiceImpl implements RotateService {

	@Inject
	private BasicDao basicDao;

	@Override
	public QueryResult getRotateList(Long rotateId, String rotateName, Integer status, int pageNo, int pageSize) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t1.id, t1.name, t1.start_time, t1.end_time,\n");
		sb.append("	t1.participate_count, t1.status, t1.gmt_create,\n");
		sb.append("	IFNULL(t2.items, 0) AS items\n");
		sb.append("FROM rotate t1\n");
		sb.append("LEFT OUTER JOIN (\n");
		sb.append("	SELECT rotate_id, COUNT(1) AS items\n");
		sb.append("	FROM rotate_item\n");
		sb.append("	WHERE status = 1\n"); // 上线的选项
		sb.append("	GROUP BY rotate_id\n");
		sb.append(") t2\n");
		sb.append("ON (t1.id = t2.rotate_id)\n");
		sb.append("WHERE 1 = 1\n");
		if (rotateId != null) {
			sb.append("AND t1.id = " + rotateId + "\n");
		}
		if (StringUtils.isNotBlank(rotateName)) {
			sb.append("AND t1.name LIKE '%" + rotateName + "%'\n");
		}
		if (status != null) {
			sb.append("AND t1.status = " + status + "\n");
		}
		sb.append("ORDER BY t1.status DESC, t1.gmt_create DESC\n");

		QueryResult queryResult = basicDao.querySqlResult(sb.toString(), pageNo, pageSize);

		@SuppressWarnings("unchecked")
		List<Record> rotates = (List<Record>) queryResult.getList();
		if (rotates != null && rotates.size() > 0) {
			List<String> rotateIdList = new ArrayList<String>();
			for (Record rotate : rotates) {
				String id = rotate.getString("id");
				rotateIdList.add(id);
			}

			String rotateIds = StringUtils.join(rotateIdList, ",");

			// 抽奖记录数
			sb = new StringBuilder();
			sb.append("SELECT rotate_id, COUNT(1) AS total\n");
			sb.append("FROM rotate_record\n");
			sb.append("WHERE rotate_id IN (" + rotateIds + ")\n");
			sb.append("GROUP BY rotate_id\n");
			List<Record> rotateRecords = basicDao.select(sb.toString());

			Map<String, Integer> rotateMap = new HashMap<String, Integer>();
			if (rotateRecords != null && rotateRecords.size() > 0) {
				for (Record record : rotateRecords) {
					rotateMap.put(record.getString("rotate_id"), record.getInt("total"));
				}
			}

			for (Record rotate : rotates) {
				String id = rotate.getString("id");
				if (rotateMap.containsKey(id)) {
					rotate.put("part_total", rotateMap.get(id));
				} else {
					rotate.put("part_total", 0);
				}

				Date startTime = rotate.getTimestamp("start_time");
				Date endTime = rotate.getTimestamp("end_time");
				Date gmtCreate = rotate.getTimestamp("gmt_create");
				rotate.put("start_time_fmt", DateUtil.format(startTime, DateUtil.SDF_STANDARD));
				rotate.put("end_time_fmt", DateUtil.format(endTime, DateUtil.SDF_STANDARD));
				rotate.put("gmt_create_fmt", DateUtil.format(gmtCreate, DateUtil.SDF_STANDARD));
			}
		}

		return queryResult;
	}

	@Override
	public String getOnlineRotateIds() {
		String rotateIds = null;
		Record record = basicDao.selectOne("SELECT GROUP_CONCAT(id) AS ids FROM rotate WHERE status = 1");
		if (!Lang.isEmpty(record)) {
			rotateIds = record.getString("ids");
		}
		return rotateIds;
	}

	@Override
	public String getOnlineRotateItemIds(long rotateId) {
		String rotateItemIds = null;
		Record record = basicDao.selectOne("SELECT GROUP_CONCAT(id) AS ids FROM rotate_item WHERE rotate_id = " + rotateId + " AND status = 1");
		if (!Lang.isEmpty(record)) {
			rotateItemIds = record.getString("ids");
		}
		return rotateItemIds;
	}

	@Override
	public List<RotateItem> getRotateItems(long rotateId) {
		Cnd cnd = Cnd.NEW();
		cnd.and("rotate_id", "=", rotateId);
		cnd.desc("status");
		cnd.asc("id");

		List<RotateItem> items = basicDao.search(RotateItem.class, cnd);
		if (items != null && items.size() > 0) {
			int weights = 0;
			for (RotateItem item : items) {
				if (item.getStatus() == 1) { // 上线
					weights += item.getWeight();
				}
			}

			for (RotateItem item : items) {
				if (item.getStatus() == 1) { // 上线
					if (weights > 0) {
						double odds = NumberUtil.div(item.getWeight(), weights, 4);
						item.setOdds(odds); // 计算中奖概率
					} else {
						item.setOdds(0D);
					}
				}

				if (item.getType() == 1) {
					JSONObject jsonObject = JSON.parseObject(item.getData());
					int amount = jsonObject.getIntValue("amount"); // 金额(单位:分)
					int partCount = jsonObject.getIntValue("partCount"); // 拆分个数
					int expireDays = jsonObject.getIntValue("expireDays"); // 过期时间(天)
					item.setAmount(amount / 100);
					item.setPartCount(partCount);
					item.setExpireDays(expireDays);
					item.setDataDesc((item.getAmount() / partCount) + "元✖️" + partCount + "个");
				} else if (item.getType() == 2) {
					item.setAmount(Integer.parseInt(item.getData()));
					item.setDataDesc(item.getAmount() + "个");
				}
			}
		}

		return items;
	}

	@Override
	public QueryResult getRotateRecords(long rotateId, Integer type, String mobile, int pageNo, int pageSize) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t1.id, t1.user_id, t1.exchange_code, t1.status, t1.remark, t1.gmt_create, t2.name, t2.image,\n");
		sb.append("t3.mobile, t3.nick\n");
		sb.append("FROM rotate_record t1, rotate_item t2, users t3\n");
		sb.append("WHERE t1.item_id = t2.id\n");
		sb.append("AND t1.user_id = t3.id\n");
		sb.append("AND t1.rotate_id = " + rotateId + "\n");
		if (type != null) {
			sb.append("AND t2.type = " + type + "\n");
		}
		if (StringUtils.isNotBlank(mobile)) {
			sb.append("AND t3.mobile = '" + mobile + "'\n");
		}
		sb.append("ORDER BY t1.gmt_create DESC\n");

		QueryResult queryResult = basicDao.querySqlResult(sb.toString(), pageNo, pageSize);

		@SuppressWarnings("unchecked")
		List<Record> rotateRecords = (List<Record>) queryResult.getList();
		if (rotateRecords != null && rotateRecords.size() > 0) {
			for (Record record : rotateRecords) {
				Date gmtCreate = record.getTimestamp("gmt_create");
				record.put("gmt_create_fmt", DateUtil.format(gmtCreate, DateUtil.SDF_STANDARD));
			}
		}

		return queryResult;
	}

	@Override
	public Rotate getRotateById(long rotateId) {
		return basicDao.find(rotateId, Rotate.class);
	}

	@Override
	public void addRotate(Rotate rotate) {
		basicDao.save(rotate);
	}

	@Override
	public RotateItem getRotateItemById(long itemId) {
		RotateItem item = basicDao.find(itemId, RotateItem.class);
		if (item.getType() == 1) {
			JSONObject jsonObject = JSON.parseObject(item.getData());
			int amount = jsonObject.getIntValue("amount"); // 金额(单位:分)
			int partCount = jsonObject.getIntValue("partCount"); // 拆分个数
			int expireDays = jsonObject.getIntValue("expireDays"); // 过期时间(天)
			item.setAmount(amount / 100);
			item.setPartCount(partCount);
			item.setExpireDays(expireDays);
			item.setDataDesc(item.getAmount() + "元");
		} else if (item.getType() == 2) {
			item.setAmount(Integer.parseInt(item.getData()));
			item.setDataDesc(item.getAmount() + "个");
		}

		return item;
	}

	@Override
	public void updateRotate(Rotate rotate) {
		basicDao.update(rotate);
	}

	@Override
	public void addRotateItem(RotateItem rotateItem) {
		basicDao.save(rotateItem);
	}

	@Override
	public void updateRotateItem(RotateItem rotateItem) {
		basicDao.update(rotateItem);
	}

	@Override
	public void updateRotateFlag(long rotateId, long excludeItemId) {
		basicDao.executeSql("UPDATE rotate_item SET flag = 0 WHERE rotate_id = " + rotateId + " AND id != " + excludeItemId);
	}

	@Override
	public RotateRecord getRotateRecordById(long id) {
		return basicDao.find(id, RotateRecord.class);
	}

	@Override
	public void updateRotateRecord(RotateRecord rotateRecord) {
		basicDao.update(rotateRecord);
	}
}
