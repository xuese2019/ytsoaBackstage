package com.yts.ytsoa.business.yzsq.mapper.YzsqSql;

import com.yts.ytsoa.business.yzsq.model.YzsqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class YzsqSql {
    public String addYzsq(@Param("model") YzsqModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.YZSQ_TABLE);
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("sqr", "#{model.sqr}");
                VALUES("wjmc", "#{model.wjmc}");
                VALUES("sqrq", "#{model.sqrq}");
                VALUES("yzdw", "#{model.yzdw}");
                VALUES("sqlx", "#{model.sqlx}");
                VALUES("bz", "#{model.bz}");
                VALUES("ssnf", "#{model.ssnf}");
                VALUES("shzt", "#{model.shzt}");
                VALUES("xmid", "#{model.xmid}");
                VALUES("xmmc", "#{model.xmmc}");
            }
        }.toString();
    }

    public String find(@Param("model") YzsqModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(Tables.YZSQ_TABLE);
                if (model.getSqr() != null && !model.getSqr().isEmpty()) {
                    WHERE("sqr=#{model.sqr}");
                }
                if (model.getXmid() != null && !model.getXmid().isEmpty()) {
                    WHERE("xmid=#{model.xmid}");
                }
                if (model.getWjmc() != null && !model.getWjmc().isEmpty()) {
                    model.setWjmc("%" + model.getWjmc() + "%");
                    WHERE("wjmc like concat ('%',#{model.wjmc},'%')");
                }
            }
        }.toString();
    }

    public String update(@Param("model") YzsqModel model) {
        return new SQL() {
            {
                UPDATE(Tables.YZSQ_TABLE);
                if (model.getWjmc() != null && !model.getWjmc().isEmpty()) {
                    SET("wjmc=#{model.wjmc}");
                }
                if (model.getSqr() != null && !model.getSqr().isEmpty()) {
                    SET("sqr=#{model.sqr}");
                }
                if (model.getSqrq() != null) {
                    SET("sqrq=#{model.sqrq}");
                }
                if (model.getShzt() != 0) {
                    SET("shzt={model.shzt}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String yzgl(@Param("model") YzsqModel model) {
        return new SQL() {
            {
                SELECT("x.xmmc,y.wjmc,y.sqr,y.sqrq,y.shzt");
                FROM(Tables.YZSQ_TABLE + " y join xmwp_table x on x.uuid=y.xmid");
                if (model.getSqr() != null && !model.getSqr().isEmpty()) {
                    WHERE("y.sqr=#{model.sqr}");
                }
                if (model.getXmid() != null && !model.getXmid().isEmpty()) {
                    WHERE("y.xmid=#{model.xmid}");
                }
            }
        }.toString();
    }

    public String yzsh(@Param("model") YzsqModel model) {
        return new SQL() {
            {
                UPDATE(Tables.YZSQ_TABLE);
                if (model.getShzt() != 0) {
                    SET("shzt=#{model.shzt}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }
}
