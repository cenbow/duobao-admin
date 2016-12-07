package com.aibinong.backyard.service;

import java.util.List;

import com.aibinong.backyard.pojo.Express;

public interface ExpressService {
	/**
	 * 获取物流列表
	 * @return
	 */
	public  List<Express> getExpressList();
	/**
	 * 根据expressId 获取相应 express
	 * @param expressId
	 * @return
	 */
	public Express  getExpressById(Long expressId);
	/**
	 * 更新
	 * @param express
	 */
	public void updateExpress(Express express);
	/**
	 * 保存
	 * @param express
	 */
	public void saveExpress(Express express);
}
