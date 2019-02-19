package com.yts.ytsoa.business.zzjg.mapper.sql;

import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.zzjg.model.ZzjgModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author LD
 */
public class ZzjgSql {

    public String findAllSql(@Param("model") ZzjgModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM("zzjg_table");
                if (model != null) {
                    if (model.getZzjgmc() != null && !model.getZzjgmc().isEmpty()) {
                        model.setZzjgmc("%" + model.getZzjgmc() + "%");
                        WHERE("zzjgmc like #{model.zzjgmc}");
                    }
                    if (model.getUuid() != null && !model.getUuid().isEmpty()) {
                        WHERE("uuid = #{model.uuid}");
                    }
                    if (model.getZzjgfj() != null && !model.getZzjgfj().isEmpty()) {
                        WHERE("zzjgfj = #{model.zzjgfj}");
                    }
                }
            }
        }.toString();
    }

    public String find(@Param("model") AccountModel model) {
        return new SQL() {
            {
                SELECT("z.zzjgmc,z.uuid");
                FROM(Tables.ACCOUNT_TABLE + " a join zzjg_table z on a.bm=z.uuid");
                if (model.getUuid() != null && !model.getUuid().isEmpty()) {
                    WHERE("a.uuid=#{model.uuid}");
                }
            }
        }.toString();
    }

    /**
     * 查看当前登陆人的组织机构名称
     *
     * @param uuid
     * @return
     */
    public String findZzjgmc(@Param("uuid") String uuid) {
        return new SQL() {
            {
                SELECT("z.*");
                FROM(" zzjg_table z JOIN shrsz_table s on s.zgbid=z.uuid join account_table a on a.uuid=s.hhrid");
                WHERE("a.uuid=#{uuid}");
            }
        }.toString();
    }
}
