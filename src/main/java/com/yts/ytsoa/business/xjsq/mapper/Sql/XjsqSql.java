package com.yts.ytsoa.business.xjsq.mapper.Sql;

import com.yts.ytsoa.business.xjsq.model.XjsqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XjsqSql {
    public String find(@Param("model") XjsqModel model) {
        return new SQL() {
            {
                SELECT("x.uuid,a.`name` AS xjsqr,x.xjqsrq,x.xjjzrq,x.xjsy,x.qdsj,x.qtsj,x.qddd,x.qtdd,x.shjg");
                FROM(" xjsq_table x LEFT JOIN account_table a ON x.xjsqr=a.uuid");
                if (model.getXjsqr() != null && !model.getXjsqr().isEmpty()) {
                    model.setXjsqr("%" + model.getXjsqr() + "%");
                    WHERE("a.name like #{model.xjsqr}");
                }
                if ((model.getShjg() == 1) || (model.getShjg() == 2 || (model.getShjg() == 3) || (model.getShjg() == 4) || (model.getShjg() == 5))) {
                    WHERE("x.shjg=1 or x.shjg=2  or x.shjg=3  or x.shjg=4 or x.shjg=5");
                }
            }
        }.toString();
    }

    public String kqgl(@Param("model") XjsqModel model) {
        return new SQL() {
            {
                SELECT("x.uuid,a.`name` AS xjsqr,x.xjqsrq,x.xjjzrq,x.xjsy,x.qdsj,x.qtsj,x.qddd,x.qtdd,x.shjg");
                FROM(" xjsq_table x LEFT JOIN account_table a ON x.xjsqr=a.uuid");
                if (model.getXjsqr() != null && !model.getXjsqr().isEmpty()) {
                    model.setXjsqr("%" + model.getXjsqr() + "%");
                    WHERE("a.name like #{model.xjsqr}");
                }
                if ((model.getShjg() == 1) || (model.getShjg() == 3 || (model.getShjg() == 2))) {
                    WHERE("x.shjg=1 or x.shjg=3  or x.shjg=2  or x.shjg=4 or x.shjg=5");
                }
            }
        }.toString();
    }

    public String findByBms(@Param("model") XjsqModel model) {
        return new SQL() {
            {
                SELECT(" x.uuid,a.`name`AS xjsqr,x.xjqsrq,x.xjjzrq,x.xjsy,x.shjg");
                FROM("xjsq_table x LEFT JOIN account_table a ON x.xjsqr=a.uuid");
                if (model.getShjg() != 0) {
                    WHERE("x.shjg=#{model.shjg}");
                }

            }
        }.toString();
    }

    public String update(@Param("model") XjsqModel model) {
        return new SQL() {
            {
                UPDATE(Tables.XJSQ_TABLE);
          /*      if ((model.getShjg() == 1) || (model.getShjg() == 3)) {
                WHERE("t.shjg=1 or t.shjg=3");
            }*/
                if (model.getShjg() != 0) {
                    SET("shjg=#{model.shjg}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }
}
