package com.yts.ytsoa.business.xmjlcy.mapper.sql;

import com.yts.ytsoa.business.xmjlcy.model.XmjlcyModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class XmjlcySql {
    public String findAllSql(@Param("xmjlcyModel") XmjlcyModel xmjlcyModel) {
        return new SQL() {
            {
                SELECT("a.*,c.name as cy");
                FROM("xmjlcy_table a");
                LEFT_OUTER_JOIN(Tables.ACCOUNT_TABLE + " c on c.uuid = a.name");
                if (xmjlcyModel.getXmid() != null && !xmjlcyModel.getXmid().isEmpty()) {
                    WHERE("a.xmid = #{xmjlcyModel.xmid}");
                }
              /*  if (xmjlcyModel.getXmid() != null && !xmjlcyModel.getXmid().isEmpty()) {
                    xmjlcyModel.setXmid("%" + xmjlcyModel.getXmid() + "%");
                    WHERE("c.xmid = #{xmjlcyModel.xmid}");
                }*/
               /* if (xmcjModel.getWtf() != null && !xmcjModel.getWtf().isEmpty()) {
                    xmcjModel.setWtf("%" + xmcjModel.getWtf() + "%");
                    WHERE("a.wtf like #{xmcjModel.wtf}");
                }*/
            }
        }.toString();
    }
}
