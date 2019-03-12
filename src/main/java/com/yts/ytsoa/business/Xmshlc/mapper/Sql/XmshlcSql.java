package com.yts.ytsoa.business.Xmshlc.mapper.Sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XmshlcSql {
    public String findShlx(@Param("uuid") String uuid) {
        return new SQL() {
            {
                SELECT("x.shlx");
                FROM("xmsh_table x join zzjg_table z on x.zzjgmc=z.zzjgmc join account_table a on a.bm=z.uuid");
                if (uuid != null && !uuid.isEmpty()) {
                    WHERE("a.uuid=#{uuid}");
                }
            }
        }.toString();
    }
}
