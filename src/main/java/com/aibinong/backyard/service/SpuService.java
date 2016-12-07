package com.aibinong.backyard.service;

import java.util.List;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.Spu;

public interface SpuService {
	/**
	 * 获取spu列表
	 * @return
	 */
	public QueryResult getSpuList(Integer toPage);
	
	public List<Spu> getSpuList();
	/**
	 * 获取某个spu
	 * @param spuId
	 * @return
	 */
	public Spu getSpu(Long spuId);
	/**
	 * 更新spu
	 * @param spu
	 */
	public void updateSpu(Spu spu);
	/**
	 * 添加spu
	 * @param spu
	 */
	public Spu addSpu(Spu spu);
	/**
	 * 删除
	 * @param spuId
	 */
	public void delSpu(Long spuId);
	
	public List<Spu> getSpuListByTile(String title);
	
}
