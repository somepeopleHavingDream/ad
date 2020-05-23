package org.yangxin.ad.index.interest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推广单元-兴趣
 *
 * @author yangxin
 * 2020/05/23 12:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitItObject {

    /**
     * 推广单元Id
     */
    private Long unitId;

    /**
     * 兴趣标签
     */
    private String itTag;
}
