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
            "insert into " + Tables.XMWP_TABLE + "(uuid,xmmc,wtf,wtflxr,wtflxdh,wtsj,bsjdw,wpr,cjbm,xmfzr,yjsf,wpdscsj,ywzt)" +
                    "values(replace(uuid(), '-', ''),#{m.xmmc},#{m.wtf},#{m.wtflxr},#{m.wtflxdh},#{m.wtsj},#{m.bsjdw},#{m.wpr},#{m.cjbm},#{m.xmfzr},#{m.yjsf},#{m.wpdscsj},#{m.ywzt})"
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

    @Select({
            "select x.*,a.name,z.zzjgmc from " + Tables.XMWP_TABLE + " x join account_table a on x.xmfzr=a.uuid join zzjg_table z on z.uuid=x.cjbm where x.uuid=#{uuid}"
    })
    ResultModel findById(@Param("uuid") String uuid);

    @SelectProvider(type = XmwpglSql.class, method = "findByXmmc")
    @Results(id = "xmwpMap", value = {
            @Result(property = "cjbm", column = "zzjgmc"),
            @Result(property = "xmfzr", column = "cjr")
    })
    List<XmwpModel> findByXmmc(@Param("model") XmwpModel model);

    @Select({
            "select xmfzr from " + Tables.XMWP_TABLE + " where uuid=#{uuid}"
    })
    String findXmfzr(@Param("uuid") String uuid);

    @UpdateProvider(type = XmwpglSql.class, method = "update")
    int update(@Param("model") XmwpModel model);

    @UpdateProvider(type = XmwpSql.class, method = "xmjlsh")
    int xmjlsh(@Param("model") XmwpModel model);

    @SelectProvider(type = XmwpSql.class, method = "findYwzt")
    XmwpModel findYwzt(@Param("uuid") String uuid);

}
