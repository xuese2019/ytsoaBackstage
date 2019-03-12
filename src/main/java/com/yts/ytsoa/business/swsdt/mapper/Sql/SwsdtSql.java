package com.yts.ytsoa.business.swsdt.mapper.Sql;

import com.yts.ytsoa.business.swsdt.model.SwsdtModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SwsdtSql {
    public String insertSwsdt(@Param("model") SwsdtModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.SWSDT_TABLE);
                VALUES("uuid", "#{model.uuid}");
                VALUES("scsj", "#{model.scsj}");
                VALUES("scr", "#{model.scr}");
                VALUES("bt", "#{model.bt}");
                VALUES("nr", "#{model.nr}");
            }
        }.toString();
    }

    public String find(@Param("model") SwsdtModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(" swsdt_table");
                ORDER_BY("scsj desc");
            }
        }.toString();
    }
}
