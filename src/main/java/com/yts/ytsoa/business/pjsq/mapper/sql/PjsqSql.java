package com.yts.ytsoa.business.pjsq.mapper.sql;

import com.yts.ytsoa.business.pjsq.model.PjsqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class PjsqSql {
    public String findAllSql(@Param("pjsqModel") PjsqModel pjsqModel) {
        return new SQL() {
            {
                SELECT("a.*");
                FROM("pjsq_table a");
                if (pjsqModel.getXmmc() != null && !pjsqModel.getXmmc().isEmpty()) {
                    pjsqModel.setXmmc("%" + pjsqModel.getXmmc() + "%");
                    WHERE("a.xmmc like #{pjsqModel.xmmc}");
                }
                if (pjsqModel.getKpbh() != null && !pjsqModel.getKpbh().isEmpty()) {
                    pjsqModel.setKpbh("%" + pjsqModel.getKpbh() + "%");
                    WHERE("a.kpbh like #{pjsqModel.kpbh}");
                }
                if (pjsqModel.getShjg() != 0) {
                    WHERE("a.shjg=#{pjsqModel.shjg}");
                }
            }
        }.toString();
    }

    public String addPjsq(@Param("model") PjsqModel pjsqModel) {
        return new SQL() {
            {
                INSERT_INTO(Tables.PJSQ_TABLE);
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("kpbh", "#{model.kpbh}");
                VALUES("bm", "#{model.bm}");
                VALUES("sqf", "#{model.sqf}");
                VALUES("fpsqr", "#{model.fpsqr}");
                VALUES("kpje", "#{model.kpje}");
                VALUES("fplx", "#{model.fplx}");
                VALUES("fplb", "#{model.fplb}");
                VALUES("kpmc", "#{model.kpmc}");
                VALUES("sh", "#{model.sh}");
                VALUES("dz", "#{model.dz}");
                VALUES("dh", "#{model.dh}");
                VALUES("khh", "#{model.khh}");
                VALUES("zh", "#{model.zh}");
                VALUES("xmfzr", "#{model.xmfzr}");
                VALUES("xmmc", "#{model.xmmc}");
                VALUES("kprq", "#{model.kprq}");
                VALUES("shjg", "#{model.shjg}");
            }
        }.toString();
    }

    public String find(@Param("model") PjsqModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(Tables.PJSQ_TABLE);
                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    WHERE("xmmc like concat('%',#{model.xmmc},'%')");
                }
                if (model.getShjg() != 0) {
                    WHERE("shjg=#{model.shjg}");
                }
            }
        }.toString();
    }

    public String updateByShjg(@Param("model") PjsqModel model) {
        return new SQL() {
            {
                UPDATE(Tables.PJSQ_TABLE);
                if (model.getShjg() != 0) {
                    SET("shjg=#{model.shjg}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String findByShjl(@Param("prentid") String prentid) {
        return new SQL() {
            {
                SELECT("y.kpbh,a.name,s.shyj,s.shjg");
                FROM("shjl_table s JOIN pjsq_table y ON y.uuid=s.prentid join account_table a on a.uuid=s.shr");
                WHERE("s.prentid=#{prentid}");
            }
        }.toString();
    }
}
