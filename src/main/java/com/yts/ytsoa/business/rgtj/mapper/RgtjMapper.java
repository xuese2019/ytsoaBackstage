package com.yts.ytsoa.business.rgtj.mapper;

import com.yts.ytsoa.business.rgtj.mapper.Sql.RgtjSql;
import com.yts.ytsoa.business.rgtj.model.RgtjModel;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface RgtjMapper {

    @InsertProvider(type = RgtjSql.class, method = "insertRgtj")
    int insertRgtj(@Param("model") RgtjModel model) throws SQLException;

    @SelectProvider(type = RgtjSql.class, method = "find")
    List<RgtjModel> find(@Param("model") RgtjModel model);
}
