package org.yangxin.ad.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.request.AdUserRequest;
import org.yangxin.ad.response.AdUserResponse;
import org.yangxin.ad.service.AdUserService;

/**
 * 用户
 *
 * @author yangxin
 * 2020/01/13 15:33
 */
@Slf4j
@RestController
public class AdUserOPController {

    private final AdUserService adUserService;

    @Autowired
    public AdUserOPController(AdUserService adUserService) {
        this.adUserService = adUserService;
    }

    /**
     * 创建用户
     */
    @PostMapping("/create/user")
    public AdUserResponse createUser(@RequestBody AdUserRequest request) throws AdException {
        log.info("ad-sponsor: request: [{}]", JSON.toJSONString(request));
        return adUserService.createUser(request);
    }
}
