package org.yangxin.ad.service;

import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.request.CreateUserRequest;
import org.yangxin.ad.response.CreateUserResponse;

/**
 * 用户
 *
 * @author yangxin
 * 2020/01/08 15:51
 */
public interface UserService {
    /**
     * 创建用户
     */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
