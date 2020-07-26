package org.yangxin.ad.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yangxin
 * 2020/07/26 18:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JSONTable {

    private String tableName;
    private Integer level;

    private List<Column> insert;
    private List<Column> update;
    private List<Column> delete;

    /**
     * @author yangxin
     * 2020/07/26 18:21
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Column {

        private String column;
    }
}
