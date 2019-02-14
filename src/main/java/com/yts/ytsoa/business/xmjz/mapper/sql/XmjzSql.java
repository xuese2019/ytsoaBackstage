package com.yts.ytsoa.business.xmjz.mapper.sql;

import com.yts.ytsoa.business.xmjz.modedl.XmjzModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XmjzSql {
    public String findAllSql(@Param("xmjzModel") XmjzModel xmjzModel) {
        return new SQL() {
            {
                SELECT("a.*");
                FROM("xmjz_table a");
                if (xmjzModel.getRq() != null) {
                    WHERE("a.rq = #{xmjzModel.rq}");
                    /* WHERE("DATE_FORMAT(a.rq,\"%Y-%m-%d\")= #{xmjzModel.rq}");*/
                }
                if (xmjzModel.getXmid() != null && !xmjzModel.getXmid().isEmpty()) {
                    WHERE("xmid=#{xmjzModel.xmid}");
                }
              /* if (xmcjModel.getWtf() != null && !xmcjModel.getWtf().isEmpty()) {
                    xmcjModel.setWtf("%" + xmcjModel.getWtf() + "%");
                    WHERE("a.wtf like #{xmcjModel.wtf}");
                }*/
            }
        }.toString();
    }

/*    public String findById(@Param("xmwpModel") XmwpModel xmwpModel) {
        return new SQL() {
            {
                SELECT("a.xmfzr");
                FROM("xmwp_table a");
                if (xmwpModel.getUuid() != null) {
                    WHERE("a.xmid=#{xmwpModel.xmid}");
                }
            *//*    if (xmjzModel.getRq() != null) {
                    WHERE("a.rq = #{xmjzModel.rq}");
                    *//**//* WHERE("DATE_FORMAT(a.rq,\"%Y-%m-%d\")= #{xmjzModel.rq}");*//**//*
                }*//*
     *//*  if (xmjzModel.getXmid() != null && !xmjzModel.getXmid().isEmpty()) {
                    WHERE("xmid=#{xmjzModel.xmid}");
                }*//*
     *//* if (xmcjModel.getWtf() != null && !xmcjModel.getWtf().isEmpty()) {
                    xmcjModel.setWtf("%" + xmcjModel.getWtf() + "%");
                    WHERE("a.wtf like #{xmcjModel.wtf}");
                }*//*
            }
        }.toString();
    }*/
}
