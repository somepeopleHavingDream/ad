package org.yangxin.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yangxin.ad.entity.Creative;

/**
 * 创意
 *
 * @author yangxin
 * 2020/01/08 15:31
 */
public interface AdCreativeRepository extends JpaRepository<Creative, Long> {
}
