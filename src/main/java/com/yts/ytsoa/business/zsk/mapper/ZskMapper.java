package com.yts.ytsoa.business.zsk.mapper;

import com.yts.ytsoa.business.zsk.mapper.sql.ZskSql;
import com.yts.ytsoa.business.zsk.model.ZskModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ZskMapper {
    /**
     * 查询所有
     *
     * @param zskModel
     * @return
     * @throws SQLException
     */
    @SelectProvider(type = ZskSql.class, method = "findAllSql")
    @Results(id = "accountMap", value = {
            @Result(property = "tjr", column = "name")
    })
    List<ZskModel> findAll(@Param("zskModel") ZskModel zskModel) throws SQLException;

    @Select({
            "select * from " + Tables.ZSK_TABLE + " where uuid = #{uuid}"
            /*  "select * from zsk_table where uuid={#uuid}"*/
    })
    /**
     * 根据uuid查询
     */
    List<ZskModel> findById(@Param("uuid") String uuid) throws SQLException;

    /**
     * 添加
     *
     * @param zskModel
     * @return
     * @throws SQLException
     */
    @Insert({
            "insert into " + Tables.ZSK_TABLE + "(uuid,wjly,wjzt,lx,jrsj,tjr,wjmc)" + "values(replace(uuid(), '-', ''),#{zskModel.wjly} ,#{zskModel.wjzt},#{zskModel.lx},#{zskModel.jrsj},#{zskModel.tjr},#{zskModel.wjmc})"
    })
    int add(@Param("zskModel") ZskModel zskModel) throws SQLException;

    /**
     * 删除
     *
     * @param uuid
     * @throws SQLException
     */
    @Delete({
            "delete from " + Tables.ZSK_TABLE + " where uuid = #{uuid}"
    })
    void delById(@Param("uuid") String uuid) throws SQLException;

    /**
     * 修改
     *
     * @param zskModel
     * @return
     * @throws SQLException
     */
    @UpdateProvider(type = ZskSql.class, method = "updateByIdSql")
    int updById(@Param("zskModel") ZskModel zskModel) throws SQLException;

    /**
     * 增加
     *
     * @param zskModel
     * @throws SQLException
     */
    @InsertProvider(type = ZskSql.class, method = "addZskPL")
    void addZskPL(@Param("zskModel") List<ZskModel> zskModel) throws SQLException;
}
