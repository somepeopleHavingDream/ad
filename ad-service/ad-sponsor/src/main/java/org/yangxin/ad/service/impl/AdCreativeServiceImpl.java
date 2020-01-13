package org.yangxin.ad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yangxin.ad.entity.Creative;
import org.yangxin.ad.repository.AdCreativeRepository;
import org.yangxin.ad.request.AdCreativeRequest;
import org.yangxin.ad.response.AdCreativeResponse;
import org.yangxin.ad.service.AdCreativeService;

/**
 * 创意
 *
 * @author yangxin
 * 2020/01/10 17:55
 */
@Service
public class AdCreativeServiceImpl implements AdCreativeService {
    private final AdCreativeRepository adCreativeRepository;

    @Autowired
    public AdCreativeServiceImpl(AdCreativeRepository adCreativeRepository) {
        this.adCreativeRepository = adCreativeRepository;
    }

    @Override
    public AdCreativeResponse createCreative(AdCreativeRequest request) {
        if (request == null) {
            return new AdCreativeResponse();
        }

        Creative creative = adCreativeRepository.save(request.convertToEntity());
        return new AdCreativeResponse(creative.getId(), creative.getName());
    }
}
