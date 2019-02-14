package com.yts.ytsoa.business.xmcj.mapper.sql;

import com.yts.ytsoa.business.xmcj.model.XmcjModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XmcjSql {
    public String findAllSql(@Param("xmcjModel") XmcjModel xmcjModel) {
        return new SQL() {
            {
                SELECT("a.*");
                FROM("xmwp_table a");
                if (xmcjModel.getXmmc() != null && !xmcjModel.getXmmc().isEmpty()) {
                    xmcjModel.setXmmc("%" + xmcjModel.getXmmc() + "%");
                    WHERE("a.xmmc like #{xmcjModel.xmmc}");
                }
                if (xmcjModel.getWtf() != null && !xmcjModel.getWtf().isEmpty()) {
                    xmcjModel.setWtf("%" + xmcjModel.getWtf() + "%");
                    WHERE("a.wtf like #{xmcjModel.wtf}");
                }
                if (xmcjModel.getYwlx() != null && !xmcjModel.getYwlx().isEmpty()) {
                    xmcjModel.setYwlx("%" + xmcjModel.getYwlx() + "%");
                    WHERE("a.ywlx like #{xmcjModel.ywlx}");
                }
                if (xmcjModel.getXmfl() != null && !xmcjModel.getXmfl().isEmpty()) {
                    xmcjModel.setXmfl("%" + xmcjModel.getXmfl() + "%");
                    WHERE("a.xmfl like #{xmcjModel.xmfl}");
                }
                if (xmcjModel.getShr() != null && !xmcjModel.getShr().isEmpty()) {
                    xmcjModel.setShr("%" + xmcjModel.getShr() + "%");
                    WHERE("a.shr like #{xmcjModel.shr}");
                }
                if (xmcjModel.getYwzt() > 0) {
                    WHERE("a.ywzt = #{xmcjModel.ywzt}");
                }
            }
        }.toString();
    }

    public String updateById(@Param("xmcjModel") XmcjModel xmcjModel) {
        return new SQL() {
            {
                UPDATE("xmwp_table");
                if (xmcjModel.getXmzmc() != null && !xmcjModel.getXmzmc().isEmpty()) {
                    SET("xmzmc=#{xmcjModel.xmzmc}");
                }
                if (xmcjModel.getYwzt() != 0) {
                    SET("ywzt=#{xmcjModel.ywzt}");
                }
                /*if (xmcjModel.getGsryap() != null && !xmcjModel.getGsryap().isEmpty()) {
                    SET("gsryap=#{xmcjModel.gsryap}");
                }*/
                if (xmcjModel.getFxpg() != null && !xmcjModel.getFxpg().isEmpty()) {
                    SET("fxpg=#{xmcjModel.fxpg}");
                }
                if (xmcjModel.getYwlx() != null && !xmcjModel.getYwlx().isEmpty()) {
                    SET("ywlx=#{xmcjModel.ywlx}");
                }
                if (xmcjModel.getXmfl() != null && !xmcjModel.getXmfl().isEmpty()) {
                    SET("xmfl=#{xmcjModel.xmfl}");
                }
                if (xmcjModel.getXmfzr() != null && !xmcjModel.getXmfzr().isEmpty()) {
                    SET("xmfzr=#{xmcjModel.xmfzr}");
                }
                if (xmcjModel.getShr() != null && !xmcjModel.getShr().isEmpty()) {
                    SET("shr=#{xmcjModel.shr}");
                }
                if (xmcjModel.getXmkssj() != null) {
                    SET("xmkssj=#{xmcjModel.xmkssj}");
                }
                if (xmcjModel.getXmxcjssj() != null) {
                    SET("xmxcjssj=#{xmcjModel.xmxcjssj}");
                }
                if (xmcjModel.getBgcjsj() != null) {
                    SET("bgcjsj=#{xmcjModel.bgcjsj}");
                }
                if (xmcjModel.getXmbzgs() != null && !xmcjModel.getXmbzgs().isEmpty()) {
                    SET("xmbzgs=#{xmcjModel.xmbzgs}");
                }
                WHERE("uuid = #{xmcjModel.uuid}");
            }

        }.toString();
    }

    public String findById(@Param("uuid") String uuid) {
        return new SQL() {
            {
                SELECT("x.*,CONCAT(zj.zzjgmc,'/',z.zzjgmc) as zzjgmc,a.name as cjr");
                FROM(Tables.XMWP_TABLE + " x");
                LEFT_OUTER_JOIN("account_table a ON x.xmfzr = a.uuid");
                LEFT_OUTER_JOIN("zzjg_table z on z.uuid = x.cjbm");
                LEFT_OUTER_JOIN("zzjg_table zj on zj.uuid = z.zzjgfj");
                WHERE("x.uuid = #{uuid}");
            }
        }.toString();
    }
}
