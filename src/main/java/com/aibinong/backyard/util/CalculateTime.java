package com.aibinong.backyard.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.lang.Mirror;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.IocBy;

import com.aibinong.backyard.Constants;
import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.pojo.SysConfig;
import com.aibinong.backyard.web.MainModule;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by ouwa on 16/8/11.
 */
public class CalculateTime {
	@Inject
    protected BasicDao basicDao;

    public void setBasicDao(BasicDao basicDao) {
        this.basicDao = basicDao;
    }

    public static void main(String[] args){
        IocBy ib = MainModule.class.getAnnotation(IocBy.class);
        Ioc ioc = Mirror.me(ib.type()).born().create(null, ib.args());
        Mvcs.setIoc(ioc);

        CalculateTime calculateTime = new CalculateTime();
        calculateTime.setBasicDao( Mvcs.getIoc().get(BasicDao.class));

        int seconds = 0;
        seconds = calculateTime.calculate(1236L, 10, null);
        seconds = calculateTime.calculate(1236L, 100, null);

        seconds = calculateTime.calculate(1237L, 10, null);
        seconds = calculateTime.calculate(1237L, 100, null);


        seconds = calculateTime.calculate(1238L, 100, null);

        seconds = calculateTime.calculate(1492L, 100, null);
    }

    private List<ChanceLevel> parseFromSysConfig(int total_count){
        SysConfig smartAiArgs = basicDao.findByCondition(SysConfig.class,
                Cnd.where("client_id", "=", "common")
                        .and("os", "=", "common")
                        .and("version", "=", "common")
                        .and("k", "=", Constants.SYS_CONFIG_SMARTAI_ARGS)
        );
        JSONObject root = JSONObject.parseObject(smartAiArgs.getV());

        JSONObject timeRateObject = root.getJSONObject("timeRate");
        JSONObject chanceLevelObject = root.getJSONObject("chanceLevel");

        Map<String, Float> timeRateMap = new HashMap<String, Float>();
        Map<String, List<ChanceLevel>> chanceLevelMap = new HashMap<String, List<ChanceLevel>>();

        Iterator<String> it = timeRateObject.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            Float value = timeRateObject.getFloat(key);

            timeRateMap.put(key, value);
        }

        chanceLevelMap.clear();
        it = chanceLevelObject.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            List<JSONObject> list = chanceLevelObject.getObject(key, List.class);
            if(list.size() > 0){
                List<ChanceLevel> chanceLevelList = new ArrayList<ChanceLevel>();
                for(int i = 0; i < list.size(); i++){
                    ChanceLevel chanceLevel = list.get(i).toJavaObject(ChanceLevel.class);
                    chanceLevelList.add(chanceLevel);
                }
                chanceLevelMap.put(key, chanceLevelList);
            }
        }

        it = chanceLevelMap.keySet().iterator();
        String key = null;
        int min = 0;
        int max = 0;
        String[] columns = null;
        while(it.hasNext()){
            key = it.next();
            columns = key.split("-");
            min = Integer.parseInt(columns[0]);
            max = Integer.parseInt(columns[1]);

            if(total_count >= min && total_count <= max){
                return chanceLevelMap.get(key);
            }
        }

        return null;
    }


    private List<ChanceLevel> parseFromProductConfig(String smartAiArgs){
        try {
            JSONObject root = JSONObject.parseObject(smartAiArgs);

            JSONObject timeRateObject = root.getJSONObject("timeRate");
            List<JSONObject> chanceLevelListObject = root.getObject("chanceLevel", List.class);

            Map<String, Float> productTimeRateMap = new HashMap<String, Float>();
            Iterator<String> it = timeRateObject.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                Float value = timeRateObject.getFloat(key);
                productTimeRateMap.put(key, value);
            }
            if (chanceLevelListObject.size() > 0) {
                List<ChanceLevel> chanceLevelList = new ArrayList<ChanceLevel>();
                for (int i = 0; i < chanceLevelListObject.size(); i++) {
                    ChanceLevel chanceLevel = chanceLevelListObject.get(i).toJavaObject(ChanceLevel.class);
                    chanceLevelList.add(chanceLevel);
                }

                return chanceLevelList;
            }

            return null;
        }catch (Exception ex){
            throw new RuntimeException("解析json失败");
        }
    }

    private int calcBuyCount(int limit_count, List<ChanceLevel> chanceLevelList){
        int total_chance = 0;
        for(ChanceLevel chanceLevel : chanceLevelList){
            total_chance += chanceLevel.getChance();
        }

        Random random = new Random();
        int rand = random.nextInt(total_chance) + 1;

        int start = 0;
        int end = 0;
        ChanceLevel used_chanceLevel  = null;
        for(ChanceLevel chanceLevel : chanceLevelList){
            start = end + 1;
            end = start + chanceLevel.getChance() - 1;

            if(rand >= start && rand <= end){
                used_chanceLevel = chanceLevel;
                break;
            }
        }

        int buy_count = random.nextInt(used_chanceLevel.getMax() - used_chanceLevel.getMin() + 1) + used_chanceLevel.getMin();

        //份数*购买倍数，比如10元购
        return buy_count * limit_count;
    }

    public  int calculate(Long product_id, int hitRate, String ai_args){
        //获取商品总份数、夺宝倍数
        String sql = "SELECT total_count, limit_count FROM product WHERE id=" + product_id;
        Record record = basicDao.selectOne(sql);
        int total_count = record.getInt("total_count");
        int limit_count = record.getInt("limit_count");

        //解析JSON
        List<ChanceLevel> chanceLevelList = null;
        if(StringUtils.isNotEmpty(ai_args)) {
            try {
                chanceLevelList = parseFromProductConfig(ai_args);
            } catch (Exception ex) {
                throw new RuntimeException("解析JSON错误，请检查JSON格式。");
            }

            if (chanceLevelList == null || chanceLevelList.size() <= 0) {
                throw new RuntimeException("解析JSON错误，请检查JSON格式。");
            }
        }else{
            chanceLevelList = parseFromSysConfig(total_count);
            if (chanceLevelList == null || chanceLevelList.size() <= 0) {
                throw new RuntimeException("本商品的JSON设置为空，从系统配置中也未找到适应本商品的配置。");
            }
        }


        //开始预测
        int total = 0;
        for(int i=0; i < 1000; i++){
            total += calcBuyCount(limit_count, chanceLevelList);
        }

        float avg = total * 1f / 1000;  //平均一次购买的份数
        float times = total_count % avg == 0 ? total_count / avg : total_count / avg + 1; //要购买多少次

        float buyRate = 100 * 1f/ hitRate;           //每多少次任务才能产生一笔购买
        int total_seconds = (int)(buyRate * times * 12);

        return total_seconds;
    }
}
