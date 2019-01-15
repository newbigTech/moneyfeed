package com.newhope.order.biz.rpc.feign.pc;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.goods.api.rest.ProductApI;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/11/19 18:51
 */
@FeignClient(value = "${feign.client.moneyfeed-goods.name}")
public interface ProductFeignClient extends ProductApI {
}
