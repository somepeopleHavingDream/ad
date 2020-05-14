package org.yangxin.ad.index.keyword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单元关键字
 *
 * @author yangxin
 * 2020/05/14 19:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitKeywordObject {

    /**
     * 推广单元Id
     */
    private Long unitId;

    /**
     * 关键字
     */
    private String keyword;
}
