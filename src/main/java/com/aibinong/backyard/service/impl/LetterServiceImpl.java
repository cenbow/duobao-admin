package com.aibinong.backyard.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.Letter;
import com.aibinong.backyard.service.LetterService;
@IocBean(name = "letterService")
public class LetterServiceImpl implements LetterService {
	@Inject
    protected BasicDao basicDao;
	@Override
	public QueryResult getLetterList(String startTime, String endTime, Integer page) {
		// TODO Auto-generated method stub
		StringBuffer str = new StringBuffer();
		str.append("select * from letter where 1=1");
			if (StringUtils.isNotEmpty(endTime)) {

				str.append(" and send_time>='" + startTime+ "'");

			}
			if (StringUtils.isNotEmpty(endTime)) {
				str.append(" and send_time<='" + endTime+ "'");
			}
		QueryResult queryResult = basicDao.querySqlResult(str.toString(), page,Constants.DEFAULT_PAGE_SIZE);
	    return queryResult;
	}

	@Override
	public Letter getLetterDetail(Long id) {
		// TODO Auto-generated method stub
		
		return basicDao.find(id, Letter.class);
	}

	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void updateLetter(Letter letter) {
		// TODO Auto-generated method stub
		basicDao.update(letter);
	}

	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void addLetter(Letter letter) {
		// TODO Auto-generated method stub
		basicDao.save(letter);
	}

}
