package org.yangxin.ad.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yangxin.ad.Application;
import org.yangxin.ad.constant.CommonStatusEnum;
import org.yangxin.ad.dump.table.AdCreativeTable;
import org.yangxin.ad.dump.table.AdPlanTable;
import org.yangxin.ad.dump.table.AdUnitTable;
import org.yangxin.ad.entity.AdPlan;
import org.yangxin.ad.entity.AdUnit;
import org.yangxin.ad.entity.Creative;
import org.yangxin.ad.repository.AdCreativeRepository;
import org.yangxin.ad.repository.AdPlanRepository;
import org.yangxin.ad.repository.AdUnitRepository;
import org.yangxin.ad.repository.unitcondition.AdCreativeUnitRepository;
import org.yangxin.ad.repository.unitcondition.AdUnitDistrictRepository;
import org.yangxin.ad.repository.unitcondition.AdUnitItRepository;
import org.yangxin.ad.repository.unitcondition.AdUnitKeywordRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 导出数据
 *
 * @author yangxin
 * 2020/05/24 18:25
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {

    @Autowired
    private AdPlanRepository adPlanRepository;
    @Autowired
    private AdUnitRepository adUnitRepository;
    @Autowired
    private AdCreativeRepository adCreativeRepository;
    @Autowired
    private AdCreativeUnitRepository adCreativeUnitRepository;
    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;
    @Autowired
    private AdUnitItRepository adUnitItRepository;
    @Autowired
    private AdUnitKeywordRepository adUnitKeywordRepository;

    private void dumpAdPlanTable(String fileName) {
        List<AdPlan> adPlanList = adPlanRepository.findAllByPlanStatus(CommonStatusEnum.VALID.getStatus());
        if (CollectionUtils.isEmpty(adPlanList)) {
            return;
        }

        List<AdPlanTable> adPlanTableList = new ArrayList<>();
        adPlanList.forEach(adPlan -> adPlanTableList.add(new AdPlanTable(adPlan.getId(),
                adPlan.getUserId(), adPlan.getPlanStatus(), adPlan.getStartDate(), adPlan.getEndDate())));

        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdPlanTable adPlanTable : adPlanTableList) {
                bufferedWriter.write(JSON.toJSONString(adPlanTable));
            }
        } catch (IOException e) {
            log.error("dumpAdPlanTable error", e);
        }
    }

    private void dumpAdUnitTable(String fileName) {
        List<AdUnit> adUnitList = adUnitRepository.findAllByUnitStatus(CommonStatusEnum.VALID.getStatus());
        if (CollectionUtils.isEmpty(adUnitList)) {
            return;
        }

        List<AdUnitTable> adUnitTableList = new ArrayList<>();
        adUnitList.forEach(adUnit -> adUnitTableList.add(new AdUnitTable(adUnit.getId(),
                adUnit.getUnitStatus(), adUnit.getPositionType(), adUnit.getPlanId())));

        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdUnitTable adUnitTable : adUnitTableList) {
                bufferedWriter.write(JSON.toJSONString(adUnitTable));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdUnitTable error", e);
        }
    }

    private void dumpAdCreativeTable(String fileName) {
        List<Creative> creativeList = adCreativeRepository.findAll();
        if (CollectionUtils.isEmpty(creativeList)) {
            return;
        }

        List<AdCreativeTable> adCreativeTableList = new ArrayList<>();
        creativeList.forEach(creative -> adCreativeTableList.add(new AdCreativeTable(creative.getId(),
                creative.getName(), creative.getType(), creative.getMaterialType(), creative.getHeight(),
                creative.getWidth(), creative.getAuditStatus(), creative.getUrl())));

        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdCreativeTable adCreativeTable : adCreativeTableList) {
                bufferedWriter.write(JSON.toJSONString(adCreativeTable));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdPlanTable error", e);
        }
    }
}
