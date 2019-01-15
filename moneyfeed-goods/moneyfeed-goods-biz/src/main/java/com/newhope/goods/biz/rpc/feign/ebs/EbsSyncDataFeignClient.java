package com.newhope.goods.biz.rpc.feign.ebs;

import com.newhope.moneyfeed.integration.api.rest.ebs.EbsSyncDataAPI;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/12/5 15:02
 */
@FeignClient(value = "${feign.client.moneyfeed-integration.name}")
public interface EbsSyncDataFeignClient extends EbsSyncDataAPI {
}
