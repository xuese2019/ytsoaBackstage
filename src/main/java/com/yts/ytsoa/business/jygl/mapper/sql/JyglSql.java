package com.yts.ytsoa.business.jygl.mapper.sql;

import com.yts.ytsoa.business.jygl.model.JyglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class JyglSql {
    public String findAllSql(@Param("model") JyglModel jyglModel) {
        return new SQL() {
            {
                SELECT("j.uuid,j.dgjybh,d.damc,j.jyrq,a.name AS 'jyr',j.ghrq,a1.name as'ghr',j.shjg");
                FROM("jygl_table j LEFT JOIN account_table a on a.uuid=j.jyr LEFT JOIN dggd_table d on d.gdsqbh_hz=j.dgjybh LEFT JOIN account_table a1 ON j.ghr=a1.uuid");
                if (jyglModel.getDamc() != null && !jyglModel.getDamc().isEmpty()) {
                    jyglModel.setDamc("%" + jyglModel.getDamc() + "%");
                    WHERE("j.damc like #{model.damc}");
                }
                if (jyglModel.getXmmc() != null && !jyglModel.getXmmc().isEmpty()) {
                    jyglModel.setXmmc("%" + jyglModel.getXmmc() + "%");
                    WHERE("j.xmmc like #{model.xmmc}");
                }
                if (jyglModel.getDgjybh() != null && !jyglModel.getDgjybh().isEmpty()) {
                    jyglModel.setDgjybh("%" + jyglModel.getDgjybh() + "%");
                    WHERE("j.dgjybh like #{model.dgjybh}");
                }
                if ((jyglModel.getShjg() == 1) || (jyglModel.getShjg() == 3)) {
                    WHERE("t.shjg=1 or t.shjg=3");
                }
                if (jyglModel.getJyzt() != 0) {
                    WHERE("j.jyzt=#{model.jyzt}");
                }
                if (jyglModel.getGhr() != null && !jyglModel.getGhr().isEmpty()) {
                    WHERE("ghr=#{model.ghr}");
                }
            }

        }.toString();
    }

    public String updateByIdSql(@Param("model") JyglModel jyglModel) {
        return new SQL() {
            {
                UPDATE("jygl_table");

                if (jyglModel.getDamc() != null && !jyglModel.getDamc().isEmpty()) {
                    SET("damc=#{model.damc}");
                }
                if (jyglModel.getJyrq() != null) {
                    SET("jyrq=#{model.jyrq}");
                }
                if (jyglModel.getGhr() != null && !jyglModel.getGhr().isEmpty()) {
                    SET("ghr=#{model.ghr}");
                }
                if (jyglModel.getGhrq() != null) {
                    SET("ghrq=#{model.ghrq}");
                }
                if (jyglModel.getBz() != null && !jyglModel.getBz().isEmpty()) {
                    SET("bz=#{model.bz}");
                }
                if (jyglModel.getShjg() != 0) {
                    SET("shjg=#{model.shjg}");
                }
                if (jyglModel.getJyzt() != 0) {
                    SET("jyzt=#{model.jyzt}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    /*public String add(@Param("model") JyglModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.JYGL_TABLE);
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("dgjybh", "#{model.dgjybh}");
                VALUES("damc", "#{model.damc}");
                VALUES("bz", "#{model.bz}");
                VALUES("jyrq", "#{model.jyrq}");
                VALUES("jyr", "#{model.dgid}");
                VALUES("xmmc", "#{model.xmmc}");
                VALUES("dgid", "#{model.dgid}");
                VALUES("jyzt", "#{model.jyzt}");
            }
        }.toString();
    }*/
    public String add(@Param("model") JyglModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.JYGL_TABLE);
                VALUES("uuid", "replace(uuid(),'-','')");
                if (model.getDamc() != null && !model.getDamc().isEmpty()) {
                    VALUES("damc", "#{model.damc}");
                }
                if (model.getSsnf() != null) {
                    VALUES("ssnf", "#{model.ssnf}");
                }
                if (model.getBz() != null && !model.getBz().isEmpty()) {
                    VALUES("bz", "#{model.bz}");
                }
                if (model.getJyrq() != null) {
                    VALUES("jyrq", "#{model.jyrq}");
                }
                if (model.getJyr() != null && !model.getJyr().isEmpty()) {
                    VALUES("jyr", "#{model.jyr}");
                }
                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    VALUES("xmmc", "#{model.xmmc}");
                }
                if (model.getJyzt() != 0) {
                    VALUES("jyzt", "#{model.jyzt}");
                }
                if (model.getDgid() != null && !model.getDgid().isEmpty()) {
                    VALUES("dgid", "#{model.dgid}");
                }
                if (model.getDgjybh() != null && !model.getDgjybh().isEmpty()) {
                    VALUES("dgjybh", "#{model.dgjybh}");
                }
                if (model.getShjg() != 0) {
                    VALUES("shjg", "#{model.shjg}");
                }
            }
        }.toString();
    }

    public String update(@Param("model") JyglModel model) {
        return new SQL() {
            {
                UPDATE(Tables.JYGL_TABLE);
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
                SELECT("j.dgjybh,a.name AS'shr',s.shyj,s.shjg");
                FROM("shjl_table s JOIN jygl_table j ON j.uuid=s.prentid JOIN account_table a ON a.uuid=s.shr");
                WHERE("s.prentid=#{prentid}");
            }
        }.toString();
    }
}
