package com.yts.ytsoa.business.xjsq.mapper;

import com.yts.ytsoa.business.xjsq.mapper.Sql.XjsqSql;
import com.yts.ytsoa.business.xjsq.model.XjsqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface XjsqMapper {
    @Insert({
            "Insert into " + Tables.XJSQ_TABLE + "(uuid,xjsqr,xjqsrq,xjjzrq,xjsy,ssnf,shjg) values(replace(uuid(), '-', ''),#{m.xjsqr},#{m.xjqsrq},#{m.xjjzrq},#{m.xjsy},#{m.ssnf},#{m.shjg})"
    })
    void addXjsq(@Param("m") XjsqModel model) throws SQLException;

    @SelectProvider(type = XjsqSql.class, method = "find")
    List<XjsqModel> find(@Param("model") XjsqModel model) throws SQLException;

    @Select({
            "select  x.uuid,a.`name` AS 'xjsqr',x.xjqsrq,x.xjjzrq,x.xjsy  from  xjsq_table x LEFT JOIN account_table a ON x.xjsqr=a.uuid  where x.uuid=#{uuid}"
    })
    XjsqModel findById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = XjsqSql.class, method = "update")
    void update(@Param("model") XjsqModel model) throws SQLException;
}
