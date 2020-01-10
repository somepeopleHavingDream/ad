package org.yangxin.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 推广单元-兴趣
 *
 * @author yangxin
 * 2020/01/10 16:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitItRequest {
    private List<UnitIt> unitIts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitIt {
        /**
         * 推广单元Id
         */
        private Long unitId;

        /**
         * 兴趣标签
         */
        private String itTag;
    }
}
