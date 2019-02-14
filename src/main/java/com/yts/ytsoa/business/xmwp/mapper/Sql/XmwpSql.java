package com.yts.ytsoa.business.xmwp.mapper.Sql;

import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XmwpSql {
    public String xmjlsh(@Param("model") XmwpModel model) {
        return new SQL() {
            {
                UPDATE(Tables.XMWP_TABLE);
                if (model.getBz() != null && !model.getBz().isEmpty()) {
                    SET("bz=#{model.bz}");
                }
                if (model.getYwzt() != 0) {
                    SET("ywzt=#{model.ywzt}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String findYwzt(@Param("uuid") String uuid) {
        return new SQL() {
            {
                SELECT("x.*");
                FROM(Tables.XMWP_TABLE);
                if (uuid != null && !uuid.isEmpty()) {
                    WHERE("uuid=#{uuid}");
                }
            }
        }.toString();
    }
}
