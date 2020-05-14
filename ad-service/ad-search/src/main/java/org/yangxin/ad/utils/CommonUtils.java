package org.yangxin.ad.utils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * 公共工具类
 *
 * @author yangxin
 * 2020/05/14 20:05
 */
public class CommonUtils {

    public static <K, V> V getOrCreate(K key, Map<K, V> map, Supplier<V> factory) {
        return map.computeIfAbsent(key, k -> factory.get());
    }
}
