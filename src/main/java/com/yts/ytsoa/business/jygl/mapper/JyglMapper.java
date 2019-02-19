package com.yts.ytsoa.business.jygl.mapper;


import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.jygl.mapper.sql.JyglSql;
import com.yts.ytsoa.business.jygl.model.JyglModel;
import com.yts.ytsoa.business.jygl.model.ResultModel;
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
    List<JyglModel> findAll(@Param("model") JyglModel jyglModel) throws SQLException;

    @Select({
            "select j.uuid,j.dgjybh,d.damc,j.jyrq,a.name as 'jyr'  from jygl_table j join dggd_table d on d.gdsqbh_hz=j.dgjybh join account_table a on a.uuid=j.jyr where j.uuid = #{uuid}"
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
    int updById(@Param("model") JyglModel jyglModel) throws SQLException;

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

    @UpdateProvider(type = JyglSql.class, method = "update")
    int update(@Param("model") JyglModel model) throws SQLException;

    @SelectProvider(type = JyglSql.class, method = "findByShjl")
    List<ResultModel> findByShjl(@Param("prentid") String prentid) throws SQLException;
}
