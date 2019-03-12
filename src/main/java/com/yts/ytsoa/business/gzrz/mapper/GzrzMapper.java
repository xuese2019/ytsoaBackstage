package com.yts.ytsoa.business.gzrz.mapper;

import com.yts.ytsoa.business.gzrz.mapper.Sql.GzrzSql;
import com.yts.ytsoa.business.gzrz.model.GzrzModel;
import com.yts.ytsoa.business.gzrz.model.RztjsjModel;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
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

    @Update({
            "update rztjsj_table set shi=#{model.shi},fen=#{model.fen}"
    })
    int updateTjsj(@Param("model") RztjsjModel model) throws SQLException;

    @Select({
            "select * from rztjsj_table"
    })
    RztjsjModel findTjsj();

    @Update({
            "update gzrz_table set rzdp=#{model.rzdp} where uuid=#{model.uuid}"
    })
    int addRzdp(@Param("model") GzrzModel model) throws SQLException;

    @Select({
            "select * from gzrz_table where uuid = #{uuid}"
    })
    List<GzrzModel> findById(@Param("uuid") String uuid) throws SQLException;
}
