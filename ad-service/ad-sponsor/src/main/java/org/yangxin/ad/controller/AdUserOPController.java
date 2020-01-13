package org.yangxin.ad.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.request.AdUserRequest;
import org.yangxin.ad.response.AdCreativeUnitResponse;
import org.yangxin.ad.response.AdUserResponse;
import org.yangxin.ad.service.UserService;

/**
 * 用户
 *
 * @author yangxin
 * 2020/01/13 15:33
 */
@Slf4j
@RestController
public class AdUserOPController {
    private final UserService userService;

    @Autowired
    public AdUserOPController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 创建用户
     */
    @PostMapping("/create/user")
    public AdUserResponse createUser(@RequestBody AdUserRequest request) throws AdException {
        log.info("ad-sponsor: request: [{}]", JSON.toJSONString(request));
        return userService.createUser(request);
    }
}
