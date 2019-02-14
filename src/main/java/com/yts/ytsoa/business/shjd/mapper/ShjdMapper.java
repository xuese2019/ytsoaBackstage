package com.yts.ytsoa.business.shjd.mapper;

import com.yts.ytsoa.business.shjd.mapper.Sql.ShjdSql;
import com.yts.ytsoa.business.shjd.model.ShjdModel;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ShjdMapper {
    @SelectProvider(type = ShjdSql.class, method = "find")
    ShjdModel find(@Param("model") ShjdModel model);

    @InsertProvider(type = ShjdSql.class, method = "insert")
    int insert(@Param("model") ShjdModel model);

    @UpdateProvider(type = ShjdSql.class, method = "update")
    int update(@Param("model") ShjdModel model);
}
