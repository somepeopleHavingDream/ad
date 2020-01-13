package org.yangxin.ad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.yangxin.ad.constant.Constants;
import org.yangxin.ad.entity.AdPlan;
import org.yangxin.ad.entity.AdUnit;
import org.yangxin.ad.entity.unitcondition.AdUnitDistrict;
import org.yangxin.ad.entity.unitcondition.AdUnitIt;
import org.yangxin.ad.entity.unitcondition.AdUnitKeyword;
import org.yangxin.ad.entity.unitcondition.AdCreativeUnit;
import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.repository.AdCreativeRepository;
import org.yangxin.ad.repository.AdPlanRepository;
import org.yangxin.ad.repository.AdUnitRepository;
import org.yangxin.ad.repository.unitcondition.AdUnitDistrictRepository;
import org.yangxin.ad.repository.unitcondition.AdUnitItRepository;
import org.yangxin.ad.repository.unitcondition.AdUnitKeywordRepository;
import org.yangxin.ad.repository.unitcondition.AdCreativeUnitRepository;
import org.yangxin.ad.request.*;
import org.yangxin.ad.response.*;
import org.yangxin.ad.service.AdUnitService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 推广计划
 *
 * @author yangxin
 * 2020/01/10 15:44
 */
@Service
public class AdUnitServiceImpl implements AdUnitService {
    private final AdPlanRepository planRepository;
    private final AdUnitRepository unitRepository;
    private final AdCreativeRepository creativeRepository;
    private final AdCreativeUnitRepository creativeUnitRepository;

    private final AdUnitKeywordRepository unitKeywordRepository;
    private final AdUnitItRepository unitItRepository;
    private final AdUnitDistrictRepository unitDistrictRepository;

    @Autowired
    public AdUnitServiceImpl(AdPlanRepository planRepository, AdUnitRepository unitRepository, AdCreativeRepository creativeRepository, AdCreativeUnitRepository CreativeUnitRepository, AdUnitKeywordRepository unitKeywordRepository, AdUnitItRepository unitItRepository, AdUnitDistrictRepository unitDistrictRepository) {
        this.planRepository = planRepository;
        this.unitRepository = unitRepository;
        this.creativeRepository = creativeRepository;
        this.creativeUnitRepository = CreativeUnitRepository;
        this.unitKeywordRepository = unitKeywordRepository;
        this.unitItRepository = unitItRepository;
        this.unitDistrictRepository = unitDistrictRepository;
    }

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdPlan> adPlanOptional = planRepository.findById(request.getPlanId());
        if (!adPlanOptional.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CANT_FIND_RECORD);
        }

        AdUnit oldAdUnit = unitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if (oldAdUnit != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }

        AdUnit newAdUnit = unitRepository.save(new AdUnit(request.getPlanId(),
                request.getUnitName(),
                request.getPositionType(),
                request.getBudget()));
        return new AdUnitResponse(newAdUnit.getId(), newAdUnit.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        // 如果为空的就不执行
        if (request == null || CollectionUtils.isEmpty(request.getUnitKeywordRequests())) {
            return new AdUnitKeywordResponse(Collections.emptyList());
        }

        // 获得请求的推广单元Id并校验
        List<AdUnitKeywordRequest.UnitKeywordRequest> unitKeywordRequestList = request.getUnitKeywordRequests();
        List<Long> unitIdList = unitKeywordRequestList.stream()
                .map(AdUnitKeywordRequest.UnitKeywordRequest::getUnitId)
                .collect(Collectors.toList());
        if (isRelatedUnitNotExist(unitIdList)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        // 构建推广单元-关键字对象
        List<AdUnitKeyword> adUnitKeywordList = new ArrayList<>();
        for (AdUnitKeywordRequest.UnitKeywordRequest unitKeywordRequest : unitKeywordRequestList) {
            adUnitKeywordList.add(new AdUnitKeyword(unitKeywordRequest.getUnitId(), unitKeywordRequest.getKeyword()));
        }

        // 数据库操作，并返回保存的推广单元-关键字id
        List<Long> idList = unitKeywordRepository.saveAll(adUnitKeywordList).stream()
                .map(AdUnitKeyword::getId)
                .collect(Collectors.toList());
        return new AdUnitKeywordResponse(idList);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        if (request == null || CollectionUtils.isEmpty(request.getUnitItRequests())) {
            return new AdUnitItResponse(Collections.emptyList());
        }

        List<AdUnitItRequest.UnitItRequest> unitItRequestList = request.getUnitItRequests();
        List<Long> unitIdList = unitItRequestList.stream()
                .map(AdUnitItRequest.UnitItRequest::getUnitId)
                .collect(Collectors.toList());
        if (isRelatedUnitNotExist(unitIdList)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitIt> adUnitItList = new ArrayList<>();
        for (AdUnitItRequest.UnitItRequest unitItRequest : unitItRequestList) {
            adUnitItList.add(new AdUnitIt(unitItRequest.getUnitId(), unitItRequest.getItTag()));
        }

        List<Long> idList = unitItRepository.saveAll(adUnitItList).stream()
                .map(AdUnitIt::getId)
                .collect(Collectors.toList());
        return new AdUnitItResponse(idList);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        if (request == null || CollectionUtils.isEmpty(request.getUnitDistrictRequests())) {
            return new AdUnitDistrictResponse(Collections.emptyList());
        }

        List<AdUnitDistrictRequest.UnitDistrictRequest> unitDistrictRequestList = request.getUnitDistrictRequests();
        List<Long> unitIdList = request.getUnitDistrictRequests().stream()
                .map(AdUnitDistrictRequest.UnitDistrictRequest::getUnitId)
                .collect(Collectors.toList());
        if (isRelatedUnitNotExist(unitIdList)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitDistrict> adUnitDistrictList = new ArrayList<>();
        for (AdUnitDistrictRequest.UnitDistrictRequest unitDistrictRequest : unitDistrictRequestList) {
            adUnitDistrictList.add(new AdUnitDistrict(unitDistrictRequest.getUnitId(),
                    unitDistrictRequest.getProvince(),
                    unitDistrictRequest.getCity()));
        }

        List<Long> idList = unitDistrictRepository.saveAll(adUnitDistrictList).stream()
                .map(AdUnitDistrict::getId)
                .collect(Collectors.toList());
        return new AdUnitDistrictResponse(idList);
    }

    @Override
    public AdCreativeUnitResponse createCreativeUnit(AdCreativeUnitRequest request) throws AdException {
        List<Long> unitIdList = request.getUnitItems().stream()
                .map(AdCreativeUnitRequest.CreativeUnitItemRequest::getUnitId)
                .collect(Collectors.toList());
        List<Long> creativeIdList = request.getUnitItems().stream()
                .map(AdCreativeUnitRequest.CreativeUnitItemRequest::getCreativeId)
                .collect(Collectors.toList());

        if (!(isRelatedUnitNotExist(unitIdList) && isRelatedUnitNotExist(creativeIdList))) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdCreativeUnit> creativeUnitList = new ArrayList<>();
        for (AdCreativeUnitRequest.CreativeUnitItemRequest creativeUnitItemRequest : request.getUnitItems()) {
            creativeUnitList.add(new AdCreativeUnit(creativeUnitItemRequest.getCreativeId(),
                    creativeUnitItemRequest.getUnitId()));
        }

        List<Long> ids = creativeUnitRepository.saveAll(creativeUnitList).stream()
                .map(AdCreativeUnit::getId)
                .collect(Collectors.toList());
        return new AdCreativeUnitResponse(ids);
    }

    /**
     * 是否存在相关的推广单元
     */
    private boolean isRelatedUnitNotExist(List<Long> unitIds) {
        if (CollectionUtils.isEmpty(unitIds)) {
            return true;
        }

        return unitRepository.findAllById(unitIds).size() != new HashSet<>(unitIds).size();
    }

    /**
     * 是否存在相关的创意
     */
    private boolean isRelatedCreativeExist(List<Long> creativeIds) {
        if (CollectionUtils.isEmpty(creativeIds)) {
            return false;
        }

        return creativeRepository.findAllById(creativeIds).size() == new HashSet<>(creativeIds).size();
    }
}
