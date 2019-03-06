package com.yts.ytsoa.business.tpsq.mapper.sql;

import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.tpsq.model.TpsqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class TpsqSql {
    public String findAllSql(@Param("model") TpsqModel model) {
        return new SQL() {
            {
                SELECT("t.uuid,t.kpbh,t.tpje,a.name as tpsqr,t.xmfzr as name,t.fplb,t.fpsqr,t.tpsqsj");
                FROM("tpsq_table t LEFT JOIN account_table a on t.tpsqr=a.name");
                if (model.getBh() != null && !model.getBh().isEmpty()) {
                    WHERE("bh like concat('%',#{model.bh},'%')");
                }
                if (model.getKpbh() != null && !model.getKpbh().isEmpty()) {
                    model.setKpbh("%" + model.getKpbh() + "%");
                    WHERE("t.kpbh like #{model.kpbh}");
                }
                if ((model.getShjg() == 1) || (model.getShjg() == 3)) {
                    WHERE("t.shjg=1 or t.shjg=3");
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

    public String update(@Param("model") TpsqModel model) {
        return new SQL() {
            {
                UPDATE(Tables.TPSQ_TABLE);
                if (model.getShjg() != 0) {
                    SET("shjg=#{model.shjg}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String addTpsq(@Param("tpsqModel") TpsqModel tpsqModel) {
        return new SQL() {
            {
                INSERT_INTO(Tables.TPSQ_TABLE);
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("fpsqr", "#{tpsqModel.fpsqr}");
                VALUES("xmfzr", "#{tpsqModel.xmfzr}");
                VALUES("tpsqr", "#{tpsqModel.tpsqr}");

                VALUES("sqf", "#{tpsqModel.sqf}");
                VALUES("tpsqsj", "#{tpsqModel.tpsqsj}");
                VALUES("tpyy", "#{tpsqModel.tpyy}");

                VALUES("kpbh", "#{tpsqModel.kpbh}");
                VALUES("tpje", "#{tpsqModel.tpje}");
                VALUES("fplx", "#{tpsqModel.fplx}");
                VALUES("fplb", "#{tpsqModel.fplb}");
                VALUES("shjg", "#{tpsqModel.shjg}");
                VALUES("prentid", "#{tpsqModel.prentid}");

            }
        }.toString();
    }

    public String findByShjl(@Param("prentid") String prentid) {
        return new SQL() {
            {
                SELECT("y.kpbh,a.name as 'shr',s.shyj,s.shjg");
                FROM("shjl_table s JOIN tpsq_table y ON y.uuid=s.prentid join account_table a on a.uuid=s.shr");
                WHERE("s.prentid=#{prentid}");
            }
        }.toString();
    }
}
