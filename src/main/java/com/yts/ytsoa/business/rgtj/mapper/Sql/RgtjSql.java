package com.yts.ytsoa.business.rgtj.mapper.Sql;

import com.yts.ytsoa.business.rgtj.model.RgtjModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class RgtjSql {
    public String insertRgtj(@Param("model") RgtjModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.RGTJ_TABLE);
                VALUES("uuid", "replace(uuid(),'-','')");
                VALUES("name", "#{model.name}");
                VALUES("xmts", "#{model.xmts}");
                VALUES("sjgss", "#{model.sjgss}");
                VALUES("ccts", "#{model.ccts}");
                VALUES("xmid", "#{model.xmid}");
            }
        }.toString();
    }

    public String find(@Param("model") RgtjModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(Tables.RGTJ_TABLE);
                if (model.getXmid() != null && !model.getXmid().isEmpty()) {
                    WHERE("xmid=#{model.xmid}");
                }
            }
        }.toString();
    }
}
