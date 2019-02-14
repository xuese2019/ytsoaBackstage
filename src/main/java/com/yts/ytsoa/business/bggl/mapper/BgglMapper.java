package com.yts.ytsoa.business.bggl.mapper;


import com.yts.ytsoa.business.bggl.mapper.BgglSql.BgglSql;
import com.yts.ytsoa.business.bggl.model.Bggl2Model;
import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BgglMapper {
    @InsertProvider(type = BgglSql.class, method = "addBggl")
    int addBggl(@Param("model") BgglModel model) throws SQLException;

    @InsertProvider(type = BgglSql.class, method = "addBgglpl")
    int addBgglPl(@Param("models") List<BgglModel> models) throws SQLException;


    @Select({
            "select bgbhsjs from " + Tables.BGGL_TABLE + " where bgbhsjs=#{bgbhsjs}"
    })
    int findByBgbhsjs(@Param("bgbhsjs") int bgbhsjs);

    @SelectProvider(type = BgglSql.class, method = "find")
    List<BgglModel> find(@Param("model") BgglModel model) throws SQLException;

    @Select({
            "select count(1) from bggl_table where bglx=#{bglx}"
    })
    int countByBglx(@Param("bglx") int bglx);

    @Select({
            "SELECT  MAX(bgbhsjs) from bggl_table b where b.bglx=#{bglx}"
    })
    int max(@Param("bglx") int bglx);

    @Delete({
            "delete from " + Tables.BGGL_TABLE + " where uuid=#{model.uuid}"

    })
    int delete(@Param("model") BgglModel model);

    @UpdateProvider(type = BgglSql.class, method = "update")
    int update(@Param("model") BgglModel model);

    @Select({
            "SELECT b.bgbhsjs \n" +
                    "FROM bggl_table b \n" +
                    "WHERE b.bglx=#{bglx} \n" +
                    "ORDER BY b.bgbhsjs DESC\n" +
                    "LIMIT 1 "
    })
    Integer limit(@Param("bglx") int bglx);

    @Select({
            "select bg.uuid,bg.bgbh,bg.xmmc,sh.shr,sh.shyj,sh.shjg from bggl_table  bg join shjl_table sh  ON bg.uuid=sh.prentid " +
                    "where bg.uuid=#{uuid}"
    })
    List<Bggl2Model> findById(@Param("uuid") String uuid) throws SQLException;
}
