package com.aibinong.backyard.service;

import java.util.List;

import org.nutz.dao.QueryResult;

import com.aibinong.backyard.pojo.AdApp;
import com.aibinong.backyard.pojo.Ads;

/**
 * Created by lvfugang on 16/7/5.
 */
public interface AdsService {

    public QueryResult getAdsList(String clientId,Integer toPage);

    public Ads getAdById(Long adId);
    
    public List<AdApp> getAppAdList(Long adId);

    public void updateAd(Ads ads);

    public void insertAd(Ads ads);
    
    public void adsDel(Ads ads);
}
