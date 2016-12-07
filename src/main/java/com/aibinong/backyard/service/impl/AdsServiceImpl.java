package com.aibinong.backyard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.AdApp;
import com.aibinong.backyard.pojo.Ads;
import com.aibinong.backyard.pojo.App;
import com.aibinong.backyard.service.AdsService;

/**
 * Created by lvfugang on 16/7/5.
 */
@IocBean(name = "adsService")
public class AdsServiceImpl  implements AdsService{
    @Inject
    protected BasicDao basicDao;
    private static final Log LOG = Logs.get();
    @Override
    public QueryResult getAdsList(String clientId,Integer toPage) {
   	 	StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotEmpty(clientId)){
        	  sb = new StringBuilder();
	        	 sb.append("     select a.*,p.client_id from ads a,");
	             sb.append("     ad_app p where a.id = p.ad_id\n  and a.deleted = 0 ");
	             sb.append("     and p.client_id= '"+clientId+"'\n");
	             sb.append("     order by a.gmt_create desc");
        }else{
        		 sb.append("     select * from ads a  where a.deleted = 0  order by gmt_create desc");
        }
		QueryResult queryResult = basicDao.querySqlResult(sb.toString(), toPage, Constants.DEFAULT_PAGE_SIZE);
		return queryResult;
    }
    @Override
    public Ads getAdById(Long adId) {
    	
    	 Ads ads=basicDao.find(adId,Ads.class);
        return ads;
    }
    @Override
    @Aop(TransAop.READ_COMMITTED)
    public void updateAd(Ads ads) {
		basicDao.update(ads);
		if (ads.getClientIds() != null) {
			deleteAppRelation(ads);
			addAppRelation(ads);
		}
    }
    
    private void deleteAppRelation(Ads ads){
    	Condition condition = Cnd.where("ad_id", "=", ads.getId());
		basicDao.delete("ad_app", condition);
    }
    private void addAppRelation(Ads ads){
    	List<AdApp> applist = new ArrayList<AdApp>();
		for (String client : ads.getClientIds()) {
			AdApp adp = new AdApp();
			adp.setAd_id(ads.getId());
			adp.setClient_id(client);
			applist.add(adp);
		}
		basicDao.saveAll(applist);
    }
    @Override
    @Aop(TransAop.READ_COMMITTED)
    public void insertAd(Ads ads) {
    	ads=basicDao.save(ads);
    	if(ads.getClientIds()!=null){
    		addAppRelation(ads);
    	}
    	
    }
    @Override
	public List<AdApp> getAppAdList(Long adId) {
		// TODO Auto-generated method stub
		Condition condition =Cnd.where("ad_id","=",adId);
		List<AdApp> adslist = basicDao.search(AdApp.class, condition);
		return adslist;
	}
    @Override
	 @Aop(TransAop.READ_COMMITTED)
	public void adsDel(Ads ads) {
		// TODO Auto-generated method stub
		basicDao.update(ads);
		Condition cond =Cnd.where("ad_id","=",ads.getId());
		basicDao.delete("ad_app", cond);
	}

}
