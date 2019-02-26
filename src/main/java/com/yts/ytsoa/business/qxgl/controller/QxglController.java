package com.yts.ytsoa.business.qxgl.controller;

import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.service.QxglService;
import com.yts.ytsoa.business.qxgl.utils.QxUtils;
import com.yts.ytsoa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LD
 */
@Api(value = "权限接口", description = "权限接口")
@Slf4j
@RestController
@RequestMapping("/qxgl")
public class QxglController {

    @Autowired
    private QxglService service;

    @ApiOperation(value = "重置权限，只有管理员能操作")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public ResponseResult<QxglModel> rest() throws Exception {
        return service.saves(new QxUtils().getQxData());
    }

    /**
     * 获取权限tree
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取所有的权限树")
    @RequestMapping(value = "/qxgl", method = RequestMethod.GET)
    public ResponseResult<List<QxglModel>> findAll() throws Exception {
        return service.findAll();
    }

}
