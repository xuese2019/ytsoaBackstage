package com.yts.ytsoa.business.sz.mapper.sql;

import com.yts.ytsoa.business.sz.model.SzModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SzSql {
    public String findAllSql() {
        return new SQL() {
            {
                SELECT("a.name as uuid,s.accid");
                FROM("suozhang_table s");
                LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " a on a.uuid = s.accid");
           /*     if (zskModel.getTjr() != null && !zskModel.getTjr().isEmpty()) {
                    zskModel.setTjr("%" + zskModel.getTjr() + "%");
                    WHERE("c.name like #{zskModel.tjr}");
                }*/

            }
        }.toString();
    }

    public String update(@Param("szModel") SzModel szModel) {
        return new SQL() {
            {
                UPDATE("suozhang_table");
                if (szModel.getAccid() != null && !szModel.getAccid().isEmpty()) {
                    SET("accid=#{szModel.accid}");
                }
            }
        }.toString();
    }

    public String addSz(@Param("szModel") SzModel szModel) {
        return new SQL() {
            {
                INSERT_INTO(Tables.SZ_TABLE);
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("accid", "#{szModel.accid}");

            }
        }.toString();
    }
}
