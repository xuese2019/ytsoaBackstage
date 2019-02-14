package com.yts.ytsoa.business.bgshjl.mapper;

import com.yts.ytsoa.business.bgshjl.mapper.Sql.BgshjlSql;
import com.yts.ytsoa.business.bgshjl.model.BgshjlModel;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface BgshjlMapper {

    @InsertProvider(type = BgshjlSql.class, method = "insert")
    int insert(@Param("model") BgshjlModel model);

    @SelectProvider(type = BgshjlSql.class, method = "find")
    List<BgshjlModel> find(@Param("model") BgshjlModel model);
}
