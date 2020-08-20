package org.yangxin.ad.search.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yangxin.ad.index.DataTable;
import org.yangxin.ad.index.adunit.AdUnitIndex;
import org.yangxin.ad.search.Search;
import org.yangxin.ad.search.vo.SearchRequest;
import org.yangxin.ad.search.vo.SearchResponse;
import org.yangxin.ad.search.vo.feature.DistrictFeature;
import org.yangxin.ad.search.vo.feature.FeatureRelationEnum;
import org.yangxin.ad.search.vo.feature.ITFeature;
import org.yangxin.ad.search.vo.feature.KeywordFeature;
import org.yangxin.ad.search.vo.media.AdSlot;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yangxin
 * 2020/08/20 10:32
 */
@Slf4j
@Service
public class SearchImpl implements Search {

    @Override
    public SearchResponse fetchAds(SearchRequest request) {
        // 请求的广告位信息
        List<AdSlot> adSlots = request.getRequestInfo().getAdSlotList();

        // 三个Feature
        SearchRequest.FeatureInfo featureInfo = request.getFeatureInfo();
        KeywordFeature keywordFeature = featureInfo.getKeywordFeature();
        DistrictFeature districtFeature = featureInfo.getDistrictFeature();
        ITFeature itFeature = featureInfo.getItFeature();

        FeatureRelationEnum relation = featureInfo.getRelation();

        // 构造响应对象
        SearchResponse response = new SearchResponse();
        Map<String, List<SearchResponse.Creative>> adSlot2Ads = response.getAdSlot2Ads();

        for (AdSlot adSlot : adSlots) {
            Set<Long> targetUnitIdSet;

            // 根据流量类型获取初始AdUnit
            Set<Long> adUnitIdSet = DataTable.of(AdUnitIndex.class).match(adSlot.getPositionType());
        }

        return null;
    }
}
