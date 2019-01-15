package com.newhope.openapi.biz.rpc.feign.feedback;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.newhope.moneyfeed.feedback.api.rest.FeedbacksApi;

@FeignClient(value = "${feign.client.moneyfeed-feedback.name}")
public interface FeedbackFeignClient extends FeedbacksApi {

}
