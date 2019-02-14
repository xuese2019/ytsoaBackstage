package com.yts.ytsoa.business.bgshjl.mapper.Sql;

import com.yts.ytsoa.business.bgshjl.model.BgshjlModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BgshjlSql {
    public String insert(@Param("model") BgshjlModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.BGSHJL_TABLE);
                VALUES("uuid", "replace(uuid(),'-','')");
                VALUES("bgid", "#{model.bgid}");
                VALUES("shrid", "#{model.shrid}");
                VALUES("shyj", "#{model.shyj}");
                VALUES("tof", "#{model.tof}");
                VALUES("sxh", "#{model.sxh}");
            }
        }.toString();
    }

    public String find(@Param("model") BgshjlModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(Tables.BGSHJL_TABLE);
                if (model.getBgid() != null && !model.getBgid().isEmpty()) {
                    WHERE("bgid=#{model.bgid}");
                }
                if (model.getShrid() != null && !model.getShrid().isEmpty()) {
                    WHERE("shrid=#{model.shrid}");
                }
                if (model.getUuid() != null && !model.getUuid().isEmpty()) {
                    WHERE("uuid=#{model.uuid}");
                }
            }
        }.toString();
    }
}
