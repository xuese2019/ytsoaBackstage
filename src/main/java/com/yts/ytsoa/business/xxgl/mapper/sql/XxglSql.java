package com.yts.ytsoa.business.xxgl.mapper.sql;

import com.yts.ytsoa.business.xxgl.model.XxglModel;
import com.yts.ytsoa.utils.Tables;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
public class XxglSql {
    /**
     * 查询所有
     *
     * @param model
     * @return
     */
    public String findAllSql(@Param("model") XxglModel model) {
        return new SQL() {
            {
                SELECT("x.*,a.name");
                FROM(Tables.XXGL_TABLE + " x");
                LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " a on a.uuid = x.fsr");
                if (model != null) {
                    if (model.getFsr() != null && !model.getFsr().isEmpty()) {
                        WHERE("x.fsr = #{model.fsr}");
                    }
                    if (model.getJsr() != null && !model.getJsr().isEmpty()) {
                        WHERE("x.jsr = #{model.jsr}");
                    }
                    if (model.getZt() > 0 || model.getZt() < 0) {
                        WHERE("x.zt = #{model.zt}");
                    }
                }
            }
        }.toString();
    }

    public String savesSql(@Param("models") List<XxglModel> models) {
        StringJoiner sj = new StringJoiner("");
        models.forEach(k -> {
            String s = new SQL() {
                {
                    INSERT_INTO(Tables.XXGL_TABLE);
                    VALUES("uuid", "replace(uuid(), '-', '')");
                    VALUES("xxlx", k.getXxlx() + "");
                    VALUES("xxbt", "'" + k.getXxbt() + "'");
                    VALUES("xxnr", "'" + k.getXxnr() + "'");
                    VALUES("tzlj", k.getTzlj() == null ? "''" : "'" + k.getTzlj() + "'");
                    VALUES("xxfssj", "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(k.getXxfssj()) + "'");
                    VALUES("zt", "" + k.getZt() + "");
                    VALUES("fsr", "'" + k.getFsr() + "'");
                    VALUES("jsr", "'" + k.getJsr() + "'");
                    VALUES("tyt_flag", "'" + k.getTytFlag() + "'");
                }
            }.toString();
            sj.add(s + ";");
        });
        return sj.toString().substring(0, sj.toString().length() - 1);
    }
}
