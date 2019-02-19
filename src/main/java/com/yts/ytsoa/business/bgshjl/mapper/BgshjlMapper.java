package com.yts.ytsoa.business.bgshjl.mapper;

import com.yts.ytsoa.business.bgshjl.mapper.Sql.BgshjlSql;
import com.yts.ytsoa.business.bgshjl.model.BgshjlModel;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BgshjlMapper {

    @InsertProvider(type = BgshjlSql.class, method = "insert")
    int insert(@Param("model") BgshjlModel model);

    @SelectProvider(type = BgshjlSql.class, method = "find")
    List<BgshjlModel> find(@Param("model") BgshjlModel model);

    /**
     * 根据报告的uuid查出该项目的负责人
     *
     * @param uuid
     * @return
     * @throws SQLException
     */
    @Select({
            "SELECT xt.*" +
                    "FROM bggl_table b join xmzmc_table x on x.uuid=b.xmid join xmwp_table xt on xt.uuid=x.parentid" +
                    "where b.uuid=#{uuid}"
    })
    XmwpModel findXmfzr(@Param("uuid") String uuid) throws SQLException;
}
