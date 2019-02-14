package com.yts.ytsoa.business.qxgl.service.impl;

import com.yts.ytsoa.business.qxgl.mapper.ZzQxMapper;
import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.model.ZzQxModel;
import com.yts.ytsoa.business.qxgl.service.ZzQxService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LD
 */
@Slf4j
@Service
public class ZzQxServiceImpl implements ZzQxService {

    @Autowired
    private ZzQxMapper mapper;

    @Override
    public ResponseResult<List<ZzQxModel>> findByZzid(String zzid) throws Exception {
        List<ZzQxModel> list = mapper.findByZzid(zzid);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "成功", list);
        }
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<List<QxglModel>> findByZzid2(String zzid) throws Exception {
        List<QxglModel> list = mapper.findByZzid2(zzid);
        if (list.size() > 0) {
            List<QxglModel> qxglModels = buildByRecursiveMenu(list);
            return new ResponseResult<>(true, "成功", qxglModels);
        }
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<List<QxglModel>> findByAccid(String accid) throws Exception {
        List<QxglModel> list = mapper.findByAccid(accid);
        if (list.size() > 0 && list.get(0) != null) {
            List<QxglModel> newList = buildByRecursiveMenu(list);
            return new ResponseResult<>(true, "成功", newList);
        }
        return new ResponseResult<>(false, "未查询到记录");
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    private static List<QxglModel> buildByRecursiveMenu(List<QxglModel> treeNodes) {
        List<QxglModel> trees = new ArrayList<>();
        for (QxglModel treeNode : treeNodes) {
            if (treeNode != null && treeNode.getUuid() != null) {
                if ("0".equals(treeNode.getQxfj())) {
                    trees.add(findChildrenMenu(treeNode, treeNodes));
                }
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    private static QxglModel findChildrenMenu(QxglModel treeNode, List<QxglModel> treeNodes) {
        for (QxglModel it : treeNodes) {
            if (it != null && it.getUuid() != null) {
                if (treeNode.getUuid().equals(it.getQxfj())) {
                    treeNode.getList().add(findChildrenMenu(it, treeNodes));
                }
            }
        }
        return treeNode;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<ZzQxModel> setQx(String zzid, List<ZzQxModel> list) throws Exception {
        mapper.deleteByZzid(zzid);
        if (list != null && list.size() > 0) {
            mapper.adds(list);
        }
        return new ResponseResult<>(true, "成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<ZzQxModel> setQx(String zzid, String qxstr) throws Exception {
        List<ZzQxModel> list = mapper.findByZzidAndQxid(zzid, qxstr);
        if (list.size() > 0) {
            mapper.deleteByUuid(list.get(0).getUuid());
        } else {
            ZzQxModel model = new ZzQxModel();
            model.setZzid(zzid);
            model.setQxid(qxstr);
            mapper.add(model);
        }
        return new ResponseResult<>(true, "成功");
    }
}
