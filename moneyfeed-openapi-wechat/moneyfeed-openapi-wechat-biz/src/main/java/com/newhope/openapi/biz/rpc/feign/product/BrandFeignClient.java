package com.newhope.openapi.biz.rpc.feign.product;

import com.newhope.moneyfeed.goods.api.rest.BrandAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author : tom
 * @project:  moneyfeed-openapi-wechat
 * @date : 2018/12/24 09:01
 */
@FeignClient(value = "${feign.client.moneyfeed-goods.name}")
public interface BrandFeignClient extends BrandAPI {
}
