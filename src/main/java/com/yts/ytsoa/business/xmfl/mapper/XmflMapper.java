package com.yts.ytsoa.business.xmfl.mapper;

import com.yts.ytsoa.business.xmfl.mapper.sql.XmflSql;
import com.yts.ytsoa.business.xmfl.model.XmflModel;
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
public interface XmflMapper {

    @Insert({
            "insert into " + Tables.XMFL_TABLE + " (uuid,xmflmc) values (replace(uuid(), '-', ''),#{model.xmflmc})"
    })
    void add(@Param("model") XmflModel model) throws SQLException;

    @Delete({
            "delete from " + Tables.XMFL_TABLE + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws SQLException;

    @Update({
            "update " + Tables.XMFL_TABLE + " set xmflmc = #{model.xmflmc} where uuid = #{model.uuid}"
    })
    void updateById(@Param("model") XmflModel model) throws SQLException;

    @SelectProvider(type = XmflSql.class, method = "findAllSql")
    List<XmflModel> findAll(@Param("model") XmflModel model) throws SQLException;

    @Select({
            "select * from" + Tables.XMFL_TABLE + " where uuid = #{id}"
    })
    XmflModel getById(@Param("id") String id) throws SQLException;

    @Select({
            "select * from" + Tables.XMFL_TABLE + " where xmflmc = #{xmflmc}"
    })
    List<XmflModel> findByxmflmc(@Param("xmflmc") String xmflmc) throws SQLException;

}
