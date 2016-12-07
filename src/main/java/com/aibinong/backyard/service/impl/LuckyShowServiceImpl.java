package com.aibinong.backyard.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.LuckyShow;
import com.aibinong.backyard.service.LuckyShowService;
@IocBean(name = "luckyShowService")
public class LuckyShowServiceImpl implements LuckyShowService {
	@Inject
    protected BasicDao basicDao;
	@Override
	public QueryResult getLuckShowList(Integer toPage, Long period,String productName, Long spuId) {
		// TODO Auto-generated method stub
		 StringBuffer sb = new StringBuffer();
			 sb.append("  select w.*,u.nick,u.mobile from lucky_show w ");
			 sb.append("  left join users u on w.user_id=u.id  where 1=1 ");
		 if(period!=null){
			 sb.append("  and period_id="+period);
		 }
		 if(spuId!=null && spuId!=-1){
			 sb.append("  and spu_id="+spuId);
		 }
		 if(StringUtils.isNotEmpty(productName)){
			 List<Record> recordlist =basicDao.select("select * from product where title like'%"+productName+"%'");
				StringBuffer prostr = new StringBuffer();
				if(recordlist!=null && !recordlist.isEmpty()){
					for (Record record : recordlist) {
						prostr.append(record.get("id")).append(",");
					}
				}
				if(StringUtils.isEmpty(prostr.toString())){
					return null;
				}
				sb.append(" and product_id in (").append(prostr.toString().substring(0, prostr.length()-1)).append(")");
		 }
		 		sb.append(" order by w.gmt_create desc");
		 	List<Record> list = basicDao.querySqlByPage(sb.toString(), toPage,  Constants.DEFAULT_PAGE_SIZE);
		 	QueryResult queryResult = new QueryResult();
		 	if(list!=null &&!list.isEmpty()){
		 		for (Record record : list) {
		 			String imgs = record.getString("imgs");
		 			if(StringUtils.isNotEmpty(imgs)){
		 				record.set("imglist", imgs.split(","));
		 			}
				}
		 		int count = basicDao.fetchInt(sb.toString());
				Pager pager = basicDao.createPager(toPage, Constants.DEFAULT_PAGE_SIZE);
				pager.setRecordCount(count);
				queryResult.setList(list);
				queryResult.setPager(pager);
		 	}
		 	
			return queryResult;
	}
	@Override
	public LuckyShow getLuckyShowDetail(Long luckyShowId) {
		// TODO Auto-generated method stub
		return basicDao.find(luckyShowId, LuckyShow.class);
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void updateLuckyShow(LuckyShow luckyShow) {
		// TODO Auto-generated method stub
		basicDao.update(luckyShow);
	}
	@Override
	public LuckyShow getLuckShowByPeriod(Long periodId) {
		// TODO Auto-generated method stub
		Condition condition = Cnd.where("period_id", "=", periodId);
		LuckyShow luckyShow =basicDao.findByCondition(LuckyShow.class, condition);
		return luckyShow;
	}
	@Override
	@Aop(TransAop.READ_COMMITTED)
	public LuckyShow saveLuckyShow(LuckyShow luckyShow) {
		// TODO Auto-generated method stub
		return basicDao.save(luckyShow);
	}

}
