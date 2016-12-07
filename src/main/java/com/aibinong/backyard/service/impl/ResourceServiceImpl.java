package com.aibinong.backyard.service.impl;

import java.util.List;

import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.Resource;
import com.aibinong.backyard.service.ResourceService;
@IocBean(name = "resourceService")
public class ResourceServiceImpl implements ResourceService {
	@Inject
    protected BasicDao basicDao;
	@Override
	public List<Resource> getResourceList() {
		// TODO Auto-generated method stub
		List<Resource> resourcelist = basicDao.search(Resource.class, "id");
		return resourcelist;
	}

}
