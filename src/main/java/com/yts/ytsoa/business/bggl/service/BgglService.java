package com.yts.ytsoa.business.bggl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bggl.model.Bggl2Model;
import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface BgglService {
    /**
     * 批量/申请报告
     *
     * @param model
     * @return
     */
    ResponseResult<BgglModel> addBggl(BgglModel model) throws Exception;


    /**
     * 条件查询带分页
     */
    ResponseResult<PageInfo<BgglModel>> find(int pageNow, int pageSize, BgglModel model, String fsr) throws Exception;

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
    ResponseResult<Bggl2Model> findById(String uuid) throws Exception;

    ResponseResult<XmshModel> update(XmshModel model) throws Exception;
}