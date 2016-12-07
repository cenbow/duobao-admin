package com.aibinong.backyard.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.mvc.Mvcs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.aibinong.backyard.dao.BasicDao;
import com.aibinong.backyard.dao.RedisDao;
import com.aibinong.backyard.pojo.Redpack;
import com.aibinong.backyard.pojo.RedpackTurn;
import com.aibinong.backyard.pojo.TurnConf;
import com.aibinong.backyard.service.RedpackService;
import com.aibinong.backyard.util.DateUtil;
import com.aibinong.backyard.util.NumberUtil;
import com.aibinong.backyard.util.NutzUtil;
import com.aibinong.backyard.util.RedpackRand;
import com.aibinong.backyard.util.StringUtil;
import com.aibinong.backyard.web.MainModule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@IocBean(name = "redpackService")
public class RedpackServiceImpl implements RedpackService {
	private final static Logger LOG = LoggerFactory.getLogger(RedpackServiceImpl.class);

	@Inject
	private BasicDao basicDao;

	@Inject
	private RedisDao redisDao;

	@Override
	public Redpack getRedpack(long redpackId) {
		Redpack redpack = basicDao.find(redpackId, Redpack.class);
		if (redpack != null && StringUtils.isNotBlank(redpack.getTurnConf())) {
			redpack.setRangeMax(redpack.getRangeMax() / 100);
			redpack.setRangeMin(redpack.getRangeMin() / 100);
			redpack.setRangeAvg(redpack.getRangeAvg() / 100);
			List<TurnConf> turnList = JSON.parseArray(redpack.getTurnConf(), TurnConf.class);
			redpack.setTurnList(turnList);
		}
		return redpack;
	}

	@Override
	public void deleteRedpackTurn(long redpackId) {
		// 清除redis轮次列表
		String key = StringUtil.format("redpack:turn_list:redpack_id:{redpackId}", redpackId);
		redisDao.del(key);
		// 删除数据库轮次列表
		basicDao.executeSql("DELETE FROM redpack_turn WHERE redpack_id = " + redpackId);
	}

	@Override
	public String getOnlineRedpackIds() {
		String redpackIds = null;
		Record record = basicDao.selectOne("SELECT GROUP_CONCAT(id) AS ids FROM redpack WHERE status = 1");
		if (!Lang.isEmpty(record)) {
			redpackIds = record.getString("ids");
		}
		return redpackIds;
	}

	@Override
	public void createRedpack(Redpack redpack) {
		basicDao.save(redpack);
	}

	@Override
	public void updateRedpack(Redpack redpack) {
		basicDao.update(redpack);
	}

	@Override
	public List<Redpack> getOnlineRedpackList() {
		Cnd cnd = Cnd.NEW();
		cnd.and("status", "=", 1);
		cnd.orderBy("id", "ASC");
		return basicDao.search(Redpack.class, cnd);
	}

	@Override
	public QueryResult getRedpackList(Long id, String name, String startDate, String endDate, Integer status, int pageNo, int pageSize) {
		Cnd cnd = Cnd.NEW();
		if (id != null) {
			cnd.and("id", "=", id);
		}
		if (StringUtils.isNotBlank(name)) {
			cnd.and("name", "LIKE", "%" + name + "%");
		}
		if (StringUtils.isNotBlank(startDate)) {
			cnd.and("start_date", ">=", startDate);
		}
		if (StringUtils.isNotBlank(endDate)) {
			cnd.and("end_date", "<=", endDate);
		}
		if (status != null) {
			cnd.and("status", "=", status);
		}
		cnd.desc("status").desc("id");

		Pager pager = basicDao.createPager(pageNo, pageSize);
		pager.setPageNumber(pageNo);
		pager.setPageSize(pageSize);
		pager.setRecordCount(basicDao.searchCount(Redpack.class, cnd));

		List<Redpack> list = basicDao.searchByPage(Redpack.class, cnd, pager);

		QueryResult result = new QueryResult();
		result.setPager(pager);
		result.setList(list);

		return result;
	}

	@Override
	public QueryResult getRedpackTurnList(long redpackId, String startDate, String endDate, int pageNo, int pageSize) {
		Cnd cnd = Cnd.NEW();
		cnd.and("redpack_id", "=", redpackId);
		if (StringUtils.isNotBlank(startDate)) {
			cnd.and("turn_time", ">=", startDate.replaceAll("-", ""));
		}
		if (StringUtils.isNotBlank(endDate)) {
			cnd.and("end_time", "<=", endDate.replaceAll("-", ""));
		}
		cnd.orderBy("turn_time", "ASC");

		Pager pager = basicDao.createPager(pageNo, pageSize);
		pager.setPageNumber(pageNo);
		pager.setPageSize(pageSize);
		pager.setRecordCount(basicDao.searchCount(RedpackTurn.class, cnd));

		List<RedpackTurn> list = basicDao.searchByPage(RedpackTurn.class, cnd, pager);
		if (list != null && list.size() > 0) {
			List<Long> turnIds = new ArrayList<Long>();
			for (RedpackTurn turn : list) {
				turnIds.add(turn.getId());

				String timeRange = null;
				try {
					Date turnTime = DateUtil.parse(turn.getTurnTime(), "yyyyMMddHHmm");
					Date endTime = DateUtil.parse(turn.getEndTime(), "yyyyMMddHHmm");
					timeRange = DateUtil.format(turnTime, "yyyy-MM-dd HH:mm") + "~" + DateUtil.format(endTime, "HH:mm");
					turn.setTimeRange(timeRange);
				} catch (Exception ex) {
				}

				String amountString = turn.getAmountList();
				if (StringUtils.isNotBlank(amountString)) {
					String[] amountList = amountString.split(",");

					Integer minAmount = null; // 最小金额
					int maxAmount = 0; // 最大金额
					double avgAmount = 0; // 平均金额
					int totalAmount = 0; // 总金额
					int totalCount = 0; // 总个数
					int remainCount = 0; // 剩余个数
					for (String s : amountList) {
						int amount = Integer.parseInt(s);
						if (amount > maxAmount) {
							maxAmount = amount;
						}
						if (minAmount == null) {
							minAmount = amount;
						}
						if (amount < minAmount) {
							minAmount = amount;
						}
						totalAmount += amount;
						totalCount ++;
					}

					avgAmount = NumberUtil.div(totalAmount, totalCount, 2);

					turn.setMaxAmount(maxAmount);
					turn.setMinAmount(minAmount);
					turn.setAvgAmount(avgAmount);
					turn.setTotalAmount(totalAmount);
					turn.setTotalCount(totalCount);
					turn.setRemainCount(remainCount);
				}
			}

			Map<Long, Integer> turnMap = this.getTurnCountMap(turnIds);
			for (RedpackTurn turn : list) {
				if (turnMap.containsKey(turn.getId())) {
					turn.setRemainCount(turn.getTotalCount() - turnMap.get(turn.getId()));
				} else {
					turn.setRemainCount(turn.getTotalCount());
				}
			}
		}

		QueryResult result = new QueryResult();
		result.setPager(pager);
		result.setList(list);


		return result;
	}

	@Aop(TransAop.READ_COMMITTED)
	@Override
	public void createRedpackTurn(String dateString) {
		List<Redpack> list = this.getOnlineRedpackList();
		if (list != null && list.size() > 0) {
			for (Redpack redpack : list) {
				try {
					Date startDate = DateUtil.parse(redpack.getStartDate(), DateUtil.SDF_SHORT);
					Date endDate = DateUtil.parse(redpack.getEndDate(), DateUtil.SDF_SHORT);

					// 活动开始前一天开始新建红包活动
					if (DateUtil.diffDate(new Date(), startDate) > 24 * 60 * 60) {
						LOG.info("红包活动{}开始日期为{}，还没开始。", new Object[] { redpack.getId(), redpack.getStartDate() });
						continue;
					}

					if (endDate.before(new Date())) {
						LOG.info("红包活动{}结束日期为{}，已经结束。", new Object[] { redpack.getId(), redpack.getEndDate() });
						continue;
					}

					if (StringUtils.isBlank(dateString)) {
						dateString = DateUtil.format(DateUtil.addDays(new Date(), 1), "yyyyMMdd");
					}

					if (this.hasRedpackTurn(redpack.getId(), dateString)) {
						LOG.info("红包活动{}已经创建了{}的轮次，暂时跳过。", new Object[] { redpack.getId(), dateString });
						continue;
					}

					JSONArray turnConf = JSON.parseArray(redpack.getTurnConf());
					List<RedpackTurn> turns = new ArrayList<RedpackTurn>();
					for (Object object : turnConf) {
						JSONObject json = JSON.parseObject(object.toString());
						String clock = json.getString("clock");
						int count = json.getIntValue("count");
						String turnTime = dateString + clock;
						String endTime = this.randomEndTime(turnTime, redpack.getEndMinute());
						// 随机金额(元)
						int[] amountYuanList = RedpackRand.random(count, redpack.getRangeMax() / 100, redpack.getRangeAvg() / 100, redpack.getRangeMin() / 100);

						// 元转分
						List<Integer> amountFenList = new ArrayList<Integer>();
						for (Integer i : amountYuanList) {
							amountFenList.add(i * 100);
						}

						RedpackTurn redpackTurn = new RedpackTurn();
						redpackTurn.setRedpackId(redpack.getId());
						redpackTurn.setTurnTime(turnTime);
						redpackTurn.setEndTime(endTime);
						redpackTurn.setAmountList(StringUtils.join(amountFenList, ","));
						redpackTurn.setGmtCreate(new Date());
						redpackTurn.setGmtModified(new Date());
						turns.add(redpackTurn);
					}

					Jedis jedis = redisDao.getJedis();
					try {
						// 把红包轮次放入队列
						for (RedpackTurn turn : turns) {
							String key = StringUtil.format("redpack:amount_list:redpack_id:{redpack_id}:{yyyyMMddHHmm}", turn.getRedpackId(),
									turn.getTurnTime());
							jedis.del(key);
							jedis.lpush(key, turn.getAmountList().split(","));
							jedis.expire(key, 3 * 24 * 60 * 60); // 缓存3天
						}

						// 队列放入成功，则添加入库
						basicDao.saveAll(turns);
					} catch (Exception ex) {
						// 如果出现异常，把队列清空
						for (RedpackTurn turn : turns) {
							String key = StringUtil.format("redpack:amount_list:redpack_id:{redpack_id}:{yyyyMMddHHmm}", turn.getRedpackId(),
									turn.getTurnTime());
							jedis.del(key);
						}
						// 扔出异常，事务回滚
						throw new RuntimeException(ex);
					} finally {
						redisDao.closeJedis(jedis);
					}
					LOG.info("红包ID={}, 日期={}的轮次新建成功！", redpack.getId(), dateString);
				} catch (Exception ex) {
					LOG.error("红包轮次生成出错: {}", redpack.getId(), ex);
				}
			}
		}
	}

	/**
	 * 是否已经创建了轮次
	 * @author zhang_zg
	 * @param dateString yyyyMMdd
	 * @return
	 */
	private boolean hasRedpackTurn(long redpackId, String dateString) {
		int total = basicDao.fetchInt("SELECT COUNT(1) FROM redpack_turn WHERE redpack_id = " + redpackId + " AND turn_time LIKE  '" + dateString + "%'");
		return total > 0;
	}

	/**
	 * 随机轮次结束时间
	 * @author zhang_zg
	 * @param turnTime
	 * @param endMinute
	 * @return
	 * @throws ParseException 
	 */
	private String randomEndTime(String turnTime, int endMinute) throws RuntimeException {
		try {
			Date date = DateUtil.parse(turnTime, "yyyyMMddHHmm");

			Random random = new Random();
			int randMinute = endMinute;
			if (randMinute > 3) {
				randMinute = endMinute / 2;
			}
			if (random.nextBoolean()) {
				endMinute = endMinute + random.nextInt(randMinute);
			} else {
				endMinute = endMinute - random.nextInt(randMinute);
			}
			date = DateUtil.addMinutes(date, endMinute);
			return DateUtil.format(date, "yyyyMMddHHmm");
		} catch (Exception ex) {
			LOG.error("生成随机结束时间异常", ex);
			throw new RuntimeException(ex);
		}
	}

	public static void main(String[] args) {
		NutzUtil.init(MainModule.class);

		RedpackService service = Mvcs.getIoc().get(RedpackService.class, "redpackService");
		service.createRedpackTurn("20160929");
	}

	/**
	 * 获取轮次抢红包次数
	 * @author zhang_zg
	 * @param turnIds
	 * @return
	 */
	private Map<Long, Integer> getTurnCountMap(List<Long> turnIds) {
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		if (Lang.isEmpty(turnIds)) {
			return map;
		}

		String ids = StringUtils.join(turnIds, ",");
		String sql = "SELECT turn_id, count(user_id) AS total FROM redpack_record WHERE turn_id IN(" + ids + ") GROUP BY turn_id";

		List<Record> list = basicDao.select(sql);
		if (list != null && list.size() > 0) {
			for (Record e : list) {
				map.put(Long.parseLong(e.getString("turn_id")), e.getInt("total"));
			}
		}
		return map;
	}
}
