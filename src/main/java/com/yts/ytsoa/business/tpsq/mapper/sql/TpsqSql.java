package com.yts.ytsoa.business.tpsq.mapper.sql;

import com.yts.ytsoa.business.pjsq.model.PjsqModel;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.tpsq.model.TpsqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class TpsqSql {
    public String findAllSql(@Param("model") TpsqModel model) {
        return new SQL() {
            {
                SELECT("t.*,a.name");
                FROM(Tables.TPSQ_TABLE + " t join account_table a on a.uuid=t.xmfzr");
                if (model.getBh() != null && !model.getBh().isEmpty()) {
                    WHERE("t.bh like concat('%','#{model.bh}')");
                }
            }
        }.toString();
    }

    public String tpsh(@Param("model") XmshModel model) {
        return new SQL() {
            {
                INSERT_INTO("shjl_table");
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("prentid", "#{model.prentid}");
                VALUES("shyj", "#{model.shyj}");
                VALUES("shjg", "#{model.shjg}");
                VALUES("shsj", "#{model.shsj}");
                VALUES("shr", "#{model.shr}");
            }
        }.toString();
    }

    public String update(@Param("model") PjsqModel model) {
        return new SQL() {
            {
                UPDATE(Tables.TPSQ_TABLE);
                if (model.getShzt() != 0) {
                    SET("shzt=#{model.shzt}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }
}
