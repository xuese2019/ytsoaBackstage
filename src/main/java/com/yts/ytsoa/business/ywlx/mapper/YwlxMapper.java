package com.yts.ytsoa.business.ywlx.mapper;

import com.yts.ytsoa.business.ywlx.mapper.sql.YwlxSql;
import com.yts.ytsoa.business.ywlx.model.YwlxModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface YwlxMapper {

    @Insert({
            "insert into " + Tables.YWLX_TABLE + " (uuid,ywlxmc) values (replace(uuid(), '-', ''),#{model.ywlxmc})"
    })
    void add(@Param("model") YwlxModel model) throws SQLException;

    @Delete({
            "delete from " + Tables.YWLX_TABLE + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws SQLException;

    @Update({
            "update " + Tables.YWLX_TABLE + " set ywlxmc = #{model.ywlxmc} where uuid = #{model.uuid}"
    })
    void updateById(@Param("model") YwlxModel model) throws SQLException;

    /**
     * 查询所有
     *
     * @param model
     * @return
     * @throws SQLException
     */
    @SelectProvider(type = YwlxSql.class, method = "findAllSql")
    List<YwlxModel> findAll(@Param("model") YwlxModel model) throws SQLException;

    @Select({
            "select * from" + Tables.YWLX_TABLE + " where uuid = #{id}"
    })
    YwlxModel getById(@Param("id") String id) throws SQLException;

    @Select({
            "select * from" + Tables.YWLX_TABLE + " where ywlxmc = #{ywlxmc}"
    })
    List<YwlxModel> findByYwlxmc(@Param("ywlxmc") String ywlxmc) throws SQLException;

}
