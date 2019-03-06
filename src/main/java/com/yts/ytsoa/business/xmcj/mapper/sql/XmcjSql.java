package com.yts.ytsoa.business.xmcj.mapper.sql;

import com.yts.ytsoa.business.xmcj.model.XmcjModel;
import com.yts.ytsoa.business.xmcj.model.XmzmcModel;
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
                if (xmcjModel.getYwzt() != 0) {
                    SET("ywzt=#{xmcjModel.ywzt}");
                }
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
                SELECT("x.uuid,x.xmmc,x.xmkssj, xmfzr,x.xmxcjssj,x.ywzt");
                FROM(Tables.XMWP_TABLE + " x left join bumen_table b on x.cjbm=b.uuid left join account_table a on a.uuid=x.xmfzr");
                if (uuid != null && !uuid.isEmpty()) {
                    WHERE("x.uuid=#{uuid}");
                }
            }
        }.toString();
    }

    public String insertXmzmc(@Param("model") XmzmcModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.XMZMC_TABLE);
                VALUES("uuid", "replace(uuid(),'-','')");
                VALUES("xmzmc", "#{model.xmzmc}");
                VALUES("parentid", "#{model.parentid}");
            }
        }.toString();
    }

    public String findXmzmc(@Param("model") XmzmcModel model) {
        return new SQL() {
            {
                SELECT("x.*");
                FROM(Tables.XMZMC_TABLE + " x");
                WHERE("x.parentid=#{model.parentid}");
            }
        }.toString();
    }
}
