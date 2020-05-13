package org.yangxin.ad.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.yangxin.ad.request.AdPlanRequest;
import org.yangxin.ad.response.AdPlanResponse;
import org.yangxin.ad.vo.CommonResponseVO;

import java.util.List;

/**
 * 赞助商
 *
 * @author yangxin
 * 2020/04/09 20:18
 */
//@FeignClient(value = "eureka-client-ad-sponsor")
@FeignClient(value = "eureka-client-ad-sponsor", fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    /**
     * 获取广告计划
     */
//    @PostMapping("/ad/ad-sponsor/adPlan/list")
    @PostMapping("/ad-sponsor/adPlan/list")
//    @PostMapping(value = "/ad-sponsor/adPlan/list", produces = "application/json;charset=UTF-8")
    CommonResponseVO<List<AdPlanResponse>> listAdPlans(@RequestBody AdPlanRequest request);
}
