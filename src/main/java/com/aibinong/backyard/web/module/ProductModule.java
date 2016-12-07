package com.aibinong.backyard.web.module;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;
import org.nutz.mvc.view.ForwardView;
import org.nutz.mvc.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.App;
import com.aibinong.backyard.pojo.Category;
import com.aibinong.backyard.pojo.Period;
import com.aibinong.backyard.pojo.Product;
import com.aibinong.backyard.pojo.ProductCategory;
import com.aibinong.backyard.pojo.SmartAi;
import com.aibinong.backyard.pojo.Spu;
import com.aibinong.backyard.pojo.SysConfig;
import com.aibinong.backyard.service.AppService;
import com.aibinong.backyard.service.CategoryService;
import com.aibinong.backyard.service.ConfigService;
import com.aibinong.backyard.service.PeriodService;
import com.aibinong.backyard.service.ProductService;
import com.aibinong.backyard.service.SpuService;
import com.aibinong.backyard.service.UploadService;
import com.aibinong.backyard.util.CalculateTime;

/**
 * Created by lvfugang on 16/7/9.
 */
@IocBean
public class ProductModule extends BaseModule{
	private static Logger logger = LoggerFactory.getLogger(ProductModule.class);
    @Inject
    private ProductService productService;
    @Inject
    private PeriodService periodService;
    @Inject
    private UploadService uploadService;
    @Inject
	private CategoryService categoryService;
    @Inject
	private SpuService spuService;
    @Inject
	private AppService appService;
    @Inject
	private ConfigService configService;
    @Inject
    private BasicDao basicDao;
    
    /**
     * 获取商品列表
     * @param title
     * @param clientId
     * @param catId
     * @param toPage
     * @param request
     */
    @At("/productlist")
    public View getProdcutList(@Param("title")String title, @Param("client_id")String client_id,
                                 @Param("cateId")Long cateId,@Param("page")Integer page,@Param("spu_id")String spu_id,HttpServletRequest request){
        if(page ==null || page <= 1){
            page = 1;
        }
        QueryResult result = productService.getProductList(title, client_id, cateId,spu_id, page);
        request.setAttribute("productlist", result.getList());
        request.setAttribute("pager", result.getPager());
        List<App> applist=appService.getAppList();
		request.setAttribute("applist", applist);
		List<Category> cateList = categoryService.getCategoryList();
    	request.setAttribute("catelist", cateList);
		if(StringUtils.isNotEmpty(title)){
			request.setAttribute("title", title);
		}
		if(StringUtils.isNotEmpty(client_id)){
			request.setAttribute("clientId", client_id);
		}
		if(StringUtils.isNotEmpty(spu_id)){
			request.setAttribute("spu_id", spu_id);
		}
		if(cateId!=null){
			request.setAttribute("cateId", cateId);
		}
		List<Spu> spulist = spuService.getSpuList();
    	request.setAttribute("spulist", spulist);
    	return new JspView("jsp/product/productlist");
    }

    
    /**
     * 创建商品
     * @param productId
     * @param request
     */
    @At("/productIndex")
    @Ok("jsp:jsp.product.createproduct.jsp")
    public View createProduct(HttpServletRequest request){
    	List<Category> cateList = categoryService.getCategoryList();
    	request.setAttribute("catelist", cateList);
    	List<Spu> spulist = spuService.getSpuList();
    	request.setAttribute("spulist", spulist);
    	List<App> applist=appService.getAppList();
		request.setAttribute("applist", applist);
    	return new JspView("jsp/product/createproduct");
    }
    

    /**
     * 创建商品
     * @param client_id
     * @param bigImg
     * @param files
     * @param product
     */
    @At("/createProduct")
    @AdaptBy(type = UploadAdaptor.class) 
    @Ok("redirect:/productlist.html")
    public void createProduct(@Param("list_img")File list_img,@Param("loop_img1")File loop_img1,@Param("loop_img2")File loop_img2,
    		@Param("loop_img3")File loop_img3,@Param("loop_img4")File loop_img4,@Param("loop_img5")File loop_img5,@Param("cate_id")String[] cate_id,
                              @Param("..")final Product product,HttpServletRequest request
    		){
        try {
    		logService.operateLog(getAccount(request).getId(), "/createProduct", "创建商品操作");
            if(list_img!=null){
                String path =uploadService.uploadFile(list_img);
                product.setList_img(path);
            }
            StringBuffer looplist= new StringBuffer();
            if(loop_img1!=null){
                String path =uploadService.uploadFile(loop_img1);
                looplist.append(path).append(",");
            }
            if(loop_img2!=null){
                String path =uploadService.uploadFile(loop_img2);
                looplist.append(path).append(",");
            }
            if(loop_img3!=null){
                String path =uploadService.uploadFile(loop_img3);
                looplist.append(path).append(",");
            }
            if(loop_img4!=null){
                String path =uploadService.uploadFile(loop_img4);
                looplist.append(path).append(",");
            }
            if(loop_img5!=null){
                String path =uploadService.uploadFile(loop_img5);
                looplist.append(path).append(",");
            }
            if(looplist!=null && StringUtils.isNotEmpty(looplist.toString())){
            	product.setLoop_img(looplist.toString().substring(0, looplist.toString().length()-1));
            }
           
            if(product.getDuobao_type()==Product.Duobao_Type.ONEBUY.value()){
            	product.setLimit_count(Constants.DUOBAO_PRODUCT_TYPE_ONE_BUY);
            }
            if(product.getDuobao_type()==Product.Duobao_Type.TENBUY.value()){
            	product.setLimit_count(Constants.DUOBAO_PRODUCT_TYPE_TEN_BUY);
            }
            if(product.getDuobao_type()==Product.Duobao_Type.HUNDREDBUY.value()){
            	product.setLimit_count(Constants.DUOBAO_PROCUT_TYPE_BAIYUAN_BUY);
            }
            product.setDeleted(Product.Deleted.NORMAL.value());
            product.setGmt_create(new Date());
            product.setStatus(Product.Status.UNDERCARRIAGE.value());
            product.setGmt_modified(new Date());
            product.setRemark("本活动所有奖品与苹果公司无关");
            if(cate_id!=null && cate_id.length>0){
           	 product.setCate_id(cate_id);
            }
            if(product.getIs_hot()==null){
            	product.setIs_hot(0);
            }
            if(product.getIs_new()==null){
            	product.setIs_new(0);
            }
            if(product.getReal_cost()!=null){
            	product.setReal_cost(product.getReal_cost()*100);
            }
            productService.inserProduct(product);
        } catch (Exception e) {
        	logger.info("创建商品异常："+e);
        	request.setAttribute("msg", "创建商品异常");
        }
    }
    @At("/productDetail")
    public View getProductDetail(@Param("productId")Long productId,HttpServletRequest request){
    	if(productId==null){
        	return new JspView("jsp/product/productlist");
    	}
		List<Category> cateList = categoryService.getCategoryList();
		
		request.setAttribute("catelist", cateList);
		Map<Long,ProductCategory> procatelist= categoryService.getCateList(productId);
		request.setAttribute("procatelist", procatelist);
		List<Spu> spulist = spuService.getSpuList();
		request.setAttribute("spulist", spulist);
		List<App> applist = appService.getAppList();
		request.setAttribute("applist", applist);
    	Product product = productService.getProductById(productId);
    	if(product!=null){
    		Integer real_cost = product.getReal_cost()/100;
    		product.setReal_cost(real_cost);
    		request.setAttribute("product", product);
    	}else{
    		request.setAttribute("msg", "查询错误");
    	}
    	return new JspView("jsp/product/productDetail");
    }
    @At("/productEdit")
    @AdaptBy(type = UploadAdaptor.class) 
    public void productEdit(@Param("id") String id,@Param("list_img")File list_img,@Param("loop_img1")File loop_img1,@Param("loop_img2")File loop_img2,
    		@Param("loop_img3")File loop_img3,@Param("loop_img4")File loop_img4,@Param("loop_img5")File loop_img5,@Param("cate_id")String[] cate_id,
            @Param("..")final Product product,@Param("content")String content,HttpServletRequest request,HttpServletResponse response){
    	try{
	    		 if(list_img!=null){
	                 String path =uploadService.uploadFile(list_img);
	                 product.setList_img(path);
	             }
	             StringBuffer looplist= new StringBuffer();
	             if(loop_img1!=null){
	                 String path =uploadService.uploadFile(loop_img1);
	                 looplist.append(path).append(",");
	             }
	             if(loop_img2!=null){
	                 String path =uploadService.uploadFile(loop_img2);
	                 looplist.append(path).append(",");
	             }
	             if(loop_img3!=null){
	                 String path =uploadService.uploadFile(loop_img3);
	                 looplist.append(path).append(",");
	             }
	             if(loop_img4!=null){
	                 String path =uploadService.uploadFile(loop_img4);
	                 looplist.append(path).append(",");
	             }
	             if(loop_img5!=null){
	                 String path =uploadService.uploadFile(loop_img5);
	                 looplist.append(path).append(",");
	             }
	             if(looplist!=null && StringUtils.isNotEmpty(looplist.toString())){
	            	 product.setLoop_img(looplist.toString().substring(0, looplist.lastIndexOf(","))); 
	             }
	            
	             if(product.getDuobao_type()!=null){
	            	 if(product.getDuobao_type()==Product.Duobao_Type.ONEBUY.value()){
	 	             	product.setLimit_count(Constants.DUOBAO_PRODUCT_TYPE_ONE_BUY);
	 	             }
	 	             if(product.getDuobao_type()==Product.Duobao_Type.TENBUY.value()){
	 	             	product.setLimit_count(Constants.DUOBAO_PRODUCT_TYPE_TEN_BUY);
	 	             }
	 	             if(product.getDuobao_type()==Product.Duobao_Type.HUNDREDBUY.value()){
	 	             	product.setLimit_count(Constants.DUOBAO_PROCUT_TYPE_BAIYUAN_BUY);
	 	             } 
	             }
	             if(cate_id!=null && cate_id.length>0){
	            	 product.setCate_id(cate_id);
	             }
	             if(product.getNumbers()==0){
	            	 product.setStatus(0);
	             }
	             logger.info("content::"+content);
	             if(StringUtils.isNotEmpty(content)){
	            	 product.setContent(content);
	             }
	             
	             product.setGmt_modified(new Date());
	             if(product.getReal_cost()!=null){
	             	product.setReal_cost(product.getReal_cost()*100);
	             }
	             productService.updateProduct(product);
	             logService.operateLog(getAccount(request).getId(), "/productEdit", "修改商品操作");
	             response.sendRedirect("/productDetail.html?productId="+product.getId());
    	}catch(Exception e){
    		request.setAttribute("msg", "修改商品错误");
    		try {
				response.sendRedirect("/productDetail.html?productId="+product.getId());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	}
    }
    
    /**
	 * 上下架product
	 * @param id
	 * @return
	 */
	@At("/productDel")
	@Ok("json")
	public Object spuDel(@Param("productId")Long productId,@Param("status") Integer status,HttpServletRequest request){
		Product product  = productService.getProductById(productId);
		Map<String,String> map  = new HashMap<String, String>();
		if(product!=null){
			product.setStatus(status);
			if(status==Product.Status.GROUNDING.value()){
				 List<Period>  periodlist= periodService.getNewPeriodByProduct(product.getId());
				 if(periodlist==null || periodlist.isEmpty() && product.getReal_cost()>0 && product.getDefault_count()>0 
						 && product.getNumbers()>0 && StringUtils.isNotEmpty(product.getTitle())
						 && StringUtils.isNotEmpty(product.getLoop_img()) && StringUtils.isNotEmpty(product.getList_img()) && product.getSpu_id()!=null
						 && product.getWeight()!=null  && StringUtils.isNotEmpty(product.getRemark()) && product.getTotal_count()!=null && product.getTotal_count()>0){
					 periodService.createPeriod(product);
				 }	
			}
			productService.updateProduct(product);
			map.put("code", "0");
		}
		return map;
	}  
	@At("/productAilist")
	public View getAiProductList(@Param("title")String title, @Param("client_id")String client_id,
            @Param("catId")Long catId,@Param("page")Integer page,@Param("spu_id")String spu_id,HttpServletRequest request){
		 if(page ==null || page <= 1){
	            page = 1;
	        }
	        QueryResult result = productService.getProductList(title, client_id, catId,spu_id, page);
	        request.setAttribute("productlist", result.getList());
	        request.setAttribute("pager", result.getPager());
	        List<App> applist=appService.getAppList();
			request.setAttribute("applist", applist);
			List<Category> cateList = categoryService.getCategoryList();
	    	request.setAttribute("catelist", cateList);
	    	Map<Long,SmartAi> aiMap =productService.getAiMap();
	    	request.setAttribute("aiMap", aiMap);
			if(StringUtils.isNotEmpty(title)){
				request.setAttribute("title", title);
			}
			if(StringUtils.isNotEmpty(client_id)){
				request.setAttribute("clientId", client_id);
			}
			if(StringUtils.isNotEmpty(spu_id)){
				request.setAttribute("spu_id", spu_id);
			}
			List<Spu> spulist = spuService.getSpuList();
	    	request.setAttribute("spulist", spulist);
	    	return new JspView("jsp/product/productAilist");
	}
	
	@At("/productAiDetail")
	public View getAiConfig(@Param("productId")Long productId,HttpServletRequest request){
		Product  product = productService.getProductById(productId);
		if(product!=null){
			SmartAi ai =productService.getSmartAiByPro(productId);
			request.setAttribute("smartAi", ai);
			SysConfig config=configService.getConfigByKey("smartAiArgs");
			if(config!=null){
				request.setAttribute("config", config.getV());
			}
			if(ai!=null){
				CalculateTime  expect = new CalculateTime();
				expect.setBasicDao(basicDao);
				int time =expect.calculate(productId, ai.getHit_rate(), ai.getAi_args());
				request.setAttribute("time", cal(time));
			}
		}
		
		
		request.setAttribute("product", product);
		return new JspView("jsp/product/aiconfigdetail");
	}
	
	@At("/smartAiEdit")
	public void smartAiEdit(@Param("proid")Long proid,@Param("ai_args_id")Long ai_args_id,
			@Param("hit_rate")Integer hit_rate,@Param("ai_args")String ai_args,HttpServletRequest request,HttpServletResponse response){
		SmartAi ai = new SmartAi();
		if(StringUtils.isNotEmpty(ai_args)){
			ai.setAi_args(ai_args);
		}
		if(hit_rate!=null){
			ai.setHit_rate(hit_rate);
		}
		if(proid!=null){
			ai.setProduct_id(proid);
		}
		ai.setGmt_modified(new Date());
		if(ai_args_id!=null){
			ai.setId(ai_args_id);
			if(StringUtils.isEmpty(ai_args)){
				ai.setAi_args("");
			}
			productService.updateAi(ai);
			logService.operateLog(getAccount(request).getId(), "/smartAiEdit", "修改AI设置操作");
			try {
				response.sendRedirect("/productAiDetail.html?productId="+proid);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			ai.setGmt_create(new Date());
			//ai.setAi_args(ai_args);
			productService.inserAi(ai);
			logService.operateLog(getAccount(request).getId(), "/smartAiEdit", "创建AI设置操作");
			try {
				response.sendRedirect("/productAiDetail.html?productId="+proid);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * 
	 * @param productId
	 * @param hitRate
	 * @param hitArgs
	 * @param request
	 * @return
	 */
	@At("/getExpectTime")
	public Map getExpectTime(@Param("productId")Long productId,@Param("hitRate")Integer hitRate,@Param("hitArgs")String hitArgs,HttpServletRequest request){
		CalculateTime  expect = new CalculateTime();
		expect.setBasicDao(basicDao);
		Map<String,String> map = new HashMap<String, String>();
		try{
		 int time =expect.calculate(productId, hitRate, hitArgs);
		 if(time>0){
			 map.put("code", "0");
			 map.put("time", cal(time));
		 }
		}catch(Exception e){
			map.put("code", "1");
		}
		return map;
	}
	 public  String cal(int second){
		  int h = 0;
		  int d = 0;
		  int s = 0;
		  int temp = second%3600;
		       if(second>3600){
		         h= second/3600;
		              if(temp!=0){
		         if(temp>60){
		         d = temp/60;
		      if(temp%60!=0){
		         s = temp%60;
		      }
		      }else{
		         s = temp;
		      }
		     }
		    }else{
		        d = second/60;
		     if(second%60!=0){
		        s = second%60;
		     }
		    }

		   return h+"时"+d+"分"+s+"秒";
		 }
}
