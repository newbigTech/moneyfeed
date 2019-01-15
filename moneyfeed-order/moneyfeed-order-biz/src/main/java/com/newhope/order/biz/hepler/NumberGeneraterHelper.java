package com.newhope.order.biz.hepler;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.common.cache.redislock.RedisLock;
import com.newhope.moneyfeed.common.cache.redislock.RedisLockManager;
import com.newhope.moneyfeed.common.concurrent.ManagedThreadPool;
import com.newhope.moneyfeed.order.api.bean.OrderSysParamModel;
import com.newhope.moneyfeed.order.api.exception.NumberGeneratorException;
import com.newhope.order.biz.constant.OrderCacheConstant;
import com.newhope.order.biz.hepler.selector.OrderSegmentSelector;
import com.newhope.order.biz.hepler.selector.PaySegmentSelector;
import com.newhope.order.biz.hepler.selector.SegmentSelector;
import com.newhope.order.biz.service.OrderSysParamService;
import com.newhope.order.biz.task.NumberGenerateTask;

@Component
public class NumberGeneraterHelper {

	private final Logger logger = LoggerFactory.getLogger(NumberGeneraterHelper.class);

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private OrderSysParamService orderSysParamService;

	@Autowired
	RedisLockManager redisLockManager;

	@Autowired
	private ManagedThreadPool managedThreadPool;

	private RedisSerializer<Object> keySerializer;

	private RedisSerializer<Object> valueSerializer;

	@SuppressWarnings("unchecked")
	@PostConstruct
	void init() {
		keySerializer = (RedisSerializer<Object>) redisTemplate.getKeySerializer();
		valueSerializer = (RedisSerializer<Object>) redisTemplate.getValueSerializer();
	}

	public synchronized String genOrderNo() {
		SegmentSelector selector = new OrderSegmentSelector();
		selector.setCurrentSegment(OrderCacheConstant.CURRENT_ORDERNO_SEGMENT);
		selector.setSegment1(OrderCacheConstant.ORDERNO_SEGMENT1);
		selector.setSegment2(OrderCacheConstant.ORDERNO_SEGMENT2);
		selector.setSegmentMaxLeftQty(IdGeneraterHelper.ORDER_SEGMENT_MAX_LEFT_QTY);
		return generateNumber(selector);
	}

	public synchronized String genPayNo() {
		SegmentSelector selector = new PaySegmentSelector();
		selector.setCurrentSegment(OrderCacheConstant.CURRENT_PAYNO_SEGMENT);
		selector.setSegment1(OrderCacheConstant.PAYNO_SEGMENT1);
		selector.setSegment2(OrderCacheConstant.PAYNO_SEGMENT2);
		selector.setSegmentMaxLeftQty(IdGeneraterHelper.PAY_SEGMENT_MAX_LEFT_QTY);
		return generateNumber(selector);
	}

	protected String generateNumber(SegmentSelector selector) {
		// 当前使用的 segment
		String segmentName = getSegmet(selector.getCurrentSegment());
		String segment1 = selector.getSegment1();
		String segment2 = selector.getSegment2();
		String segment2Switch = null;
		if (segmentName == null) {
			segmentName = segment1;
			segment2Switch = segment2;
			setSegment(selector.getCurrentSegment(), selector.getSegment1());
		} else {// 设置默认segment
			if (segment1.equals(segmentName)) {
				segment2Switch = segment2;
			} else if (segment2.equals(segmentName)) {
				segment2Switch = segment1;
			}
		}
		BoundSetOperations<Object, Object> redisSet = getRedisSet(segmentName);
		long size = redisSet.size(); // 容量检查
		if (size >= selector.getSegmentMaxLeftQty()) {
			return (String) redisSet.pop();
		} else if (size < selector.getSegmentMaxLeftQty() && size > 0) {
			if (getRedisSet(segment2Switch).size() > 0) {
				return (String) redisSet.pop();
			} else {
				selector.setSegmentName(segment2Switch);
				return setAndGetNum(selector);
			}
		} else {
			BoundSetOperations<Object, Object> prepaRedisSet = getRedisSet(segment2Switch);
			if (prepaRedisSet.size() == 0) {
				selector.setSegmentName(segmentName);
				return setAndGetNum(selector);
			} else {
				setSegment(selector.getCurrentSegment(), segment2Switch);
				return (String) prepaRedisSet.pop();
			}
		}
	}

	/**
	 * 订单号生成器： 1. 获取订单号相关系统参数，并更新当前值 2. 根据当前值和步长生成随机号集合 3. 生成4位时间压缩编号 4.
	 * 获取业务系统编号 5. 根据规则组合业务编号+时间压缩编号+随机编号，生成订单号集合 6. 缓存订单号集合
	 * 
	 * @param segment（moneyfeed-order:number:segment1）
	 * @param segment（moneyfeed-order:number:segment2）
	 * @return
	 */
	public void generateNumberSegment(SegmentSelector selector) {
		selector.initSysParam();//初始化参数
		// 获取订单号相关系统参数，并更新当前值
		OrderSysParamModel sysParamQuery = new OrderSysParamModel();
		sysParamQuery.setType(selector.getSysParamType());
		sysParamQuery.setCode(selector.getSysParamCode());
		List<OrderSysParamModel> sysParams = orderSysParamService.query(sysParamQuery);
		OrderSysParamModel sysParam = sysParams.get(0);
		// 更新当前值
		final Long currentVal = Long.valueOf(sysParam.getName());
		final Long stepVal = Long.valueOf(sysParam.getValue());
		if (stepVal.intValue() != selector.getSegmentMaxQty()) { // 步长与MAX_QTY不等表示MAX值在数据库侧更新，则将数据库值赋予MAX_QTY
			selector.initQty(stepVal.intValue(), stepVal.intValue() / 10);
		}

		String newVal = String.valueOf(currentVal + stepVal);
		sysParamQuery.setName(sysParam.getName());
		OrderSysParamModel newSysParam = new OrderSysParamModel();
		newSysParam.setName(newVal);
		if (!orderSysParamService.update(sysParamQuery, newSysParam)) {
			logger.error(String.format(selector.getErrCode().getDesc() + ":{sysParam:{%s},newSysParam:{%s}}",
					JSON.toJSONString(sysParam), JSON.toJSONString(newSysParam)));
			throw new NumberGeneratorException(selector.getErrCode());
		}
		// 生成4位时间压缩编号
		String daysEncode = IdGeneraterHelper.getCurrentTimeEncode();
		// 业务系统编号
		String bizCode = selector.getBizCode();
		redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.openPipeline();
				byte[] key = serializeKey(selector.getSegmentName());
				for (long i = 1; i <= stepVal; i++) {
					String idVal = String.valueOf(currentVal + i); // 随机编号
					// 根据规则组合业务编号+时间压缩编号+随机编号，生成订单号集合
					String num = IdGeneraterHelper.orderGeneraterEngine(bizCode, daysEncode, idVal);
					connection.sAdd(key, serializeValue(num));
				}
				connection.closePipeline();
				return null;
			}
		});
	}

	protected BoundSetOperations<Object, Object> getRedisSet(Object key) {
		return redisTemplate.boundSetOps(key);

	}

	protected void setSegment(String key, String value) {
		redisTemplate.boundValueOps(key).set(value);
	}

	protected String getSegmet(String key) {
		return (String) redisTemplate.boundValueOps(key).get();
	}

	void prepareSegmentSet(SegmentSelector selector) {
		RedisLock redisLock = redisLockManager.getLock(selector.getLockKey());
		redisLock.lock();
		try {
			BoundSetOperations<Object, Object> prepaRedisSet = getRedisSet(selector.getSegmentName());
			if (prepaRedisSet.size() == 0) { // 剩余空间小于SEGMENT_MAX_LEFT_QTY
				managedThreadPool.submit(new NumberGenerateTask(selector));
			}
		} finally {
			redisLock.unlock();
		}

	}

	protected String loopGetNum(SegmentSelector selector) {
		Long start = new Date().getTime();
		while (true) {
			if (getRedisSet(selector.getSegmentName()).size() > selector.getSegmentMaxLeftQty()) {
				return (String) getRedisSet(getSegmet(selector.getCurrentSegment())).pop();
			}
			if (new Date().getTime() - start > 3000) {
				selector.initError();
				throw new BizException(selector.getErrCode().getCode(), "创建订单超时,请稍后再试");
			}
		}
	}

	protected String setAndGetNum(SegmentSelector selector) {
		try {
			prepareSegmentSet(selector);
			return loopGetNum(selector);
		} catch (NumberGeneratorException e) {
			return loopGetNum(selector);
		} catch (BizException e) {
			throw e;
		}
	}

	protected byte[] serializeKey(String key) {
		return keySerializer.serialize(key);
	}

	protected byte[] serializeValue(String value) {
		return valueSerializer.serialize(value);
	}
}
