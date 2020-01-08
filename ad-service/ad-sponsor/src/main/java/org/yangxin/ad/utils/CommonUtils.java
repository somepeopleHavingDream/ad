package org.yangxin.ad.utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author yangxin
 * 2020/01/08 16:18
 */
public class CommonUtils {
    /**
     * md5摘要
     */
    public static String md5(String value) {
        return DigestUtils.md5Hex(value).toUpperCase();
    }
}
