package com.yts.ytsoa.business.pjsq.mapper;

import com.yts.ytsoa.business.pjsq.mapper.sql.PjsqSql;
import com.yts.ytsoa.business.pjsq.model.PjsqModel;
import com.yts.ytsoa.business.pjsq.model.ResPjsqModel;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PjsqMapper {
    @InsertProvider(type = PjsqSql.class, method = "addPjsq")
    int add(@Param("model") PjsqModel model) throws SQLException;

    @SelectProvider(type = PjsqSql.class, method = "findAllSql")
    List<PjsqModel> findAll(@Param("pjsqModel") PjsqModel pjsqModel) throws SQLException;

    @SelectProvider(type = PjsqSql.class, method = "find")
    List<PjsqModel> find(@Param("model") PjsqModel model) throws SQLException;

    @UpdateProvider(type = PjsqSql.class, method = "updateByShjg")
    int update(@Param("model") PjsqModel model) throws SQLException;

    @Select({
            "select p.*,a.name as 'fpsqr' from pjsq_table p join account_table a on a.name=p.fpsqr where p.uuid=#{uuid}"
    })
    PjsqModel findById(@Param("uuid") String uuid) throws SQLException;

    @SelectProvider(type = PjsqSql.class, method = "findByShjl")
    List<ResPjsqModel> findByShjl(@Param("prentid") String prentid) throws SQLException;
}
