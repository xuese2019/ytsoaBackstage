package com.yts.ytsoa.business.xmwp.mapper.Sql;

import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XmwpglSql {
    public String find(@Param("model") XmwpModel model) {
        return new SQL() {
            {
                SELECT("x.*,CONCAT(zj.zzjgmc,'/',z.zzjgmc) as zzjgmc,a.name as cjr");
                FROM(Tables.XMWP_TABLE + " x");
                LEFT_OUTER_JOIN("account_table a ON x.xmcjr = a.uuid");
                LEFT_OUTER_JOIN("zzjg_table z on z.uuid = x.cjbm");
                LEFT_OUTER_JOIN("zzjg_table zj on zj.uuid = z.zzjgfj");
                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    WHERE("x.xmmc like concat('%',#{model.xmmc},'%')");
                }
                if (model.getWtsj() != null) {
                    WHERE("x.wtsj = #{model.wtsj}");
                }
                if (model.getXmfzr() != null && !model.getXmfzr().isEmpty()) {
                    WHERE("x.xmfzr=#{model.xmfzr}");
                }
                if (model.getYwzt() != 0 && !model.getXmfzr().isEmpty()) {
                    WHERE("x.ywzt > 2");
                }
            }
        }.toString();
    }

    public String updById(@Param("model") XmwpModel model) {
        return new SQL() {
            {
                UPDATE(Tables.XMWP_TABLE);
                if (model.getWtf() != null && !model.getWtf().isEmpty()) {
                    SET("wtf=#{model.wtf}");
                }
                if (model.getWtflxr() != null && !model.getWtflxr().isEmpty()) {
                    SET("wtflxr=#{model.wtflxr}");
                }
                if (model.getWtflxdh() != null && !model.getWtflxdh().isEmpty()) {
                    SET("wtflxdh=#{model.wtflxdh}");
                }
                if (model.getWtsj() != null) {
                    SET("wtsj=#{model.wtsj}");
                }
                if (model.getBsjdw() != null && !model.getBsjdw().isEmpty()) {
                    SET("bsjdw=#{model.bsjdw}");
                }
                if (model.getWpr() != null && !model.getWpr().isEmpty()) {
                    SET("wpr=#{model.wpr}");
                }
                if (model.getCjbm() != null && !model.getCjbm().isEmpty()) {
                    SET("cjbm=#{model.cjbm}");
                }
                if (model.getXmfzr() != null && !model.getXmfzr().isEmpty()) {
                    SET("xmfzr=#{model.xmfzr}");
                }
                if (model.getYwzt() != 0) {
                    SET("yjsf=#{model.yjsf}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String findByXmmc(@Param("model") XmwpModel model) {
        return new SQL() {
            {
                SELECT("x.*,CONCAT(zj.zzjgmc,'/',z.zzjgmc) as zzjgmc,a.name as cjr");
                FROM(Tables.XMWP_TABLE + " x");
                LEFT_OUTER_JOIN("account_table a ON x.xmfzr = a.uuid");
                LEFT_OUTER_JOIN("zzjg_table z on z.uuid = x.cjbm");
                LEFT_OUTER_JOIN("zzjg_table zj on zj.uuid = z.zzjgfj");
                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    WHERE("x.xmmc like concat('%',#{model.xmmc},'%')");
                }
                if (model.getYwzt() > 0) {
                    WHERE("x.ywzt = #{model.ywzt}");
                }
                if (model.getXmfzr() != null && !model.getXmfzr().isEmpty()) {
                    WHERE("x.xmfzr=#{model.xmfzr}");
                }
                if (model.getWpr() != null && !model.getWpr().isEmpty()) {
                    WHERE("x.wpr=#{model.wpr}");
                }
                if (model.getXmshzt() != 0) {
                    WHERE("x.xmshzt=#{model.xmshzt}");
                }
                if (model.getShr() != null && !model.getShr().isEmpty()) {
                    WHERE("x.shr=#{model.shr}");
                }
                if (model.getUuid() != null && !model.getUuid().isEmpty()) {
                    WHERE("x.uuid = #{model.uuid}");
                }
            }
        }.toString();
    }

    public String update(@Param("model") XmwpModel model) {
        return new SQL() {
            {
                UPDATE(Tables.XMWP_TABLE);
                if (model.getXmshzt() != 0) {
                    SET("xmshzt=#{model.xmshzt}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

}
