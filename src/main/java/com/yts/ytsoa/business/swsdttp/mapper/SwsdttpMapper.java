package com.yts.ytsoa.business.swsdttp.mapper;

import com.yts.ytsoa.business.swsdttp.mapper.Sql.SwsdttpSql;
import com.yts.ytsoa.business.swsdttp.model.SwsdttpModel;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SwsdttpMapper {

    @InsertProvider(type = SwsdttpSql.class, method = "tpsc")
    void tpsc(@Param("models") List<SwsdttpModel> models);

    @Select({
            "select * from swsdttp_table where swsdtid=#{swsdtid}"
    })
    List<SwsdttpModel> find(@Param("swsdtid") String swsdtid);
}
