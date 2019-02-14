package com.yts.ytsoa.business.gdgl.mapper.GdglSql;

import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.gdgl.query.GdglQueryModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class GdglSql {
    public String addGdsq(@Param("model") GdglModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.DGGD_TABLE);
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("gdsqbh_hz", "#{model.gdsqbh_hz}");
                VALUES("damc", "#{model.damc}");
                VALUES("sqrq", "#{model.sqrq}");
                VALUES("sqf", "#{model.sqf}");
                VALUES("das", "#{model.das}");
                VALUES("gdlx", "#{model.gdlx}");
                VALUES("dgcs", "#{model.dgcs}");
                VALUES("gryyda", "#{model.gryyda}");
                VALUES("xmmc", "#{model.xmmc}");
                VALUES("xmzmc", "#{model.xmzmc}");
                VALUES("bgbh", "#{model.bgbh}");
                VALUES("sfby", "#{model.sfby}");
                VALUES("sfzz", "#{model.sfzz}");
                VALUES("sfdz", "#{model.sfdz}");
                VALUES("bz", "#{model.bz}");
                VALUES("fjgs", "#{model.fjgs}");
                VALUES("ssnf", "#{model.ssnf}");
                VALUES("xmid", "#{model.xmid}");
                VALUES("zt", "#{model.zt}");
                VALUES("jyzt", "#{model.jyzt}");
            }
        }.toString();
    }

    /**
     * 查询所有
     *
     * @param model
     * @return
     */
    public String find(@Param("model") GdglModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM("dggd_table a");
                if (model.getXmid() != null) {
                    WHERE("a.xmid=#{model.xmid}");
                }
                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    WHERE("a.xmmc=#{model.xmmc}");
                }

                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    model.setXmmc("%" + model.getXmmc() + "%");
                    WHERE("a.xmmc like #{model.xmmc}");
                }
                if (model.getSqf() != null && !model.getSqf().isEmpty()) {
                    model.setSqf("%" + model.getSqf() + "%");
                    WHERE("a.sqf like #{model.sqf}");
                }
                if (model.getDas() != null && !model.getDas().isEmpty()) {
                    model.setDas("%" + model.getDas() + "%");
                    WHERE("a.das like #{model.das}");
                }
                if (model.getDamc() != null && !model.getDamc().isEmpty()) {
                    model.setDamc("%" + model.getDamc() + "%");
                    WHERE("a.damc like #{model.damc}");
                }
                if (model.getXmid() != null && !model.getXmid().isEmpty()) {
                    WHERE("xmid=#{model.xmid}");
                }
                if (model.getJyzt() != 0 && !model.getXmid().isEmpty()) {
                    WHERE("jyzt=#{model.jyzt}");
                }

            }
        }.toString();
    }

    public String findGdsh(@Param("model") GdglQueryModel model) {
        return new SQL() {
            {
                SELECT("g.gdsqbh_hz,g.damc,g.ssnf,x.bsjdw,x.cjbm,g.bgcs,g.dgcs");
                FROM(Tables.XMWP_TABLE + " x join " + Tables.DGGD_TABLE + " g on x.xmmc =g.xmmc ");

                if (model.getGdsqbh_hz() != null) {
                    WHERE("g.gdsqbh_hz=#{model.gdsqbh_hz}");
                }
                if (model.getBsjdw() != null && !model.getBsjdw().isEmpty()) {
                    WHERE("x.bsjdw=#{model.bsjdw}");
                }
                if (model.getCjbm() != null && !model.getCjbm().isEmpty()) {
                    WHERE("x.cjbm=#{model.cjbm}");
                }
                if (model.getSsnf() != null && !model.getSsnf().isEmpty()) {
                    WHERE("g.ssnf=#{model.ssnf}");
                }


            }
        }.toString();
    }

    public String updateByIdSql(@Param("gdglModel") GdglModel gdglModel) {
        return new SQL() {
            {
                UPDATE("dggd_table");
                if (gdglModel.getSqrq() != null) {
                    SET("sqrq=#{gdglModel.sqrq}");
                }
                if (gdglModel.getSqf() != null && !gdglModel.getSqf().isEmpty()) {
                    SET("sqf=#{gdglModel.sqf}");
                }
                if (gdglModel.getDas() != null) {
                    SET("das=#{gdglModel.das}");
                }
                if (gdglModel.getDgcs() != 0) {
                    SET("dgcs=#{gdglModel.dgcs}");
                }
                if (gdglModel.getDamc() != null && !gdglModel.getDamc().isEmpty()) {
                    SET("damc=#{gdglModel.damc}");
                }
                if (gdglModel.getJyzt() != 0 && !gdglModel.getDamc().isEmpty()) {
                    SET("jyzt=#{gdglModel.jyzt}");
                }
                if (gdglModel.getWczt() != 0) {
                    SET("wczt=9");
                }
                WHERE("uuid = #{gdglModel.uuid}");
            }
        }.toString();
    }

    public String update(@Param("model") GdglModel model) {
        return new SQL() {
            {
                UPDATE(Tables.DGGD_TABLE);
                if (model.getStatus() != 0) {
                    SET("zt=#{model.status}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String findByDamc(@Param("model") GdglModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(Tables.DGGD_TABLE);
                if (model.getDamc() != null && !model.getDamc().isEmpty()) {
                    WHERE("damc like concat ('%',#{model.damc},'%')");
                }
                if (model.getJyzt() != 0) {
                    WHERE("jyzt=#{mdoel.jyzt}");
                }
                WHERE("zt=2");
            }
        }.toString();
    }

    public String updatejyzt(@Param("model") GdglModel model) {
        return new SQL() {
            {
                UPDATE(Tables.DGGD_TABLE);
                if (model.getJyzt() != 0) {
                    SET("jyzt=#{model.jyzt}");
                }
                WHERE("gdsqbh_hz=#{mdoel.gdsqbh_hz}");
            }
        }.toString();
    }
}
