package com.aibinong.backyard.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.SysConfig;
import com.aibinong.backyard.service.ConfigService;
@IocBean(name = "configService")
public class ConfigServiceImpl implements ConfigService {
	@Inject
    protected BasicDao basicDao;

	@Override
	public QueryResult getConfiglist(String client_id, String k, Integer page) {
		// TODO Auto-generated method stub
				StringBuffer sb = new StringBuffer();
				sb.append(" select  * from sys_config where 1=1");
				if(StringUtils.isNotEmpty(client_id)){
					sb.append(" and client_id like '%"+client_id+"%' ");
				}
				if(StringUtils.isNotEmpty(k)){
					sb.append(" and k like '%"+k+"%' ");
				}
					sb.append(" order by id desc");
				QueryResult queryResult = basicDao.querySqlResult(sb.toString(), page, Constants.DEFAULT_PAGE_SIZE);
	  return queryResult;
	}

	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void updateConfig(SysConfig config) {
		// TODO Auto-generated method stub
		basicDao.update(config);
	}

	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void addConfig(SysConfig config) {
		// TODO Auto-generated method stub
		basicDao.save(config);
	}

	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void delConfig(SysConfig config) {
		// TODO Auto-generated method stub
		basicDao.delById(config.getId(), SysConfig.class);
	}

	@Override
	public SysConfig getConfigDetail(Long configId) {
		// TODO Auto-generated method stub
		SysConfig config =basicDao.find(configId, SysConfig.class);
		return config;
	}

	@Override
	public SysConfig getConfigByKey(String k) {
		// TODO Auto-generated method stub
		Condition con = Cnd.where("k", "=", k);
		return basicDao.findByCondition(SysConfig.class, con);
	}
}

