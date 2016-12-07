package com.aibinong.backyard.service;

import java.util.Date;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.Letter;

public interface LetterService {
	/**
	 * 获取站内信列表
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	public QueryResult  getLetterList(String startTime,String endTime,Integer page);
	/**
	 * 站内信详情
	 * @param id
	 * @return
	 */
	public Letter getLetterDetail(Long id);
	/**
	 * 修改站内信
	 * @param letter
	 */
	public void updateLetter(Letter letter);
	/**
	 * 添加站内信
	 * @param letter
	 */
	public void addLetter(Letter letter);
}
