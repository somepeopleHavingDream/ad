package org.yangxin.ad.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.yangxin.ad.annotation.IgnoreResponseAdvice;
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

    @Autowired
    public SearchController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @IgnoreResponseAdvice
    @PostMapping("/getAdPlanByRibbon")
    @SuppressWarnings("all")
    public CommonResponseVO<List<AdPlanResponse>> listAdPlansByRibbon(@RequestBody AdPlanRequest request) {
        log.info("request: [{}]", JSON.toJSONString(request));
        return restTemplate.postForEntity("http://eureka-client-ad-sponsor/ad-sponsor/get/adPlan",
                request, CommonResponseVO.class)
                .getBody();
    }
}
