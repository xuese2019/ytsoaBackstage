package com.yts.ytsoa.business.gzrz.mapper;

import com.yts.ytsoa.business.gzrz.mapper.Sql.GzrzSql;
import com.yts.ytsoa.business.gzrz.model.GzrzModel;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface GzrzMapper {
    @SelectProvider(type = GzrzSql.class, method = "find")
    List<GzrzModel> find(@Param("model") GzrzModel model);

    @InsertProvider(type = GzrzSql.class, method = "addGzrz")
    int addGzrz(@Param("model") GzrzModel model);

    @SelectProvider(type = GzrzSql.class, method = "findByXmid")
    List<GzrzModel> findByXmid(@Param("model") GzrzModel model);

    @SelectProvider(type = GzrzSql.class, method = "rgtj")
    List<GzrzModel> rgtj(@Param("model") GzrzModel model);
}
