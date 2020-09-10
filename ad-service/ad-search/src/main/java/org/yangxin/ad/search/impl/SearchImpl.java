package org.yangxin.ad.search.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.yangxin.ad.index.CommonStatus;
import org.yangxin.ad.index.DataTable;
import org.yangxin.ad.index.adunit.AdUnitIndex;
import org.yangxin.ad.index.adunit.AdUnitObject;
import org.yangxin.ad.index.creative.CreativeIndex;
import org.yangxin.ad.index.creative.CreativeObject;
import org.yangxin.ad.index.creativeunit.CreativeUnitIndex;
import org.yangxin.ad.index.district.UnitDistrictIndex;
import org.yangxin.ad.index.interest.UnitItIndex;
import org.yangxin.ad.index.keyword.UnitKeywordIndex;
import org.yangxin.ad.search.Search;
import org.yangxin.ad.search.vo.SearchRequest;
import org.yangxin.ad.search.vo.SearchResponse;
import org.yangxin.ad.search.vo.feature.DistrictFeature;
import org.yangxin.ad.search.vo.feature.FeatureRelationEnum;
import org.yangxin.ad.search.vo.feature.ITFeature;
import org.yangxin.ad.search.vo.feature.KeywordFeature;
import org.yangxin.ad.search.vo.media.AdSlot;

import java.util.*;

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

        // 三个Feature，三个特征
        SearchRequest.FeatureInfo featureInfo = request.getFeatureInfo();
        // 关键字特征
        KeywordFeature keywordFeature = featureInfo.getKeywordFeature();
        // 地域特征
        DistrictFeature districtFeature = featureInfo.getDistrictFeature();
        // 兴趣标签特征
        ITFeature itFeature = featureInfo.getItFeature();

        // 特征关系
        FeatureRelationEnum relation = featureInfo.getRelation();

        // 构造响应对象
        SearchResponse response = new SearchResponse();
        Map<String, List<SearchResponse.Creative>> adsByAdSlot = response.getAdsByAdSlot();

        // 遍历广告位集合，构建adsByAdSlot对象
        for (AdSlot adSlot : adSlots) {
            Set<Long> targetUnitIdSet;

            // 根据流量类型获取初始AdUnit
            Set<Long> adUnitIdSet = DataTable.of(AdUnitIndex.class).match(adSlot.getPositionType());

            if (relation == FeatureRelationEnum.AND) {
                filterKeywordFeature(adUnitIdSet, keywordFeature);
                filterDistrictFeature(adUnitIdSet, districtFeature);
                filterITTagFeature(adUnitIdSet, itFeature);

                targetUnitIdSet = adUnitIdSet;
            } else {
                targetUnitIdSet = getOrRelationUnitIds(adUnitIdSet, keywordFeature, districtFeature, itFeature);
            }

            List<AdUnitObject> unitObjects = DataTable.of(AdUnitIndex.class)
                    .fetch(targetUnitIdSet);

            filterAdUnitAndPlanStatus(unitObjects, CommonStatus.VALID);

            List<Long> adIds = DataTable.of(CreativeUnitIndex.class)
                    .selectAds(unitObjects);
            List<CreativeObject> creatives = DataTable.of(CreativeIndex.class).fetch(adIds);

            // 通过AdSlot实现对CreativeObject的过滤
            filterCreativeByAdSlot(creatives, adSlot.getWidth(), adSlot.getHeight(), adSlot.getType());
            adsByAdSlot.put(adSlot.getAdSlotCode(), buildCreativeResponse(creatives));
        }

        log.info("fetchAds: [{}]-[{}]", JSON.toJSONString(request), JSON.toJSON(response));

        return response;
    }

    private void filterAdUnitAndPlanStatus(List<AdUnitObject> unitObjects, CommonStatus status) {
        if (CollectionUtils.isEmpty(unitObjects)) {
            return;
        }

        CollectionUtils.filter(unitObjects, object -> Objects.equals(object.getUnitStatus(), status.getStatus())
            && Objects.equals(object.getAdPlanObject().getPlanStatus(), status.getStatus()));
    }

    private Set<Long> getOrRelationUnitIds(Set<Long> adUnitIdSet,
                                           KeywordFeature keywordFeature,
                                           DistrictFeature districtFeature,
                                           ITFeature itFeature) {
        if (CollectionUtils.isEmpty(adUnitIdSet)) {
            return Collections.emptySet();
        }

        Set<Long> keywordUnitIdSet = new HashSet<>(adUnitIdSet);
        Set<Long> districtUnitIdSet = new HashSet<>(adUnitIdSet);
        Set<Long> itUnitIdSet = new HashSet<>(adUnitIdSet);

        filterKeywordFeature(keywordUnitIdSet, keywordFeature);
        filterDistrictFeature(districtUnitIdSet, districtFeature);
        filterITTagFeature(itUnitIdSet, itFeature);

        return new HashSet<>(CollectionUtils.union(CollectionUtils.union(keywordUnitIdSet, districtUnitIdSet), itUnitIdSet));
    }

    private void filterKeywordFeature(Collection<Long> adUnitIds, KeywordFeature keywordFeature) {
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(keywordFeature.getKeywords())) {
            CollectionUtils.filter(adUnitIds, adUnitId -> DataTable.of(UnitKeywordIndex.class)
                    .match(adUnitId, keywordFeature.getKeywords()));
        }
    }

    private void filterDistrictFeature(Collection<Long> adUnitIds, DistrictFeature districtFeature) {
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(districtFeature.getDistricts())) {
            CollectionUtils.filter(adUnitIds, adUnitId -> DataTable.of(UnitDistrictIndex.class)
                    .match(adUnitId, districtFeature.getDistricts()));
        }
    }

    private void filterITTagFeature(Collection<Long> adUnitIds, ITFeature itFeature) {
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(itFeature.getIts())) {
            CollectionUtils.filter(adUnitIds, adUnitId -> DataTable.of(UnitItIndex.class)
                    .match(adUnitId, itFeature.getIts()));
        }
    }

    private void filterCreativeByAdSlot(List<CreativeObject> creatives,
                                        Integer width,
                                        Integer height,
                                        List<Integer> type) {
        if (CollectionUtils.isEmpty(creatives)) {
            return;
        }

        CollectionUtils.filter(creatives, creative -> Objects.equals(creative.getAuditStatus(), CommonStatus.VALID.getStatus())
            && Objects.equals(creative.getWidth(), width)
            && Objects.equals(creative.getHeight(), height)
            && type.contains(creative.getType()));
    }

    private List<SearchResponse.Creative> buildCreativeResponse(List<CreativeObject> creatives) {
        if (CollectionUtils.isEmpty(creatives)) {
            return Collections.emptyList();
        }

        CreativeObject randomObject = creatives.get(Math.abs(new Random().nextInt()) % creatives.size());

        return Collections.singletonList(SearchResponse.convert(randomObject));
    }
}
