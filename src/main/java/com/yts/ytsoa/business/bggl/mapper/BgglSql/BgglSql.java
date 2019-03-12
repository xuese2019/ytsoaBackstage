package com.yts.ytsoa.business.bggl.mapper.BgglSql;

import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BgglSql {
    public String addBggl(@Param("model") BgglModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.BGGL_TABLE);
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("xmmc", "#{model.xmmc}");
                VALUES("bgzbr", "#{model.bgzbr}");
                VALUES("bgmc", "#{model.bgmc}");
                VALUES("sqf", "#{model.sqf}");
                VALUES("bgrq", "#{model.bgrq}");
                VALUES("qzzs1", "#{model.qzzs1}");
                VALUES("qzzs2", "#{model.qzzs2}");
                VALUES("bglx", "#{model.bglx}");
                VALUES("bgbh", "#{model.bgbh}");
                VALUES("bgfwbh_qz", "#{model.bgfwbhQz}");
                VALUES("bgfwbh_hz", "#{model.bgfwbhHz}");
                VALUES("bgcs", "#{model.bgcs}");
                VALUES("zcze", "#{model.zcze}");
                VALUES("fzze", "#{model.fzze}");
                VALUES("srlr", "#{model.srlr}");
                VALUES("lrze", "#{model.lrze}");
                VALUES("jlr", "#{model.jlr}");
                VALUES("xgcs", "#{model.xgcs}");
                VALUES("xgyy", "#{model.xgyy}");
                VALUES("xmid", "#{model.xmid}");
                VALUES("shjg", "#{model.shjg}");
                VALUES("gdyxq", "#{model.gdyxq}");
            }
        }.toString();
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public String update(@Param("model") BgglModel model) {
        return new SQL() {
            {
                UPDATE(Tables.BGGL_TABLE);
                if (model.getWczt() != 0) {
                    SET("wczt=#{model.wczt}");
                }
                if (model.getShjg() != 0) {
                    SET("shjg=#{model.shjg}");
                }
                if (model.getGdzt() != 0) {
                    SET("gdzt=#{model.gdzt}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String find(@Param("model") BgglModel model) {
        return new SQL() {
            {
                SELECT("b.uuid,b.bgbh,b.bgzbr,b.sqf,b.bglx,b.bgcs,b.bgrq,b.shjg,b.gdyxq,x.xmzmc,a.name as zbr,xx.xmmc as xmmc");
                FROM(Tables.BGGL_TABLE + " b ");
                LEFT_OUTER_JOIN("xmzmc_table x on b.xmid=x.uuid");
                LEFT_OUTER_JOIN("account_table a on a.uuid=b.bgzbr");
                LEFT_OUTER_JOIN("xmwp_table xx on xx.uuid=x.parentid");
                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    WHERE("b.xmmc like concat ('%',#{model.xmmc},'%')");
                }
                if (model.getSqf() != null && !model.getSqf().isEmpty()) {
                    WHERE("b.sqf=#{model.sqf}");
                }
                if (model.getXmid() != null && !model.getXmid().isEmpty()) {
                    WHERE("b.xmid=#{model.xmid}");
                }
                if (model.getShjg() != 0) {
                    WHERE("b.shjg=#{model.shjg}");
                }
                if (model.getBgzbr() != null && !model.getBgzbr().isEmpty()) {
                    WHERE("b.bgzbr=#{model.bgzbr}");
                }
            }
        }.toString();
    }

    public String findBgByXmid(@Param("xmid") String xmid) {
        return new SQL() {
            {
                SELECT("b.uuid,\n" +
                        "\tb.bgbh,\n" +
                        "\ta. NAME AS bgzbr,\n" +
                        "\tb.sqf,\n" +
                        "\tb.bglx,\n" +
                        "\tb.bgcs,\n" +
                        "\tb.bgrq");
                FROM("\tbggl_table b\n" +
                        "LEFT JOIN account_table a ON a.uuid = b.bgzbr\n" +
                        "LEFT JOIN xmzmc_table x ON x.uuid = b.xmid");
                WHERE("b.xmid in(\n" +
                        "\n" +
                        "SELECT x.uuid\n" +
                        "FROM xmzmc_table x \n" +
                        "WHERE x.parentid=#{xmid} and b.shjg=6\n" +
                        ")");
            }
        }.toString();
    }

    public String updateGdyxq(@Param("model") BgglModel model) {
        return new SQL() {
            {
                UPDATE(Tables.BGGL_TABLE);
                if (model.getGdyxq() != null) {
                    SET("gdyxq=#{model.gdyxq}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String findXmYwztByBgXmid(@Param("prentid") String prentid) {
        return new SQL() {
            {
                SELECT("xm.*");
                FROM(" shjl_table s join bggl_table b on b.uuid=s.prentid join xmzmc_table x on x.uuid=b.xmid left JOIN xmwp_table xm on xm.uuid=x.parentid");
                if (prentid != null && !prentid.isEmpty()) {
                    WHERE("s.prentid=#{prentid}");
                }
                WHERE("s.shjg=2");
            }
        }.toString();
    }
}
