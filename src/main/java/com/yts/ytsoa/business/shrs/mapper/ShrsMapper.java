package com.yts.ytsoa.business.shrs.mapper;

import com.yts.ytsoa.business.shrs.mapper.sql.ShrsSql;
import com.yts.ytsoa.business.shrs.model.ShrsModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ShrsMapper {
    @SelectProvider(type = ShrsSql.class, method = "findAllSql")
    List<ShrsModel> findAll(@Param("shrsModel") ShrsModel shrsModel) throws SQLException;

    @Select({
            "select s.uuid,s.shbm,a.name as 'shr' from  shrs_table s join account_table a on a.uuid=s.shr  where s.uuid = #{uuid}"
    })
    ShrsModel findById(@Param("uuid") String uuid) throws SQLException;

    @Insert({
            "insert into " + Tables.SHRS_TABLE + "(uuid,accountid,shbm,shr)" + "values(replace(uuid(), '-', ''),#{shrsModel.accountid} ,#{shrsModel.shbm},#{shrsModel.shr})"
    })
    int add(@Param("shrsModel") ShrsModel shrsModel) throws SQLException;

    @Delete({
            "delete from " + Tables.SHRS_TABLE + " where uuid = #{uuid}"
    })
    void delById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = ShrsSql.class, method = "updateByIdSql")
    int updById(@Param("shrsModel") ShrsModel shrsModel) throws SQLException;

    @Select({
            "select *  from  shrs_table s where s.shlb=#{shlb}"
    })
    ShrsModel findBylb(@Param("shlb") int shlb) throws SQLException;
}
