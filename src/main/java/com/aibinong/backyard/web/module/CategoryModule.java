package com.aibinong.backyard.web.module;

import java.io.File;
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
import com.aibinong.backyard.pojo.Category;
import com.aibinong.backyard.service.CategoryService;
import com.aibinong.backyard.service.UploadService;
/**
 * Created by lvfugang on 16/7/11.
 */
@IocBean
public class CategoryModule extends BaseModule{

	private static Logger logger = LoggerFactory.getLogger(CategoryModule.class);
	@Inject
	private CategoryService categoryService;
	@Inject
	private UploadService uploadService;
	@At("/categorylist")
	@Ok("jsp:jsp.cate.catelist")
	public View getCategoryList(@Param("page")Integer page,HttpServletRequest request){
		 if(page ==null || page <= 1){
            page = 1;
         }
		 QueryResult result=	categoryService.getCategoryList(page);
		 request.setAttribute("catelist", result.getList());
		 request.setAttribute("pager", result.getPager());
		return new JspView("jsp/cate/catelist");
	}
	@At("/catedetail")
	public View getCategoryDetail(@Param("cateId") Long cateId,HttpServletRequest request){
		 Category cate = categoryService.getCategoryByCateId(cateId);
		 request.setAttribute("cate", cate);
		 return new JspView("jsp/cate/catedetail");
	} 
	
	
	@At("/cateEdit")
	 @AdaptBy(type = UploadAdaptor.class) 
	public void cateEdit(@Param("img")File img,@Param("cateId") Long cateId,@Param("title")String title,@Param("weight")Integer weight,HttpServletRequest request,HttpServletResponse response){
		logService.operateLog(getAccount(request).getId(), "/cateEdit", "修改类目操作");
		try{
		Category cate = categoryService.getCategoryByCateId(cateId);
		if(cate!=null){
			if(StringUtils.isNotEmpty(title)){
				cate.setTitle(title);
			}
			if(weight!=null){
				cate.setWeight(weight);
			}
			if(img!=null){
	            String path;
				try {
					path = uploadService.uploadFile(img);
					cate.setImg(path);
					cate.setGmt_modified(new Date());
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
			categoryService.updateCategory(cate);
		}
			response.sendRedirect("/catedetail.html?cateId="+cate.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@At("/addCate")
	@Ok("jsp:jsp.cate.cateadd")
	public View addCate(HttpServletRequest request){
		return new JspView("jsp/cate/cateadd"); 
	}
	
	
	/**
	 * 删除cate
	 * @param id
	 * @return
	 */
	@At("/cateDel")
	@Ok("json")
	public Object cateDel(@Param("cateId")Long cateId,@Param("status") Integer status,HttpServletRequest request){
		logService.operateLog(getAccount(request).getId(), "/cateDel", "删除类目操作");
		Category cate = categoryService.getCategoryByCateId(cateId);
		Map<String,String> map  = new HashMap<String, String>();
		if(cate!=null){
			cate.setDeleted(status);
			categoryService.updateCategory(cate);
			map.put("code", "0");
		}
		return map;
	} 
	
	
	@At("/insertCate")
	 @AdaptBy(type = UploadAdaptor.class) 
	public View insertCate(@Param("img")File img,@Param("title")String title,@Param("weight")Integer weight,HttpServletRequest request){
		logService.operateLog(getAccount(request).getId(), "/insertCate", "添加类目操作");
		if(StringUtils.isEmpty(title)){
			request.setAttribute("msg", "请填写类目名称");
			return new JspView("jsp/cate/addCate"); 
		}
		List<Category> spulist = categoryService.getCateByTitle(title);
		if(spulist!=null && !spulist.isEmpty()){
			request.setAttribute("msg", "类目已存在");
			return new JspView("jsp/cate/addCate"); 
		}
		Category cate = new Category();
			cate.setDeleted(Category.Deleted.NORMAL.value());
			cate.setTitle(title);
			if(weight==null){
				cate.setWeight(Constants.BACKYARD_DEFAULT_WEIGHT);
			}else{
				cate.setWeight(weight);
			}
			if(img!=null){
	            String path;
				try {
					path = uploadService.uploadFile(img);
					 cate.setImg(path);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
			cate.setGmt_create(new Date());
			cate.setGmt_modified(new Date());
			cate = categoryService.addCategory(cate);
			request.setAttribute("cate", cate);
		return new JspView("jsp/cate/catedetail"); 
	}
	
}
