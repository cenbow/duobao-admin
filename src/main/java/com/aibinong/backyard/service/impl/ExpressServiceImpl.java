package com.aibinong.backyard.service.impl;

import java.util.List;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.Express;
import com.aibinong.backyard.service.ExpressService;
@IocBean(name = "expressService")
public class ExpressServiceImpl implements ExpressService {
	@Inject
    protected BasicDao basicDao;
	@Override
	public List<Express> getExpressList() {
		// TODO Auto-generated method stub
		List<Express> expressList = basicDao.search(Express.class, "id");
		return expressList;
	}
	@Override
	public Express getExpressById(Long expressId) {
		// TODO Auto-generated method stub
		 Express express = basicDao.find(expressId, Express.class);
		return express;
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void updateExpress(Express express) {
		// TODO Auto-generated method stub
		basicDao.update(express);
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void saveExpress(Express express) {
		// TODO Auto-generated method stub
		basicDao.save(express);
	}

}
