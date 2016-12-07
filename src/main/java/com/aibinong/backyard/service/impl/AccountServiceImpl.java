package com.aibinong.backyard.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;

import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.AccountRole;
import com.aibinong.backyard.pojo.ManageAccount;
import com.aibinong.backyard.pojo.Resource;
import com.aibinong.backyard.pojo.Role;
import com.aibinong.backyard.service.AccountService;
import com.aibinong.backyard.util.MD5Util;

@IocBean(name = "accountService")
public class AccountServiceImpl implements AccountService {

	@Inject
	protected BasicDao basicDao;

	@Override
	public ManageAccount findAccount(String account, String password) {
		Cnd con = Cnd.where("account", "=", account).and("password", "=", MD5Util.getMD5String(password));
		ManageAccount accountuser = basicDao.findByCondition(ManageAccount.class, con);
		if (accountuser != null) {
			accountuser = getResourceList(accountuser);
		}
		return accountuser;
	}

	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void addAccount(ManageAccount account, String[] roleIds) {
		account = basicDao.save(account);
		List<AccountRole> accroleList = new ArrayList<AccountRole>();
		if (roleIds != null && roleIds.length > 0) {
			for (int i = 0; i < roleIds.length; i++) {
				AccountRole accRole = new AccountRole();
				accRole.setUser_id(account.getId());
				accRole.setRole_id(Integer.parseInt(roleIds[i]));
				accroleList.add(accRole);
			}
			basicDao.saveAll(accroleList);
		}
	}

	@Override
	public void updateAccount(ManageAccount account) {
		basicDao.update(account);
	}

	@Override
	public void addUserRole(Long userId, Integer roleId) {
		ManageAccount user = basicDao.find(userId, ManageAccount.class);
		Role role = new Role();
		role.setId(roleId);
		user.setRoles(Lang.list(role));
		basicDao.saveRelation(user, "roles");
	}

	@Override
	public void removeRole(Long userId, Integer roleId) {
		ManageAccount user = basicDao.find(userId, ManageAccount.class);
		Role role = new Role();
		role.setId(roleId);
		user.setRoles(Lang.list(role));
		basicDao.clearRelation(user, "roles");
	}

	@Override
	public ManageAccount findAccount(String account) {
		Cnd con = Cnd.where("account", "=", account);
		ManageAccount accountuser = basicDao.findByCondition(ManageAccount.class, con);
		return accountuser;
	}

	@Override
	public ManageAccount getAccountById(Long user_id) {
		ManageAccount account = basicDao.find(user_id, ManageAccount.class);
		account = getResourceList(account);
		return account;
	}

	private ManageAccount getResourceList(ManageAccount account) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT t2.resource_id, t3.resource_name, t3.level, t3.parent_id, t3.url\n");
		sql.append("FROM account_role t1, resource_role t2, resource t3, role t4 \n");
		sql.append("WHERE t1.role_id = t2.role_id\n");
		sql.append("AND t1.role_id = t4.id\n");
		sql.append("AND t4.status = 1 \n");
		sql.append("AND t2.resource_id = t3.id\n");
		sql.append("AND t1.user_id = ").append(account.getId()).append("\n");
		sql.append("ORDER BY t3.level DESC, t2.resource_id ASC");

		List<Record> list = this.basicDao.select(sql.toString());
		
		// 一级节点列表：List<一级节点>
		List<Resource> resourceList = new ArrayList<Resource>();
		// 二级节点列表：Map<一级节点ID, 二级节点列表>
		Map<String, List<Resource>> childListMap = new HashMap<String, List<Resource>>();

		for (Record record : list) {
			String resourceId = record.getString("resource_id");
			String resourceName = record.getString("resource_name");
			String url = record.getString("url");

			// 根据节点级别进行分组
			if (record.getInt("level") == 1) {
				String parentId = record.getString("parent_id");
				// 列表是否为空
				if (childListMap.get(parentId) == null) {
					Resource resource = new Resource();
					resource.setId(Long.parseLong(resourceId));
					resource.setResource_name(resourceName);
					resource.setUrl(url);

					List<Resource> childList = new ArrayList<Resource>();
					childList.add(resource);
					childListMap.put(parentId, childList);
				} else {
					Resource resource = new Resource();
					resource.setId(Long.parseLong(resourceId));
					resource.setResource_name(resourceName);
					resource.setUrl(url);
					childListMap.get(parentId).add(resource);
				}
			} else {
				Resource resource = new Resource();
				resource.setId(Long.parseLong(resourceId));
				resource.setResource_name(resourceName);
				resource.setUrl(url);
				resource.setChildList(childListMap.get(resourceId));
				resourceList.add(resource);
			}
		}
		account.setResourceList(resourceList);
		return account;
	}

	@Override
	public List<ManageAccount> getMangeList() {
		return basicDao.search(ManageAccount.class, "id");
	}

	@Override
	public Map<Integer, AccountRole> getAccountMapp(ManageAccount account) {
		Condition con = Cnd.where("user_id", "=", account.getId());
		List<AccountRole> accountList = basicDao.search(AccountRole.class, con);
		Map<Integer, AccountRole> roleMap = new HashMap<Integer, AccountRole>();
		if (accountList != null && !accountList.isEmpty()) {
			for (AccountRole accountRole : accountList) {
				roleMap.put(accountRole.getRole_id(), accountRole);
			}
		}
		return roleMap;
	}

	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void updateAccountAuthority(Long userid, String[] roleIds) {
		Condition con = Cnd.where("user_id", "=", userid);
		List<AccountRole> accountList = basicDao.search(AccountRole.class, con);
		if (accountList != null) {
			basicDao.delete("account_role", con);
		}
		List<AccountRole> accountRl = new ArrayList<AccountRole>();
		if (roleIds != null && roleIds.length > 0) {
			for (String roleid : roleIds) {
				AccountRole arole = new AccountRole();
				arole.setRole_id(Integer.parseInt(roleid));
				arole.setUser_id(userid);
				accountRl.add(arole);
			}
			basicDao.saveAll(accountRl);
		}
	}

}
