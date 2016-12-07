package com.aibinong.backyard.service.impl;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.Spu;
import com.aibinong.backyard.service.SpuService;
@IocBean(name = "spuService")
public class SpuServiceImpl implements SpuService {
	
	@Inject
    protected BasicDao basicDao;
	@Override
	public QueryResult getSpuList(Integer toPage) {
		// TODO Auto-generated method stub
		 StringBuffer sb = new StringBuffer();
		 sb.append(" select * from spu  order by id desc");
		 QueryResult queryResult = basicDao.querySqlResult(sb.toString(), toPage, Constants.DEFAULT_PAGE_SIZE);
		return queryResult;
	}
	@Override
	public Spu getSpu(Long spuId) {
		// TODO Auto-generated method stub
		
		return basicDao.find(spuId, Spu.class);
	}
	@Override
	public void updateSpu(Spu spu) {
		// TODO Auto-generated method stub
		basicDao.update(spu);
	}
	@Override
	public Spu addSpu(Spu spu) {
		// TODO Auto-generated method stub
		spu=basicDao.save(spu);
		return spu;
	}
	@Override
	public void delSpu(Long spuId) {
		// TODO Auto-generated method stub
		basicDao.delById(spuId,Spu.class);
	}
	@Override
	public List<Spu> getSpuListByTile(String title) {
		// TODO Auto-generated method stub
		Condition con = Cnd.where("title","=",title);
		List<Spu>  spulist = basicDao.search(Spu.class, con);
		return spulist;
	}
	@Override
	public List<Spu> getSpuList() {
		// TODO Auto-generated method stub
		Condition cnd = Cnd.where("deleted", "=", "0");
		List<Spu> spulist = basicDao.search(Spu.class, cnd);
		return spulist;
	}

}
