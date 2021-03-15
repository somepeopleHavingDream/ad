package org.yangxin.ad.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;
import org.yangxin.ad.exception.AdException;

import java.text.ParseException;
import java.util.Date;

/**
 * @author yangxin
 * 2020/01/08 16:18
 */
public class CommonUtils {

    /**
     * 解析格式
     */
    private static final String[] PARSE_PATTERNS = {
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"
    };

    /**
     * md5摘要
     */
    public static String md5(String value) {
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    /**
     * 将日期从字符串转换到日期
     */
    public static Date parseStringDate(String dateString) throws AdException {
        try {
            return DateUtils.parseDate(dateString, PARSE_PATTERNS);
        } catch (ParseException e) {
            throw new AdException(e.getMessage());
        }
    }
}
