package com.yts.ytsoa.business.bgflbh.mapper;

import com.yts.ytsoa.business.bgflbh.model.BglxModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;


@Mapper
public interface BgflbhMapper {

    @UpdateProvider(type = BglxSql.class, method = "updByBglx")
    int update(@Param("model") BglxModel model);

    @Select({
            "SELECT b.bgbhsjs \n" +
                    "FROM bggl_table b \n" +
                    "WHERE b.bglx=#{bglx} \n" +
                    "ORDER BY b.bgbhsjs DESC\n" +
                    "LIMIT 1 "
    })
    int num(@Param("bglx") int bglx);

    @Select({
            "select bgbh_hz from bglx_table where bglx=#{bglx}"
    })
    int findByBglx(@Param("bglx") int bglx);

    @Select({
            "SELECT b.bgbh_hz as bgbhHz,b.lb\n" +
                    "FROM bglx_table b \n" +
                    "where b.bglx=#{model.bglx}"
    })
    BglxModel find(@Param("model") BglxModel model);
}
