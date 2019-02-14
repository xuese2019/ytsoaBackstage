package com.yts.ytsoa.business.cwgl.mapper.sql;

import com.yts.ytsoa.business.cwgl.model.CwglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class CwglSql {
    public String findAllSql(@Param("cwglModel") CwglModel cwglModel) {
        return new SQL() {
            {
                SELECT("c.uuid,c.rq,c.sflx,c.zt,x.xmmc as 'xmid'");
                FROM("cwgl_table c");
                LEFT_OUTER_JOIN(Tables.XMWP_TABLE + " x on c.xmid = x. uuid");
                if (cwglModel.getRq() != null) {
                    WHERE("c.rq = #{cwglModel.rq}");
                }
                if (cwglModel.getXmid() != null && !cwglModel.getXmid().isEmpty()) {
                    WHERE("xmid=#{cwglModel.xmid}");
                }
            }
        }.toString();
    }

    public String updateByIdSql(@Param("cwglModel") CwglModel cwglModel) {
        return new SQL() {
            {
                UPDATE("cwgl_table");
                if (cwglModel.getRq() != null) {
                    SET("rq=#{cwglModel.rq}");
                }
                if (cwglModel.getSflx() != null) {
                    SET("sflx=#{cwglModel.sflx}");
                }
                if (cwglModel.getZt() != 0) {
                    SET("zt=#{cwglModel.zt}");
                }
                WHERE("uuid = #{cwglModel.uuid}");
            }
        }.toString();
    }
}
