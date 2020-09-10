package org.yangxin.ad.search.impl;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yangxin.ad.search.Search;
import org.yangxin.ad.search.vo.SearchRequest;
import org.yangxin.ad.search.vo.feature.DistrictFeature;
import org.yangxin.ad.search.vo.feature.FeatureRelationEnum;
import org.yangxin.ad.search.vo.feature.ITFeature;
import org.yangxin.ad.search.vo.feature.KeywordFeature;
import org.yangxin.ad.search.vo.media.AdSlot;
import org.yangxin.ad.search.vo.media.App;
import org.yangxin.ad.search.vo.media.Device;
import org.yangxin.ad.search.vo.media.Geo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yangxin
 * 2020/09/09 17:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchImplTest {

    @Autowired
    private Search search;

    @Test
    public void fetchAds() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setMediaId("imooc-ad");

        // 第一个测试条件
        // 设置请求的基本信息
        searchRequest.setRequestInfo(new SearchRequest.RequestInfo("aaa",
                Collections.singletonList(new AdSlot("ad-x",
                        1,
                        1080,
                        720,
                        Arrays.asList(1, 2),
                        1000)),
                buildExampleApp(),
                buildExampleGeo(),
                buildExampleDevice()));
        // 设置请求的匹配信息
        searchRequest.setFeatureInfo(buildExampleFeatureInfo(Arrays.asList("宝马", "大众"),
                Collections.singletonList(new DistrictFeature.ProvinceAndCity("安徽省", "合肥市")),
                Arrays.asList("台球", "游泳"),
                FeatureRelationEnum.OR));
        System.out.println(JSON.toJSONString(searchRequest));
        System.out.println(JSON.toJSONString(search.fetchAds(searchRequest)));

        // 第二个测试条件
        searchRequest.setRequestInfo(new SearchRequest.RequestInfo("aaa",
                Collections.singletonList(new AdSlot("ad-y", 1, 1080, 720, Arrays.asList(1, 2), 1000)),
                buildExampleApp(),
                buildExampleGeo(),
                buildExampleDevice()));
        searchRequest.setFeatureInfo(buildExampleFeatureInfo(Arrays.asList("宝马", "大众", "标志"),
                Collections.singletonList(new DistrictFeature.ProvinceAndCity("安徽省", "合肥市")),
                Arrays.asList("台球", "游泳"),
                FeatureRelationEnum.AND));
        System.out.println(JSON.toJSONString(searchRequest));
        System.out.println(JSON.toJSONString(search.fetchAds(searchRequest)));
    }

    /**
     * 构建样例App对象
     */
    private App buildExampleApp() {
        return new App("imooc", "imooc", "com.imooc", "video");
    }

    /**
     * 构建样例Geo对象
     */
    private Geo buildExampleGeo() {
        return new Geo((float) 100.28, (float) 88.61, "北京市", "北京市");
    }

    /**
     * 构建Device对象
     */
    private Device buildExampleDevice() {
        return new Device("iphone", "0xxxxx", "127.0.0.1", "x", "1080 720", "1080 720", "123456789");
    }

    /**
     * 构建样例FeatureInfo对象
     */
    private SearchRequest.FeatureInfo buildExampleFeatureInfo(List<String> keywords,
                                                              List<DistrictFeature.ProvinceAndCity> provinceAndCities,
                                                              List<String> its,
                                                              FeatureRelationEnum relationEnum) {
        return new SearchRequest.FeatureInfo(new KeywordFeature(keywords),
                new DistrictFeature(provinceAndCities),
                new ITFeature(its),
                relationEnum);
    }
}