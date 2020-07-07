package org.yangxin.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 推广单元-地域
 *
 * @author yangxin
 * 2020/01/10 16:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictRequest {

    private List<UnitDistrictRequest> unitDistrictRequests;

    /**
     * 地域
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitDistrictRequest {
        /**
         * 推广单元Id
         */
        private Long unitId;

        /**
         * 省份
         */
        private String province;

        /**
         * 城市
         */
        private String city;
    }
}
