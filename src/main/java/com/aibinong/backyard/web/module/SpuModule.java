package com.aibinong.backyard.web.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.pojo.Spu;
import com.aibinong.backyard.service.SpuService;
/**
 * Created by lvfugang on 16/7/11.
 */
@IocBean
public class SpuModule extends BaseModule{

	private static Logger logger = LoggerFactory.getLogger(SpuModule.class);
	
	@Inject
	private SpuService spuService;
	/**
	 * spu列表
	 * @param page
	 * @param request
	 * @return
	 */
	@At("/spulist")
	@Ok("jsp:jsp.spu.spulist.jsp")
	public View getSpuList(@Param("page")Integer page,HttpServletRequest request){
		if (page == null || page <= 1) {
			page = 1;
		}
		QueryResult result = spuService.getSpuList(page);
		request.setAttribute("spulist", result.getList());
		request.setAttribute("pager", result.getPager());
		return new JspView("jsp/spu/spulist");
	}
	/**
	 * 
	 * @param spuId
	 * @param request
	 * @return
	 */
	@At("/spudetail")
	public View getSpuDetail(@Param("spuId") Long spuId,HttpServletRequest request){
		if (spuId == null) {
			return new JspView(">>:/spulist");
		}
		Spu spu = spuService.getSpu(spuId);
		request.setAttribute("spu", spu);
		return new JspView("jsp/spu/spudetail");
	}
	/**
	 * 
	 * @param spuId
	 * @param request
	 * @return
	 */
	@At("/spuEdit")
	public View getSpuEdit(@Param("spuId") Long spuId,@Param("title")String title,HttpServletRequest request){
		if (spuId == null) {
			return new JspView("/spulist");
		}

		Spu spu = spuService.getSpu(spuId);
		if (spu != null && StringUtils.isNotEmpty(title)) {
			spu.setTitle(title);
			spuService.updateSpu(spu);
			logService.operateLog(getAccount(request).getId(), "/spuEdit","修改spu操作");
		}
		request.setAttribute("spu", spu);
		return new JspView("jsp/spu/spudetail");
	}
	
	/**
	 * 删除spu
	 * @param id
	 * @return
	 */
	@At("/spuDel")
	@Ok("json")
	public Object spuDel(@Param("spuId")Long spuId,@Param("status") Integer status,HttpServletRequest request){
		
		Spu spu = spuService.getSpu(spuId);
		Map<String, String> map = new HashMap<String, String>();
		if (spu != null) {
			spu.setDeleted(status);
			spuService.updateSpu(spu);
			logService.operateLog(getAccount(request).getId(), "/spuDel",
					"上下架SPU操作");
			map.put("code", "0");
		}
		return map;
	}  
	@At("/addSpu")
	@Ok("jsp:jsp.spu.spuadd")
	public View addSpu(HttpServletRequest request){
		return new JspView("jsp/spu/spuadd"); 
	}
	@At("/insertSpu")
	public View insertSpu(@Param("title")String title,HttpServletRequest request){
		
		if (StringUtils.isEmpty(title)) {
			request.setAttribute("msg", "请填写SPU名称");
			return new JspView("jsp/spu/spuadd");
		}
		List<Spu> spulist = spuService.getSpuListByTile(title);
		if (spulist != null && !spulist.isEmpty()) {
			request.setAttribute("msg", "SPU名称已存在");
			return new JspView("jsp/spu/spuadd");
		}
		Spu spu = new Spu();
		spu.setTitle(title);
		spu.setDeleted(Spu.Deleted.NORMAL.value());
		spu = spuService.addSpu(spu);
		logService.operateLog(getAccount(request).getId(), "/insertSpu","添加SPU操作");
		request.setAttribute("spu", spu);
		return new JspView("jsp/spu/spudetail");
	}
}
