package org.yangxin.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * App
 *
 * @author yangxin
 * 2020/08/19 16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class App {

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用包名
     */
    private String packageName;

    /**
     * activity 名称
     */
    private String activityName;
}
