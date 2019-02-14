package com.yts.ytsoa.business.bggl.mapper.BgglSql;

import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.StringJoiner;

public class BgglSql {

    public String addBgglpl(@Param("models") List<BgglModel> models) {
        StringJoiner sj = new StringJoiner("");
        models.forEach(k -> {
            String s = new SQL() {
                {
                    INSERT_INTO(Tables.BGGL_TABLE);
                    VALUES("uuid", "replace(uuid(), '-', '')");
                    VALUES("xmmc", "#{models.xmmc}");
                    VALUES("bgzbr", "#{models.bgzbr}");
                    VALUES("bgmc", "#{models.bgmc}");
                    VALUES("sqf", "#{models.sqf}");
                    VALUES("bgrq", "#{models.bgrq}");
                    VALUES("qzzs1", "#{models.qzzs1}");
                    VALUES("qzzs2", "#{models.qzzs2}");
                    VALUES("bglx", "#{models.bglx}");
                    VALUES("bgbh", "#{models.bgbh}");
                    VALUES("bgfwbh_qz", "#{models.bgfwbhQz}");
                    VALUES("bgfwbh_hz", "#{models.bgfwbhHz}");
                    VALUES("bgcs", "#{models.bgcs}");
                    VALUES("zcze", "#{models.zcze}");
                    VALUES("fzze", "#{models.fzze}");
                    VALUES("srlr", "#{models.srlr}");
                    VALUES("lrze", "#{models.lrze}");
                    VALUES("jlr", "#{models.jlr}");
                    VALUES("xgcs", "#{models.xgcs}");
                    VALUES("xmid", "#{models.xmid}");
                    VALUES("shjg", "1");
                }
            }.toString();
            sj.add(s + ";");
        });
        return sj.toString().substring(0, sj.toString().length() - 1);
    }

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
                VALUES("xmid", "#{model.xmid}");
                VALUES("shjg", "1");
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
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String find(@Param("model") BgglModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(Tables.BGGL_TABLE);
                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    WHERE("xmmc like concat ('%',#{model.xmmc},'%')");
                }
                if (model.getSqf() != null && !model.getSqf().isEmpty()) {
                    WHERE("sqf=#{model.sqf}");
                }
                if (model.getXmid() != null && !model.getXmid().isEmpty()) {
                    WHERE("xmid=#{model.xmid}");
                }
                if (model.getShjg() != 0) {
                    WHERE("shjg=1");
                }
            }
        }.toString();
    }

}
