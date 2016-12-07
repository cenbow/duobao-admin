package com.aibinong.backyard.service;

import java.util.List;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.Rotate;
import com.aibinong.backyard.pojo.RotateItem;
import com.aibinong.backyard.pojo.RotateRecord;

public interface RotateService {

	/**
	 * 幸运大转盘活动列表
	 * @author zhang_zg
	 * @param rotateId
	 * @param rotateName
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public QueryResult getRotateList(Long rotateId, String rotateName, Integer status, int pageNo, int pageSize);

	/**
	 * 活动奖品选项
	 * @author zhang_zg
	 * @param rotateId
	 * @return
	 */
	public List<RotateItem> getRotateItems(long rotateId);

	/**
	 * 获取已经上线的活动ID
	 * @author zhang_zg
	 * @return
	 */
	public String getOnlineRotateIds();

	/**
	 * 获取已经上线的奖品选项
	 * @author zhang_zg
	 * @return
	 */
	public String getOnlineRotateItemIds(long rotateId);

	/**
	 * 中奖记录
	 * @author zhang_zg
	 * @param rotateId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public QueryResult getRotateRecords(long rotateId, Integer type, String mobile, int pageNo, int pageSize);

	public Rotate getRotateById(long rotateId);

	public void addRotate(Rotate rotate);

	public void updateRotate(Rotate rotate);

	public RotateItem getRotateItemById(long itemId);

	public void addRotateItem(RotateItem rotateItem);

	public void updateRotateItem(RotateItem rotateItem);

	public void updateRotateFlag(long rotateId, long excludeItemId);

	public RotateRecord getRotateRecordById(long id);

	public void updateRotateRecord(RotateRecord rotateRecord);
}
