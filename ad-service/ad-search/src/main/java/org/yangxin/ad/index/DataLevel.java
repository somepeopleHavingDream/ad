package org.yangxin.ad.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yangxin
 * 2020/08/05 13:52
 */
@Getter
@AllArgsConstructor
public enum DataLevel {

    LEVEL2("2", "level 2"),
    LEVEL3("3", "level 3"),
    LEVEL4("4", "level 4");

    private final String level;
    private final String desc;
}
