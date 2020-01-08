package org.yangxin.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yangxin.ad.entity.AdPlan;

import java.util.List;

/**
 * 推广计划
 *
 * @author yangxin
 * 2020/01/08 15:18
 */
public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {
    /**
     * 通过Id和UserId查找
     *
     * @param id id
     * @param userId userId
     * @return 推广计划
     */
    AdPlan findByIdAndUserId(Long id, Long userId);

    /**
     * 通过Id集和用户Id批量查找
     *
     * @param ids ids
     * @param userId userId
     * @return 推广计划集
     */
    List<AdPlan> findAllByIdInAndUserId(List<Long> ids, Long userId);

    /**
     * 通过用户Id和推广计划名称查找
     *
     * @param userId 用户Id
     * @param planName 推广计划名称
     * @return 推广计划
     */
    AdPlan findByUserIdAndPlanName(Long userId, String planName);

    /**
     * 通过推广计划状态查找
     *
     * @param status 状态
     * @return 所有有效的推广计划
     */
    List<AdPlan> findAllByPlanStatus(Integer status);
}
