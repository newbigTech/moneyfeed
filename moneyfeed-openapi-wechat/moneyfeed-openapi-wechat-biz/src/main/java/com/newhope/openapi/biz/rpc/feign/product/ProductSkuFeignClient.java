package com.newhope.openapi.biz.rpc.feign.product;

import com.newhope.moneyfeed.goods.api.rest.ProductSkuAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/11/22 15:11
 */
@FeignClient(value = "${feign.client.moneyfeed-goods.name}")
public interface ProductSkuFeignClient extends ProductSkuAPI {
}
