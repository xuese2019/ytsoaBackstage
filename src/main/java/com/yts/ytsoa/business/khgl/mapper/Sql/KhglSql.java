package com.yts.ytsoa.business.khgl.mapper.Sql;

import com.yts.ytsoa.business.khgl.model.KhglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class KhglSql {
    public String findAllSql(@Param("khglModel") KhglModel khglModel) {
        return new SQL() {
            {
                SELECT("y.uuid,x.xmmc as xmmc,y.ywhfr,y.ywhfsj,y.ywhfnr");
                FROM("khgl_table y");
                LEFT_OUTER_JOIN("xmwp_table x  ON x.uuid=y.xmmc ");
                if (khglModel.getXmmc() != null && !khglModel.getXmmc().isEmpty()) {
                    khglModel.setXmmc("%" + khglModel.getXmmc() + "%");
                    WHERE("x.xmmc like #{khglModel.xmmc}");
                }
                /*if (ycsqModel.getCphgsd() != null && !ycsqModel.getCphgsd().isEmpty()) {
                    ycsqModel.setCphgsd("%" + ycsqModel.getCphgsd() + "%");
                    WHERE("y.cphgsd like #{ycsqModel.cphgsd}");
                }
                if (ycsqModel.getSqr() != null && !ycsqModel.getSqr().isEmpty()) {
                    ycsqModel.setSqr("%" + ycsqModel.getSqr() + "%");
                    WHERE("y.sqr like #{ycsqModel.sqr}");
                }
                if ((ycsqModel.getShjg() == 1) || (ycsqModel.getShjg() == 3)) {
                    WHERE("y.shjg=1 or y.shjg=3");
                }*/
            }
        }.toString();
    }

    public String add(@Param("khglModel") KhglModel khglModel) {
        return new SQL() {
            {
                INSERT_INTO(Tables.KHGL_TABLE);
                VALUES("uuid", "replace(uuid(),'-','')");
                VALUES("xmmc", "#{khglModel.xmmc}");
                VALUES("ywhfr", "#{khglModel.ywhfr}");
                VALUES("ywhfsj", "#{khglModel.ywhfsj}");
                VALUES("ywhfnr", "#{khglModel.ywhfnr}");
                VALUES("ssnf", "#{khglModel.ssnf}");
            }
        }.toString();
    }

    public String updById(@Param("khglModel") KhglModel khglModel) {
        return new SQL() {
            {
                UPDATE(Tables.KHGL_TABLE);
                if (khglModel.getXmmc() != null && !khglModel.getXmmc().isEmpty()) {
                    SET("xmmc=#{khglModel.xmmc}");
                }
                if (khglModel.getYwhfnr() != null && !khglModel.getYwhfnr().isEmpty()) {
                    SET("ywhfnr=#{khglModel.ywhfnr}");
                }
                if (khglModel.getYwhfr() != null && !khglModel.getYwhfr().isEmpty()) {
                    SET("ywhfr=#{khglModel.ywhfr}");
                }

                WHERE("uuid=#{khglModel.uuid}");
            }
        }.toString();
    }
}
