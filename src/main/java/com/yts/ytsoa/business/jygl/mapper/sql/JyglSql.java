package com.yts.ytsoa.business.jygl.mapper.sql;

import com.yts.ytsoa.business.jygl.model.JyglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class JyglSql {
    public String findAllSql(@Param("JyglModel") JyglModel jyglModel) {
        return new SQL() {
            {
                SELECT("j.*,a.name");
                FROM("jygl_table j join account_table a on a.uuid=j.jyr");
                if (jyglModel.getDamc() != null && !jyglModel.getDamc().isEmpty()) {
                    jyglModel.setDamc("%" + jyglModel.getDamc() + "%");
                    WHERE("j.damc like #{jyglModel.damc}");
                }
                if (jyglModel.getXmmc() != null && !jyglModel.getXmmc().isEmpty()) {
                    jyglModel.setXmmc("%" + jyglModel.getXmmc() + "%");
                    WHERE("j.xmmc like #{jyglModel.xmmc}");
                }
                if (jyglModel.getDgjybh() != null && !jyglModel.getDgjybh().isEmpty()) {
                    jyglModel.setDgjybh("%" + jyglModel.getDgjybh() + "%");
                    WHERE("j.dgjybh like #{jyglModel.dgjybh}");
                }
            }

        }.toString();
    }

    public String updateByIdSql(@Param("JyglModel") JyglModel jyglModel) {
        return new SQL() {
            {
                UPDATE("jygl_table");
                if (jyglModel.getDamc() != null && !jyglModel.getDamc().isEmpty()) {
                    SET("damc=#{JyglModel.damc}");
                }
                if (jyglModel.getJyrq() != null) {
                    SET("jyrq=#{JyglModel.jyrq}");
                }
                if (jyglModel.getJyr() != null && !jyglModel.getJyr().isEmpty()) {
                    SET("damc=#{JyglModel.damc}");
                }

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
            }
        }.toString();
    }
}
