package org.yangxin.ad.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yangxin.ad.entity.AdPlan;
import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.request.AdPlanRequest;
import org.yangxin.ad.response.AdPlanResponse;
import org.yangxin.ad.service.AdPlanService;

import java.util.List;

/**
 * 推广计划
 *
 * @author yangxin
 * 2020/01/13 15:39
 */
@Slf4j
@RestController
public class AdPlanOPController {

    private final AdPlanService adPlanService;

    @Autowired
    public AdPlanOPController(AdPlanService adPlanService) {
        this.adPlanService = adPlanService;
    }

    /**
     * 创建推广计划
     */
    @PostMapping("/create/adPlan")
    public AdPlanResponse createAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: request: [{}]", request);
        return adPlanService.createAdPlan(request);
    }

    /**
     * 根据Ids获取推广计划
     */
    @PostMapping(value = "/adPlan/list")
//    @PostMapping(value = "/adPlan/list", produces = "application/json;charset=UTF-8")
    public List<AdPlan> listAdPlanByIds(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor request: [{}]", request);

        return adPlanService.listAdPlanByIds(request);
    }

    /**
     * 更新推广计划
     */
    @PutMapping("/adPlan/update")
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: request: [{}]", request);
        return adPlanService.updateAdPlan(request);
    }

    /**
     * 删除推广计划
     */
    @DeleteMapping("/delete/adPlan")
    public void deleteAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: request: [{}]", request);
        adPlanService.deleteAdPlan(request);
    }
}
