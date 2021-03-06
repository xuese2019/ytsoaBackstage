package com.yts.ytsoa.business.bggl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface BgglService {
    /**
     * 添加申请报告
     *
     * @param model
     * @return
     */
    ResponseResult<BgglModel> addBggl(BgglModel model) throws Exception;

    /**
     * 条件查询带分页
     */
    ResponseResult<PageInfo<BgglModel>> find(int pageNow, int pageSize, BgglModel model, String fsr, String accid) throws Exception;

    /**
     * 条件查询带分页
     */
    ResponseResult<List<BgglModel>> findsh(int pageNow, BgglModel model, String fsr, String accid) throws Exception;

    /**
     * 删除一条申请记录
     */
    ResponseResult<BgglModel> delete(BgglModel model);

    /**
     * excel导出
     */
    ResponseResult<String> excel(String basePath, String outPath, BgglModel model) throws Exception;

    /**
     * 修改之前根据id查出详细信息
     */
    ResponseResult<BgglModel> findById(String uuid) throws Exception;

    /**
     * 修改报告审核状态
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<XmshModel> update(XmshModel model) throws Exception;

    /**
     * 用于报告管理
     *
     * @param pageNow
     * @param pageSize
     * @param model
     * @param fsr
     * @param accid
     * @return
     * @throws Exception
     */
    ResponseResult<PageInfo<BgglModel>> bggl(int pageNow, int pageSize, BgglModel model, String fsr, String accid) throws Exception;

    /**
     * 项目详情，报告管理
     *
     * @param pageNow
     * @param pageSize
     * @return
     * @throws Exception
     */
    ResponseResult<List<BgglModel>> findBgByXmid(int pageNow, int pageSize, BgglModel model, String xmid) throws Exception;

    ResponseResult<BgglModel> findByXmZmc(String uuid) throws Exception;

    /**
     * 修改归档有效期
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<BgglModel> updateGdyxq(BgglModel model, String accid) throws Exception;

}
