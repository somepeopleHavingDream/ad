package org.yangxin.ad.index.adunit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.yangxin.ad.index.IndexAware;

import java.util.*;
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

    private static final Map<Long, AdUnitObject> AD_UNIT_OBJECT_MAP;
//    private static final Map<Long, AdUnitObject> adUnitObjectMap;

    static {
        AD_UNIT_OBJECT_MAP = new ConcurrentHashMap<>();
    }

    /**
     * 匹配操作
     *
     * @param positionType 位置类型|流量类型
     */
    public Set<Long> match(Integer positionType) {
        Set<Long> adUnitIds = new HashSet<>();

        AD_UNIT_OBJECT_MAP.forEach((k, v) -> {
            if (AdUnitObject.isAdSlotTypeOK(positionType, v.getPositionType())) {
                adUnitIds.add(k);
            }
        });

        return adUnitIds;
    }

    public List<AdUnitObject> fetch(Collection<Long> adUnitIds) {
        if (CollectionUtils.isEmpty(adUnitIds)) {
            return Collections.emptyList();
        }

        List<AdUnitObject> result = new ArrayList<>();
        adUnitIds.forEach(u -> {
            AdUnitObject object = get(u);
            if (object == null) {
                log.error("AdUnitObject not found: [{}]", u);
                return;
            }
            result.add(object);
        });

        return result;
    }

    @Override
    public AdUnitObject get(Long key) {
        return AD_UNIT_OBJECT_MAP.get(key);
    }

    @Override
    public void add(Long key, AdUnitObject value) {
        log.info("before add: [{}]", AD_UNIT_OBJECT_MAP);
        AD_UNIT_OBJECT_MAP.put(key, value);
        log.info("after add: [{}]", AD_UNIT_OBJECT_MAP);
    }

    @Override
    public void update(Long key, AdUnitObject value) {
        log.info("before update: [{}]", AD_UNIT_OBJECT_MAP);

        AdUnitObject adUnitObject = AD_UNIT_OBJECT_MAP.get(key);
        if (adUnitObject == null) {
            AD_UNIT_OBJECT_MAP.put(key, value);
        } else {
            adUnitObject.update(value);
        }

        log.info("after update: [{}]", AD_UNIT_OBJECT_MAP);
    }

    @Override
    public void delete(Long key, AdUnitObject value) {
        log.info("before delete: [{}]", AD_UNIT_OBJECT_MAP);
        AD_UNIT_OBJECT_MAP.remove(key);
        log.info("after delete: [{}]", AD_UNIT_OBJECT_MAP);
    }
}
