package com.yts.ytsoa.business.qxfy.service.impl;

import com.yts.ytsoa.business.qxfy.mapper.QxfyMapper;
import com.yts.ytsoa.business.qxfy.model.QxfyModel;
import com.yts.ytsoa.business.qxfy.service.QxfyService;
import com.yts.ytsoa.business.qxgl.mapper.QxglMapper;
import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class QxfyServiceImpl implements QxfyService {

    @Autowired
    private QxfyMapper mapper;
    @Autowired
    private QxglMapper qxglMapper;

    @Override
    public ResponseResult<List<QxglModel>> findByAccId(String accId) throws Exception {
        List<QxglModel> list2 = qxglMapper.findAll();
        List<QxglModel> list = mapper.findByAccId(accId);
        if (list2.size() > 0) {
            list2.forEach(k -> {
                list.forEach(j -> {
                    if (k.getUuid().equals(j.getUuid())) {
                        k.setIco("1");
                    }
                });
            });
            List<QxglModel> list3 = buildByRecursive(list2);
            return new ResponseResult<>(true, "成功", list3);
        }
        return new ResponseResult<>(false, "未查询到数据");
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    private static List<QxglModel> buildByRecursive(List<QxglModel> treeNodes) {
        List<QxglModel> trees = new ArrayList<>();
        for (QxglModel treeNode : treeNodes) {
            if ("0".equals(treeNode.getQxfj())) {
                trees.add(findChildren(treeNode, treeNodes));
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
    private static QxglModel findChildren(QxglModel treeNode, List<QxglModel> treeNodes) {
        for (QxglModel it : treeNodes) {
            if (treeNode.getUuid().equals(it.getQxfj())) {
                treeNode.getList().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<String> setQxByAcc(QxfyModel model) throws Exception {
//        判断指定的账号有没有指定的权限
        List<QxfyModel> list = mapper.findByAccIdAndQxId(model.getAccId(), model.getQxId());
        if (list.size() > 0) {
//            如果有指定的权限，则变为取消权限
            mapper.deleteByAccIdAndQxId(model.getAccId(), model.getQxId());
        } else {
//            如果没有指定的权限，则新增权限
            mapper.save(model);
        }
        return new ResponseResult<>(true, "成功");
    }
}
