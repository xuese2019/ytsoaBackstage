package com.yts.ytsoa.business.jygl.mapper;


import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.jygl.mapper.sql.JyglSql;
import com.yts.ytsoa.business.jygl.model.JyglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface JyglMapper {
    /**
     * 查询所有
     *
     * @param jyglModel
     * @return
     * @throws SQLException
     */
    @SelectProvider(type = JyglSql.class, method = "findAllSql")
    List<JyglModel> findAll(@Param("JyglModel") JyglModel jyglModel) throws SQLException;

    @Select({
            "select * from " + Tables.JYGL_TABLE + " where uuid = #{uuid}"
    })
    /**
     * 根据uuid进行查询
     */
    JyglModel findById(@Param("uuid") String uuid) throws SQLException;

    /**
     * 根据uuid修改
     *
     * @param jyglModel
     * @return
     * @throws SQLException
     */
    @UpdateProvider(type = JyglSql.class, method = "updateByIdSql")
    int updById(@Param("JyglModel") JyglModel jyglModel) throws SQLException;

    /**
     * 添加
     *
     * @param jyglModel
     * @return
     * @throws SQLException
     */

    @InsertProvider(type = JyglSql.class, method = "add")
    int add(@Param("model") JyglModel jyglModel) throws SQLException;

    @Select({
            "SELECT d.*\n" +
                    "FROM dggd_table d join jygl_table j on j.dgid=d.uuid\n" +
                    "where j.dgid=#{dgid}"
    })
    GdglModel findGdglByGdId(@Param("dgid") String dgid) throws SQLException;
}
