package com.yts.ytsoa.business.zzjg.mapper;

import com.yts.ytsoa.business.zzjg.model.ZzjgModel;
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
public interface ZzjgMapper {

    @Insert({
            "insert into " + Tables.ZZJG_TABLE + " (uuid,zzjgfj,zzjgmc) values (replace(uuid(), '-', ''),#{model.zzjgfj},#{model.zzjgmc})"
    })
    void add(@Param("model") ZzjgModel model) throws SQLException;

    @Delete({
            "delete from " + Tables.ZZJG_TABLE + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws SQLException;

    @Update({
            "update " + Tables.ZZJG_TABLE + " set zzjgmc = #{model.zzjgmc} where uuid = #{model.uuid}"
    })
    void updateById(@Param("model") ZzjgModel model) throws SQLException;

    @Select({
            "select * from " + Tables.ZZJG_TABLE
    })
    List<ZzjgModel> findAll() throws SQLException;

    @Select({
            "select * from" + Tables.ZZJG_TABLE + " where uuid = #{id}"
    })
    ZzjgModel getById(@Param("id") String id) throws SQLException;

    @Select({
            "select * from" + Tables.ZZJG_TABLE + " where zzjgfj = #{zzjgfj}"
    })
    List<ZzjgModel> findByzzjgfj(@Param("zzjgfj") String zzjgfj) throws SQLException;

    @Select({
            "select * from" + Tables.ZZJG_TABLE + " where zzjgmc = #{zzjgmc}"
    })
    List<ZzjgModel> findByzzjgmc(@Param("zzjgmc") String zzjgmc) throws SQLException;

}
