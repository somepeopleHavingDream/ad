package org.yangxin.ad.service;

import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.request.AdUserRequest;
import org.yangxin.ad.response.AdUserResponse;

/**
 * 用户
 *
 * @author yangxin
 * 2020/01/08 15:51
 */
public interface AdUserService {

    /**
     * 创建用户
     *
     * @param request 请求
     * @return 响应
     * @throws AdException 业务异常
     */
    AdUserResponse createUser(AdUserRequest request) throws AdException;
}
