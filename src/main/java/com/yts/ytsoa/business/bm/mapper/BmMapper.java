package com.yts.ytsoa.business.bm.mapper;

import com.yts.ytsoa.business.bm.mapper.sql.BmSql;
import com.yts.ytsoa.business.bm.model.BmModel;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BmMapper {


    @SelectProvider(type = BmSql.class, method = "findAllSql")
    @Results(id = "bmMap", value = {
            @Result(id = true, property = "uuid", column = "uuid"),
            @Result(property = "bmzwList", column = "uuid", javaType = List.class,
                    many = @Many(select = "com.yts.ytsoa.business.bmzw.mapper.BmzwMapper.bmzw"))

    })
    List<BmModel> findAll(@Param("bmModel") BmModel bmModel) throws SQLException;

    @Select({
            "select * from bumen_table  where uuid=#{uuid}"
    })
    BmModel findById(@Param("uuid") String uuid) throws SQLException;

    @Select({
            "select bmmc from bumen_table where uuid=#{uuid}"
    })
    String findBmmcById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = BmSql.class, method = "update")
    int update(@Param("bmModel") BmModel bmModel) throws SQLException;

    @Delete({
            "delete from  bumen_table   where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws SQLException;

    @InsertProvider(type = BmSql.class, method = "addBm")
    int add(@Param("bmModel") BmModel bmModel) throws SQLException;
}
