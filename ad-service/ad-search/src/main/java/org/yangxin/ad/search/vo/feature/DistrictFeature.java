package org.yangxin.ad.search.vo.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 地域特征
 *
 * @author yangxin
 * 2020/08/19 16:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictFeature {

    /**
     * 地域列表
     */
    private List<ProvinceAndCity> districts;

    /**
     * 省和城市
     *
     * @author yangxin
     * 2020/08/19 16:31
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProvinceAndCity {

        /**
         * 省
         */
        private String province;

        /**
         * 城市
         */
        private String city;
    }
}
