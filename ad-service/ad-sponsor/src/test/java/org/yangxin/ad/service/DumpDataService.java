package org.yangxin.ad.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yangxin.ad.Application;
import org.yangxin.ad.constant.CommonStatusEnum;
import org.yangxin.ad.dump.DumpConstant;
import org.yangxin.ad.dump.table.*;
import org.yangxin.ad.entity.AdPlan;
import org.yangxin.ad.entity.AdUnit;
import org.yangxin.ad.entity.Creative;
import org.yangxin.ad.entity.unitcondition.AdCreativeUnit;
import org.yangxin.ad.entity.unitcondition.AdUnitDistrict;
import org.yangxin.ad.entity.unitcondition.AdUnitIt;
import org.yangxin.ad.entity.unitcondition.AdUnitKeyword;
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

    @Test
    public void dumpAdTableData() {
        // ad_plan
        dumpAdPlanTable(String.format("%s%s", DumpConstant.DATA_ROOT_DIR, DumpConstant.AD_PLAN));
        // ad_unit
        dumpAdUnitTable(String.format("%s%s", DumpConstant.DATA_ROOT_DIR, DumpConstant.AD_UNIT));
        // ad_creative
        dumpAdCreativeTable(String.format("%s%s", DumpConstant.DATA_ROOT_DIR, DumpConstant.AD_CREATIVE));
        // creative_unit
        dumpAdCreativeUnitTable(String.format("%s%s", DumpConstant.DATA_ROOT_DIR, DumpConstant.AD_CREATIVE_UNIT));
        // ad_unit_district
        dumpAdUnitDistrictTable(String.format("%s%s", DumpConstant.DATA_ROOT_DIR, DumpConstant.AD_UNIT_DISTRICT));
        // ad_unit_it
        dumpAdUnitItTable(String.format("%s%s", DumpConstant.DATA_ROOT_DIR, DumpConstant.AD_UNIT_IT));
        // ad_unit_keyword
        dumpAdUnitKeywordTable(String.format("%s%s", DumpConstant.DATA_ROOT_DIR, DumpConstant.AD_UNIT_KEYWORD));
    }

    /**
     * 导出ad_plan表中的有效数据
     *
     * @param fileName 文件名称
     */
    private void dumpAdPlanTable(String fileName) {
        // 取出ad_plan表中的所有记录
        List<AdPlan> adPlanList = adPlanRepository.findAllByPlanStatus(CommonStatusEnum.VALID.getStatus());
        if (CollectionUtils.isEmpty(adPlanList)) {
            return;
        }
        log.info("adPlanList.size: [{}]", adPlanList.size());


        // 只需要处理几个字段就行，无用字段不用导出到文件中
        List<AdPlanTable> adPlanTableList = new ArrayList<>();
        adPlanList.forEach(adPlan -> adPlanTableList.add(new AdPlanTable(adPlan.getId(),
                adPlan.getUserId(),
                adPlan.getPlanStatus(),
                adPlan.getStartDate(),
                adPlan.getEndDate())));

        // 获取文件路径对象
        Path path = Paths.get(fileName);
        log.info("path: [{}]", path);

        // 向文件中写入数据
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdPlanTable adPlanTable : adPlanTableList) {
                bufferedWriter.write(JSON.toJSONString(adPlanTable));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdPlanTable error", e);
        }
    }

    /**
     * 导出ad_unit表中的有效记录
     *
     * @param fileName 文件名称
     */
    private void dumpAdUnitTable(String fileName) {
        List<AdUnit> adUnitList = adUnitRepository.findAllByUnitStatus(CommonStatusEnum.VALID.getStatus());
        if (CollectionUtils.isEmpty(adUnitList)) {
            return;
        }
        log.info("adUnitList.size: [{}]", adUnitList.size());

        List<AdUnitTable> adUnitTableList = new ArrayList<>();
        adUnitList.forEach(adUnit -> adUnitTableList.add(new AdUnitTable(adUnit.getId(),
                adUnit.getUnitStatus(),
                adUnit.getPositionType(),
                adUnit.getPlanId())));

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

    /**
     * 导出ad_creative表
     *
     * @param fileName 文件名称
     */
    private void dumpAdCreativeTable(String fileName) {
        List<Creative> creativeList = adCreativeRepository.findAll();
        if (CollectionUtils.isEmpty(creativeList)) {
            return;
        }
        log.info("creativeList.size: [{}]", creativeList.size());

        List<AdCreativeTable> adCreativeTableList = new ArrayList<>();
        creativeList.forEach(creative -> adCreativeTableList.add(new AdCreativeTable(creative.getId(),
                creative.getName(),
                creative.getType(),
                creative.getMaterialType(),
                creative.getHeight(),
                creative.getWidth(),
                creative.getAuditStatus(),
                creative.getUrl())));

        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdCreativeTable adCreativeTable : adCreativeTableList) {
                bufferedWriter.write(JSON.toJSONString(adCreativeTable));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdCreativeTable error", e);
        }
    }

    /**
     * 导出creative_unit表记录
     *
     * @param fileName 文件名称
     */
    private void dumpAdCreativeUnitTable(String fileName) {
        List<AdCreativeUnit> adCreativeUnitList = adCreativeUnitRepository.findAll();
        if (CollectionUtils.isEmpty(adCreativeUnitList)) {
            return;
        }
        log.info("adCreativeUnitList.size: [{}]", adCreativeUnitList.size());

        List<AdCreativeUnitTable> adCreativeUnitTableList = new ArrayList<>();
        adCreativeUnitList.forEach(adCreativeUnit
                -> adCreativeUnitTableList.add(new AdCreativeUnitTable(adCreativeUnit.getCreativeId(),
                adCreativeUnit.getUnitId())));

        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdCreativeUnitTable adCreativeUnitTable : adCreativeUnitTableList) {
                bufferedWriter.write(JSON.toJSONString(adCreativeUnitTable));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdCreativeUnitTable error", e);
        }
    }

    /**
     * 导出ad_unit_district表数据
     *
     * @param fileName 文件名称
     */
    private void dumpAdUnitDistrictTable(String fileName) {
        List<AdUnitDistrict> adUnitDistrictList = adUnitDistrictRepository.findAll();
        if (CollectionUtils.isEmpty(adUnitDistrictList)) {
            return;
        }
        log.info("adUnitDistrictList.size: [{}]", adUnitDistrictList.size());

        List<AdUnitDistrictTable> adUnitDistrictTableList = new ArrayList<>();
        adUnitDistrictList.forEach(adUnitDistrict -> adUnitDistrictTableList.add(new AdUnitDistrictTable(adUnitDistrict.getUnitId(),
                adUnitDistrict.getProvince(),
                adUnitDistrict.getCity())));

        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdUnitDistrictTable adUnitDistrictTable : adUnitDistrictTableList) {
                bufferedWriter.write(JSON.toJSONString(adUnitDistrictTable));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdUnitDistrictTable error", e);
        }
    }

    /**
     * 导出ad_unit_it表数据
     *
     * @param fileName 文件名称
     */
    private void dumpAdUnitItTable(String fileName) {
        List<AdUnitIt> adUnitItList = adUnitItRepository.findAll();
        if (CollectionUtils.isEmpty(adUnitItList)) {
            return;
        }
        log.info("adUnitItList.size: [{}]", adUnitItList.size());

        List<AdUnitItTable> adUnitItTableList = new ArrayList<>();
        adUnitItList.forEach(adUnitIt -> adUnitItTableList.add(new AdUnitItTable(adUnitIt.getUnitId(),
                adUnitIt.getItTag())));

        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdUnitItTable adUnitItTable : adUnitItTableList) {
                bufferedWriter.write(JSON.toJSONString(adUnitItTable));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdUnitItTable error", e);
        }
    }

    /**
     * 导出ad_unit_keyword表数据
     *
     * @param fileName 文件名称
     */
    private void dumpAdUnitKeywordTable(String fileName) {
        List<AdUnitKeyword> adUnitKeywordList = adUnitKeywordRepository.findAll();
        if (CollectionUtils.isEmpty(adUnitKeywordList)) {
            return;
        }
        log.info("adUnitKeywordList.size: [{}]", adUnitKeywordList.size());

        List<AdUnitKeywordTable> adUnitKeywordTableList = new ArrayList<>();
        adUnitKeywordList.forEach(adUnitKeyword
                -> adUnitKeywordTableList.add(new AdUnitKeywordTable(adUnitKeyword.getUnitId(),
                adUnitKeyword.getKeyword())));

        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdUnitKeywordTable adUnitKeywordTable : adUnitKeywordTableList) {
                bufferedWriter.write(JSON.toJSONString(adUnitKeywordTable));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdUnitKeywordTable error", e);
        }
    }
}
