package com.yts.ytsoa.business.qxgl.mapper;

import com.yts.ytsoa.business.qxgl.mapper.sql.QxglSql;
import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface QxglMapper {

    @Select({
            "select * from " + Tables.QXGL_TABLE + " order by uuid"
    })
    List<QxglModel> findAll() throws Exception;

    @Select({
            "select * from " + Tables.QXGL_TABLE + " where qxlx = '1' order by uuid"
    })
    List<QxglModel> findMenu() throws Exception;

    @InsertProvider(type = QxglSql.class, method = "saves")
    void saves(@Param("list") List<QxglModel> list) throws Exception;

    @Delete({
            "delete from " + Tables.QXGL_TABLE
    })
    void deleteAll() throws Exception;

}
