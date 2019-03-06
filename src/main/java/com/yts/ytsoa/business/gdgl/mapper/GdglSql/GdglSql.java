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
                VALUES("shjg", "#{model.shjg}");
            }
        }.toString();
    }

    public String find(@Param("model") GdglModel model) {
        return new SQL() {
            {
                SELECT("d.uuid,d.gdsqbh_hz,d.sqrq,d.sqf,d.xmmc,x.xmzmc AS xmzmc,d.bgbh,d.das,d.damc,d.dgcs,d.zt,d.jyzt,d.shjg");
                FROM("dggd_table  d ");
                LEFT_OUTER_JOIN("xmzmc_table x ON x.uuid=d.xmzmc");
                if (model.getXmid() != null) {
                    WHERE("a.xmid=#{model.xmid}");
                }
                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    model.setXmmc("%" + model.getXmmc() + "%");
                    WHERE("d.xmmc like #{model.xmmc}");
                }
                if (model.getSqf() != null && !model.getSqf().isEmpty()) {
                    model.setSqf("%" + model.getSqf() + "%");
                    WHERE("d.sqf like #{model.sqf}");
                }
                if (model.getDas() != null && !model.getDas().isEmpty()) {
                    model.setDas("%" + model.getDas() + "%");
                    WHERE("d.das like #{model.das}");
                }
                if (model.getDamc() != null && !model.getDamc().isEmpty()) {
                    WHERE("d.damc like concat ('%',#{model.damc},'%')");
                }

                if (model.getJyzt() != 0) {
                    WHERE("d.jyzt=#{model.jyzt}");
                }
                if (model.getBgbh() != null && !model.getBgbh().isEmpty()) {
                    WHERE("d.bgbh=#{model.bgbh}");
                }
                if ((model.getShjg() == 1) || (model.getShjg() == 3)) {
                    WHERE("d.shjg=1 or d.shjg=3 ");
                }
               /* if ((model.getShjg() !=0)) {
                    WHERE(" d.shjg=#{model.shjg}");
                }*/
            }
        }.toString();
    }

/*    public String find() {
        return new SQL() {

        }.toString();
    }*/

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
                if (gdglModel.getShjg() != 0) {
                    SET("shjg=#{gdglModel.shjg}");
                }
                WHERE("uuid = #{gdglModel.uuid}");
            }
        }.toString();
    }

    public String update(@Param("model") GdglModel model) {
        return new SQL() {
            {
                UPDATE(Tables.DGGD_TABLE);
                if (model.getShjg() != 0) {
                    SET("shjg=#{model.shjg}");
                }
                if (model.getWczt() != 0) {
                    SET("wczt=#{model.wczt}");
                }
                if (model.getJyzt() != 0) {
                    SET("jyzt=#{model.jyzt}");
                }

                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String updates(@Param("model") GdglModel model) {
        return new SQL() {
            {
                UPDATE(Tables.DGGD_TABLE);
                if (model.getShjg() != 0) {
                    SET("shjg=#{model.shjg}");
                }
                if (model.getWczt() != 0) {
                    SET("wczt=#{model.wczt}");
                }
                if (model.getJyzt() != 0) {
                    SET("jyzt=#{model.jyzt}");
                }

                WHERE("damc=#{model.damc}");
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
                    WHERE("jyzt=#{model.jyzt}");
                }
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
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String findBgByUuid(@Param("uuid") String uuid) {
        return new SQL() {
            {
                SELECT("b.*");
                FROM("bggl_table b join xmzmc_table x on x.uuid=b.xmid");
                if (uuid != null && !uuid.isEmpty()) {
                    WHERE("x.uuid=#{uuid}");
                }
                WHERE("b.shjg>6");
            }
        }.toString();
    }

    public String findByShjl(@Param("prentid") String prentid) {
        return new SQL() {
            {
                SELECT("j.bgbh,a.name AS'shr',s.shyj,s.shjg");
                FROM("shjl_table s JOIN dggd_table j ON j.uuid=s.prentid JOIN account_table a ON a.uuid=s.shr");
                WHERE("s.prentid=#{prentid}");
            }
        }.toString();
    }

    public String findGdByXmid(@Param("xmid") String xmid) {
        return new SQL() {
            {
                SELECT("d.*");
                FROM("dggd_table d  LEFT JOIN xmzmc_table x ON x.uuid = d.xmzmc");
                WHERE("d.xmzmc in(SELECT x.uuid FROM xmzmc_table x WHERE x.parentid=#{xmid} and d.shjg=2)");
            }
        }.toString();
    }

    /**
     * 根据审核记录主键查出项目详情
     *
     * @param uuid
     * @return
     */
    public String findXmwpByShjlUuid(@Param("uuid") String uuid) {
        return new SQL() {
            {
                SELECT("xm.*");
                FROM(" shjl_table s join bggl_table b on s.prentid=b.uuid join xmzmc_table x on x.uuid=b.xmid join xmwp_table xm on xm.uuid=x.parentid");
                if (uuid != null && !uuid.isEmpty()) {
                    WHERE(" s.uuid=#{uuid}");
                }
            }
        }.toString();
    }
}
