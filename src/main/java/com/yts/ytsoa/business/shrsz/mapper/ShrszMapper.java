package com.yts.ytsoa.business.shrsz.mapper;

import com.yts.ytsoa.business.shrsz.mapper.Sql.ShrszSql;
import com.yts.ytsoa.business.shrsz.model.ShrszModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;

@Mapper
public interface ShrszMapper {
    @UpdateProvider(type = ShrszSql.class, method = "shrsz")
    int shrsz(@Param("model") ShrszModel model) throws SQLException;

    @SelectProvider(type = ShrszSql.class, method = "find")
    ShrszModel find(@Param("xmid") String xmid);

    @Select({
            "select * from " + Tables.SHRSZ_TABLE
    })
    ShrszModel findShr();

    @Select({
            "SELECT s.uuid,a.name as hhrid,a1.name as bmjlid,a2.name as zgbid FROM shrsz_table s left join account_table a on a.uuid=s.hhrid left join account_table a1 on a1.uuid=s.bmjlid left join account_table a2 on a2.uuid=s.zgbid"
    })
    ShrszModel findAll() throws SQLException;

    @Select({
            "select * from shrsz_table"
    })
    ShrszModel findAllShr();
}
