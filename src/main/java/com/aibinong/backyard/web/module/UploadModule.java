package com.aibinong.backyard.web.module;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;

import com.aibinong.backyard.service.UploadService;

@IocBean
public class UploadModule extends BaseModule{

	@Inject
    private UploadService uploadService;
	@At("/uploadFile")
	@Ok("json")
	@AdaptBy(type = UploadAdaptor.class) 
	public Map uploadFile(@Param("imgFile")File file){
		Map<String,String> map  = new HashMap<String, String>();
		String path;
		try {
			path =uploadService.uploadFile(file);
			map.put("error", "0");
			map.put("url", path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
}
