package com.aibinong.backyard.datasource;

import com.aibinong.backyard.Constants;
import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;

public class AbnDruidDataSource extends DruidDataSource {
	private static final long serialVersionUID = 1L;

	@Override
	public void setUsername(String username) {
		try {
			username = ConfigTools.decrypt(Constants.DB_ENCRYPT_PUBLIC_KEY,username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setUsername(username);
	}

	@Override
	public void setPassword(String password) {
		try {
			password = ConfigTools.decrypt(Constants.DB_ENCRYPT_PUBLIC_KEY,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setPassword(password);
	}
	public static void main(String[] args) {
        String userName = "jyduobao1010";
        String pwd = "jiYU826233";
        try {
            String userName_encrypt = ConfigTools.encrypt(Constants.DB_ENCRYPT_PRIVATE_KEY, userName);
            String pwd_encrypt = ConfigTools.encrypt(Constants.DB_ENCRYPT_PRIVATE_KEY, pwd);
            System.out.println(userName_encrypt);
            System.out.println(pwd_encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
