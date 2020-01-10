package org.yangxin.ad.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推广单元
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitResponse {
    /**
     * 推广单元Id
     */
    private Long id;

    /**
     * 单元名称
     */
    private String unitName;
}
