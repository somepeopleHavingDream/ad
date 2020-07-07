package org.yangxin.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 推广单元-关键字
 *
 * @author yangxin
 * 2020/01/10 16:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordRequest {

    private List<UnitKeywordRequest> unitKeywordRequests;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitKeywordRequest {
        /**
         * 单元id
         */
        private Long unitId;

        /**
         * 关键字
         */
        private String keyword;
    }
}
