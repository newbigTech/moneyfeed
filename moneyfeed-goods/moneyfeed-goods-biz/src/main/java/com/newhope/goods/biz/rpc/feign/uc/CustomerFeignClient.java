package com.newhope.goods.biz.rpc.feign.uc;

import com.newhope.moneyfeed.user.api.rest.client.CustomerAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 11:37
 */
@FeignClient(value = "${feign.client.moneyfeed-user.name}")
public interface CustomerFeignClient  extends CustomerAPI {
}
