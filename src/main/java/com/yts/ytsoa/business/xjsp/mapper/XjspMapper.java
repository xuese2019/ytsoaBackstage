package com.yts.ytsoa.business.xjsp.mapper;

import com.yts.ytsoa.business.xjsp.model.XjspModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface XjspMapper {
    @Insert({
            "insert into " + Tables.XJSP_TABLE + " (uuid,xjsqr,xjqsrq,xjjzrq,xjsy,shyj,status,ssnf) " +
                    "values(replace(uuid(), '-', ''),#{x.xjsqr},#{x.xjqsrq},#{x.xjjzrq},#{x.xjsy},#{x.shyj},#{x.status},#{x.ssnf})"
    })
    int addXjsp(@Param("x") XjspModel model);
}
