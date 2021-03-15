package org.yangxin.ad.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.request.*;
import org.yangxin.ad.response.*;
import org.yangxin.ad.service.AdUnitService;

/**
 * 推广单元
 *
 * @author yangxin
 * 2020/01/13 15:52
 */
@Slf4j
@RestController
public class AdUnitOpController {

    private final AdUnitService adUnitService;

    @Autowired
    public AdUnitOpController(AdUnitService adUnitService) {
        this.adUnitService = adUnitService;
    }

    /**
     * 创建推广单元
     */
    @PutMapping("/create/adUnit")
    public AdUnitResponse createUnit(@RequestBody AdUnitRequest request) throws AdException {
        log.info("ad-sponsor: [{}]", JSON.toJSONString(request));
        return adUnitService.createUnit(request);
    }

    /**
     * 创建推广单元-关键字
     */
    @PutMapping("/create/unitKeyword")
    public AdUnitKeywordResponse createUnitKeyword(@RequestBody AdUnitKeywordRequest request) throws AdException {
        log.info("ad-sponsor: request: [{}]", request);
        return adUnitService.createUnitKeyword(request);
    }

    /**
     * 创建推广单元-兴趣
     */
    @PutMapping("/create/unitIt")
    public AdUnitItResponse createUnitIt(@RequestBody AdUnitItRequest request) throws AdException {
        log.info("ad-sponsor: request: [{}]", JSON.toJSONString(request));
        return adUnitService.createUnitIt(request);
    }

    /**
     * 创建推广单元-地域限制
     */
    @PutMapping("/create/unitDistrict")
    public AdUnitDistrictResponse createUnitDistrict(@RequestBody AdUnitDistrictRequest request) throws AdException {
        log.info("ad-sponsor: request: [{}]", JSON.toJSONString(request));
        return adUnitService.createUnitDistrict(request);
    }

    /**
     * 创建创意单元
     */
    @PostMapping("/create/creativeUnit")
    public AdCreativeUnitResponse createCreativeUnit(@RequestBody AdCreativeUnitRequest request) throws AdException {
        log.info("ad-sponsor: request: [{}]", JSON.toJSONString(request));
        return adUnitService.createCreativeUnit(request);
    }
}
