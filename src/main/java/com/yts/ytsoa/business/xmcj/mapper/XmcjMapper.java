package com.yts.ytsoa.business.xmcj.mapper;

import com.yts.ytsoa.business.xmcj.mapper.sql.XmcjSql;
import com.yts.ytsoa.business.xmcj.model.XmcjModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface XmcjMapper {
    @SelectProvider(type = XmcjSql.class, method = "findAllSql")
  /*  @Results(id = "xmcjMap", value = {
            @Result(property = "xmfzr", column = "name")
    })*/
    List<XmcjModel> findAll(@Param("xmcjModel") XmcjModel xmcjModel) throws SQLException;

    @Delete({
            "delete from " + Tables.XMWP_TABLE + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws SQLException;

    @SelectProvider(type = XmcjSql.class, method = "findById")
    @Results(id = "xmcjMap", value = {
            @Result(property = "cjbm", column = "zzjgmc"),
            @Result(property = "xmfzr", column = "cjr")
    })
    List<XmcjModel> findById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = XmcjSql.class, method = "updateById")
    int updateById(@Param("xmcjModel") XmcjModel xmcjModel) throws SQLException;

}
