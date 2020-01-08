package org.yangxin.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yangxin.ad.entity.AdUser;

/**
 * 用户
 *
 * @author yangxin
 * 2020/01/08 15:14
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {
    /**
     * 通过用户名查找
     *
     * @param username 用户名
     * @return 用户
     */
    AdUser findByUsername(String username);
}
