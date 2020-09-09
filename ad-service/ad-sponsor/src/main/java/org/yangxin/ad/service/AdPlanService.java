package org.yangxin.ad.service;

import org.yangxin.ad.entity.AdPlan;
import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.request.AdPlanRequest;
import org.yangxin.ad.response.AdPlanResponse;

import java.util.List;

/**
 * 推广计划
 *
 * @author yangxin
 * 2020/01/08 16:58
 */
public interface AdPlanService {

    /**
     * 创建推广计划
     */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 获取推广计划
     */
    List<AdPlan> listAdPlanByIds(AdPlanRequest request) throws AdException;

    /**
     * 更新推广计划
     */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 删除推广计划
     */
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
