package com.yts.ytsoa.business.tpsq.mapper;

import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.tpsq.mapper.sql.TpsqSql;
import com.yts.ytsoa.business.tpsq.model.ResultTpsqModel;
import com.yts.ytsoa.business.tpsq.model.TpsqModel;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TpsqMapper {
    @InsertProvider(type = TpsqSql.class, method = "addTpsq")
    int add(@Param("tpsqModel") TpsqModel tpsqModel) throws SQLException;

    @SelectProvider(type = TpsqSql.class, method = "findAllSql")
    List<TpsqModel> findAll(@Param("model") TpsqModel tpsqModel) throws SQLException;

    @InsertProvider(type = TpsqSql.class, method = "tpsh")
    int tpsh(@Param("model") XmshModel model) throws SQLException;

    @UpdateProvider(type = TpsqSql.class, method = "update")
    int update(@Param("model") TpsqModel model) throws SQLException;

    @Select({
            "select * from tpsq_table  where  uuid=#{uuid}"
    })
    TpsqModel findById(@Param("uuid") String uuid);

    @SelectProvider(type = TpsqSql.class, method = "findByShjl")
    List<ResultTpsqModel> findByShjl(@Param("prentid") String prentid) throws SQLException;
}
