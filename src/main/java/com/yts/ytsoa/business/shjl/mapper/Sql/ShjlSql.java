package com.yts.ytsoa.business.shjl.mapper.Sql;

import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ShjlSql {
    public String findShjl(@Param("prentid") String prentid) {
        return new SQL() {
            {
                SELECT("s.*,a.name");
                FROM(Tables.SHJL_TABLE + " s join account_table a on a.uuid=s.shr");
                WHERE("prentid=#{prentid}");
            }
        }.toString();
    }
}
