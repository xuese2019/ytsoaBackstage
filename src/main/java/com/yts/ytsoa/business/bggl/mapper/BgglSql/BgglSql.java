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
                SELECT("b.*");
                FROM(Tables.BGGL_TABLE + " b");
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
                    WHERE("b.shjg=1");
                }
            }
        }.toString();
    }
}
