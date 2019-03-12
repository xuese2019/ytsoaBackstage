package com.yts.ytsoa.business.bgshr.mapper.Sql;

import com.yts.ytsoa.business.bgshr.model.BgshrModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BgshrSql {
    public String bgshrsz(@Param("model") BgshrModel model) {
        return new SQL() {
            {
                UPDATE(Tables.BGSHR_TABLE);
                if (model.getBmjlid() != null && !model.getUuid().isEmpty()) {
                    SET("bmjlid=#{model.bmjlid}");
                }
                if (model.getZkbid() != null && !model.getZkbid().isEmpty()) {
                    SET("zkbid=#{model.zkbid}");
                }
                if (model.getHhrid() != null && !model.getHhrid().isEmpty()) {
                    SET("hhrid=#{model.hhrid}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }
}
