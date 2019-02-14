package com.yts.ytsoa.business.ywjl.mapper.sql;

import com.yts.ytsoa.business.ywjl.model.YwjlModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class YwjlSql {
    public String findAllSql(@Param("ywjlModel") YwjlModel ywjlModel) {
        return new SQL() {
            {

//                SELECT("a.uuid,ac.name as fqr,a.fqzt,a.fqsj");
                SELECT("a.*,ac.name");
                FROM("ywjl_table a");
                LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " ac on ac.uuid = a.fqr");
                if (ywjlModel.getFqzt() != null && !ywjlModel.getFqzt().isEmpty()) {
                    ywjlModel.setFqzt("%" + ywjlModel.getFqzt() + "%");
                    WHERE("a.fqzt like #{ywjlModel.fqzt}");
                }
            }
        }.toString();
    }
}
