package org.yangxin.ad.index.district;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.yangxin.ad.index.IndexAware;
import org.yangxin.ad.search.vo.feature.DistrictFeature;
import org.yangxin.ad.utils.CommonUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * 地域索引
 *
 * @author yangxin
 * 2020/05/23 12:44
 */
@SuppressWarnings("DuplicatedCode")
@Slf4j
@Component
public class UnitDistrictIndex implements IndexAware<String, Set<Long>> {

    private static final Map<String, Set<Long>> districtUnitMap;
    private static final Map<Long, Set<String>> unitDistrictMap;

    static {
        districtUnitMap = new ConcurrentHashMap<>();
        unitDistrictMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        return districtUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("UnitDistrictIndex, before add: [{}]", unitDistrictMap);

        Set<Long> unitSet = CommonUtils.getOrCreate(key, districtUnitMap, ConcurrentSkipListSet::new);
        unitSet.addAll(value);

        for (Long unitId : value) {
            Set<String> districtSet = CommonUtils.getOrCreate(unitId, unitDistrictMap, ConcurrentSkipListSet::new);
            districtSet.add(key);
        }

        log.info("UnitDistrictIndex, after add: [{}]", unitDistrictMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("district index can not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitDistrictIndex, before delete: [{}]", unitDistrictMap);

        Set<Long> unitIdSet = CommonUtils.getOrCreate(key, districtUnitMap, ConcurrentSkipListSet::new);
        unitIdSet.removeAll(value);

        for (Long unitId : value) {
            Set<String> districtSet = CommonUtils.getOrCreate(unitId, unitDistrictMap, ConcurrentSkipListSet::new);
            districtSet.remove(key);
        }

        log.info("UnitDistrictIndex, after delete: [{}]", unitDistrictMap);
    }

    public boolean match(Long adUnitId, List<DistrictFeature.ProvinceAndCity> districts) {
        if (unitDistrictMap.containsKey(adUnitId) && CollectionUtils.isNotEmpty(unitDistrictMap.get(adUnitId))) {
            Set<String> unitDistricts = unitDistrictMap.get(adUnitId);
            List<String> targetDistricts = districts.stream()
                    .map(d -> CommonUtils.stringConcat(d.getProvince(), d.getCity()))
                    .collect(Collectors.toList());
            return CollectionUtils.isSubCollection(targetDistricts, unitDistricts);
        }

        return false;
    }
}