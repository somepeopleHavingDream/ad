package org.yangxin.ad.service;

import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.request.*;
import org.yangxin.ad.response.*;

/**
 * 推广单元
 *
 * @author yangxin
 * 2020/01/10 15:34
 */
public interface AdUnitService {
    /**
     * 创建推广单元
     */
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    /**
     * 创建推广单元-关键字
     */
    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    /**
     * 创建推广单元-兴趣
     */
    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    /**
     * 创建推广单元-地域
     */
    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;

    /**
     * 创建创意-推广单元
     */
    AdCreativeUnitResponse createCreativeUnit(AdCreativeUnitRequest request) throws AdException;
}
