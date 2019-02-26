package com.yts.ytsoa.business.bggl.mapper;


import com.yts.ytsoa.business.bggl.mapper.BgglSql.BgglSql;
import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.business.bggl.model.BgglsModel;
import com.yts.ytsoa.business.xmcj.model.XmzmcModel;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BgglMapper {
    @InsertProvider(type = BgglSql.class, method = "addBggl")
    int addBggl(@Param("model") BgglModel model) throws SQLException;

    @Select({
            "select bgbhsjs from " + Tables.BGGL_TABLE + " where bgbhsjs=#{bgbhsjs}"
    })
    int findByBgbhsjs(@Param("bgbhsjs") int bgbhsjs);

    @SelectProvider(type = BgglSql.class, method = "find")
    List<BgglModel> find(@Param("model") BgglModel model) throws SQLException;


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
            "select bg.uuid,bg.bgbh,bg.xmmc,sh.shr,sh.shyj,sh.shjg from bggl_table bg join shjl_table sh  ON bg.uuid=sh.prentid " +
                    "where bg.uuid=#{uuid}"
    })
    List<BgglsModel> findById(@Param("uuid") String uuid) throws SQLException;

    /**
     * 根据报告的xmid 查询该项目的信息
     *
     * @param xmid
     * @return
     * @throws SQLException
     */
    @Select({
            "SELECT xm.* FROM bggl_table b join xmzmc_table x on x.uuid=b.xmid join xmwp_table xm on xm.uuid=x.parentid WHERE b.xmid=#{xmid}"
    })
    XmwpModel findByXmid(@Param("xmid") String xmid);

    /**
     * 查询项目下的所有报告
     *
     * @param uuid
     * @return
     */
    @Select({
            "SELECT b.* FROM xmwp_table x join xmzmc_table xm on x.uuid=xm.parentid join bggl_table b on b.xmid= xm.uuid where x.uuid=#{uuid}"
    })
    List<BgglModel> findBgsByUuid(@Param("uuid") String uuid);

    /**
     * 根据报告的xmid查出子名称
     *
     * @param parentid
     * @return
     */
    @Select({
            "select xmzmc from xmzmc_table where parentid=#{parentid}"
    })
    XmzmcModel findByParentid(@Param("parentid") String parentid);

    @SelectProvider(type = BgglSql.class, method = "findBgByXmid")
    List<BgglModel> findBgByXmid(@Param("uuid") String uuid, @Param("bgzbr") String bgzbr);
}
