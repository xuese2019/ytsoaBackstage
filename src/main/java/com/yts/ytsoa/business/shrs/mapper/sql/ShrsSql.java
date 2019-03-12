package com.yts.ytsoa.business.shrs.mapper.sql;

import com.yts.ytsoa.business.shrs.model.ShrsModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ShrsSql {
    public String findAllSql(@Param("shrsModel") ShrsModel shrsModel) {
        return new SQL() {
            {
                SELECT("s.uuid,a.name AS 'shr',s.shbm");
                FROM("shrs_table s");
                LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " a on a.uuid = s.shr");

            }
        }.toString();
    }

    public String updateByIdSql(@Param("shrsModel") ShrsModel shrsModel) {
        return new SQL() {
            {
                UPDATE("shrs_table");
             /*   if (shrsModel.getShlb() != 0) {
                    SET("shlb=#{shrsModel.shlb}");
                }*/
                if (shrsModel.getBmjl() != null && !shrsModel.getBmjl().isEmpty()) {
                    SET("bmjl=#{shrsModel.bmjl}");
                }
                WHERE("shlb = #{gdglModel.shlb}");
            }
        }.toString();
    }
}
