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
                VALUES("yzfs", "#{model.yzfs}");
                VALUES("shjg", "#{model.shjg}");
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
                if (model.getYzfs() != 0) {
                    WHERE("yzfs=#{model.yzfs}");
                }
                if (model.getWjmc() != null && !model.getWjmc().isEmpty()) {
                    model.setWjmc("%" + model.getWjmc() + "%");
                    WHERE("wjmc like concat ('%',#{model.wjmc},'%')");
                }
                if ((model.getShjg() == 1) || (model.getShjg() == 3)) {
                    WHERE("shjg=1 or shjg=3");
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
                if (model.getShjg() != 0) {
                    SET("shjg=#{model.shjg}");
                }
                if (model.getYzfs() != 0) {
                    SET("yzfs=#{model.yzfs}");
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
                SELECT("y.wjmc,a.name as shr,s.shyj,s.shjg");
                FROM("shjl_table s JOIN yzsq_table y ON y.uuid=s.prentid join account_table a on a.uuid=s.shr");
                WHERE("s.prentid=#{prentid}");
            }
        }.toString();
    }

    public String updateByShjg(@Param("model") YzsqModel model) {
        return new SQL() {
            {
                UPDATE(Tables.YZSQ_TABLE);
                if (model.getShjg() != 0) {
                    SET("shjg=#{model.shjg}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }
}
