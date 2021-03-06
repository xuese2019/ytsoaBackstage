package com.yts.ytsoa.business.gzrz.mapper.Sql;

import com.yts.ytsoa.business.gzrz.model.GzrzModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class GzrzSql {
    public String find(@Param("model") GzrzModel model) {
        return new SQL() {
            {
                SELECT("x.uuid,a.name as tjr,x.tjsj,x.rzlx,x.gznr,x.yxgs,x.trgxmsj,x.xmmc,x.sfcc,x.ccdd,x.shjg");
                FROM(Tables.GZRZ_TABLE + " x ");
                LEFT_OUTER_JOIN("account_table a ON x.tjr = a.uuid");
                ORDER_BY("x.tjsj Desc");
                if (model.getSsnf() != null && !model.getSsnf().isEmpty()) {
                    WHERE("ssnf=#{model.ssnf}");
                }
                if (model.getShjg() != 0) {
                    WHERE("x.shjg=#{model.shjg}");
                }
                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    model.setXmmc("%" + model.getXmmc() + "%");
                    WHERE("x.xmmc like #{model.xmmc}");
                }
            /*    if (model.getTjr() != null && !model.getTjr().isEmpty()) {
                    model.setTjr("%" + model.getTjr() + "%");
                    WHERE("x.tjr like #{model.tjr}");
                }*/
                if (model.getYxgs() != 0) {
                    WHERE("x.yxgs=#{model.yxgs}");
                }
                if (model.getRq() != null) {
                    WHERE("x.rq=#{model.rq}");
                }
                if (model.getTjsj() != null) {
                    WHERE("x.tjsj=#{model.tjsj}");
                }
            }
        }.toString();
    }

    public String addGzrz(@Param("model") GzrzModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.GZRZ_TABLE);
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("xmid", "#{model.xmid}");
                VALUES("trgxmsj", "#{model.trgxmsj}");
                VALUES("sfcc", "#{model.sfcc}");
                VALUES("gznr", "#{model.gznr}");
                VALUES("tjsj", "#{model.tjsj}");
                VALUES("tjr", "#{model.tjr}");
                VALUES("rzlx", "#{model.rzlx}");
                VALUES("xmmc", "#{model.xmmc}");
                VALUES("yxgs", "#{model.yxgs}");
                VALUES("ccdd", "#{model.ccdd}");
                VALUES("shjg", "#{model.shjg}");
                VALUES("rzdp", "#{model.rzdp}");
            }
        }.toString();
    }

    public String findByXmid(@Param("model") GzrzModel model) {
        return new SQL() {
            {
                SELECT("g.tjsj,a.name as 'tjr',g.yxgs,g.gznr");
                FROM(Tables.GZRZ_TABLE + " g join account_table a on a.uuid=g.tjr");
                if (model.getXmid() != null && !model.getXmid().isEmpty()) {
                    WHERE("xmid=#{model.xmid}");
                }
                if (model.getTjsj() != null) {
                    WHERE("tjsj=#{model.tjsj}");
                }
            }
        }.toString();
    }

    public String rgtj(@Param("model") GzrzModel model) {
        return new SQL() {
            {
                SELECT("g.uuid,g.xmid,a.name as tjr,SUM(g.trgxmsj) as trgxmsj,SUM(g.trgxmsj)*a.zj as xmts,COUNT(CASE when g.sfcc=1 then 1 else null end) as ccts");
                FROM(Tables.GZRZ_TABLE + " g join " + Tables.ACCOUNT_TABLE + " a on g.tjr=a.uuid");
                if (model.getXmid() != null && !model.getXmid().isEmpty()) {
                    WHERE("g.xmid=#{model.xmid}");
                }
                GROUP_BY("g.tjr");
            }
        }.toString();
    }

    public String update(@Param("gzrzModel") GzrzModel gzrzModel) {
        return new SQL() {
            {
                UPDATE("gzrz_table");
                if (gzrzModel.getShjg() != 0) {
                    SET("shjg=#{gzrzModel.shjg}");
                }
                if (gzrzModel.getShyj() != null && !gzrzModel.getShyj().isEmpty()) {
                    SET("shyj=#{gzrzModel.shyj}");
                }
                if (gzrzModel.getShr() != null && !gzrzModel.getShr().isEmpty()) {
                    SET("shr=#{gzrzModel.shr}");
                }
                WHERE("uuid=#{gzrzModel.uuid}");
            }
        }.toString();
    }
}
