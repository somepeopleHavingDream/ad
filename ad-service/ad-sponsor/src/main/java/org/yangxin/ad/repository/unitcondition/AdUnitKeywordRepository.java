package org.yangxin.ad.repository.unitcondition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yangxin.ad.entity.unitcondition.AdUnitKeyword;

/**
 * 推广单元-关键字
 *
 * @author yangxin
 * 2020/01/08 15:43
 */
public interface AdUnitKeywordRepository extends JpaRepository<AdUnitKeyword, Long> {
}
