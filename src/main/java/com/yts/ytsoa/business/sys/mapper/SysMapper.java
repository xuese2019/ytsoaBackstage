package com.yts.ytsoa.business.sys.mapper;

import com.yts.ytsoa.business.sys.mapper.sql.SysSql;
import com.yts.ytsoa.utils.annot.AnnotC;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: LD
 * @date:
 * @description:
 */
@AnnotC
@Mapper
public interface SysMapper {

    @DeleteProvider(type = SysSql.class, method = "deleteAll")
    void deleteAll();
}
