package com.aibinong.backyard.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.Category;
import com.aibinong.backyard.pojo.ProductCategory;
import com.aibinong.backyard.service.CategoryService;
@IocBean(name = "categoryService")
public class CategoryServiceImpl implements CategoryService{
	@Inject
    protected BasicDao basicDao;
	@Override
	public QueryResult getCategoryList(Integer toPage) {
		// TODO Auto-generated method stub
		 StringBuffer sb = new StringBuffer();
		 sb.append(" select * from category  order by id desc");
		 QueryResult queryResult = basicDao.querySqlResult(sb.toString(), toPage, Constants.DEFAULT_PAGE_SIZE);
		return queryResult;
	}
	@Override
	public Category getCategoryByCateId(Long cateId) {
		// TODO Auto-generated method stub
		
		return basicDao.find(cateId, Category.class);
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void updateCategory(Category cate) {
		// TODO Auto-generated method stub
		basicDao.update(cate);
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public Category addCategory(Category cate) {
		// TODO Auto-generated method stub
		return basicDao.save(cate);
	}
	@Override
	public List<Category> getCateByTitle(String title) {
		// TODO Auto-generated method stub
		Condition condition = Cnd.where("title", "=", title);
		List<Category> catelist = basicDao.search(Category.class, condition);
		return catelist;
	}
	@Override
	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		Condition condition = Cnd.where("id", "!=", "-1").and("deleted", "=", 0);
		List<Category> catelist = basicDao.search(Category.class,condition);
		return catelist;
	}
	@Override
	public  Map<Long,ProductCategory> getCateList(Long productId) {
		// TODO Auto-generated method stub
		Condition condition = Cnd.where("product_id", "=", productId);
		List<ProductCategory> categoryList = basicDao.search(ProductCategory.class, condition);
		List<Long> cateIds = new ArrayList<Long>();
		Map<Long, ProductCategory> cateMap = new HashMap<Long, ProductCategory>();
		if (categoryList != null && !categoryList.isEmpty()) {
			for (ProductCategory cate : categoryList) {
				cateIds.add(cate.getCategory_id());
				cateMap.put(cate.getCategory_id(), cate);
			}
		}
		return cateMap;
	}

	
}
