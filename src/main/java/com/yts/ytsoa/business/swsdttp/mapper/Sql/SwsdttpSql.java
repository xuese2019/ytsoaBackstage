package com.yts.ytsoa.business.swsdttp.mapper.Sql;

import com.yts.ytsoa.business.swsdttp.model.SwsdttpModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.StringJoiner;

public class SwsdttpSql {
    public String tpsc(@Param("models") List<SwsdttpModel> models) {
        StringJoiner sj = new StringJoiner("");
        models.forEach(k -> {
            String s = new SQL() {
                {
                    INSERT_INTO("swsdttp_table");
                    VALUES("uuid", "replace(uuid(), '-', '')");
                    VALUES("swsdtid", "'" + k.getSwsdtid() + "'");
                    VALUES("tpmc", "'" + k.getTpmc() + "'");
                }
            }.toString();
            sj.add(s + ";");
        });
        return sj.toString().substring(0, sj.toString().length() - 1);
    }
}
