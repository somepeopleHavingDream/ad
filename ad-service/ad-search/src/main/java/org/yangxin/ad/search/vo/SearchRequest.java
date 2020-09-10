package org.yangxin.ad.search.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yangxin.ad.search.vo.feature.DistrictFeature;
import org.yangxin.ad.search.vo.feature.FeatureRelationEnum;
import org.yangxin.ad.search.vo.feature.ITFeature;
import org.yangxin.ad.search.vo.feature.KeywordFeature;
import org.yangxin.ad.search.vo.media.AdSlot;
import org.yangxin.ad.search.vo.media.App;
import org.yangxin.ad.search.vo.media.Device;
import org.yangxin.ad.search.vo.media.Geo;

import java.util.List;

/**
 * 对广告的搜索请求
 *
 * @author yangxin
 * 2020/08/19 16:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    /**
     * 媒体方的请求标识
     */
    private String mediaId;

    /**
     * 请求基本信息
     */
    private RequestInfo requestInfo;

    /**
     * 匹配信息
     */
    private FeatureInfo featureInfo;

    /**
     * 请求基本信息
     *
     * @author yangxin
     * 2020/08/19 16:02
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestInfo {

        /**
         * 请求Id
         */
        private String requestId;

        /**
         * 槽
         */
        private List<AdSlot> adSlotList;

        /**
         * App
         */
        private App app;

        /**
         * Geo
         */
        private Geo geo;

        /**
         * 设备
         */
        private Device device;
    }

    /**
     * 匹配信息
     *
     * @author yangxin
     * 2020/08/19 16:03
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FeatureInfo {

        /**
         * 关键词特征
         */
        private KeywordFeature keywordFeature;

        /**
         * 地域特征
         */
        private DistrictFeature districtFeature;

        /**
         * 标签特征
         */
        private ITFeature itFeature;

        /**
         * 特征关系
         */
        private FeatureRelationEnum relation = FeatureRelationEnum.AND;
    }
}
