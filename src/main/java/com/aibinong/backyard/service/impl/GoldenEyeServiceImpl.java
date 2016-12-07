package com.aibinong.backyard.service.impl;

import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.AlarmBalance;
import com.aibinong.backyard.pojo.AlarmConf;
import com.aibinong.backyard.service.GoldenEyeService;

@IocBean(name = "goldenEyeService")
public class GoldenEyeServiceImpl implements GoldenEyeService {

	@Inject
	protected BasicDao basicDao;

	@Override
	public List<AlarmConf> getConfigList() {
		return this.basicDao.search(AlarmConf.class, Cnd.orderBy().asc("id"));
	}

	@Override
	public AlarmConf getConfig(long id) {
		return this.basicDao.find(id, AlarmConf.class);
	}

	@Override
	public void updateConfig(AlarmConf config) {
		Chain chain = Chain.make("name", config.getName());
		chain.add("conf", config.getConf());
		chain.add("alarm_list", config.getAlarm_list());
		chain.add("onoff", config.getOnoff());
		this.basicDao.update(AlarmConf.class, chain, Cnd.where("id", "=", config.getId()));
	}

	@Override
	public QueryResult getRecordList(Integer type, int page) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, type, content, gmt_create\n");
		sql.append("FROM alarm_record\n");
		if (type != null && type.intValue() >= 0) {
			sql.append("WHERE type = ").append(type).append("\n");
		}
		sql.append("ORDER BY id DESC");

		if (page <= 0) {
			page = 1;
		}
		return this.basicDao.querySqlResult(sql.toString(), page, Constants.DEFAULT_PAGE_SIZE);
	}

	@Override
	public List<AlarmBalance> getBalanceList() {
		return this.basicDao.search(AlarmBalance.class, Cnd.orderBy().asc("id"));
	}

}
