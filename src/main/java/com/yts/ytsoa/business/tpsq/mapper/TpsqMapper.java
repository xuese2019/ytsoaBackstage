package com.yts.ytsoa.business.tpsq.mapper;

import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.tpsq.mapper.sql.TpsqSql;
import com.yts.ytsoa.business.tpsq.model.TpsqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TpsqMapper {
    @Insert({
            "insert into " + Tables.TPSQ_TABLE + "(uuid,bh,fpsqr,fplb,xmfzr,tpsqr,sqf,tpsqsj,tpyy,kpbh,tpje,fplx,prentid)" + "values(replace(uuid(), '-', '')," +
                    "#{tpsqModel.bh}," +
                    "#{tpsqModel.fpsqr}," +
                    "#{tpsqModel.fplb}," +
                    "#{tpsqModel.xmfzr}," +
                    "#{tpsqModel.tpsqr}," +
                    "#{tpsqModel.sqf}," +
                    "#{tpsqModel.tpsqsj}," +
                    "#{tpsqModel.tpyy}," +
                    "#{tpsqModel.kpbh}," +
                    "#{tpsqModel.tpje}," +
                    "#{tpsqModel.fplx}," +
                    "#{tpsqModel.prentid}"
    })
    int add(@Param("tpsqModel") TpsqModel tpsqModel) throws SQLException;

    @SelectProvider(type = TpsqSql.class, method = "findAllSql")
    List<TpsqModel> findAll(@Param("model") TpsqModel tpsqModel) throws SQLException;

    @InsertProvider(type = TpsqSql.class, method = "tpsh")
    int tpsh(@Param("model") XmshModel model) throws SQLException;

    @UpdateProvider(type = TpsqSql.class, method = "update")
    int update(@Param("model") TpsqModel model);

    @Select({
            "select t.*,a.name from tpsq_table t join account_table a on a.uuid=t.xmfzr where t.uuid=#{uuid}"
    })
    TpsqModel findById(@Param("uuid") String uuid);
}
