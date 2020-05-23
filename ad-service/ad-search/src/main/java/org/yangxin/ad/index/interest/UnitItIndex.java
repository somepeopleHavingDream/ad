package org.yangxin.ad.index.interest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.yangxin.ad.index.IndexAware;
import org.yangxin.ad.utils.CommonUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * It索引
 *
 * @author yangxin
 * 2020/05/23 12:24
 */
@Slf4j
@Component
public class UnitItIndex implements IndexAware<String, Set<Long>> {

    /**
     * <itTag, adUnitId set>
     */
    private static final Map<String, Set<Long>> itUnitMap;

    /**
     * <unitId, itTag set>
     */
    private static final Map<Long, Set<String>> unitItMap;

    static {
        itUnitMap = new ConcurrentHashMap<>();
        unitItMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        return itUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("UnitItIndex, before add: [{}]", unitItMap);

        Set<Long> unitIdSet = CommonUtils.getOrCreate(key, itUnitMap, ConcurrentSkipListSet::new);
        unitIdSet.addAll(value);

        for (Long unitId : value) {
            Set<String> itSet = CommonUtils.getOrCreate(unitId, unitItMap, ConcurrentSkipListSet::new);
            itSet.add(key);
        }

        log.info("UnitIndex, after add: [{}]", unitItMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("it index can not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitItIndex, before delete: [{}]", unitItMap);

        Set<Long> unitIdSet = CommonUtils.getOrCreate(key, itUnitMap, ConcurrentSkipListSet::new);
        unitIdSet.removeAll(value);

        for (Long unitId : value) {
            Set<String> itTagSet = CommonUtils.getOrCreate(unitId, unitItMap, ConcurrentSkipListSet::new);
            itTagSet.remove(key);
        }

        log.info("UnitItIndex, after delete: [{}]", unitItMap);
    }

    public boolean match(Long unitId, List<String> itTagList) {
        if (unitItMap.containsKey(unitId) && CollectionUtils.isNotEmpty(unitItMap.get(unitId))) {
            Set<String> unitKeywordSet = unitItMap.get(unitId);
            return CollectionUtils.isSubCollection(itTagList, unitKeywordSet);
        }

        return false;
    }
}
