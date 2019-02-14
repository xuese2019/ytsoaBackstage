package com.yts.ytsoa.business.jyjl.mapper;

import com.yts.ytsoa.business.jyjl.mapper.Sql.JyjlSql;
import com.yts.ytsoa.business.jyjl.model.JyjlModel;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

@Mapper
public interface JyjlMapper {

    @InsertProvider(type = JyjlSql.class, method = "insert")
    int insert(@Param("model") JyjlModel model) throws SQLException;
}
