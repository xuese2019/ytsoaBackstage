package com.yts.ytsoa.business.shjd.mapper.Sql;

import com.yts.ytsoa.business.shjd.model.ShjdModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ShjdSql {
    public String find(@Param("model") ShjdModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(Tables.SHJD_TABLE);
                if (model.getUuid() != null && !model.getUuid().isEmpty()) {
                    WHERE("uuid=#{model.uuid}");
                }
            }
        }.toString();
    }

    public String insert(@Param("model") ShjdModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.SHJD_TABLE);
                VALUES("uuid", "#{model.uuid}");
                VALUES("shr1", "#{model.shr1}");
                VALUES("shr2", "#{model.shr2}");
                VALUES("shr3", "#{model.shr3}");
                VALUES("shr4", "#{model.shr4}");
                VALUES("shr5", "#{model.shr5}");
                VALUES("shr6", "#{model.shr6}");
                VALUES("shr7", "#{model.shr7}");
                VALUES("shr8", "#{model.shr8}");
                VALUES("shr9", "#{model.shr9}");
                VALUES("shr10", "#{model.shr10}");
            }
        }.toString();
    }

    public String update(@Param("model") ShjdModel model) {
        return new SQL() {
            {
                UPDATE(Tables.SHJD_TABLE);
                if (model.getShr1() != null && !model.getShr1().isEmpty()) {
                    SET("shr1=#{model.shr1}");
                }
                if (model.getShr2() != null && !model.getShr2().isEmpty()) {
                    SET("shr1=#{model.shr2}");
                }
                if (model.getShr3() != null && !model.getShr3().isEmpty()) {
                    SET("shr1=#{model.shr3}");
                }
                if (model.getShr4() != null && !model.getShr4().isEmpty()) {
                    SET("shr1=#{model.shr4}");
                }
                if (model.getShr5() != null && !model.getShr5().isEmpty()) {
                    SET("shr1=#{model.shr5}");
                }
                if (model.getShr6() != null && !model.getShr6().isEmpty()) {
                    SET("shr1=#{model.shr6}");
                }
                if (model.getShr7() != null && !model.getShr7().isEmpty()) {
                    SET("shr1=#{model.shr7}");
                }
                if (model.getShr8() != null && !model.getShr8().isEmpty()) {
                    SET("shr1=#{model.shr8}");
                }
                if (model.getShr9() != null && !model.getShr9().isEmpty()) {
                    SET("shr1=#{model.shr9}");
                }
                if (model.getShr10() != null && !model.getShr10().isEmpty()) {
                    SET("shr1=#{model.shr10}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }
}
