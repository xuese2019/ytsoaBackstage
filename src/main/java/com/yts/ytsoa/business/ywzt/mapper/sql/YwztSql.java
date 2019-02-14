package com.yts.ytsoa.business.ywzt.mapper.sql;

import com.yts.ytsoa.business.ywzt.model.YwztModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class YwztSql {
    public String findAllSql(@Param("ywztModel") YwztModel ywztModel) {
        return new SQL() {
            {
                SELECT("a.*,ac.name");
                FROM("ywjl_c_table a");
                LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " ac on ac.uuid = a.hfr");
                ORDER_BY("a.hfsj Desc");
                /*  LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " c on c.uuid = a.tjr");*/
              /*  if (zskModel.getTjr() != null && !zskModel.getTjr().isEmpty()) {
                    zskModel.setTjr("%" + zskModel.getTjr() + "%");
                    WHERE("c.name like #{zskModel.tjr}");
                }*/
                if (ywztModel.getFqzt_d() != null && !ywztModel.getFqzt_d().isEmpty()) {
                    /*  xmcjModel.setWtf("%" + xmcjModel.getWtf() + "%");*/
                    WHERE("a.fqzt_d = #{ywztModel.fqzt_d}");
                }
            }
        }.toString();
    }
}
