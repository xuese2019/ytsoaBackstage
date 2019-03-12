package com.yts.ytsoa.business.jyjl.mapper.Sql;

import com.yts.ytsoa.business.jyjl.model.JyjlModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class JyjlSql {
    public String insert(@Param("model") JyjlModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.JYJL_TABLE);
                VALUES("uuid", "replace(uuid(),'-','')");
                VALUES("dgjybh", "#{model.dgjybh}");
                VALUES("xmid", "#{model.xmid}");
                VALUES("jyrid", "#{model.jyrid}");
                VALUES("ghrq", "#{model.ghrq}");
                VALUES("ghrid", "#{model.ghrid}");
            }
        }.toString();
    }

}
