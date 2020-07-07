package org.yangxin.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 广告创意-推广单元
 *
 * @author yangxin
 * 2020/01/13 14:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCreativeUnitRequest {

    private List<CreativeUnitItemRequest> unitItems;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreativeUnitItemRequest {
        /**
         * 创意Id
         */
        private Long creativeId;

        /**
         * 推广单元Id
         */
        private Long unitId;
    }
}
