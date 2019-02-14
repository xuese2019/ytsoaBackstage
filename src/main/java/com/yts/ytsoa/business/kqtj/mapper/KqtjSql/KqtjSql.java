package com.yts.ytsoa.business.kqtj.mapper.KqtjSql;

import com.yts.ytsoa.business.kqtj.model.KqtjModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class KqtjSql {
    public String findAllSql(@Param("kqtjModel") KqtjModel kqtjModel) {
        return new SQL() {
            {
                SELECT("a.*");
                FROM("kqtj_table a");
                /*     LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " c on c.uuid = a.tjr");*/
                if (kqtjModel.getYf() != null) {

                    WHERE("c.yf = #{kqtjModel.yf}");
                }
               /* if (xmcjModel.getWtf() != null && !xmcjModel.getWtf().isEmpty()) {
                    xmcjModel.setWtf("%" + xmcjModel.getWtf() + "%");
                    WHERE("a.wtf like #{xmcjModel.wtf}");
                }*/
            }
        }.toString();
    }
}
