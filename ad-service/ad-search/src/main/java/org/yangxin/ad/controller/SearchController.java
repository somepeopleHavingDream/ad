package org.yangxin.ad.controller;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.yangxin.ad.annotation.IgnoreResponseAdvice;
import org.yangxin.ad.client.SponsorClient;
import org.yangxin.ad.request.AdPlanRequest;
import org.yangxin.ad.response.AdPlanResponse;
import org.yangxin.ad.vo.CommonResponseVO;

import java.util.List;

/**
 * 检索
 *
 * @author yangxin
 * 2020/01/14 17:11
 */
@Slf4j
@RestController
public class SearchController {

    private final RestTemplate restTemplate;

    private final SponsorClient sponsorClient;

    @Autowired
    public SearchController(RestTemplate restTemplate, @Qualifier("sponsorClientHystrix") SponsorClient sponsorClient) {
        this.restTemplate = restTemplate;
        this.sponsorClient = sponsorClient;
    }

    /**
     * 通过ribbon获取广告投放计划
     */
    @IgnoreResponseAdvice
    @PostMapping("/adPlan/listByRibbon")
    @SuppressWarnings("all")
    public CommonResponseVO<List<AdPlanResponse>> listAdPlansByRibbon(@RequestBody AdPlanRequest request) {
        log.info("request: [{}]", JSON.toJSONString(request));
        return restTemplate.postForEntity("http://eureka-client-ad-sponsor/ad-sponsor/get/adPlan",
                request, CommonResponseVO.class)
                .getBody();
    }

    /**
     * 通过feign获取广告投放计划
     */
    @IgnoreResponseAdvice
//    @PostMapping(value = "/adPlan/list")
    @PostMapping(value = "/adPlan/list", produces = "application/json;charset=UTF-8")
    public CommonResponseVO<List<AdPlanResponse>> listAdPlans(@RequestBody AdPlanRequest request) {
        log.info("request: [{}]", JSON.toJSONString(request));
        return sponsorClient.listAdPlans(request);
    }
}
