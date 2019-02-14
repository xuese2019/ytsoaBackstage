package com.yts.ytsoa.business.ywjl.mapper;

import com.yts.ytsoa.business.ywjl.mapper.sql.YwjlSql;
import com.yts.ytsoa.business.ywjl.model.YwjlModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface YwjlMapper {

    @SelectProvider(type = YwjlSql.class, method = "findAllSql")
    @Results(id = "ywjlMap", value = {
            @Result(property = "fqr", column = "name")
    })
    List<YwjlModel> findAll(@Param("ywjlModel") YwjlModel ywjlModel) throws SQLException;

    /**
     * 添加
     *
     * @param ywjlModel
     * @return
     * @throws SQLException
     */
    @Insert({
            "insert into " + Tables.YWJL_TABLE + "(uuid,fqr,bm,fqzt,fqsj,ssnf)" + "values(replace(uuid(), '-', ''),#{ywjlModel.fqr} ,#{ywjlModel.bm},#{ywjlModel.fqzt},#{ywjlModel.fqsj},#{ywjlModel.ssnf})"
    })
    int add(@Param("ywjlModel") YwjlModel ywjlModel) throws SQLException;

    /**
     * 删除
     *
     * @param uuid
     * @throws SQLException
     */
    @Delete({
            "delete from " + Tables.YWJL_TABLE + " where uuid = #{uuid}"
    })
    void delById(@Param("uuid") String uuid) throws SQLException;
}
