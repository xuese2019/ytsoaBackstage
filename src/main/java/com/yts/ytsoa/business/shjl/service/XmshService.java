package com.yts.ytsoa.business.shjl.service;

import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.sql.SQLException;
import java.util.List;

public interface XmshService {
    ResponseResult<XmshModel> add(XmshModel xmshModel) throws Exception;

    ResponseResult<XmshModel> findById(String uuid) throws SQLException;

    ResponseResult<List<XmshModel>> findShjl(String prentid) throws Exception;

    ResponseResult<XmshModel> jysh(XmshModel model, String accid) throws Exception;

    ResponseResult<XmshModel> bgsh(XmshModel model, String accid) throws Exception;

}
