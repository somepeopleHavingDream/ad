package org.yangxin.ad.index.creativeunit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创意单元
 *
 * @author yangxin
 * 2020/05/23 19:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitObject {

    /**
     * 广告Id
     */
    private Long adId;

    /**
     * 单元Id
     */
    private Long unitId;

    // adId-unitId
}
