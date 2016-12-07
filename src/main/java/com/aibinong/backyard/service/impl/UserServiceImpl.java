package com.aibinong.backyard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.dao.TableName;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.User;
import com.aibinong.backyard.pojo.UserAccountRecord;
import com.aibinong.backyard.service.UserService;
@IocBean(name = "userService")
public class UserServiceImpl implements UserService {
	@Inject
    protected BasicDao basicDao;
	@Override
	public User getUserById(Long userId) {
		return basicDao.find(userId, User.class);
	}
	@Override
	public List<UserAccountRecord> getAccountRecordList(Long userId) {
		try {
		  TableName.set(basicDao.tableSuffix(userId));
          Cnd cnd = Cnd.where("type", "=", 0).and("user_id", "=", userId);

          cnd.orderBy("id", "desc");
          List<UserAccountRecord> userAccountRecordList = basicDao.search(UserAccountRecord.class, cnd);
          return userAccountRecordList;
		}finally{
			TableName.clear();
		}
		
	}
	@Override
	public QueryResult getUserList(String mobile, String user_id,Integer page) {
		StringBuffer str = new StringBuffer();
		str.append("select * from users where ai=0");
		if(StringUtils.isNotEmpty(mobile)){
			str.append(" and mobile="+mobile);
		}
		if(StringUtils.isNotEmpty(user_id)){
			str.append(" and id="+user_id);
		}
		str.append(" order by id desc");
		QueryResult queryResult = basicDao.querySqlResult(str.toString(), page, Constants.DEFAULT_PAGE_SIZE);
	    return queryResult;
	}
	@Override
	public  List<Record> getUserDuobaoRecord(Long userId,Long periodId) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT sum(buy_count) as y_count,a.id as period_id, a.list_img, a.title, a.status, a.start_time,a.type,");
			sb.append(" a.award_time, a.lucky_code, b.id, b.user_id, b.buy_code,");
			sb.append(" sum(ifnull(b.lucky,0)) as lucky, b.gmt_create as buy_time\n");
			sb.append(" FROM period a, "+ basicDao.dynamicTable("user_duobao_record", userId)+ " b\n");
			sb.append(" WHERE a.id = b.period_id\n");
			sb.append(" AND b.user_id=" + userId + "\n");
			if(periodId!=null){
				sb.append(" AND a.id=" + periodId + "\n");
			}
			sb.append(" group by a.id  ORDER by b.gmt_create desc");
			List<Record> list = basicDao.select(sb.toString());
			return list;
		} finally {
			TableName.clear();
		}
		
	}
	
	
	@Override
	public Map<Long, Integer> getUserList() {
		Condition con = Cnd.where("ai", "=", "0");
		List<User> userlist = basicDao.search(User.class, con);
		Map<Long, Integer> userMap = new HashMap<Long, Integer>();
		if (userlist != null && !userlist.isEmpty()) {
			for (User user : userlist) {
				userMap.put(user.getId(), user.getTest());
			}

		}
		return userMap;
	}
	@Override
	public List<Record> getUserDuobaoLuckyByPeriod(Long periodId,
			Long userId) {
		StringBuffer str = new StringBuffer();
		str.append("  select * from "+basicDao.dynamicTable("user_duobao_record", userId));
		str.append("  where period_id =" + periodId);
		str.append("  and user_id =" + userId);
		List<Record> list = basicDao.select(str.toString());
		
		return list;
	}
	@Override
	public User getUserByMobile(String mobile) {
		// TODO Auto-generated method stub
		Condition con = Cnd.where("mobile", "=", mobile);
		User user = basicDao.findByCondition(User.class, con);
		if(user!=null){
			StringBuffer str = new StringBuffer();
			str.append("  select * from "+basicDao.dynamicTable("user_account_record", user.getId()));
			List<UserAccountRecord> list = basicDao.searchBySql(UserAccountRecord.class,str.toString());
			if(list!=null && !list.isEmpty()){
				user.setBalance(list.get(0).getBalance());
			}
		}
		return user;
		
	}

}
