package com.yts.ytsoa.business.zsk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.zsk.mapper.ZskMapper;
import com.yts.ytsoa.business.zsk.model.ZskModel;
import com.yts.ytsoa.business.zsk.service.ZskService;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ZskServiceImpl implements ZskService {
    @Autowired
    private ZskMapper zskMapper;

    /**
     * 条件查询所有
     *
     * @param pageNow
     * @param pageSize
     * @param zskModel
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<PageInfo<ZskModel>> findAll(int pageNow, int pageSize, ZskModel zskModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize, "a.jrsj desc");
        List<ZskModel> list = zskMapper.findAll(zskModel);
        PageInfo<ZskModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    /**
     * 根据id查询
     *
     * @param zskModel
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<List<ZskModel>> findById(ZskModel zskModel) throws Exception {
        List<ZskModel> list = zskMapper.findById(zskModel.getUuid());
        if (list.size() > 0) {
            return new ResponseResult<>(true, "查询成功", list);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    /**
     * 添加
     *
     * @param zskModel
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<ZskModel> add(ZskModel zskModel) throws Exception {
        int list = zskMapper.add(zskModel);
        if (list != 0) {
            return new ResponseResult<>(true, "添加成功", null);
        } else {
            return new ResponseResult<>(false, "添加失败", null);
        }
    }

    /**
     * 删除
     *
     * @param uuid
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<ZskModel> delById(String uuid) throws Exception {
        zskMapper.delById(uuid);
        return new ResponseResult<>(true, "删除成功", null);
    }

    /**
     * 修改
     *
     * @param zskModel
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<ZskModel> updById(ZskModel zskModel) throws Exception {
        int result = zskMapper.updById(zskModel);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        } else return new ResponseResult<>(false, "修改失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<ZskModel> addZsk(List<ZskModel> zskModel) throws Exception {
        String uuid = GetUuid.getUUID();
        if (zskModel.size() == 1 && zskModel != null) {
            zskModel.get(0).setUuid(uuid);
            zskModel.get(0).setTjr(zskModel.get(0).getTjr());
            zskModel.get(0).setJrsj(zskModel.get(0).getJrsj());
            zskModel.get(0).setLx(zskModel.get(0).getLx());
            zskModel.get(0).setWjly(zskModel.get(0).getWjly());
            zskModel.get(0).setWjzt(zskModel.get(0).getWjzt());
            zskMapper.add(zskModel.get(0));
            return new ResponseResult<>(true, "添加成功");
        } else {
            for (int i = 0; i < zskModel.size(); i++) {
                for (int j = 0; j < zskModel.size(); j++) {
                    zskModel.get(i).setUuid(zskModel.get(j).getUuid());
                    zskModel.get(i).setTjr(zskModel.get(j).getTjr());
                    zskModel.get(i).setJrsj(zskModel.get(j).getJrsj());
                    zskModel.get(i).setLx(zskModel.get(j).getLx());
                    zskModel.get(i).setWjly(zskModel.get(j).getWjly());
                    zskModel.get(i).setWjzt(zskModel.get(j).getWjzt());
                    zskMapper.addZskPL(zskModel);
                    return new ResponseResult<>(true, "批量添加成功");
                }
            }
        }
        return new ResponseResult<>(false, "添加失败");
    }
}
