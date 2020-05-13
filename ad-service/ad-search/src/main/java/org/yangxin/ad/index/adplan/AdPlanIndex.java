package org.yangxin.ad.index.adplan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yangxin.ad.index.IndexAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 推广计划索引
 *
 * @author yangxin
 * 2020/05/13 20:38
 */
@Slf4j
@Component
public class AdPlanIndex implements IndexAware<Long, AdPlanObject> {

    private static final Map<Long, AdPlanObject> adPlanObjectMap;

    static {
        adPlanObjectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdPlanObject get(Long key) {
        return adPlanObjectMap.get(key);
    }

    @Override
    public void add(Long key, AdPlanObject value) {
        log.info("before add: [{}]", adPlanObjectMap);
        adPlanObjectMap.put(key, value);
        log.info("after add: [{}]", adPlanObjectMap);
    }

    @Override
    public void update(Long key, AdPlanObject value) {
        log.info("before update: [{}]", adPlanObjectMap);

        AdPlanObject adPlanObject = adPlanObjectMap.get(key);
        if (null == adPlanObject) {
            adPlanObjectMap.put(key, value);
        } else {
            adPlanObject.update(value);
        }

        log.info("after update: [{}]", adPlanObjectMap);
    }

    @Override
    public void delete(Long key, AdPlanObject value) {
        log.info("before delete: [{}]", adPlanObjectMap);
        adPlanObjectMap.remove(key);
        log.info("after delete: [{}]", adPlanObjectMap);
    }
}
