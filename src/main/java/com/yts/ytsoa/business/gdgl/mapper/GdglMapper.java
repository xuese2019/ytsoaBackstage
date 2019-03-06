package com.yts.ytsoa.business.gdgl.mapper;

import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.business.gdgl.mapper.GdglSql.GdglSql;
import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.gdgl.model.GdglResultModel;
import com.yts.ytsoa.business.gdgl.query.GdglQueryModel;
import com.yts.ytsoa.business.gdgl.result.ResultModel;
import com.yts.ytsoa.business.xmcj.model.XmzmcModel;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface GdglMapper {
    /**
     * 添加
     *
     * @param model
     * @return
     */
    @InsertProvider(type = GdglSql.class, method = "addGdsq")
    int addGdsq(@Param("model") GdglModel model);


    @SelectProvider(type = GdglSql.class, method = "findGdsh")
    List<ResultModel> findGdsh(@Param("model") GdglQueryModel model);

    @SelectProvider(type = GdglSql.class, method = "find")
    @Results(id = "gdglMap", value = {
            @Result(column = "xmzmc", property = "xmzmc")
    })
    List<GdglModel> find(@Param("model") GdglModel model);

    @UpdateProvider(type = GdglSql.class, method = "updateByIdSql")
    int updById(@Param("gdglModel") GdglModel gdglModel) throws SQLException;

    @Select({
            "select d.uuid,d.gdsqbh_hz,d.sqrq,d.sqf,d.xmmc,x.xmzmc AS xmzmc,d.bgbh,d.das,d.damc,d.dgcs,d.zt,d.jyzt,d.shjg  from dggd_table d left join xmzmc_table x ON x.uuid=d.xmzmc  where d.uuid = #{uuid}"
            /*  "select * from zsk_table where uuid={#uuid}"*/
    })
    GdglModel findById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = GdglSql.class, method = "update")
    int update(@Param("model") GdglModel model);

    @SelectProvider(type = GdglSql.class, method = "findByDamc")
    List<GdglModel> findByDamc(@Param("model") GdglModel model) throws SQLException;

    @UpdateProvider(type = GdglSql.class, method = "updatejyzt")
    int updatejyzt(@Param("model") GdglModel model);

    /**
     * 二级联动查询
     *
     * @param parentid
     * @return
     */
    @Select({
            "SELECT * FROM xmzmc_table x where x.parentid=#{parentid}"
    })
    List<XmzmcModel> findByUuid(@Param("parentid") String parentid) throws SQLException;

    @SelectProvider(type = GdglSql.class, method = "findBgByUuid")
    List<BgglModel> findBgByUuid(@Param("uuid") String uuid);


    @SelectProvider(type = GdglSql.class, method = "findByShjl")
    List<GdglResultModel> findByShjl(@Param("prentid") String prentid) throws SQLException;

    @SelectProvider(type = GdglSql.class, method = "updates")
    void updates(@Param("gdglModel") GdglModel gdglModel) throws SQLException;

    @SelectProvider(type = GdglSql.class, method = "findGdByXmid")
    List<GdglModel> findGdByXmid(@Param("xmid") String xmid) throws SQLException;

    /**
     * 根据审核记录的uuid查出项目详情
     *
     * @param uuid
     * @return
     */
    @SelectProvider(type = GdglSql.class, method = "findXmwpByShjlUuid")
    XmwpModel findXmwpByShjlUuid(@Param("uuid") String uuid);

    /**
     * 根据项目的uuid查出所有归档记录
     *
     * @param uuid
     * @return
     */
    @Select({
            "SELECT d.* from xmwp_table x join dggd_table d on x.uuid=d.xmid where x.uuid=#{uuid}"
    })
    List<GdglModel> findGdglByXmUuid(@Param("uuid") String uuid);
}
