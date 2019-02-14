package com.yts.ytsoa.business.xmjlcy.mapper;

import com.yts.ytsoa.business.xmjlcy.mapper.sql.XmjlcySql;
import com.yts.ytsoa.business.xmjlcy.model.XmjlcyModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface XmjlcyMapper {
    @SelectProvider(type = XmjlcySql.class, method = "findAllSql")
    List<XmjlcyModel> findAll(@Param("xmjlcyModel") XmjlcyModel xmjlcyModel) throws SQLException;

    @Insert({
            "insert into " + Tables.XMJLCY_TABLE + "(uuid,xmid,name)" + "values(replace(uuid(), '-', ''),#{xmjlcyModel.xmid} ,#{xmjlcyModel.name})"
    })
    int add(@Param("xmjlcyModel") XmjlcyModel xmjlcyModel) throws SQLException;

    @Delete({
            "delete from " + Tables.XMJLCY_TABLE + " where uuid = #{uuid}"
    })
    void delById(@Param("uuid") String uuid) throws SQLException;

    @Select({
            "select * from " + Tables.XMJLCY_TABLE + " where uuid = #{uuid}"

            /*  "select * from zsk_table where uuid={#uuid}"*/
    })
    List<XmjlcyModel> findById(@Param("uuid") String uuid) throws SQLException;
}
