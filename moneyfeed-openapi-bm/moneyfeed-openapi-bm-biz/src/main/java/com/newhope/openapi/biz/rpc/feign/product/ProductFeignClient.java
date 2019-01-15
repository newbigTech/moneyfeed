package com.newhope.openapi.biz.rpc.feign.product;

import com.newhope.moneyfeed.goods.api.rest.ProductApI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author : tom
 * @project: moneyfeed-openapi-bm
 * @date : 2018/11/29 09:50
 */
@FeignClient(value = "${feign.client.moneyfeed-goods.name}")
public interface ProductFeignClient extends ProductApI {
}
