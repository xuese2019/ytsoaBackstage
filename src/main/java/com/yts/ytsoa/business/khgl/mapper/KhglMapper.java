package com.yts.ytsoa.business.khgl.mapper;

import com.yts.ytsoa.business.khgl.mapper.Sql.KhglSql;
import com.yts.ytsoa.business.khgl.model.KhglModel;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface KhglMapper {
    @SelectProvider(type = KhglSql.class, method = "findAllSql")
    List<KhglModel> findAll(@Param("khglModel") KhglModel khglModel) throws SQLException;

    @InsertProvider(type = KhglSql.class, method = "add")
    int add(@Param("khglModel") KhglModel khglModel) throws SQLException;

    @Delete({
            "delete from khgl_table where uuid=#{uuid}"
    })
    int delById(@Param("uuid") String uuid) throws SQLException;

    @Select({
            "select y.uuid,x.xmmc as xmmc,y.ywhfr,y.ywhfsj,y.ywhfnr from khgl_table y left join  xmwp_table x  ON x.uuid=y.xmmc where y.uuid=#{uuid}"
    })
    List<KhglModel> findById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = KhglSql.class, method = "updById")
    int updById(@Param("khglModel") KhglModel khglModel) throws SQLException;
}
