package com.aibinong.backyard.web.module;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.aibinong.backyard.pojo.CustomerOperateLog;
import com.aibinong.backyard.pojo.LuckyShow;
import com.aibinong.backyard.pojo.OrderExpress;
import com.aibinong.backyard.pojo.Period;
import com.aibinong.backyard.pojo.Product;
import com.aibinong.backyard.pojo.Spu;
import com.aibinong.backyard.service.LuckyShowService;
import com.aibinong.backyard.service.PeriodService;
import com.aibinong.backyard.service.ProductService;
import com.aibinong.backyard.service.SpuService;
import com.aibinong.backyard.service.UploadService;

@IocBean
public class LuckyShowModule extends BaseModule{
	private static Logger logger = LoggerFactory.getLogger(LuckyShowModule.class);
	@Inject
	private LuckyShowService luckyShowService;
	@Inject
    private ProductService productService;
	@Inject
    private UploadService uploadService;
	@Inject
    private PeriodService periodService;
	@Inject
	private SpuService spuService;
	@At("/luckyShowlist")
	@Ok("jsp:jsp.flaunt.flauntlist")
	public View getLuckShow(@Param("periodId")Long periodId,@Param("productName")String productName,@Param("spuId")Long spuId,
			@Param("page") Integer page,HttpServletRequest request){
		if (page == null || page <= 1) {
			page = 1;
		}
		if (periodId != null) {
			request.setAttribute("periodId", periodId);
		}
		if (spuId != null) {
			request.setAttribute("spuId", spuId);
		}
		if (StringUtils.isNotEmpty(productName)) {
			request.setAttribute("productName", productName);
		}
		QueryResult result = luckyShowService.getLuckShowList(page, periodId,productName, spuId);
		if (result != null) {
			request.setAttribute("luckyShowlist", result.getList());
			request.setAttribute("pager", result.getPager());
		}
		List<Product> prodcutlist = productService.getAllProduct();
		Map<Long, Product> proMap = new HashMap<Long, Product>();
		for (Product product : prodcutlist) {
			proMap.put(product.getId(), product);
		}
		request.setAttribute("proMap", proMap);
		List<Spu> spulist = spuService.getSpuList();
		request.setAttribute("spulist", spulist);
	    return new JspView("jsp/flaunt/flauntlist");   
	}
	
	@At("/notLuckShowPeriodlist")
	public View getNotLuckShowPeriod(@Param("periodId")Long periodId,@Param("productName")String productName,
			@Param("page") Integer page,HttpServletRequest request){
		if(page ==null || page <= 1){
            page = 1;
        }
		if(periodId!=null){
			request.setAttribute("periodId", periodId);
		}
		if(StringUtils.isNotEmpty(productName)){
			request.setAttribute("productName", productName);
		}
		QueryResult result = periodService.notLuckShowList(page, periodId, productName,Constants.DUOBAO_AI_STATUS);
		List<Product> prodcutlist = productService.getAllProduct();
        Map<Long,Product> proMap = new HashMap<Long,Product>();
        for (Product product : prodcutlist) {
        	proMap.put(product.getId(), product);
		}
        request.setAttribute("proMap", proMap);
        if (result != null){
             request.setAttribute("periodlist", result.getList());
             request.setAttribute("pager", result.getPager());
        }
		return new JspView("jsp/flaunt/notShareList");
	}
	@At("/getPersionNotLuckShowlist")
	public View getPersionNotLuckShowPeriod(@Param("periodId")Long periodId,@Param("productName")String productName,
			@Param("page") Integer page,HttpServletRequest request){
		if(page ==null || page <= 1){
            page = 1;
        }
		if(periodId!=null){
			request.setAttribute("periodId", periodId);
		}
		if(StringUtils.isNotEmpty(productName)){
			request.setAttribute("productName", productName);
		}
		QueryResult result = periodService.notLuckShowList(page, periodId, productName,Constants.DUOBAO_NOTAI_STATUS);
		List<Product> prodcutlist = productService.getAllProduct();
        Map<Long,Product> proMap = new HashMap<Long,Product>();
        for (Product product : prodcutlist) {
        	proMap.put(product.getId(), product);
		}
        Map<Long,CustomerOperateLog> operatelogMap = logService.getCustomerLogByType(Constants.DUOBAO_CUSTOMER_LOGS_STATUS_SHARE_CALLBACK);
        request.setAttribute("operatelogMap", operatelogMap);
        request.setAttribute("proMap", proMap);
        request.setAttribute("periodlist", result.getList());
        request.setAttribute("pager", result.getPager());
		return new JspView("jsp/flaunt/personNotShareList");
	}
	
	
	@At("/createLuckShow")
	public View shareLuckShow(@Param("periodId")Long periodId,HttpServletRequest request){
		Period period = periodService.getPeriodById(periodId);
		request.setAttribute("period", period);
		return new JspView("jsp/flaunt/createFlaunt");
	}
	
	@At("/shareLuckShow")
	@AdaptBy(type = UploadAdaptor.class) 
	@Ok("redirect:/luckyShowlist")
	public Object shareLuckShow(@Param("img1")File img1,@Param("img2")File img2,
    		@Param("img3")File img3,@Param("content")String content,@Param("weight")Integer weight,@Param("title")String title,
    		@Param("periodId")Long periodId,@Param("audit_status") Integer audit_status,@Param("good") Integer good,
    		@Param("recommend") Integer recommend,@Param("id") String id,@Param("shareTime")Date shareTime,HttpServletRequest request){
		 logService.operateLog(getAccount(request).getId(), "/shareLuckShow", "晒单操作");
		if(periodId!=null){
			Period period = periodService.getPeriodById(periodId);
			if(period==null){
				request.setAttribute("msg", "该期次不存在！");
				return new JspView("jsp/flaunt/createFlaunt");
			}
			LuckyShow luckshow = luckyShowService.getLuckShowByPeriod(periodId);
			if(luckshow!=null && id==null){
				request.setAttribute("msg", "该期次已经晒过单！");
				return new JspView("jsp/flaunt/createFlaunt");
			}
			StringBuffer imglist= new StringBuffer();
			try{
		        if(img1!=null){
		            String path =uploadService.uploadFile(img1);
		            imglist.append(path).append(",");
		        }
		        if(img2!=null){
		            String path =uploadService.uploadFile(img2);
		            imglist.append(path).append(",");
		        }
		        if(img3!=null){
		            String path =uploadService.uploadFile(img3);
		            imglist.append(path).append(",");
		        }
		        LuckyShow luckyShow  = new LuckyShow();
		        if(periodId!=null){
		        	luckyShow.setPeriod_id(periodId);
		        }
		        if(StringUtils.isNotEmpty(title)){
		        	 luckyShow.setTitle(title);
		        }
		        if(audit_status!=null){
		    	   luckyShow.setAudit_status(audit_status);
		        }
		        if(good!=null){
		        	luckyShow.setGood(good);
		        }
		        if(recommend!=null){
		        	 luckyShow.setRecommend(recommend);
		        }
		        if(StringUtils.isNotEmpty(content)){
		        	luckyShow.setContent(content);
		        }
		        if(imglist!=null){
		        	 luckyShow.setImgs(imglist.toString());
		        }
		        if(weight!=null){
		        	 luckyShow.setWeight(weight);
		        }
		        luckyShow.setSpu_id(period.getSpu_id());
		        luckyShow.setGmt_modified(new Date());
		        if(StringUtils.isEmpty(id)){
		        		luckyShow.setGmt_create(shareTime);
				        luckyShow.setProduct_id(period.getProduct_id());
				        luckyShow.setUser_id(period.getLucky_user_id());
				        luckyShow=  luckyShowService.saveLuckyShow(luckyShow);
		        }else{
		        	luckyShowService.updateLuckyShow(luckyShow);
		        }
			}catch(Exception e){
				logger.error("创建晒单异常："+e);
				request.setAttribute("msg", "创建失败！");
				return new ForwardView("/createLuckShow");
			}
		}
		return null;
	}
	
	@At("/luckyDetail")
	public View luckyShowDetail(@Param("luckShowId")Long luckShowId,HttpServletRequest request){
		if(luckShowId==null){
			return new ForwardView("/luckyShowlist");
		}
		LuckyShow luckyShow = 	luckyShowService.getLuckyShowDetail(luckShowId);
		if(luckyShow!=null){
			Period period = periodService.getPeriodById(luckyShow.getPeriod_id());
			request.setAttribute("period", period);
			request.setAttribute("luckshow", luckyShow);
		}
		return new JspView("jsp/flaunt/flauntdetail");
	}
	
	@At("/luckShowEdit")
	@AdaptBy(type = UploadAdaptor.class) 
	public View LuckShowEdit(@Param("img1")File img1,@Param("img2")File img2,
    		@Param("img3")File img3,@Param("content")String content,@Param("weight")Integer weight,@Param("title")String title,
    		@Param("audit_status") Integer audit_status,@Param("good") Integer good,
    		@Param("recommend") Integer recommend,@Param("id") Long id,HttpServletRequest request){
			 logService.operateLog(getAccount(request).getId(), "/luckShowEdit", "修改晒单操作");
			StringBuffer imglist= new StringBuffer();
			LuckyShow luckyShow	=luckyShowService.getLuckyShowDetail(id);
			try{
				
		        if(img1!=null){
		            String path =uploadService.uploadFile(img1);
		            imglist.append(path).append(",");
		        }
		        if(img2!=null){
		            String path =uploadService.uploadFile(img2);
		            imglist.append(path).append(",");
		        }
		        if(img3!=null){
		            String path =uploadService.uploadFile(img3);
		            imglist.append(path).append(",");
		        }
		        if(StringUtils.isNotEmpty(title)){
		        	 luckyShow.setTitle(title);
		        }
		        if(audit_status!=null){
		    	   luckyShow.setAudit_status(audit_status);
		        }
		        if(good!=null){
		        	luckyShow.setGood(good);
		        }
		        if(recommend!=null){
		        	 luckyShow.setRecommend(recommend);
		        }
		        if(StringUtils.isNotEmpty(content)){
		        	luckyShow.setContent(content);
		        }
		        if(imglist!=null && imglist.toString().length()>0){
		        	 luckyShow.setImgs(imglist.toString());
		        }
		        if(weight!=null){
		        	 luckyShow.setWeight(weight);
		        }
		        luckyShow.setGmt_modified(new Date());
		        luckyShowService.updateLuckyShow(luckyShow);
		        request.setAttribute("luckshow", luckyShow);
		        return new ForwardView("/luckyDetail.html?luckShowId="+luckyShow.getId());
			}catch(Exception e){
				logger.error("编辑晒单异常："+e);
				request.setAttribute("msg", "编辑失败！");
				return new ForwardView("/luckyDetail.html?luckShowId="+id);
			}
		
	}
	@At("/updateRecommend")
	public Map updateRecommend(@Param("id")Long id,@Param("good")Integer good,@Param("recommend")Integer recommend,@Param("status")Integer status){
		LuckyShow show = luckyShowService.getLuckyShowDetail(id);
		Map<String,String> map = new HashMap<String, String>();
		if(show!=null){
			if(good!=null){
				show.setGood(good);
			}
			if(recommend!=null){
				show.setRecommend(recommend);
			}
			if(status!=null){
				show.setAudit_status(status);
			}
			luckyShowService.updateLuckyShow(show);
			map.put("code", "0");
		}
		return map;
	}
	
	@At("/customerShowLog")
	public Map customerLog(@Param("periodId")Long periodId,@Param("mobile") String mobile,@Param("type") Integer type,@Param("remark") String remark,HttpServletRequest request){
		Map<String,String> map = new HashMap<String, String>();
		Period period =periodService.getPeriodById(periodId);
		if(period!=null){
			CustomerOperateLog log = new CustomerOperateLog();
			log.setGmt_create(new Date());
			log.setGmt_modified(new Date());
			log.setMobile(mobile);
			log.setPeriod_id(period.getId());
			log.setRemark(remark);
			log.setType(type);
			log.setUser_id(period.getLucky_user_id());
			log.setOperate_id(getAccount(request).getId());
			logService.customerOperateLog(log);
			map.put("code", "0");
			map.put("period_id", period.getId()+"");
		}else{
			map.put("code", "1");
		}
		return  map;
	}
}
