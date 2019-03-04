package com.yts.ytsoa.business.xmcy.mapper;

import com.yts.ytsoa.business.xmcy.mapper.XmcySql.XmcySql;
import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.business.xmcy.result.resultModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface XmcyMapper {
    /**
     * 批量保存
     *
     * @param models
     * @throws Exception
     */
    @InsertProvider(type = XmcySql.class, method = "saves")
    void saves(@Param("models") List<XmcyModel> models) throws Exception;

    @Insert({
            "insert into " + Tables.XMCY_TABLE + "(uuid,xmid,ygid) values(replace(uuid(), '-', ''),#{model.xmid},#{model.ygid})"
    })
    int addXmcy(@Param("model") XmcyModel model) throws SQLException;

    @InsertProvider(type = XmcySql.class, method = "addXmcyPl")
    int addXmcyPl(@Param("models") List<XmcyModel> models) throws SQLException;

    @SelectProvider(type = XmcySql.class, method = "find")
    List<XmcyModel> find(@Param("model") XmcyModel model) throws SQLException;

    @UpdateProvider(type = XmcySql.class, method = "update")
    int update(@Param("model") XmcyModel model) throws SQLException;

/*    @SelectProvider(type = XmcySql.class, method = "rgtj")
    List<resultModel> rgtj(@Param("x") resultModel model) throws SQLException;*/

    @SelectProvider(type = XmcySql.class, method = "findById")
    XmcyModel findById(@Param("uuid") String uuid) throws SQLException;

    @InsertProvider(type = XmcySql.class, method = "insertXmcy")
    int insertXmcy(@Param("model") XmcyModel model) throws SQLException;

    @SelectProvider(type = XmcySql.class, method = "findXmcy")
    List<XmcyModel> findYgid(@Param("xmid") String xmid) throws SQLException;

    @InsertProvider(type = XmcySql.class, method = "insertRgglTable")
    int insertRgglTable(@Param("models") List<resultModel> models) throws SQLException;

    @Select({
            "SELECT x.ygid FROM xmcy_table x WHERE x.xmid=#{xmid}"
    })
    List<XmcyModel> findYgidByXmid(@Param("xmid") String xmid);

    @Select({
            "SELECT x.uuid,x.ygid FROM xmcy_table x WHERE x.xmid=#{xmid}"
    })
    List<XmcyModel> findxmid(@Param("xmid") String xmid) throws SQLException;
}
