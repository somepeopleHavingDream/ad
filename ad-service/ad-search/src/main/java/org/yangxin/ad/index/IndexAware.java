package org.yangxin.ad.index;

/**
 * 索引
 *
 * @author yangxin
 * 2020/05/13
 */
public interface IndexAware<K, V> {

    /**
     * 获得
     */
    V get(K key);

    /**
     * 添加
     */
    void add(K key, V value);

    /**
     * 更新
     */
    void update(K key, V value);

    /**
     * 删除
     */
    void delete(K key, V value);
}
