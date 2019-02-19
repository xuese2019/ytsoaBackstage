package com.yts.ytsoa.business.ycsq.mapper;

import com.yts.ytsoa.business.ycsq.mapper.sql.YcsqSql;
import com.yts.ytsoa.business.ycsq.model.ResultModel;
import com.yts.ytsoa.business.ycsq.model.YcsqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface YcsqMapper {
    @SelectProvider(type = YcsqSql.class, method = "findAllSql")
    List<YcsqModel> findAll(@Param("ycsqModel") YcsqModel ycsqModel) throws SQLException;

    @Insert({
            "insert into " + Tables.YCSQ_TABLE + "(uuid,ghrq,sqr,shr,sqrq,ycsy,cphgsd,cph,cfgls,cfsj,fhsj,ghr,fhgls,shjg,shyj,bz) " + " values (replace(uuid(), '-', '')," +

                    "#{ycsqModel.ghrq}," +
                    "#{ycsqModel.sqr}," +
                    "#{ycsqModel.shr}," +
                    "#{ycsqModel.sqrq}," +
                    "#{ycsqModel.ycsy}," +
                    "#{ycsqModel.cphgsd}," +
                    "#{ycsqModel.cph}," +
                    "#{ycsqModel.cfgls}," +
                    "#{ycsqModel.cfsj}," +
                    "#{ycsqModel.fhsj}," +
                    "#{ycsqModel.ghr}," +
                    "#{ycsqModel.fhgls}," +
                    "#{ycsqModel.shjg}," +
                    "#{ycsqModel.shyj}," +
                    "#{ycsqModel.bz})"
    })
    int add(@Param("ycsqModel") YcsqModel ycsqModel) throws SQLException;

    @Select({
            "select * from " + Tables.YCSQ_TABLE + " where uuid = #{uuid}"
            /*  "select * from zsk_table where uuid={#uuid}"*/
    })
    List<YcsqModel> findById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = YcsqSql.class, method = "updateByIdSql")
    int updById(@Param("ycsqModel") YcsqModel ycsqModel) throws SQLException;

    @UpdateProvider(type = YcsqSql.class, method = "update")
    int update(@Param("model") YcsqModel model) throws SQLException;

    @SelectProvider(type = YcsqSql.class, method = "findByShjl")
    List<ResultModel> findByShjl(@Param("prentid") String prentid) throws SQLException;
}
