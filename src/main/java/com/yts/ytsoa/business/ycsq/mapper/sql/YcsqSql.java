package com.yts.ytsoa.business.ycsq.mapper.sql;

import com.yts.ytsoa.business.ycsq.model.YcsqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class YcsqSql {
    public String findAllSql(@Param("ycsqModel") YcsqModel ycsqModel) {
        return new SQL() {
            {
                SELECT("y.uuid,a.name AS 'sqr',y.ycsy,y.cphgsd,y.cph,y.cfgls,y.cfsj,y.fhsj,y.sjfhsj,y.fhgls");
                FROM("ycsq_table AS y");
                LEFT_OUTER_JOIN("account_table a  ON y.sqr = a.uuid");
                if (ycsqModel.getSqrq() != null) {
                    WHERE("a.sqrq = #{ycsqModel.sqrq}");
                }
                if (ycsqModel.getCph() != null) {
                    ycsqModel.setCph("%" + ycsqModel.getCph() + "%");
                    WHERE("y.cph like #{ycsqModel.cph}");
                }
                if (ycsqModel.getCphgsd() != null && !ycsqModel.getCphgsd().isEmpty()) {
                    ycsqModel.setCphgsd("%" + ycsqModel.getCphgsd() + "%");
                    WHERE("y.cphgsd like #{ycsqModel.cphgsd}");
                }
                if (ycsqModel.getSqr() != null && !ycsqModel.getSqr().isEmpty()) {
                    ycsqModel.setSqr("%" + ycsqModel.getSqr() + "%");
                    WHERE("y.sqr like #{ycsqModel.sqr}");
                }
            }
        }.toString();
    }

    public String updateByIdSql(@Param("ycsqModel") YcsqModel ycsqModel) {
        return new SQL() {
            {
                UPDATE("ycsq_table");
                if (ycsqModel.getSjfhsj() != null) {
                    SET("sjfhsj=#{ycsqModel.sjfhsj}");
                }
                if (ycsqModel.getShyj() != null && !ycsqModel.getShyj().isEmpty()) {
                    SET("shyj=#{ycsqModel.shyj}");
                }
                if (ycsqModel.getZt() != 0 && !ycsqModel.getShyj().isEmpty()) {
                    SET("zt=#{ycsqModel.zt}");
                }
                if (ycsqModel.getFhgls() != null && !ycsqModel.getFhgls().isEmpty()) {
                    SET("fhgls=#{ycsqModel.fhgls}");
                }
                if (ycsqModel.getBz() != null && !ycsqModel.getBz().isEmpty()) {
                    SET("bz=#{ycsqModel.bz}");
                }
                if (ycsqModel.getShjg() != 0 && !ycsqModel.getBz().isEmpty()) {
                    SET("shjg=#{ycsqModel.shjg}");
                }
            }
        }.toString();
    }

    public String update(@Param("model") YcsqModel model) {
        return new SQL() {
            {
                UPDATE(Tables.YCSQ_TABLE);
                if (model.getShjg() != 0) {
                    SET("shjg=#{model.shjg}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }
}
