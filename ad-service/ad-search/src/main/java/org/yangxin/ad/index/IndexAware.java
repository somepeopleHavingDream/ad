package org.yangxin.ad.index;

/**
 * 索引
 *
 * @author yangxin
 * 2020/05/13
 */
public interface IndexAware<K, V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);
}
