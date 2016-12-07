package com.aibinong.backyard.web.module;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.view.JspView;

import com.aibinong.backyard.pojo.Code;
import com.aibinong.backyard.pojo.Resource;
import com.aibinong.backyard.service.ResourceService;
@IocBean
public class ResourceModule extends BaseModule{
	@Inject
	private ResourceService resourceService;
	
	@At("/resourcelist")
	public JspView getResourceList(HttpServletRequest request){
		List<Resource> resourcelist = resourceService.getResourceList();
		List<Code> codelist= new ArrayList<Code>();
		if(resourcelist!=null && !resourcelist.isEmpty()){
			for (Resource resource : resourcelist) {
				Code code = new Code();
				if(resource.getLevel()==0){
					code.setIsParent(true);
				}else{
					code.setIsParent(false);
				}
				code.setPid(resource.getParent_id());
				
				code.setName(resource.getResource_name());
				code.setId(resource.getId());
				if(resource.getLevel()==0){
					code.setOpen(true);
				}else{
					code.setOpen(false);
				}
				codelist.add(code);
			}
			
		}
		request.setAttribute("resourcejson", Json.toJson(codelist));
		return new JspView("jsp/resurce/resourcelist");
		
	}
}
