package org.yangxin.ad.service;

import org.yangxin.ad.request.CreativeRequest;
import org.yangxin.ad.response.CreativeResponse;

/**
 * 创意
 *
 * @author yangxin
 * 2020/01/10 17:43
 */
public interface CreativeService {
    /**
     * 创建创意
     */
    CreativeResponse createCreative(CreativeRequest request);
}
