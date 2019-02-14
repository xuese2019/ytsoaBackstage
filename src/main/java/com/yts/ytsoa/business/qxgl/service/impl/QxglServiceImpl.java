package com.yts.ytsoa.business.qxgl.service.impl;

import com.yts.ytsoa.business.qxgl.mapper.QxglMapper;
import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.service.QxglService;
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
public class QxglServiceImpl implements QxglService {

    @Autowired
    private QxglMapper mapper;

    @Override
    public ResponseResult<List<QxglModel>> findAll() throws Exception {
        List<QxglModel> list = mapper.findAll();
        if (list.size() > 0) {
            List<QxglModel> newList = buildByRecursive(list);
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

    @Override
    public ResponseResult<List<QxglModel>> findMenu() throws Exception {
        List<QxglModel> list = mapper.findMenu();
        if (list.size() > 0) {
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
            if ("0".equals(treeNode.getQxfj())) {
                trees.add(findChildrenMenu(treeNode, treeNodes));
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
            if (treeNode.getUuid().equals(it.getQxfj())) {
                treeNode.getList().add(findChildrenMenu(it, treeNodes));
            }
        }
        return treeNode;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<QxglModel> saves(List<QxglModel> list) throws Exception {
        mapper.deleteAll();
        mapper.saves(list);
        return new ResponseResult<>(true, "权限重置成功");
    }
}
