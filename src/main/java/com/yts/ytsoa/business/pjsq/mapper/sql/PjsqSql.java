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
                if (model.getShzt() != 0) {
                    WHERE("shzt=1");
                }
            }
        }.toString();
    }

    public String update(@Param("model") PjsqModel model) {
        return new SQL() {
            {
                UPDATE(Tables.PJSQ_TABLE);
                if (model.getShzt() != 0) {
                    SET("shzt=#{model.shzt}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }
}
