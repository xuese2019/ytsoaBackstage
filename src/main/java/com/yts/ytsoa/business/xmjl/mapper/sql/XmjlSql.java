package com.yts.ytsoa.business.xmjl.mapper.sql;

import com.yts.ytsoa.business.xmjl.model.XmjlModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XmjlSql {
    public String findAllSql(@Param("xmjlModel") XmjlModel xmjlModel) {
        return new SQL() {
            {
                SELECT("a.*");
                FROM("xmjl_table a");
                /*      LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " ac on ac.uuid = a.fqr");*/
                if (xmjlModel.getXmmc() != null && !xmjlModel.getXmmc().isEmpty()) {
                    xmjlModel.setXmmc("%" + xmjlModel.getXmmc() + "%");
                    WHERE("a.xmmc like #{xmjlModel.xmmc}");
                }
            }
        }.toString();
    }
}
