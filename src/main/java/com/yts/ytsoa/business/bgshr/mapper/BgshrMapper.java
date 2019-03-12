package com.yts.ytsoa.business.bgshr.mapper;

import com.yts.ytsoa.business.bgshr.mapper.Sql.BgshrSql;
import com.yts.ytsoa.business.bgshr.model.BgshrModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BgshrMapper {

    /**
     * 报告审核人设置
     *
     * @param model
     * @return
     * @throws SQLException
     */
    @UpdateProvider(type = BgshrSql.class, method = "bgshrsz")
    int bgshrsz(@Param("model") BgshrModel model) throws SQLException;

    @Select({
            "SELECT a.uuid,a.name AS zkbid FROM  bgshr_table  b LEFT JOIN account_table a ON a.uuid=b.zkbid"
    })
    BgshrModel find();

    @Select({
            "select * from bgshr_table"
    })
    List<BgshrModel> find2();
}
