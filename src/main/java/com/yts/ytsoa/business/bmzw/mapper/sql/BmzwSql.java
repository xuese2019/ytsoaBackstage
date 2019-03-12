package com.yts.ytsoa.business.bmzw.mapper.sql;

import com.yts.ytsoa.business.bmzw.model.BmzwModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BmzwSql {
    public String findAllSql(@Param("bmzwModel") BmzwModel bmzwModel) {
        return new SQL() {
            {
                SELECT("a.uuid,a.bmzw,(case a.bmparentid when '0' THEN a.bmparentid else b.bmmc END) as bmparentid");
                FROM("bmzw_table a left join bumen_table b on b.uuid=a.bmparentid");
                /* LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " c on c.uuid = a.tjr");*/
           /*     if (zskModel.getTjr() != null && !zskModel.getTjr().isEmpty()) {
                    zskModel.setTjr("%" + zskModel.getTjr() + "%");
                    WHERE("c.name like #{zskModel.tjr}");
                }*/

            }
        }.toString();
    }

    public String update(@Param("bmzwModel") BmzwModel bmzwModel) {
        return new SQL() {
            {
                UPDATE("bmzw_table");
                if (bmzwModel.getBmparentid() != null && !bmzwModel.getBmparentid().isEmpty()) {
                    SET("bmparentid=#{bmzwModel.bmparentid}");
                }
                if (bmzwModel.getBmzw() != null && !bmzwModel.getBmzw().isEmpty()) {
                    SET("bmzw=#{bmzwModel.bmzw}");
                }
                WHERE("uuid=#{bmzwModel.uuid}");
            }
        }.toString();
    }

    public String addBmzw(@Param("bmzwModel") BmzwModel bmzwModel) {
        return new SQL() {
            {
                INSERT_INTO("bmzw_table");
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("bmparentid", "#{bmzwModel.bmparentid}");
                VALUES("bmzw", "#{bmzwModel.bmzw}");

            }
        }.toString();
    }
}
