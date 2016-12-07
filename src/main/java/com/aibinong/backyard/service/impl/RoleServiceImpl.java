package com.aibinong.backyard.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.AccountRole;
import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.pojo.Resource;
import com.aibinong.backyard.pojo.ResourceRole;
import com.aibinong.backyard.pojo.Role;
import com.aibinong.backyard.service.RoleService;
@IocBean(name = "roleService")
public class RoleServiceImpl implements RoleService{
	@Inject
    protected BasicDao basicDao;
	@Override
	public List<Role> getRoleListByAccount(ManageAccount account) {
		// TODO Auto-generated method stub
		Condition condition =Cnd.where("user_id","=",account.getId());
		List<Role> rolelist = basicDao.search(Role.class, condition);
		return rolelist;
	}
	@Override
	public List<Role> getRoleListByRoleIds(List<Integer> roleIds) {
		// TODO Auto-generated method stub
		StringBuffer str= new StringBuffer();
		if(roleIds!=null && !roleIds.isEmpty()){
			for (Integer long1 : roleIds) {
				str.append(long1).append(",");
			}
		}
		if(str!=null && str.toString().length()>0){
			List<Role> rolelist = basicDao.searchByIds(Role.class, str.toString().substring(0, str.toString().length()-1), "id");
			return rolelist;
		}else{
			return null;
		}
		
	}
	@Override
	public List<ResourceRole> getResourceRole(Role role) {
		// TODO Auto-generated method stub
		Condition condition =Cnd.where("role_id","=",role.getId());
		List<ResourceRole> rolelist=basicDao.search(ResourceRole.class, condition);
		return rolelist;
	}
	@Override
	public List<String> getPermissionIdList(Role role) {
		// TODO Auto-generated method stub
			role=basicDao.findLink(role, "resources");
			List<String> permissionNameList = new ArrayList<String>();

			for (Resource permission : role.getResources()) {
				permissionNameList.add(permission.getResource_name());
			}
			return permissionNameList;
	}
	@Override
	public List<Role> getRoleList(ManageAccount account) {
		
		account=basicDao.findLink(account, "roles");
		List<Role> rolelist = account.getRoles();
		return rolelist;
	}
	@Override
	public List<Role> getAllRoleList() {
		// TODO Auto-generated method stub
		List<Role> rolelist=basicDao.search(Role.class, "id");
		return rolelist;
	}
	@Override
	public Role getRoleById(Long roleId) {
		// TODO Auto-generated method stub
		return basicDao.find(roleId, Role.class);
	}
	@Override
	public List<Map<String,Object>> getResourceByRole(Long roleId) {
		// TODO Auto-generated method stub
		StringBuffer str = new StringBuffer();
		str.append("SELECT ");
		str.append("  r.resource_id,");
		str.append("  ro.id,");
		str.append("  ro.role_name,");
	    str.append("  ce.resource_name,");
	    str.append("  ce.level,");
	    str.append("  ce.parent_id,");
	    str.append("  ce.url");
	    str.append("  FROM");
	    str.append("  role ro,");
	    str.append("  resource_role r,");
	    str.append("  resource ce");
	    str.append("  WHERE");
	    str.append("  ro.id = r.role_id");
	    str.append("    AND r.resource_id = ce.id");
	    str.append("    AND ro.id = "+roleId);
	    str.append("    AND level = 1");
	    str.append("    AND ro.status = 1");
	    List<Record> recordlist=   basicDao.select(str.toString());
	    List<Map<String,Object>>  listmap = new ArrayList<Map<String,Object>>();
	    if(recordlist!=null && !recordlist.isEmpty()){
	    	for (Record record : recordlist) {
				Integer role_id = record.getInt("id");
				Integer resource_id = record.getInt("resource_id");
				Integer level = record.getInt("level");
				Integer parent_id = record.getInt("parent_id");
				String role_name = record.getString("role_name");
				String resource_name = record.getString("resource_name");
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("role_id", role_id);
				map.put("resource_id", resource_id);
				map.put("level", level);
				map.put("parent_id", parent_id);
				map.put("role_name", role_name);
				map.put("resource_name", resource_name);
				listmap.add(map);
			}
	    }
		return listmap;
	}
	@Override
	public List<Resource> getResourceList() {
		// TODO Auto-generated method stub
		Condition con =Cnd.where("level", "=", "1");
		List<Resource> resourcelist = basicDao.search(Resource.class, con);
		return resourcelist;
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void updateRoleResource(Integer roleId, String roleName,
			String[] resourceIds) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setId(roleId);
		role.setRole_name(roleName);
		role.setGmt_modified(new Date());
		basicDao.update(role);
		if(resourceIds!=null && resourceIds.length>0){
			Condition condition =Cnd.where("role_id","=",roleId);
    		basicDao.delete("resource_role", condition);
    		saveResourceRole(resourceIds,role);
		}
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void roleAdd(String roleName, String[] resourceIds) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setRole_name(roleName);
		role.setType(1);
		role.setGmt_create(new Date());
		role.setStatus(Constants.ROLE_DEFAULT_STATUS_VALUE);
		role.setGmt_modified(new Date());
		role=basicDao.save(role);
		if(role!=null && resourceIds!=null && resourceIds.length>0){
			saveResourceRole(resourceIds,role);
		}
	}
	
	private void saveResourceRole(String[] resourceIds,Role role){
		StringBuffer str = new StringBuffer();
		for (String rid : resourceIds) {
			str.append(rid).append(",");
		}
		Set<Long> resourceSet = new HashSet<Long>();
		List<ResourceRole> rolerelist = new ArrayList<ResourceRole>();
		List<Resource> resourcelist = basicDao.searchByIds(Resource.class, str.toString().substring(0, str.toString().length() - 1), "id");
		if (resourcelist != null && !resourcelist.isEmpty()) {
			for (Resource resource : resourcelist) {
				if (!resourceSet.contains(resource.getId())) {
					ResourceRole resourceRole = new ResourceRole();
					resourceRole.setResource_id(resource.getId());
					resourceRole.setRole_id(role.getId());
					rolerelist.add(resourceRole);
				}
				if (!resourceSet.contains(resource.getParent_id())) {
					ResourceRole resourceRole = new ResourceRole();
					resourceRole.setResource_id(resource.getParent_id());
					resourceRole.setRole_id(role.getId());
					rolerelist.add(resourceRole);
				}
			}
			basicDao.saveAll(rolerelist);
		}
	}
	@Override
	public void delRole(Role role) {
		// TODO Auto-generated method stub
		basicDao.update(role);
	}
}
