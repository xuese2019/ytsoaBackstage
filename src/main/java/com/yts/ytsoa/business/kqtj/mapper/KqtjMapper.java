package com.yts.ytsoa.business.kqtj.mapper;

import com.yts.ytsoa.business.kqtj.mapper.KqtjSql.KqtjSql;
import com.yts.ytsoa.business.kqtj.model.KqtjModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface KqtjMapper {
    @SelectProvider(type = KqtjSql.class, method = "findAllSql")
    List<KqtjModel> findAll(@Param("kqtjModel") KqtjModel kqtjModel) throws SQLException;
}
