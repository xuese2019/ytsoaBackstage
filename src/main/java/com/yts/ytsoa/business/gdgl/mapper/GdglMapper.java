package com.yts.ytsoa.business.gdgl.mapper;

import com.yts.ytsoa.business.gdgl.mapper.GdglSql.GdglSql;
import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.gdgl.query.GdglQueryModel;
import com.yts.ytsoa.business.gdgl.result.ResultModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface GdglMapper {
    /**
     * 添加
     *
     * @param model
     * @return
     */
    @InsertProvider(type = GdglSql.class, method = "addGdsq")
    int addGdsq(@Param("model") GdglModel model);

    @SelectProvider(type = GdglSql.class, method = "find")
    List<GdglModel> find(@Param("model") GdglModel model);

    @SelectProvider(type = GdglSql.class, method = "findGdsh")
    List<ResultModel> findGdsh(@Param("model") GdglQueryModel model);

    @UpdateProvider(type = GdglSql.class, method = "updateByIdSql")
    int updById(@Param("gdglModel") GdglModel gdglModel) throws SQLException;

    @Select({
            "select * from " + Tables.DGGD_TABLE + " where uuid = #{uuid}"
            /*  "select * from zsk_table where uuid={#uuid}"*/
    })
    GdglModel findById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = GdglSql.class, method = "update")
    int update(@Param("model") GdglModel model);

    @SelectProvider(type = GdglSql.class, method = "findByDamc")
    List<GdglModel> findByDamc(@Param("model") GdglModel model) throws SQLException;

    @UpdateProvider(type = GdglSql.class, method = "updatejyzt")
    int updatejyzt(@Param("model") GdglModel model);
}
