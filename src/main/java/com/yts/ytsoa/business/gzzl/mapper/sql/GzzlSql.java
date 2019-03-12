package com.yts.ytsoa.business.gzzl.mapper.sql;

import com.yts.ytsoa.business.gzzl.model.GzzlModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringJoiner;

public class GzzlSql {

    public String findAllSql(@Param("gzzlModel") GzzlModel gzzlModel) {
        return new SQL() {
            {
                SELECT("a.uuid,c.name as fsr,cc.name as jsr,a.fsnr");
                FROM("gzzl_table a");
                LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " c on c.uuid = a.fsr");
                LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " cc on cc.uuid = a.jsr");
                if (gzzlModel.getXmid() != null && !gzzlModel.getXmid().isEmpty()) {
                    WHERE("a.xmid = #{gzzlModel.xmid}");
                }
            }
        }.toString();
    }

    public String saveSql(@Param("gzzlModels") List<GzzlModel> gzzlModels) {
        StringJoiner sj = new StringJoiner("");
        gzzlModels.forEach(k -> {
            String s = new SQL() {
                {
                    INSERT_INTO(Tables.GZZL_TABLE);
                    VALUES("uuid", "replace(uuid(), '-', '')");
                    VALUES("fsr", "'" + k.getFsr() + "'");
                    VALUES("jsr", "'" + k.getJsr() + "'");
                    VALUES("xmid", "'" + k.getXmid() + "'");
                    VALUES("fsnr", "'" + k.getFsnr() + "'");
                    VALUES("fssj", "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(k.getFssj()) + "'");
                    VALUES("zt", "" + k.getZt() + "");
                }
            }.toString();
            sj.add(s + ";");
        });
        return sj.toString().substring(0, sj.toString().length() - 1);
    }
}
