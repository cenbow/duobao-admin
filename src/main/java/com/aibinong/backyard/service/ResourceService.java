package com.aibinong.backyard.service;

import java.util.List;

import com.aibinong.backyard.pojo.Resource;

public interface ResourceService {

	/**
	 * 获取所有资源
	 * @return
	 */
	public List<Resource> getResourceList();
	
}
