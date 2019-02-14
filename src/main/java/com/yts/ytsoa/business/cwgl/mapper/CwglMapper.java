package com.yts.ytsoa.business.cwgl.mapper;

import com.yts.ytsoa.business.cwgl.mapper.sql.CwglSql;
import com.yts.ytsoa.business.cwgl.model.CwglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CwglMapper {
    @SelectProvider(type = CwglSql.class, method = "findAllSql")
    List<CwglModel> findAll(@Param("cwglModel") CwglModel cwglModel) throws SQLException;

    @Insert({
            "insert into " + Tables.CWGL_TABLE + "(uuid,rq,sflx,zt,xmid)" + "values(replace(uuid(), '-', ''),#{cwglModel.rq} ,#{cwglModel.sflx},#{cwglModel.zt},#{cwglModel.xmid})"
    })
    int add(@Param("cwglModel") CwglModel cwglModel) throws SQLException;

    @Select({
            "select * from " + Tables.CWGL_TABLE + " where uuid = #{uuid}"
            /*  "select * from zsk_table where uuid={#uuid}"*/
    })
    List<CwglModel> findById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = CwglSql.class, method = "updateByIdSql")
    int updById(@Param("cwglModel") CwglModel cwglModel) throws SQLException;
}
