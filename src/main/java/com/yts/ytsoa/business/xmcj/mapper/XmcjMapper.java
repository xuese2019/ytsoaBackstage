package com.yts.ytsoa.business.xmcj.mapper;

import com.yts.ytsoa.business.xmcj.mapper.sql.XmcjSql;
import com.yts.ytsoa.business.xmcj.model.XmcjModel;
import com.yts.ytsoa.business.xmcj.model.XmzmcModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface XmcjMapper {
    @SelectProvider(type = XmcjSql.class, method = "findAllSql")
  /*  @Results(id = "xmcjMap", value = {
            @Result(property = "xmfzr", column = "name")
    })*/
    List<XmcjModel> findAll(@Param("xmcjModel") XmcjModel xmcjModel) throws SQLException;

    @Delete({
            "delete from " + Tables.XMWP_TABLE + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws SQLException;

    @SelectProvider(type = XmcjSql.class, method = "findById")
    @Results(id = "xmcjMap", value = {
            @Result(id = true, property = "uuid", column = "uuid"),
            @Result(property = "cjbm", column = "cjbm", javaType = String.class,
                    one = @One(select = "com.yts.ytsoa.business.bm.mapper.BmMapper.findBmmcById")),
            @Result(property = "ghr", column = "ghr", javaType = String.class,
                    one = @One(select = "com.yts.ytsoa.business.account.mapper.AccountMapper.getByUuid")),
            @Result(property = "wpr", column = "wpr", javaType = String.class,
                    one = @One(select = "com.yts.ytsoa.business.account.mapper.AccountMapper.getByUuid")),
            @Result(property = "xmfzr", column = "xmfzr", javaType = String.class,
                    one = @One(select = "com.yts.ytsoa.business.account.mapper.AccountMapper.getByUuid")),
            @Result(property = "xmzmcModels", column = "uuid", javaType = List.class,
                    many = @Many(select = "com.yts.ytsoa.business.xmcj.mapper.XmcjMapper.findByParentid"))
    })
    List<XmcjModel> findById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = XmcjSql.class, method = "updateById")
    int updateById(@Param("xmcjModel") XmcjModel xmcjModel) throws SQLException;

    @InsertProvider(type = XmcjSql.class, method = "insertXmzmc")
    int insertXmzmc(@Param("model") XmzmcModel model);

    @SelectProvider(type = XmcjSql.class, method = "findXmzmc")
    List<XmzmcModel> findXmzmc(@Param("model") XmzmcModel model) throws SQLException;

    @Select({
            "select * from " + Tables.XMZMC_TABLE + " where parentid = #{parentid}"
    })
    List<XmzmcModel> findByParentid(@Param("parentid") String parentid) throws SQLException;

    /*@SelectProvider(type = XmcjSql.class,method = "findXmzmcByParentid")
    List<ResultModel> findXmzmcByParentid(@Param("model") XmzmcModel model);*/

    @Select({
            "SELECT a.bm FROM account_table a join bumen_table b on b.uuid=a.bm where a.uuid=#{uuid}"
    })
    String findBmByUuid(@Param("uuid") String uuid);

    @Select({
            "select * from xmzmc_table where uuid=#{uuid}"
    })
    XmzmcModel findByIds(@Param("uuid") String uuid) throws SQLException;

    @Select({
            "select * from xmzmc_table x where parentid=#{xmid}"
    })
    List<XmzmcModel> findsXmzmc(@Param("xmid") String xmid) throws SQLException;

    @Select({
            "select x.*   from xmzmc_table x where x.uuid=#{uuid}"
    })
    List<XmzmcModel> findByXmzmcId(@Param("uuid") String uuid) throws SQLException;
}
