package com.yts.ytsoa.business.gzzl.mapper;

import com.yts.ytsoa.business.gzzl.mapper.sql.GzzlSql;
import com.yts.ytsoa.business.gzzl.model.GzzlModel;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface GzzlMapper {
    @SelectProvider(type = GzzlSql.class, method = "findAllSql")
    List<GzzlModel> findAll(@Param("gzzlModel") GzzlModel gzzlModel) throws SQLException;


    @InsertProvider(type = GzzlSql.class, method = "saveSql")
    void save(@Param("gzzlModels") List<GzzlModel> gzzlModels) throws SQLException;
}
