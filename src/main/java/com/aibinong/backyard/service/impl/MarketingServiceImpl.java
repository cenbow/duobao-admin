package com.aibinong.backyard.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.AwardPlan;
import com.aibinong.backyard.pojo.LadderDiscount;
import com.aibinong.backyard.pojo.MarketingActivity;
import com.aibinong.backyard.service.MarketingService;
import com.aibinong.backyard.web.module.MarketingModule;
@IocBean(name = "marketingService")
public class MarketingServiceImpl implements MarketingService{
	@Inject
    protected BasicDao basicDao;
	private static Logger logger = LoggerFactory.getLogger(MarketingServiceImpl.class);
	@Override
	public QueryResult loadMarketingList(String name, Date startTime,
			Date endTime, Integer page) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer str = new StringBuffer();
		str.append(" select * from marketing_activity where 1=1");
		if(StringUtils.isNotEmpty(name)){
			str.append(" and name like '%"+name+"%'");
		}
		if(startTime!=null){
			str.append(" and gmt_validity_start >='"+format.format(startTime)+"'");
		}
		if(endTime!=null){
			str.append(" and gmt_validity_end <='"+format.format(endTime)+"'");
		}
		str.append(" order by id desc");
		logger.info("活动查询："+str.toString());
		QueryResult queryResult = basicDao.querySqlResult(str.toString(), page, Constants.DEFAULT_PAGE_SIZE);
	    return queryResult;
	}

	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void createMarketing(String name,Date startTime,Date endTime,String[] limit_amount,String[] reduce_amount,String[] discount) {
		// TODO Auto-generated method stub
		MarketingActivity market = new MarketingActivity();
		market.setName(name);
		market.setGmt_validity_start(startTime);
		market.setGmt_validity_end(endTime);
		market.setStatus(Constants.DEFAULT_STATUS);
		market.setGmt_create(new Date());
		market.setGmt_modified(new Date());
		market=basicDao.save(market);
		
		if(market!=null && limit_amount!=null && limit_amount.length>0){
			for (int i =0;i<limit_amount.length;i++) {
				LadderDiscount  ladder = new LadderDiscount();
				ladder.setDiscount(Double.parseDouble(discount[i]));
				ladder.setLimit_amount(Integer.parseInt(limit_amount[i])*100);
				ladder.setMarketing_id(market.getId());
				ladder.setReduce_amount(Integer.parseInt(reduce_amount[i])*100);
				ladder.setGmt_create(new Date());
				ladder.setGmt_modified(new Date());
				basicDao.save(ladder);
			}
		}
		
	}

	@Override
	public MarketingActivity getMarketDetail(Long id) {
		// TODO Auto-generated method stub
		return basicDao.find(id, MarketingActivity.class);
	}

	@Override
	public List<LadderDiscount> getDisCountListByMarket(Long marketingId) {
		// TODO Auto-generated method stub
		Condition condition= Cnd.where("marketing_id", "=", marketingId);
		return basicDao.search(LadderDiscount.class, condition);
	}

	@Override
	public LadderDiscount getLadderDisCount(Long id) {
		// TODO Auto-generated method stub
		
		return basicDao.find(id, LadderDiscount.class);
	}

	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void createPlan(LadderDiscount dis, String[] interval_date,
			String[] face_value, String[] validity_date, String[] award_data) {
		// TODO Auto-generated method stub
		basicDao.update(dis);
		
		if(interval_date!=null && interval_date.length>0){
			Condition con =  Cnd.where("discount_id", "=", dis.getId());
		    basicDao.delete("award_plan", con);
			for(int i=0;i<interval_date.length;i++){
				AwardPlan plan = new AwardPlan();
				plan.setDiscount_id(dis.getId());
				plan.setMarketing_id(dis.getMarketing_id());
				StringBuffer str = new StringBuffer();
				String[] award=award_data[i].split(",");
				if(award!=null && award.length>0){
					for (int j = 0; j < award.length; j++) {
						str.append(Integer.parseInt(award[j])*100).append(",");
					}
				}
				plan.setAward_data(str.toString().substring(0, str.toString().length()-1));
				plan.setFace_value(Integer.parseInt(face_value[i]));
				plan.setInterval_date(Integer.parseInt(interval_date[i]));
				plan.setValidity_date(Integer.parseInt(validity_date[i]));
				plan.setGmt_create(new Date());
				plan.setGmt_modified(new Date());
				basicDao.save(plan);
			}
		}
	}

	@Override
	@Aop(TransAop.READ_COMMITTED)
	public void updateMarket(MarketingActivity activity) {
		// TODO Auto-generated method stub
		basicDao.update(activity);
	}

	@Override
	public List<AwardPlan> getPlanList(Long disCountId,Long marketing_id) {
		// TODO Auto-generated method stub
		Condition condition= Cnd.where("discount_id", "=", disCountId).and("marketing_id", "=", marketing_id);
		return basicDao.search(AwardPlan.class, condition);
	}

}
