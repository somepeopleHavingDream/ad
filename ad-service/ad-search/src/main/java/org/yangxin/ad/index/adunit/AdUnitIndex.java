package org.yangxin.ad.index.adunit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yangxin.ad.index.IndexAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 推广单元索引
 *
 * @author yangxin
 * 2020/05/14 19:46
 */
@Slf4j
@Component
public class AdUnitIndex implements IndexAware<Long, AdUnitObject> {

    private static final Map<Long, AdUnitObject> adUnitObjectMap;

    static {
        adUnitObjectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdUnitObject get(Long key) {
        return adUnitObjectMap.get(key);
    }

    @Override
    public void add(Long key, AdUnitObject value) {
        log.info("before add: [{}]", adUnitObjectMap);
        adUnitObjectMap.put(key, value);
        log.info("after add: [{}]", adUnitObjectMap);
    }

    @Override
    public void update(Long key, AdUnitObject value) {
        log.info("before update: [{}]", adUnitObjectMap);

        AdUnitObject adUnitObject = adUnitObjectMap.get(key);
        if (adUnitObject == null) {
            adUnitObjectMap.put(key, value);
        } else {
            adUnitObject.update(value);
        }

        log.info("after update: [{}]", adUnitObjectMap);
    }

    @Override
    public void delete(Long key, AdUnitObject value) {
        log.info("before delete: [{}]", adUnitObjectMap);
        adUnitObjectMap.remove(key);
        log.info("after delete: [{}]", adUnitObjectMap);
    }
}
