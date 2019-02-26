package com.yts.ytsoa.business.ycsq.mapper.sql;

import com.yts.ytsoa.business.ycsq.model.YcsqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class YcsqSql {
    public String findAllSql(@Param("ycsqModel") YcsqModel ycsqModel) {
        return new SQL() {
            {
                SELECT("y.uuid,a.name AS 'sqr',y.ycsy,y.cphgsd,y.cph,y.cfgls,y.shjg,y.cfsj,y.fhsj,y.sjfhsj,y.fhgls");
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
                if ((ycsqModel.getShjg() == 1) || (ycsqModel.getShjg() == 3)) {
                    WHERE("y.shjg=1 or y.shjg=3");
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
                if (ycsqModel.getCfgls() != null && !ycsqModel.getCfgls().isEmpty()) {
                    SET("cfgls=#{ycsqModel.cfgls}");
                }
                WHERE("uuid=#{ycsqModel.uuid}");
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

    public String findByShjl(@Param("prentid") String prentid) {
        return new SQL() {
            {
                SELECT("y.cphgsd,y.cph,a.name,s.shyj,s.shjg");
                FROM("shjl_table s JOIN ycsq_table y ON y.uuid=s.prentid join account_table a on a.uuid=s.shr");
                WHERE("s.prentid=#{prentid}");
            }
        }.toString();
    }
}
