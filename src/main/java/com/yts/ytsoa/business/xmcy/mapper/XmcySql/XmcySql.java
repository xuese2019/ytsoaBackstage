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

    /**
     * 项目详情，项目成员列表
     *
     * @param model
     * @return
     */
    public String find(@Param("model") XmcyModel model) {
        return new SQL() {
            {
                SELECT("x.uuid,x.ygid,a.name as name,x.zyjsnl,x.gzxl,x.gtnl,x.zrxjtdjs,x.py,a1.name as xmfzr");
                FROM(Tables.XMCY_TABLE + " x join account_table a on a.uuid=x.ygid join xmwp_table xm on xm.uuid=x.xmid join account_table a1 on a1.uuid=xm.xmfzr");
                if (model.getXmid() != null && !model.getXmid().isEmpty()) {
                    WHERE("x.xmid=#{model.xmid}");
                }
                if (model.getName() != null && !model.getName().isEmpty()) {
                    model.setName("%" + model.getName() + "%");
                    WHERE("a.name like #{model.name}");
                }
            }
        }.toString();
    }

    public String update(@Param("model") XmcyModel model) {
        return new SQL() {
            {
                UPDATE(Tables.XMCY_TABLE);
                if (model.getZyjsnl() != null && !model.getZyjsnl().isEmpty()) {
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

    public String findXmcy(@Param("xmid") String xmid) {
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



