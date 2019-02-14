package com.yts.ytsoa.business.ywzt.mapper;

import com.yts.ytsoa.business.ywzt.mapper.sql.YwztSql;
import com.yts.ytsoa.business.ywzt.model.YwztModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface YwztMapper {

    @SelectProvider(type = YwztSql.class, method = "findAllSql")
    @Results(id = "ywztMap", value = {
            @Result(property = "hfr", column = "name")
    })
    List<YwztModel> findAll(@Param("ywztModel") YwztModel ywztModel) throws SQLException;

    /**
     * 添加
     *
     * @param ywztModel
     * @return
     * @throws SQLException
     */
    @Insert({
            "insert into " + Tables.YWZT_TABLE + "(uuid,fqzt_d,hfr,hfsj,hfnr,ssnf)" + "values(replace(uuid(), '-', ''),#{ywztModel.fqzt_d} ,#{ywztModel.hfr},#{ywztModel.hfsj},#{ywztModel.hfnr},#{ywztModel.ssnf})"
    })
    int add(@Param("ywztModel") YwztModel ywztModel) throws SQLException;

}
