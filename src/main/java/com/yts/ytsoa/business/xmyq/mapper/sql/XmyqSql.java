package com.yts.ytsoa.business.xmyq.mapper.sql;

import com.yts.ytsoa.business.xmyq.model.XmyqModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XmyqSql {
    public String findAllSql(@Param("xmyqModel") XmyqModel xmyqModel) {
        return new SQL() {
            {
                SELECT("a.*");
                FROM("xmyq_table a");
                /*               LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " c on c.uuid = a.tjr");*/
                if (xmyqModel.getXmmc() != null && !xmyqModel.getXmmc().isEmpty()) {
                    xmyqModel.setXmmc("%" + xmyqModel.getXmmc() + "%");
                    WHERE("a.xmmc like #{xmyqModel.xmmc}");
                }
                if (xmyqModel.getXmjssj() != null && xmyqModel.getXtdqsj() != null) {
                    WHERE("#{xmyqModel.xtdqsj} > #{xmyqModel.xmjssj}");
                    WHERE("a.xmzt=1");
                }
            }
        }.toString();
    }
}
