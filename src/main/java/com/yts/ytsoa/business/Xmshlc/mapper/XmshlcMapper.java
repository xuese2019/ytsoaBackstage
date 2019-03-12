package com.yts.ytsoa.business.Xmshlc.mapper;

import com.yts.ytsoa.business.Xmshlc.mapper.Sql.XmshlcSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface XmshlcMapper {
    @SelectProvider(type = XmshlcSql.class, method = "findShlx")
    Integer shlx(@Param("uuid") String uuid);
}
