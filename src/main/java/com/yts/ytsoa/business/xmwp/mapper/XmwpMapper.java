package com.yts.ytsoa.business.xmwp.mapper;

import com.yts.ytsoa.business.xmwp.mapper.Sql.XmwpSql;
import com.yts.ytsoa.business.xmwp.mapper.Sql.XmwpglSql;
import com.yts.ytsoa.business.xmwp.model.ResultModel;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface XmwpMapper {
    @Insert({
            "insert into " + Tables.XMWP_TABLE + "(uuid,xmmc,wtf,wtflxr,wtflxdh,wtsj,bsjdw,wpr,cjbm,xmfzr,yjsf,wpdscsj,ywzt,xmbzgs,hhrsh,zkbsh)" +
                    "values(replace(uuid(), '-', ''),#{m.xmmc},#{m.wtf},#{m.wtflxr},#{m.wtflxdh},#{m.wtsj},#{m.bsjdw},#{m.wpr},#{m.cjbm},#{m.xmfzr},#{m.yjsf},#{m.wpdscsj},#{m.ywzt},#{m.xmbzgs},#{m.zkbsh},#{m.hhrsh})"
    })
    int addXmwp(@Param("m") XmwpModel model);

//    @SelectProvider(type = XmwpglSql.class, method = "find")
//    List<XmwpModel> find(@Param("model") XmwpModel model) throws SQLException;

    /**
     * 根据id删除
     *
     * @param uuid
     * @return
     * @throws SQLException
     */
    @Delete({
            "delete from " + Tables.XMWP_TABLE + "where uuid =#{uuid}"
    })
    int delById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = XmwpglSql.class, method = "updById")
    int updById(@Param("model") XmwpModel model);

    @SelectProvider(type = XmwpglSql.class, method = "findByXmmc")
    @Results(id = "xmwpMap", value = {
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
    List<XmwpModel> findByXmmc(@Param("model") XmwpModel model);

    @Select({
            "select xmfzr from " + Tables.XMWP_TABLE + " where uuid=#{uuid}"
    })
    String findXmfzr(@Param("uuid") String uuid);

    @UpdateProvider(type = XmwpglSql.class, method = "update")
    int update(@Param("model") XmwpModel model);

    @UpdateProvider(type = XmwpSql.class, method = "updateYwzt")
    int updateYwzt(@Param("model") XmwpModel model);

    @SelectProvider(type = XmwpSql.class, method = "findYwzt")
    XmwpModel findYwzt(@Param("uuid") String uuid);

    /**
     * 报告申请页面
     * 项目名称搜索只能查询自己承接的项目
     * 并且必须得是审核通过的项目才能申请报告
     *
     * @param model
     * @return
     */
    @SelectProvider(type = XmwpglSql.class, method = "findByXmfzr")
    @Results(id = "resultMap", value = {
            @Result(id = true, property = "uuid", column = "uuid", javaType = String.class),
            @Result(property = "xmfzr", column = "xmfzr", javaType = String.class,
                    one = @One(select = "com.yts.ytsoa.business.account.mapper.AccountMapper.findNameByUuid")
            ),
            @Result(property = "xmzmcModels", column = "uuid", javaType = List.class,
                    many = @Many(select = "com.yts.ytsoa.business.xmcj.mapper.XmcjMapper.findByParentid"))
    })
    List<XmwpModel> findByXmfzr(@Param("model") XmwpModel model) throws SQLException;

    @Select({
            "select x.* from xmwp_table x where x.uuid=#{uuid}"
    })
    XmwpModel findXmByUuid(@Param("uuid") String uuid) throws SQLException;

    @Select({
            "SELECT x.hhrsh FROM xmwp_table x where x.uuid=#{uuid}"
    })
    int hhrsh(@Param("uuid") String uuid);

    @SelectProvider(type = XmwpglSql.class, method = "findByXmyq")
    List<XmwpModel> findByXmyq(@Param("model") XmwpModel model);

    /**
     * 项目管理页面
     * 必须是业务状态大于等于2的
     * @param model
     * @return
     */
    @SelectProvider(type = XmwpglSql.class, method = "xmgl")
    @Results(id = "xmglMap", value = {
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
    List<XmwpModel> xmgl(@Param("model") XmwpModel model);

    @Select({
            "select x.* from" + Tables.XMWP_TABLE + " x where x.uuid=#{uuid}"
    })
    @Results(id = "resultMap1", value = {
            @Result(id = true, property = "uuid", column = "uuid"),
            @Result(property = "cjbm", column = "cjbm", javaType = String.class,
                    one = @One(select = "com.yts.ytsoa.business.bm.mapper.BmMapper.findBmmcById")),
            @Result(property = "wpr", column = "wpr", javaType = String.class,
                    one = @One(select = "com.yts.ytsoa.business.account.mapper.AccountMapper.getByUuid")),
            @Result(property = "xmfzr", column = "xmfzr", javaType = String.class,
                    one = @One(select = "com.yts.ytsoa.business.account.mapper.AccountMapper.getByUuid")),
            @Result(property = "xmzmcModels", column = "uuid", javaType = List.class,
                    many = @Many(select = "com.yts.ytsoa.business.xmcj.mapper.XmcjMapper.findByParentid"))
    })
    XmwpModel findById(@Param("uuid") String uuid);
}
