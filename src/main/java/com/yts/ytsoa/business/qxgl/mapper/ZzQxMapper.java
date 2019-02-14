package com.yts.ytsoa.business.qxgl.mapper;

import com.yts.ytsoa.business.qxgl.mapper.sql.ZzQxSql;
import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.model.ZzQxModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface ZzQxMapper {

    @Select({
            "select * from " + Tables.ZZ_QX_TABLE + " where zzid = #{zzid} order by qxid"
    })
    List<ZzQxModel> findByZzid(@Param("zzid") String zzid) throws SQLException;

    @Select({
            "SELECT"
                    + " q.uuid,"
                    + " q.qxmc,"
                    + " q.qxfj,"
                    + " q.qxlx," +
                    "q.qxbs,"
                    + " CASE"
                    + " WHEN z.uuid != '' THEN"
                    + " '1'"
                    + " ELSE"
                    + " '0'"
                    + " END AS ico"
                    + " FROM"
                    + " " + Tables.QXGL_TABLE + " q"
                    + " LEFT JOIN zz_qx_table z ON z.qxid = q.uuid"
                    + " AND z.zzid = #{zzid} order by qxid"
    })
    List<QxglModel> findByZzid2(@Param("zzid") String zzid) throws SQLException;

    @Select({
            "select * from " + Tables.ZZ_QX_TABLE + " where zzid = #{zzid} and qxid = #{qxid} order by qxid"
    })
    List<ZzQxModel> findByZzidAndQxid(@Param("zzid") String zzid, @Param("qxid") String qxid) throws SQLException;

    @Select({
            "select q.* from account_table a"
                    + " RIGHT JOIN zz_qx_table z on z.zzid = a.bm"
                    + " RIGHT JOIN qxgl_table q on q.uuid = z.qxid"
                    + " where a.uuid = #{accid} order by q.uuid"
    })
    List<QxglModel> findByAccid(@Param("accid") String accid) throws SQLException;

    @Delete({
            "delete from " + Tables.ZZ_QX_TABLE + " where zzid = #{zzid}"
    })
    void deleteByZzid(@Param("zzid") String zzid) throws SQLException;

    @Delete({
            "delete from " + Tables.ZZ_QX_TABLE + " where uuid = #{uuid}"
    })
    void deleteByUuid(@Param("uuid") String uuid) throws SQLException;

    @Insert({
            "insert into " + Tables.ZZ_QX_TABLE + " (uuid,qxid,zzid) values (replace(uuid(), '-', ''),#{model.qxid},#{model.zzid})"
    })
    void add(@Param("model") ZzQxModel model) throws SQLException;

    @InsertProvider(type = ZzQxSql.class, method = "inserts")
    void adds(@Param("list") List<ZzQxModel> list) throws SQLException;

}
