package org.yangxin.ad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yangxin.ad.entity.Creative;
import org.yangxin.ad.repository.CreativeRepository;
import org.yangxin.ad.request.CreativeRequest;
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
    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {
        if (request == null) {
            return new CreativeResponse();
        }

        Creative creative = creativeRepository.save(request.convertToEntity());
        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
