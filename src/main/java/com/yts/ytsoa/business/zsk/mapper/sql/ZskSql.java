package com.yts.ytsoa.business.zsk.mapper.sql;


import com.yts.ytsoa.business.zsk.model.ZskModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.StringJoiner;

public class ZskSql {
    public String findAllSql(@Param("zskModel") ZskModel zskModel) {
        return new SQL() {
            {
                SELECT("a.*,c.name as name");
                FROM("zsk_table a");
                LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " c on c.uuid = a.tjr");
                if (zskModel.getTjr() != null && !zskModel.getTjr().isEmpty()) {
                    zskModel.setTjr("%" + zskModel.getTjr() + "%");
                    WHERE("c.name like #{zskModel.tjr}");
                }
               /* if (xmcjModel.getWtf() != null && !xmcjModel.getWtf().isEmpty()) {
                    xmcjModel.setWtf("%" + xmcjModel.getWtf() + "%");
                    WHERE("a.wtf like #{xmcjModel.wtf}");
                }*/
            }
        }.toString();
    }

    /**
     * 根据id修改
     *
     * @param zskModel
     * @return
     */
    public String updateByIdSql(@Param("zskModel") ZskModel zskModel) {
        return new SQL() {
            {
                UPDATE("zsk_table");
                if (zskModel.getTjr() != null && !zskModel.getTjr().isEmpty()) {
                    SET("tjr=#{zskModel.tjr}");
                }
                if (zskModel.getJrsj() != null) {
                    SET("jrsj=#{zskModel.jrsj}");
                }
                if (zskModel.getLx() != 0) {
                    SET("lx=#{zskModel.lx}");
                }
                if (zskModel.getWjly() != null && !zskModel.getWjly().isEmpty()) {
                    SET("wjly=#{zskModel.wjly}");
                }
                if (zskModel.getWjzt() != null && !zskModel.getWjzt().isEmpty()) {
                    SET("wjzt=#{zskModel.wjzt}");
                }
                WHERE("uuid = #{gdglModel.uuid}");
            }
        }.toString();
    }

    public String addZskPL(@Param("zskModel") List<ZskModel> zskModel) {
        StringJoiner sj = new StringJoiner("");
        zskModel.forEach(k -> {
            String s = new SQL() {
                {
                    INSERT_INTO(Tables.ZSK_TABLE);
                    VALUES("uuid", "replace(uuid(),'-','')");
                    VALUES("wjly", "'" + k.getWjly());
                    VALUES("zjzt", "'" + k.getWjzt());
                    VALUES("lx", "'" + k.getLx());
                    VALUES("jrsj", "'" + k.getJrsj());
                    VALUES("tjr", "'" + k.getTjr());
                    VALUES("wjmc", k.getWjmc());
                }
            }.toString();
            sj.add(s + ";");
        });
        return sj.toString().substring(0, sj.toString().length() - 1);
    }
}
