package org.yangxin.ad.service;

import org.yangxin.ad.request.AdCreativeRequest;
import org.yangxin.ad.response.AdCreativeResponse;

/**
 * 创意
 *
 * @author yangxin
 * 2020/01/10 17:43
 */
public interface AdCreativeService {
    /**
     * 创建创意
     */
    AdCreativeResponse createCreative(AdCreativeRequest request);
}
