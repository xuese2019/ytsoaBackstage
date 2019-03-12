package com.yts.ytsoa.business.shrsz.mapper.Sql;

import com.yts.ytsoa.business.shrsz.model.ShrszModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ShrszSql {
    public String shrsz(@Param("model") ShrszModel model) {
        return new SQL() {
            {
                UPDATE(Tables.SHRSZ_TABLE);
                if (model.getBmjlid() != null && !model.getBmjlid().isEmpty()) {
                    SET("bmjlid=#{model.bmjlid}");
                }
                if (model.getZgbid() != null && !model.getZgbid().isEmpty()) {
                    SET("zgbid=#{model.zgbid}");
                }
                if (model.getHhrid() != null && !model.getHhrid().isEmpty()) {
                    SET("hhrid=#{model.hhrid}");
                }
                WHERE("uuid=#{model.uuid}");

            }
        }.toString();
    }

    public String find(@Param("xmid") String uuid) {
        return new SQL() {
            {
                SELECT("s.hhrid,s.zbgid");
                FROM(Tables.SHRSZ_TABLE);
                if (uuid != null && !uuid.isEmpty()) {
                    WHERE("s.xmid=#{xmid}");
                }
            }
        }.toString();
    }
}
