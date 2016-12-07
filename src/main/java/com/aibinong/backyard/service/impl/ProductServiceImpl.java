package com.aibinong.backyard.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.aibinong.backyard.pojo.Product;
import com.aibinong.backyard.pojo.ProductCategory;
import com.aibinong.backyard.pojo.SmartAi;
import com.aibinong.backyard.service.ProductService;

/**
 * Created by lvfugang on 16/7/9.
 */
@IocBean(name = "productService")
public class ProductServiceImpl implements ProductService{
    @Inject
    protected BasicDao basicDao;
    @Override
    public QueryResult getProductList(String title, String clientId,Long cateId,String spu_id,Integer toPage) {
		StringBuffer sb = new StringBuffer();
			sb.append("	select p.* from product p ");
		if (cateId != null && cateId > 0) {
			sb.append("    ,product_category y \n");
		}
		if (StringUtils.isNotEmpty(clientId) && !"-1".equals(clientId)) {
			sb.append("    ,product_app ap \n");
		}
			sb.append(" 	WHERE  p.deleted = 0 \n");
		if (StringUtils.isNotEmpty(title)) {
			sb.append(" 	and title like '%" + title + "%'");
		}
		if (cateId != null && cateId > 0) {
			sb.append(" 	AND p.id = y.product_id \n");
			sb.append(" 	AND y.category_id =").append(cateId);
		}
		if (StringUtils.isNotEmpty(clientId) && !"-1".equals(clientId)) {
			sb.append(" 	AND p.id=ap.product_id");
			sb.append(" 	AND client_id=").append("'").append(clientId).append("'");
		}
		if (cateId != null && cateId > 0 && StringUtils.isNotEmpty(clientId)
				&& !"-1".equals(clientId)) {
			sb.append(" 	AND y.product_id =ap.product_id");
		}
		if (StringUtils.isNotEmpty(spu_id) && !"-1".equals(spu_id)) {
			sb.append(" 	AND p.spu_id=" + spu_id);
		}
			sb.append(" 	order by p.weight desc,p.gmt_create desc");

		QueryResult queryResult = basicDao.querySqlResult(sb.toString(),
				toPage, Constants.DEFAULT_PAGE_SIZE);
		return queryResult;
    }
    @Override
    public Product getProductById(Long proId) {
        Product product  =basicDao.find(proId, Product.class);
        return product;
    }
    @Override
    @Aop(TransAop.READ_COMMITTED)
    public void updateProduct(Product product) {
		if (product.getCate_id() != null) {
			deteProductCate(product);
			saveProductCate(product);
		}
		basicDao.update(product);
    }
    
    
    private void deteProductCate(Product product){
    	Condition con = Cnd.where("product_id", "=", product.getId());
		basicDao.delete("product_category", con);
    }
    
    private void saveProductCate(Product product){
		List<ProductCategory> procate = new ArrayList<ProductCategory>();
		for (String cate : product.getCate_id()) {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setCategory_id(Long.parseLong(cate));
			productCategory.setProduct_id(product.getId());
			procate.add(productCategory);
		}
		basicDao.saveAll(procate);
    }
    
    @Override
    @Aop(TransAop.READ_COMMITTED)
    public Product inserProduct(Product product) {
		String[] clients = null;

		product = basicDao.save(product);
		if (product.getCate_id() != null) {
			saveProductCate(product);
		}
		return product;
    }
    @Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		Condition con = Cnd.where("deleted", "=", Product.Deleted.NORMAL.value());
		 List<Product>  productlist = basicDao.search(Product.class, con);
		return productlist;
	}
	@Override
	public SmartAi getSmartAiByPro(Long productId) {
		// TODO Auto-generated method stub
		Condition cnd = Cnd.where("product_id", "=", productId);
		SmartAi ai = basicDao.findByCondition(SmartAi.class, cnd);
		return ai;
	}
	@Override
	public SmartAi getSmartById(Long id) {
		// TODO Auto-generated method stub
		basicDao.find(id, SmartAi.class);
		return basicDao.find(id, SmartAi.class);
	}
	@Override
	public void updateAi(SmartAi ai) {
		// TODO Auto-generated method stub
		basicDao.update(ai);
	}
	@Override
	public void inserAi(SmartAi ai) {
		// TODO Auto-generated method stub
		basicDao.save(ai);
	}
	@Override
	public Map<Long, SmartAi> getAiMap() {
		// TODO Auto-generated method stub
		Map<Long, SmartAi> aiMap = new HashMap<Long, SmartAi>();
		List<SmartAi> listAi =basicDao.search(SmartAi.class, "id");
		if(listAi!=null && !listAi.isEmpty()){
			for (SmartAi smartAi : listAi) {
				aiMap.put(smartAi.getProduct_id(), smartAi);
			}
		}
		return aiMap;
	}
}

