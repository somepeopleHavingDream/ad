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

    private static final Map<Long, AdPlanObject> AD_PLAN_OBJECT_MAP;
//    private static final Map<Long, AdPlanObject> adPlanObjectMap;

    static {
        AD_PLAN_OBJECT_MAP = new ConcurrentHashMap<>();
    }

    @Override
    public AdPlanObject get(Long key) {
        return AD_PLAN_OBJECT_MAP.get(key);
    }

    @Override
    public void add(Long key, AdPlanObject value) {
        log.info("before add: [{}]", AD_PLAN_OBJECT_MAP);
        AD_PLAN_OBJECT_MAP.put(key, value);
        log.info("after add: [{}]", AD_PLAN_OBJECT_MAP);
    }

    @Override
    public void update(Long key, AdPlanObject value) {
        log.info("before update: [{}]", AD_PLAN_OBJECT_MAP);

        AdPlanObject adPlanObject = AD_PLAN_OBJECT_MAP.get(key);
        if (null == adPlanObject) {
            AD_PLAN_OBJECT_MAP.put(key, value);
        } else {
            adPlanObject.update(value);
        }

        log.info("after update: [{}]", AD_PLAN_OBJECT_MAP);
    }

    @Override
    public void delete(Long key, AdPlanObject value) {
        log.info("before delete: [{}]", AD_PLAN_OBJECT_MAP);
        AD_PLAN_OBJECT_MAP.remove(key);
        log.info("after delete: [{}]", AD_PLAN_OBJECT_MAP);
    }
}
