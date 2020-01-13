package org.yangxin.ad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yangxin.ad.entity.Creative;
import org.yangxin.ad.repository.AdCreativeRepository;
import org.yangxin.ad.request.AdCreativeRequest;
import org.yangxin.ad.response.CreativeResponse;
import org.yangxin.ad.service.CreativeService;

/**
 * 创意
 *
 * @author yangxin
 * 2020/01/10 17:55
 */
@Service
public class CreativeServiceImpl implements CreativeService {
    private final AdCreativeRepository adCreativeRepository;

    @Autowired
    public CreativeServiceImpl(AdCreativeRepository adCreativeRepository) {
        this.adCreativeRepository = adCreativeRepository;
    }

    @Override
    public CreativeResponse createCreative(AdCreativeRequest request) {
        if (request == null) {
            return new CreativeResponse();
        }

        Creative creative = adCreativeRepository.save(request.convertToEntity());
        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
