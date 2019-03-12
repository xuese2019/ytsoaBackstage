package com.yts.ytsoa.business.swsdt.mapper;

import com.yts.ytsoa.business.swsdt.mapper.Sql.SwsdtSql;
import com.yts.ytsoa.business.swsdt.model.SwsdtModel;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface SwsdtMapper {
    @InsertProvider(type = SwsdtSql.class, method = "insertSwsdt")
    int insertSwsdt(@Param("model") SwsdtModel model);

    @SelectProvider(type = SwsdtSql.class, method = "find")
    @Results(id = "resultMap", value = {
            @Result(id = true, property = "uuid", column = "uuid"),
            @Result(property = "scr", column = "scr", javaType = String.class,
                    one = @One(select = "com.yts.ytsoa.business.account.mapper.AccountMapper.findNameByUuid")
            ),
            @Result(property = "models", column = "uuid", javaType = List.class,
                    many = @Many(select = "com.yts.ytsoa.business.swsdttp.mapper.SwsdttpMapper.find"))
    })
    List<SwsdtModel> find(@Param("model") SwsdtModel model) throws SQLException;
}
