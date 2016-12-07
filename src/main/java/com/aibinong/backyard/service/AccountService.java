package com.aibinong.backyard.service;

import java.util.List;
import java.util.Map;

import com.aibinong.backyard.pojo.AccountRole;
import com.aibinong.backyard.pojo.ManageAccount;

public interface AccountService {

	/**
	 * 根据账户密码获取用户信息
	 * @param account
	 * @param password
	 * @return
	 */
	public ManageAccount findAccount(String account,String password);
	
	/**
	 * 根据账户密码获取用户信息
	 * @param account
	 * @param password
	 * @return
	 */
	public ManageAccount findAccount(String account);
	/**
	 * 添加用户
	 * @param account
	 */
	public void addAccount(ManageAccount account,String[] roleIds);
	/**
	 * 修改用户信息
	 * @param account
	 */
	public void updateAccount(ManageAccount account);
	/**
	 * 为用户添加角色
	 * @param userId
	 * @param roleId
	 */
	public void addUserRole(Long userId, Integer roleId);
	/**
	 * 移除用户角色
	 * @param userId
	 * @param roleId
	 */
	 
	public void removeRole(Long userId, Integer roleId);
	/**
	 * 获取账号
	 * @param user_id
	 * @return
	 */
	public ManageAccount getAccountById(Long user_id);
	/**
	 * 获取所有后台用户
	 * @return
	 */
	public List<ManageAccount> getMangeList();
	
	
	public Map<Integer,AccountRole> getAccountMapp(ManageAccount account);
	/**
	 * 修改用户权限
	 * @param userid
	 * @param roleIds
	 */
	public void updateAccountAuthority(Long userid,String[] roleIds);
}
