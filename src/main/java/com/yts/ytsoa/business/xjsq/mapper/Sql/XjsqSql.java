package com.yts.ytsoa.business.xjsq.mapper.Sql;

import com.yts.ytsoa.business.xjsq.model.XjsqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XjsqSql {
    public String find(@Param("m") XjsqModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(Tables.XJSQ_TABLE);
                if (model.getXjsqr() != null && !model.getXjsqr().isEmpty()) {
                    WHERE("xjsqr like concat('%',#{model.xjsqr},'%')");
                }
            }
        }.toString();
    }

    public String update(@Param("model") XjsqModel model) {
        return new SQL() {
            {
                UPDATE(Tables.XJSQ_TABLE);
                if (model.getSfty() != 0) {
                    SET("sfty=#{model.sfty}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }
}
