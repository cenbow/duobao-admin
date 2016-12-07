package com.aibinong.backyard.web.module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ForwardView;
import org.nutz.mvc.view.JspView;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import com.aibinong.backyard.pojo.Express;
import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.service.ExpressService;

/**
 * Created by ouwa on 16/6/28.
 */
@IocBean
public class ExpressModule extends BaseModule{
	@Inject
    private ExpressService expressService;
    @At("/listExpress")
    public View list(HttpServletRequest request) {
        List<Express> expressList = expressService.getExpressList();

        request.setAttribute("expressList", expressList);
        return new JspView("jsp/express_list");
    }

    @At("/editExpress")
    public View edit(@Param("expressId")Long expressId, HttpServletRequest request) {
        if(expressId != null) {
            Express express = expressService.getExpressById(expressId);
            request.setAttribute("express", express);
        }
        return new JspView("jsp/express_edit");
    }

    @At("/saveExpress")
    @Ok("redirect:/listExpress.html")
    public void save(@Param("..") final Express express) {
        Trans.exec(new Atom() {
            public void run() {
                if(express.getId() != null){//update
                	expressService.updateExpress(express);
                }else{//insert
                	expressService.saveExpress(express);
                }
            }
        });
    }
}
