package com.yts.ytsoa.business.ywjlhf.mapper;

import com.yts.ytsoa.business.ywjlhf.mapper.sql.YwjlhfSql;
import com.yts.ytsoa.business.ywjlhf.model.YwjlhfModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface YwjlhfMapper {
    @Insert({
            "insert into " + Tables.YWJLHF_TABLE + "(uuid,hfr,hfsj,hfnr,xmid,ssnf)" + "values(replace(uuid(), '-', '')" +
                    ",#{ywjlhfModel.hfr} " +
                    ",#{ywjlhfModel.hfsj}" +
                    ",#{ywjlhfModel.hfnr}" +
                    ",#{ywjlhfModel.xmid}" +
                    ",#{ywjlhfModel.ssnf})"
    })
    int add(@Param("ywjlhfModel") YwjlhfModel ywjlhfModel) throws SQLException;

    @SelectProvider(type = YwjlhfSql.class, method = "findAllSql")
    @Results(id = "ywjlhfMap", value = {
            @Result(property = "hfr", column = "name")
    })
    List<YwjlhfModel> findAll(@Param("ywjlhfModel") YwjlhfModel ywjlhfModel) throws SQLException;
/*
    @SelectProvider(type = YwjlhfSql.class, method = "findAllSql")
   @Results(id = "ywjlhfMap", value = {
            @Result(property = "hfr", column = "name")
    })
    List<YwjlhfModel> findAll(@Param("ywjlhfModel") YwjlhfModel ywjlhfModel)throws SQLException;*/


}
