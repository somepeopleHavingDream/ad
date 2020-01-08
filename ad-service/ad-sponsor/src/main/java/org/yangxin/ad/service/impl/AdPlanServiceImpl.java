package org.yangxin.ad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.ad.constant.CommonStatusEnum;
import org.yangxin.ad.constant.Constants;
import org.yangxin.ad.entity.AdPlan;
import org.yangxin.ad.entity.AdUser;
import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.repository.AdPlanRepository;
import org.yangxin.ad.repository.AdUserRepository;
import org.yangxin.ad.request.AdPlanRequest;
import org.yangxin.ad.response.AdPlanResponse;
import org.yangxin.ad.service.AdPlanService;
import org.yangxin.ad.utils.CommonUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 推广计划
 *
 * @author yangxin
 * 2020/01/08 17:15
 */
@Service
public class AdPlanServiceImpl implements AdPlanService {
    private final AdUserRepository userRepository;
    private final AdPlanRepository planRepository;

    @Autowired
    public AdPlanServiceImpl(AdUserRepository userRepository, AdPlanRepository planRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        // 校验参数
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        // 确保关联的User是存在的
        Optional<AdUser> adUserOptional = userRepository.findById(request.getUserId());
        if (!adUserOptional.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CANT_FIND_RECORD);
        }

        // 校验数据库中是否存在同用户同名的推广计划
        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(request.getUserId(), request.getPlanName());
        if (oldPlan != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }

        // 数据库操作
        AdPlan newPlan = planRepository.save(new AdPlan(request.getUserId(),
                request.getPlanName(),
                CommonUtils.parseStringDate(request.getStartDate()),
                CommonUtils.parseStringDate(request.getEndDate())));

        return new AdPlanResponse(newPlan.getId(), newPlan.getPlanName());
    }

    @Override
    public List<AdPlan> listAdPlanByIds(AdPlanRequest request) throws AdException {
        // 参数校验
        if (!request.listValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        return planRepository.findAllByIdInAndUserId(request.getIds(), request.getUserId());
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        // 参数校验
        if (!request.updateValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        // 先查
        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CANT_FIND_RECORD);
        }

        // 推广计划名称
        String planName = request.getPlanName();
        if (planName != null) {
            plan.setPlanName(planName);
        }
        // 起始时间
        String startDate = request.getStartDate();
        if (startDate != null) {
            plan.setStartDate(CommonUtils.parseStringDate(startDate));
        }
        // 终止时间
        String endDate = request.getEndDate();
        if (endDate != null) {
            plan.setEndDate(CommonUtils.parseStringDate(endDate));
        }
        // 更新时间
        plan.setUpdateTime(new Date());

        // 数据库操作
        plan = planRepository.save(plan);

        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        // 删除参数校验
        if (!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        // 先查
        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CANT_FIND_RECORD);
        }

        // 数据库操作
        plan.setPlanStatus(CommonStatusEnum.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        planRepository.save(plan);
    }
}
