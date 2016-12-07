package com.aibinong.backyard.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;

import com.aibinong.backyard.pojo.User;
import com.aibinong.backyard.pojo.UserAccountRecord;
import com.aibinong.backyard.pojo.UserDuobaoRecord;

public interface UserService {

	public User getUserById(Long userId);
	
	
	public List<UserAccountRecord> getAccountRecordList(Long userId);
	
	
	public QueryResult getUserList(String mobile,String user_id,Integer page);
	
	
	public  List<Record>  getUserDuobaoRecord(Long userId,Long periodId);
	
	public Map<Long, Integer>  getUserList();
	
	public  List<Record>  getUserDuobaoLuckyByPeriod(Long periodId,Long userid);
	
	public User getUserByMobile(String  mobile);
}
