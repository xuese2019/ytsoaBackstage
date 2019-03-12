package com.yts.ytsoa.business.sz.mapper;

import com.yts.ytsoa.business.sz.mapper.sql.SzSql;
import com.yts.ytsoa.business.sz.model.SzModel;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface SzMapper {
    @SelectProvider(type = SzSql.class, method = "findAllSql")
    List<SzModel> findAll() throws SQLException;

    @Select({
            "select * from suozhang_table  where accid=#{uuid}"
    })
    SzModel findById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = SzSql.class, method = "update")
    int update(@Param("szModel") SzModel szModel) throws SQLException;

    @InsertProvider(type = SzSql.class, method = "addSz")
    int add(@Param("szModel") SzModel szModel) throws SQLException;

}
