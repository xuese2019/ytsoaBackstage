package com.yts.ytsoa.business.qxgl.mapper.sql;

import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.utils.Tables;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
public class QxglSql {

    public String saves(@Param("list") List<QxglModel> list) throws Exception {
        StringJoiner sj = new StringJoiner("");
        list.forEach(k -> {
            String s = new SQL() {
                {
                    INSERT_INTO(Tables.QXGL_TABLE);
                    VALUES("uuid", "'" + k.getUuid() + "'");
                    VALUES("qxmc", "'" + k.getQxmc() + "'");
                    VALUES("qxbs", "'" + k.getQxbs() + "'");
                    VALUES("qxfj", "'" + k.getQxfj() + "'");
                    VALUES("qxlx", "'" + k.getQxlx() + "'");
                    VALUES("ico", "'" + k.getIco() + "'");
                    VALUES("icoi", "'" + k.getIcoi() + "'");
                }
            }.toString();
            sj.add(s + ";");
        });
        return sj.toString().substring(0, sj.toString().length() - 1);
    }
}
