package org.yangxin.ad.mysql;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.yangxin.ad.mysql.constant.OpType;
import org.yangxin.ad.mysql.dto.ParseTemplate;
import org.yangxin.ad.mysql.dto.TableTemplate;
import org.yangxin.ad.mysql.dto.Template;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangxin
 * 2020/07/26 18:47
 */
@Slf4j
@Component
public class TemplateHolder {

    private ParseTemplate template;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TemplateHolder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    private void init() {
        loadJSON();
    }

    public TableTemplate getTable(String tableName) {
        return template.getTableTemplateMap().get(tableName);
    }

    private void loadJSON() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream inStream = contextClassLoader.getResourceAsStream("template.json");

        try {
            Template template = JSON.parseObject(Objects.requireNonNull(inStream), Charset.defaultCharset(), Template.class);
            this.template = ParseTemplate.parse(template);
            loadMeta();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("fail to parse json file");
        }
    }

    private void loadMeta() {
        for (Map.Entry<String, TableTemplate> entry : template.getTableTemplateMap().entrySet()) {
            TableTemplate table = entry.getValue();
            List<String> updateFields = table.getOpTypeFieldSetMap().get(OpType.UPDATE);
            List<String> insertFields = table.getOpTypeFieldSetMap().get(OpType.ADD);
            List<String> deleteFields = table.getOpTypeFieldSetMap().get(OpType.DELETE);
            String SQL_SCHEMA = "select table_schema, table_name, "
                    + "column_name, ordinal_position from information_schema.columns "
                    + "where table_schema = ? and table_name = ?";
            jdbcTemplate.query(SQL_SCHEMA,
                    new Object[] {template.getDatabase(), table.getTableName()},
                    (rs, i) -> {
                        int pos = rs.getInt("ORDINAL_POSITION");
                        String colName = rs.getString("COLUMN_NAME");

                        if ((updateFields != null && updateFields.contains(colName))
                            || (insertFields != null && insertFields.contains(colName))
                            || (deleteFields != null && deleteFields.contains(colName))) {
                            table.getPosMap().put(pos - 1, colName);
                        }
                        return null;
                    });
        }
    }
}
