package org.yangxin.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yangxin.ad.entity.AdUnit;

import java.util.List;

/**
 * 推广单元
 *
 * @author yangxin
 * 2020/01/08 15:30
 */
public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {
    /**
     * 通过推广计划Id和推广单元名称查找
     *
     * @param planId 推广计划Id
     * @param unitName 推广单元名称
     * @return 推广单元
     */
    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);

    /**
     * 通过推广单元状态查找
     *
     * @param unitStatus 推广单元状态
     * @return 所有有效的推广单元
     */
    List<AdUnit> findAllByUnitStatus(Integer unitStatus);
}
