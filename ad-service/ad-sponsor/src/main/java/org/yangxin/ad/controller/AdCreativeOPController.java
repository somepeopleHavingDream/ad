package org.yangxin.ad.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.ad.request.AdCreativeRequest;
import org.yangxin.ad.response.AdCreativeResponse;
import org.yangxin.ad.service.AdCreativeService;

/**
 * 创意
 *
 * @author yangxin
 * 2020/01/13 16:08
 */
@Slf4j
@RestController
public class AdCreativeOPController {
    private final AdCreativeService adCreativeService;

    @Autowired
    public AdCreativeOPController(AdCreativeService adCreativeService) {
        this.adCreativeService = adCreativeService;
    }

    /**
     * 创建创意
     */
    @PostMapping("/create/creative")
    public AdCreativeResponse createCreative(@RequestBody AdCreativeRequest request) {
        log.info("ad-sponsor: request: [{}]", JSON.toJSONString(request));
        return adCreativeService.createCreative(request);
    }
}
