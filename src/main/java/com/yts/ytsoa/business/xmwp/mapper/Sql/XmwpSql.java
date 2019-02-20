package com.yts.ytsoa.business.xmwp.mapper.Sql;

import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XmwpSql {
    public String updateYwzt(@Param("model") XmwpModel model) {
        return new SQL() {
            {
                UPDATE(Tables.XMWP_TABLE);
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
                FROM(Tables.XMWP_TABLE + " x");
                if (uuid != null && !uuid.isEmpty()) {
                    WHERE("x.uuid=#{uuid}");
                }
            }
        }.toString();
    }
}
