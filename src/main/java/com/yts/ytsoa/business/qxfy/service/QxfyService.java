package com.yts.ytsoa.business.qxfy.service;

import com.yts.ytsoa.business.qxfy.model.QxfyModel;
import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface QxfyService {

    /**
     * 根据账号查询当前账号所拥有的权限
     *
     * @param accId
     * @return
     * @throws Exception
     */
    ResponseResult<List<QxglModel>> findByAccId(String accId) throws Exception;

    /**
     * 根据账号赋予权限，如果指定的账号拥有指定的权限则是取消指定的权限
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<String> setQxByAcc(QxfyModel model) throws Exception;

//    /*根据指定的账号id赋予权限*/
//    @Insert({
//            "insert into " + Tables.QXFY_TABLE + " (uuid,acc_id,qx_id) values (replace(uuid(),'-',''),#{model.accId},#{model.qxId})"
//    })
//    void save(QxfyModel model) throws Exception;
//
//    /*根据指定的账号以及指定的权限id取消权限*/
//    @Delete({
//            "delete from " + Tables.QXFY_TABLE + " where acc_id = #{accId} and qx_id = #{qxId}"
//    })
//    void deleteByAccIdAndQxId(String accId, String qxId) throws Exception;
}
