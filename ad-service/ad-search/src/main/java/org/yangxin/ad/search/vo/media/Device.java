package org.yangxin.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设备
 *
 * @author yangxin
 * 2020/08/19 16:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    /**
     * 设备Id
     */
    private String deviceCode;

    /**
     * mac
     */
    private String mac;

    /**
     * ip
     */
    private String ip;

    /**
     * 机型编码
     */
    private String model;

    /**
     * 分辨率尺寸
     */
    private String displaySize;

    /**
     * 屏幕尺寸
     */
    private String screenSize;

    /**
     * 设备序列号
     */
    private String serialName;
}
