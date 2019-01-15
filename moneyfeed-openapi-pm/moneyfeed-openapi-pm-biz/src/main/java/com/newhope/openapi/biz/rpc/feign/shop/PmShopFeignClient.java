package com.newhope.openapi.biz.rpc.feign.shop;

import com.newhope.moneyfeed.user.api.rest.platform.PmShopAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Create by yyq on 2018/12/25
 */
@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface PmShopFeignClient extends PmShopAPI {
}
