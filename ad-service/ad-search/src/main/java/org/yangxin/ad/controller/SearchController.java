package org.yangxin.ad.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.ad.annotation.IgnoreResponseAdvice;
import org.yangxin.ad.client.SponsorClient;
import org.yangxin.ad.request.AdPlanRequest;
import org.yangxin.ad.response.AdPlanResponse;
import org.yangxin.ad.search.Search;
import org.yangxin.ad.search.vo.SearchRequest;
import org.yangxin.ad.search.vo.SearchResponse;
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

    private final SponsorClient sponsorClient;
    private final Search search;

    @Autowired
    public SearchController(@Qualifier("sponsorClientHystrix") SponsorClient sponsorClient, Search search) {
        this.sponsorClient = sponsorClient;
        this.search = search;
    }

    /**
     * 通过feign获取广告投放计划
     */
    @IgnoreResponseAdvice
    @PostMapping("/adPlan/list")
    public CommonResponseVO<List<AdPlanResponse>> listAdPlans(@RequestBody AdPlanRequest request) {
        log.info("ad-search request: [{}]", JSON.toJSONString(request));

        return sponsorClient.listAdPlans(request);
    }

    @PostMapping("/fetchAds")
    public SearchResponse fetchAds(@RequestBody SearchRequest request) {
        log.info("ad-search: fetchAds -> [{}]", JSON.toJSONString(request));
        return search.fetchAds(request);
    }
}