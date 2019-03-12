package com.yts.ytsoa.business.bm.mapper.sql;

import com.yts.ytsoa.business.bm.model.BmModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BmSql {
    public String findAllSql(@Param("bmModel") BmModel bmModel) {
        return new SQL() {
            {
                SELECT("a.*");
                FROM("bumen_table a ");
                /* LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " c on c.uuid = a.tjr");*/
           /*     if (zskModel.getTjr() != null && !zskModel.getTjr().isEmpty()) {
                    zskModel.setTjr("%" + zskModel.getTjr() + "%");
                    WHERE("c.name like #{zskModel.tjr}");
                }*/

            }
        }.toString();
    }

    public String addBm(@Param("bmModel") BmModel bmModel) {
        return new SQL() {
            {
                INSERT_INTO("bumen_table");
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("bmmc", "#{bmModel.bmmc}");

            }
        }.toString();
    }

    public String update(@Param("bmModel") BmModel bmModel) {
        return new SQL() {
            {
                UPDATE("bumen_table");

                if (bmModel.getBmmc() != null && !bmModel.getBmmc().isEmpty()) {
                    SET("bmmc=#{bmModel.bmmc}");
                }
                WHERE("uuid=#{bmModel.uuid}");
            }
        }.toString();
    }
}
