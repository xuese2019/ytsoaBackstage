package com.yts.ytsoa.business.bmzw.mapper;

import com.yts.ytsoa.business.bmzw.mapper.sql.BmzwSql;
import com.yts.ytsoa.business.bmzw.model.BmzwModel;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BmzwMapper {
    @SelectProvider(type = BmzwSql.class, method = "findAllSql")
    List<BmzwModel> findAll(@Param("bmzwModel") BmzwModel bmzwModel) throws SQLException;

    @Select({
            "select * from bmzw_table  where uuid=#{uuid}"
    })
    BmzwModel findById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = BmzwSql.class, method = "update")
    int update(@Param("bmzwModel") BmzwModel bmzwModel) throws SQLException;


    @Delete({
            "delete from bmzw_table   where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws SQLException;

    @InsertProvider(type = BmzwSql.class, method = "addBmzw")
    int add(@Param("bmzwModel") BmzwModel bmzwModel) throws SQLException;

    @Select({
            "select * from bmzw_table where bmparentid=#{bmparentid}"
    })
    List<BmzwModel> bmzw(@Param("bmparentid") String bmparentid);
}
