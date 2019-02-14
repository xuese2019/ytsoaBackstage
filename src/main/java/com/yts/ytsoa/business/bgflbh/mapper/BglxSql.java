package com.yts.ytsoa.business.bgflbh.mapper;

import com.yts.ytsoa.business.bgflbh.model.BglxModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BglxSql {
    public String updByBglx(@Param("model") BglxModel model) {
        return new SQL() {
            {
                UPDATE(Tables.BGLX_TABLE);
                if (model.getBgbhHz() != 0) {
                    SET("bgbh_hz=#{model.bgbhHz}");
                }
                WHERE("bglx=#{model.bglx}");
            }
        }.toString();
    }
}
