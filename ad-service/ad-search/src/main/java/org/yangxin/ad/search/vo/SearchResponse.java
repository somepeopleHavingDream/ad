package org.yangxin.ad.search.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yangxin.ad.index.creative.CreativeObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检索响应
 *
 * @author yangxin
 * 2020/08/20 09:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {

    /**
     * 槽位->创意列表
     */
    public Map<String, List<Creative>> adsByAdSlot = new HashMap<>();
//    public Map<String, List<Creative>> adSlot2Ads = new HashMap<>();

    /**
     * 创意
     *
     * @author yangxin
     * 2020/08/20 09:12
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Creative {

        /**
         * 广告Id
         */
        private Long adId;

        /**
         * 广告url
         */
        private String adUrl;

        /**
         * 宽
         */
        private Integer width;

        /**
         * 高
         */
        private Integer height;

        /**
         * 创意类型
         */
        private Integer type;

        /**
         * 材料类型
         */
        private Integer materialType;

        /**
         * 展示、监测 url
         */
        private List<String> showMonitorUrl = Arrays.asList("www.imooc.com", "www.imooc.com");

        /**
         * 点击监测 url
         */
        private List<String> clickMonitorUrl = Arrays.asList("www.imooc.com", "www.imooc.com");
    }

    /**
     * 将创意具体对象转换成创意
     */
    public static Creative convert(CreativeObject object) {
        Creative creative = new Creative();
        creative.setAdId(object.getAdId());
        creative.setAdUrl(object.getAdUrl());
        creative.setWidth(object.getWidth());
        creative.setHeight(object.getHeight());
        creative.setType(object.getType());
        creative.setMaterialType(object.getMaterialType());

        return creative;
    }
}
