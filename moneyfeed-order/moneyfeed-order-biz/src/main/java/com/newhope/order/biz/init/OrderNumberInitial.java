package com.newhope.order.biz.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.newhope.order.biz.constant.OrderCacheConstant;
import com.newhope.order.biz.hepler.IdGeneraterHelper;
import com.newhope.order.biz.hepler.NumberGeneraterHelper;
import com.newhope.order.biz.hepler.selector.OrderSegmentSelector;
import com.newhope.order.biz.hepler.selector.SegmentSelector;

/**
 * 初始化订单号数据
 *
 */
@Component
public class OrderNumberInitial implements CommandLineRunner {
	@Autowired
	NumberGeneraterHelper numberGeneraterHelper;
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;
	
	@Override
	public void run(String... args) throws Exception {

		String currentSegment = (String) redisTemplate.boundValueOps(OrderCacheConstant.CURRENT_ORDERNO_SEGMENT).get();
		BoundSetOperations<Object, Object> segment1 = redisTemplate.boundSetOps(OrderCacheConstant.ORDERNO_SEGMENT1);
		BoundSetOperations<Object, Object> segment2 = redisTemplate.boundSetOps(OrderCacheConstant.ORDERNO_SEGMENT2);
		if(segment1.size() == 0 && segment2.size() == 0){
			currentSegment = OrderCacheConstant.ORDERNO_SEGMENT1;
			SegmentSelector selector = new OrderSegmentSelector();
			selector.setSegmentName(currentSegment);
			selector.setSegmentMaxLeftQty(IdGeneraterHelper.ORDER_SEGMENT_MAX_LEFT_QTY);
			selector.setSegmentMaxQty(IdGeneraterHelper.ORDER_SEGMENT_MAX_QTY);
			numberGeneraterHelper.generateNumberSegment(selector);
		}else{
			if(segment1.size() == 0){
				currentSegment = OrderCacheConstant.ORDERNO_SEGMENT2;
			}
			if(segment2.size() == 0){
				currentSegment = OrderCacheConstant.ORDERNO_SEGMENT1;
			}
		}
		redisTemplate.boundValueOps(OrderCacheConstant.CURRENT_ORDERNO_SEGMENT).set(currentSegment);
	}

}
