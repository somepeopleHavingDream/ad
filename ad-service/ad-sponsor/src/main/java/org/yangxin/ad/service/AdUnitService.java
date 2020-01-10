package org.yangxin.ad.service;

import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.request.AdUnitRequest;
import org.yangxin.ad.response.AdUnitResponse;

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
}
