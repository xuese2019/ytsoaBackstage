package com.yts.ytsoa.business.qxfy.mapper;

import com.yts.ytsoa.business.qxfy.model.QxfyModel;
import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QxfyMapper {

    /*根据当前登录人的id查询当前登录人所拥有的所有权限*/
    @Select({
            "select x.* from " + Tables.QXFY_TABLE + " q left join " + Tables.QXGL_TABLE + " x" +
                    " on x.uuid = q.qx_id where q.acc_id = #{accId}"
    })
    List<QxglModel> findByAccId(@Param("accId") String accId) throws Exception;

    /*根据指定的账号id赋予权限*/
    @Insert({
            "insert into " + Tables.QXFY_TABLE + " (uuid,acc_id,qx_id) values (replace(uuid(),'-',''),#{model.accId},#{model.qxId})"
    })
    void save(@Param("model") QxfyModel model) throws Exception;

    /*根据指定的账号以及指定的权限id取消权限*/
    @Delete({
            "delete from " + Tables.QXFY_TABLE + " where acc_id = #{accId} and qx_id = #{qxId}"
    })
    void deleteByAccIdAndQxId(@Param("accId") String accId, @Param("qxId") String qxId) throws Exception;

    /*根据账号id和权限id查询有无记录*/
    @Select({
            "select * from " + Tables.QXFY_TABLE + " where acc_id = #{accId} and qx_id = #{qxId}"
    })
    List<QxfyModel> findByAccIdAndQxId(@Param("accId") String accId, @Param("qxId") String qxId) throws Exception;
}
