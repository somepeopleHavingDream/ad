package org.yangxin.ad.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.ad.constant.Constants;
import org.yangxin.ad.entity.AdUser;
import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.repository.AdUserRepository;
import org.yangxin.ad.request.CreateUserRequest;
import org.yangxin.ad.response.CreateUserResponse;
import org.yangxin.ad.service.UserService;

/**
 * 用户
 *
 * @author yangxin
 * 2020/01/08 16:04
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final AdUserRepository userRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        // 校验
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        // 查询数据库中是否有相同名称的用户
        AdUser oldUser = userRepository.findByUsername(request.getUsername());
        if (oldUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        // 数据库操作
        AdUser newUser = userRepository.save(new AdUser(request.getUsername(), ""));
        return new CreateUserResponse(newUser.getId(),
                newUser.getUsername(),
                newUser.getToken(),
                newUser.getCreateTime(),
                newUser.getUpdateTime());
    }
}
