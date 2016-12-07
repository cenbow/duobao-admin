package com.aibinong.backyard.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.Category;
import com.aibinong.backyard.pojo.ProductCategory;

public interface CategoryService {

	/**
	 * 获取分类列表
	 * @param toPage
	 * @return
	 */
	public QueryResult getCategoryList(Integer toPage);
	
	
	public List<Category> getCategoryList();
	
	/**
	 * 根据ID获取分类对象
	 * @param cateId
	 * @return
	 */
	public Category getCategoryByCateId(Long cateId);
	
	/**
	 * 修改Category
	 * @param cate
	 */
	public void updateCategory(Category cate);
	/**
	 * 添加Category
	 * @param cate
	 * @return
	 */
	public Category addCategory(Category cate);
	
	
	public List<Category> getCateByTitle(String title);
	
	
	public Map<Long,ProductCategory> getCateList(Long productId);
	
}
