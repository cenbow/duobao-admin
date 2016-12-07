package com.aibinong.backyard.service;

import java.util.List;
import java.util.Map;

import com.aibinong.backyard.pojo.AccountRole;
import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.pojo.Resource;
import com.aibinong.backyard.pojo.ResourceRole;
import com.aibinong.backyard.pojo.Role;

public interface RoleService {


	/**
	 * 获取角色列表根据用户iD
	 * @param accountId
	 * @return
	 */
	public List<Role> getRoleListByAccount(ManageAccount account);
	
	/**
	 * 获取角色列表根据用户iD
	 * @param accountId
	 * @return
	 */
	public List<Role> getRoleListByRoleIds(List<Integer> roleIds);
	
	/**
	 * 根据角色获取资源
	 * @param roleId
	 * @return
	 */
	public List<ResourceRole> getResourceRole(Role role);
	
	/**
	 * 获取角色列表ID
	 * @param role
	 * @return
	 */
	public List<String> getPermissionIdList(Role role);
	/**
	 * 
	 * @param account
	 * @return
	 */
	public List<Role> getRoleList(ManageAccount account);
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Role> getAllRoleList();
	
	public Role  getRoleById(Long roleId);
	/**
	 * 角色资源
	 * @param roleId
	 * @return
	 */
	public  List<Map<String,Object>>  getResourceByRole(Long roleId);
	/**
	 * 全部资源
	 * @return
	 */
	public List<Resource>  getResourceList();
	/**
	 * 修改角色资源
	 * @param roleId
	 * @param roleName
	 * @param resourceIds
	 */
	public void updateRoleResource(Integer roleId,String roleName,String[] resourceIds);
	/**
	 * 为角色添加资源
	 * @param roleName
	 * @param resourceIds
	 */
	public void roleAdd(String roleName,String[] resourceIds);
	
	public void delRole(Role role);
	
}
