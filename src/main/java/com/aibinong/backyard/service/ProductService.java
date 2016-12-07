package com.aibinong.backyard.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.Product;
import com.aibinong.backyard.pojo.SmartAi;

/**
 * Created by lvfugang on 16/7/9.
 */
public interface ProductService {
    /**
     * 根据商品名称，投放平台查询商品列表
     * @param title
     * @param clientId
     * @return
     */
    public QueryResult getProductList(String title,String clientId,Long catId,String spu_id,Integer toPage);
    /**
     * 
     * @return
     */
    public List<Product>  getAllProduct();
    /**
     * 查询某一个商品
     * @param proId
     * @return
     */
    public Product getProductById(Long proId);

    /**
     * 修改商品
     * @param product
     */
    public void updateProduct(Product product);

    /**
     * 添加商品
     * @param product
     */
    public Product inserProduct(Product product);
    
    /**
     * 获取AI
     * @param productId
     * @return
     */
    public SmartAi getSmartAiByPro(Long productId);
    /**
     * 获取所有的AI
     * @return
     */
    public Map<Long,SmartAi> getAiMap();
    /**
     * 
     * @param id
     * @return
     */
    public SmartAi getSmartById(Long id );
    /**
     * 
     * @param ai
     */
    public void updateAi(SmartAi ai);
    
    public void inserAi(SmartAi ai);

}
