package com.yts.ytsoa.business.ywjlhf.mapper.sql;

import com.yts.ytsoa.business.ywjlhf.model.YwjlhfModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class YwjlhfSql {
    public String findAllSql(@Param("ywjlhfModel") YwjlhfModel ywjlhfModel) {
        return new SQL() {
            {
                SELECT("a.*,ac.name");
                FROM("ywjlhf_table a");
                LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " ac on ac.uuid = a.hfr");
                if (ywjlhfModel.getXmid() != null && !ywjlhfModel.getXmid().isEmpty()) {
                    WHERE("a.xmid = #{ywjlhfModel.xmid}");
                }
//                ORDER_BY("a.hfsj Desc");

            }
        }.toString();
    }
}
