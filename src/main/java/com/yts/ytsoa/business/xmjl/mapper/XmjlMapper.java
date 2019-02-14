package com.yts.ytsoa.business.xmjl.mapper;

import com.yts.ytsoa.business.xmjl.mapper.sql.XmjlSql;
import com.yts.ytsoa.business.xmjl.model.XmjlModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface XmjlMapper {
    @SelectProvider(type = XmjlSql.class, method = "findAllSql")
    List<XmjlModel> findAll(@Param("xmjlModel") XmjlModel xmjlModel) throws SQLException;


}
