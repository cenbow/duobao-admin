package com.aibinong.backyard.web.module;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ForwardView;
import org.nutz.mvc.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.pojo.AccountRole;
import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.pojo.Resource;
import com.aibinong.backyard.pojo.ResourceRole;
import com.aibinong.backyard.pojo.Role;
import com.aibinong.backyard.service.ResourceService;
import com.aibinong.backyard.service.RoleService;
import com.aibinong.backyard.util.MD5Util;

@IocBean
public class RoleModule extends BaseModule{
	private static Logger logger = LoggerFactory.getLogger(RoleModule.class);
    @Inject
	private RoleService roleService;
    @Inject
    private ResourceService resourceService;
    @At("/rolelist")
    public View getRolelist(HttpServletRequest request){
	    	List<Role> rolelist = roleService.getAllRoleList();
	    	request.setAttribute("rolelist", rolelist);
	    	return new JspView("jsp/role/rolelist");
    }
    @At("/addRole")
    public void addRoleIndex(){
    	
    }
    @At("/roleDetail")
    public View roleDetail(@Param("roleId")Long roleId,HttpServletRequest request){
		Role role = roleService.getRoleById(roleId);
		if (role != null) {
			List<Resource> resourcelist = roleService.getResourceList();
			List<ResourceRole> resourceRolelist = roleService
					.getResourceRole(role);
			Map<Long, Object> resourceMap = new HashMap<Long, Object>();
			if (resourceRolelist != null && !resourceRolelist.isEmpty()) {
				for (ResourceRole resourceRole : resourceRolelist) {
					resourceMap.put(resourceRole.getResource_id(), resourceRole);
				}
			}
			request.setAttribute("role", role);
			request.setAttribute("resourceMap", resourceMap);
			request.setAttribute("resourcelist", resourcelist);
		}
    	return  new JspView("jsp/role/roledetail");
    }
    @At("/roleEdit")
    public void roleEdit(@Param("roleId")Integer roleId,@Param("role_name")String role_name,@Param("rosourceId")String[] rosourceId,
    		HttpServletRequest request,HttpServletResponse response){
		roleService.updateRoleResource(roleId, role_name, rosourceId);
		logService.operateLog(getAccount(request).getId(), "/roleEdit","编辑角色操作");
		try {
			response.sendRedirect("/roleDetail.html?roleId=" + roleId);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    @At("/roleAddIndex")
    public View roleAddIndex(HttpServletRequest request){
		List<Resource> resourcelist = roleService.getResourceList();
		request.setAttribute("resourcelist", resourcelist);
		return new JspView("jsp/role/addRole");
    }
    @At("/roleAdd")
    @Ok("redirect:/rolelist")
    public void roleAdd(@Param("role_name")String role_name,@Param("rosourceId")String[] rosourceId,HttpServletRequest request){
		roleService.roleAdd(role_name,rosourceId);
		logService.operateLog(getAccount(request).getId(), "/roleAdd", "添加角色操作");
    }
    @At("/getManageList")
    public View getManageList(HttpServletRequest request){
    	List<ManageAccount> accountlist =accountService.getMangeList();
    	request.setAttribute("accountlist", accountlist);
    	return new JspView("jsp/account/accountlist");
    }
    @At("/getAccountDetail")
    public View getAccountDetail(@Param("id")Long id,HttpServletRequest request){
		ManageAccount account = accountService.getAccountById(id);
		if (account != null) {
			Map<Integer, AccountRole> accountMapp = accountService.getAccountMapp(account);
			request.setAttribute("accountMapp", accountMapp);
			request.setAttribute("useraccount", account);
		}
		List<Role> rolelist = roleService.getAllRoleList();
		request.setAttribute("rolelist", rolelist);
    	return new JspView("jsp/account/accountDetail");
    }
    
    @At("/accountEdit")
    public void accountEdit(@Param("accountId")Long accountId,@Param("roleId")String[] roleIds,HttpServletRequest request,HttpServletResponse response){
		ManageAccount account = accountService.getAccountById(accountId);
		if (account != null) {
			accountService.updateAccountAuthority(accountId, roleIds);
		}
		try {
			response.sendRedirect("/getAccountDetail.html?id=" + accountId);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	logService.operateLog(getAccount(request).getId(), "/accountEdit", "修改用户角色操作");
    }
    @At("/accountCreate")
    public View addAccount(HttpServletRequest request){
		List<Role>  rolelist=roleService.getAllRoleList();
    	request.setAttribute("rolelist", rolelist);
		return new JspView("jsp/account/createAccount");
		
    }
    @At("/insertAccount")
    @Ok("redirect:/getManageList")
    public void insertAccount(@Param("account_name")String account_name,@Param("account_pwd") String account_pwd,@Param("roleId")String[] roleIds,HttpServletRequest request){
		ManageAccount account_m = new ManageAccount();
		account_m.setAccount(account_name);
		account_m.setStatus(Constants.DUOBAO_ACCOUNT_NORMAL_STATUS);
		account_m.setPassword(MD5Util.getMD5String(account_pwd));
		account_m.setGmt_create(new Date());
		account_m.setGmt_modified(new Date());
		accountService.addAccount(account_m, roleIds);
    }
    
    @At("/updateAccountStatus")
    public Map updateAccountStatus(@Param("user_id")Long user_id,@Param("status")Integer status,HttpServletRequest request){
    	ManageAccount account =accountService.getAccountById(user_id);
    	Map<String,String> map = new HashMap<String, String>();
    	if(account!=null){
    		account.setStatus(status);
    		accountService.updateAccount(account);
    		map.put("code", "0");
    	}else{
    		map.put("code", "1");
    	}
    	return map;
    }
    
    @At("/updateRoleStatus")
    public Map updateRoleStatus(@Param("roleId")Long roleId,@Param("status")Integer status,HttpServletRequest request){
    	Role role =roleService.getRoleById(roleId);
    	Map<String,String> map = new HashMap<String, String>();
    	if(role!=null){
    		role.setStatus(status);
    		roleService.delRole(role);
    		map.put("code", "0");
    	}else{
    		map.put("code", "1");
    	}
    	return map;
    }
}
