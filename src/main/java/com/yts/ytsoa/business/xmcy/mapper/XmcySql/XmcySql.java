package com.yts.ytsoa.business.xmcy.mapper.XmcySql;

import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.business.xmcy.result.resultModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.StringJoiner;

public class XmcySql {

    public String saves(@Param("models") List<XmcyModel> models) {
        StringJoiner sj = new StringJoiner("");
        models.forEach(k -> {
            String s = new SQL() {
                {
                    INSERT_INTO(Tables.XMCY_TABLE);
                    VALUES("uuid", "replace(uuid(), '-', '')");
                    VALUES("ygid", "'" + k.getYgid() + "'");
                    VALUES("xmid", "'" + k.getXmid() + "'");
                }
            }.toString();
            sj.add(s + ";");
        });
        return sj.toString().substring(0, sj.toString().length() - 1);
    }

    public String addXmcyPl(@Param("models") List<XmcyModel> models) {
        StringJoiner sj = new StringJoiner("");
        models.forEach(k -> {
            String s = new SQL() {
                {
                    INSERT_INTO(Tables.XMCY_TABLE);
                    VALUES("uuid", "replace(uuid(), '-', '')");
                    VALUES("name", "'" + k.getName() + "'");
                    VALUES("ygid", "'" + k.getYgid() + "'");
                    VALUES("xmid", "'" + k.getXmid() + "'");
                    VALUES("zyjsnl", "'" + k.getZyjsnl() + "'");
                    VALUES("gzxl", "'" + k.getGzxl() + "'");
                    VALUES("gtnl", "'" + k.getGtnl() + "'");
                    VALUES("zrxjtdjs", "'" + k.getZrxjtdjs() + "'");
                    VALUES("py", "'" + k.getPy() + "'");
                }
            }.toString();
            sj.add(s + ";");
        });
        return sj.toString().substring(0, sj.toString().length() - 1);
    }

    public String find(@Param("model") XmcyModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(Tables.XMCY_TABLE);
                if (model.getName() != null && !model.getName().isEmpty()) {
                    WHERE("name like concat ('%',#{model.name},'%')");
                }
                if (model.getXmid() != null && !model.getXmid().isEmpty()) {
                    WHERE("xmid=#{model.xmid}");
                }
            }
        }.toString();
    }

    public String update(@Param("model") XmcyModel model) {
        return new SQL() {
            {
                UPDATE(Tables.XMCY_TABLE);
                if (model.getZyjsnl() != 0) {
                    SET("zyjsnl=#{model.zyjsnl}");
                }
                if (model.getGzxl() != null && !model.getGzxl().isEmpty()) {
                    SET("gzxl=#{model.gzxl}");
                }
                if (model.getGtnl() != null && !model.getGtnl().isEmpty()) {
                    SET("gtnl=#{model.gtnl}");
                }
                if (model.getZrxjtdjs() != null && !model.getZrxjtdjs().isEmpty()) {
                    SET("zrxjtdjs=#{model.zrxjtdjs}");
                }
                if (model.getPy() != null && !model.getPy().isEmpty()) {
                    SET("py=#{model.py}");
                }
                WHERE("uuid=#{model.uuid}");
            }
        }.toString();
    }

    public String rgtj(@Param("x") resultModel model) {
        return new SQL() {
            {
                SELECT("x.uuid,a.name,x.zyjsnl,x.gzxl,x.gtnl,x.zrxjtdjs,x.py,g.trgxmsj,case when g.trgxmsj>=8 THEN g.trgxmsj/8 ELSE g.trgxmsj end as 'xmts',case when g.sfcc=2 then count(1) else 0 end as 'ccts'");
                FROM(Tables.ACCOUNT_TABLE + " as a join " + Tables.XMCY_TABLE + " as x on a.uuid=x.ygid join " + Tables.GZRZ_TABLE + " g on g.tjr=a.uuid");
                if (model.getXmid() != null && !model.getXmid().isEmpty()) {
                    WHERE("x.xmid=#{x.xmid}");
                }
                if (model.getName() != null && !model.getName().isEmpty()) {
                    WHERE("a.name like concat('%',#{x.name},'%')");
                }
                GROUP_BY("x.uuid");
            }
        }.toString();
    }

    public String findById(@Param("uuid") String uuid) {
        return new SQL() {
            {
                SELECT("*");
                FROM(Tables.XMCY_TABLE);
                if (uuid != null && !uuid.isEmpty()) {
                    WHERE("uuid=#{uuid}");
                }
            }
        }.toString();
    }

    public String insertXmcy(@Param("model") XmcyModel model) {
        return new SQL() {
            {
                INSERT_INTO(Tables.XMCY_TABLE);
                VALUES("uuid", "replace(uuid(), '-', '')");
                VALUES("xmid", "#{model.xmid}");
                VALUES("ygid", "#{model.ygid}");
            }
        }.toString();
    }

    public String findXmcyNotInXm(@Param("xmid") String xmid) {
        return new SQL() {
            {
                SELECT("x.ygid");
                FROM(Tables.XMCY_TABLE + " x");
                if (xmid != null && !xmid.isEmpty()) {
                    WHERE("x.xmid=#{xmid}");
                }
            }
        }.toString();
    }

    public String insertRgglTable(@Param("models") List<resultModel> models) {
        StringJoiner sj = new StringJoiner("");
        models.forEach(k -> {
            String s = new SQL() {
                {
                    INSERT_INTO("rgtj_table");
                    VALUES("uuid", "replace(uuid(), '-', '')");
                    VALUES("name", "'" + k.getName() + "'");
                    VALUES("xmts", "'" + k.getXmts() + "'");
                    VALUES("trgxmsj", "'" + k.getTrgxmsj() + "'");
                    VALUES("ccts", "'" + k.getCcts() + "'");
                }
            }.toString();
            sj.add(s + ";");
        });
        return sj.toString().substring(0, sj.toString().length() - 1);
    }
}



