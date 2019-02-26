package com.yts.ytsoa.business.xmwp.mapper.Sql;

import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XmwpglSql {
    public String find(@Param("model") XmwpModel model) {
        return new SQL() {
            {
                SELECT("x.*,b.bmmc as cjbm,a.name as cjr");
                FROM(Tables.XMWP_TABLE + " x join bumen_table b on x.cjbm=b.uuid join account_table a on a.uuid=x.xmfzr");
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
                    WHERE("x.ywzt =#{model.ywzt}");
                }
                if (model.getUuid() != null && !model.getUuid().isEmpty()) {
                    WHERE("uuid=#{model.uuid}");
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

    /**
     * 项目委派管理
     * @param model
     * @return
     */
    public String findByXmmc(@Param("model") XmwpModel model) {
        return new SQL() {
            {
                SELECT("x.*");
                FROM(Tables.XMWP_TABLE + " x");
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
                if (model.getCjbm() != null && !model.getCjbm().isEmpty()) {
                    WHERE("x.cjbm=#{model.cjbm}");
                }
                if (model.getXmxcjssj() != null) {
                    WHERE("xmxcjssj=#{model.xmxcjssj}");
                }
                if (model.getShr() != null && !model.getShr().isEmpty()) {
                    WHERE("shr=#{model.shr}");
                }
                if (model.getXmfzr() != null && !model.getXmfzr().isEmpty()) {
                    WHERE("x.xmfzr=#{model.xmfzr}");
                }
                if (model.getUuid() != null && !model.getUuid().isEmpty()) {
                    WHERE("x.uuid=#{model.uuid}");
                }
            }
        }.toString();
    }

    public String findByXmyq(@Param("model") XmwpModel model) {
        return new SQL() {
            {
                SELECT("x.*");
                FROM(Tables.XMWP_TABLE + " x");
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
                if (model.getCjbm() != null && !model.getCjbm().isEmpty()) {
                    WHERE("x.cjbm=#{model.cjbm}");
                }
                if (model.getXmxcjssj() != null) {
                    WHERE("xmxcjssj=#{model.xmxcjssj}");
                }
                if (model.getShr() != null && !model.getShr().isEmpty()) {
                    WHERE("shr=#{model.shr}");
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

    public String findByXmfzr(@Param("model") XmwpModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(Tables.XMWP_TABLE);
                if (model.getXmfzr() != null && !model.getXmfzr().isEmpty()) {
                    WHERE("xmfzr=#{model.xmfzr}");
                }
                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    WHERE("xmmc like concat('%',#{model.xmmc},'%')");
                }
                WHERE("ywzt=6");
            }
        }.toString();
    }

    /**
     * 项目管理页面
     * 条件必须是业务状态大于等于2的
     * @param model
     * @return
     */
    public String xmgl(@Param("model") XmwpModel model) {
        return new SQL() {
            {
                SELECT("x.*");
                FROM(Tables.XMWP_TABLE + " x");
                if (model.getXmmc() != null && !model.getXmmc().isEmpty()) {
                    WHERE("x.xmmc like concat('%',#{model.xmmc},'%')");
                }
                if (model.getYwzt() != 0) {
                    WHERE("x.ywzt >= #{model.ywzt}");
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
                if (model.getCjbm() != null && !model.getCjbm().isEmpty()) {
                    WHERE("x.cjbm=#{model.cjbm}");
                }
                if (model.getXmxcjssj() != null) {
                    WHERE("xmxcjssj=#{model.xmxcjssj}");
                }
                if (model.getShr() != null && !model.getShr().isEmpty()) {
                    WHERE("shr=#{model.shr}");
                }
                if (model.getXmfzr() != null && !model.getXmfzr().isEmpty()) {
                    WHERE("x.xmfzr=#{model.xmfzr}");
                }
            }
        }.toString();
    }

    public String findById(@Param("uuid") String uuid) {
        return new SQL() {
            {
                SELECT("x.*");
                FROM(Tables.XMWP_TABLE + " x");
                if (uuid != null && !uuid.isEmpty()) {
                    WHERE("uuid=#{uuid}");
                }
            }
        }.toString();
    }
}
