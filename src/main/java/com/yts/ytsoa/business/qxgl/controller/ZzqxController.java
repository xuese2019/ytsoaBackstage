package com.yts.ytsoa.business.qxgl.controller;

import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.service.ZzQxService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Api(value = "组织机构权限对应接口", description = "组织机构权限对应接口")
@Slf4j
@RestController
@RequestMapping("/zzqx")
public class ZzqxController {

    @Autowired
    private ZzQxService service;

    /**
     * 获取当前登陆人的权限
     *
     * @return
     */
    @ApiOperation(value = "获取当前登陆人的权限")
    @RequestMapping(value = "/zzqx", method = RequestMethod.GET)
    public ResponseResult<List<QxglModel>> find(HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        return service.findByAccid(accId);
    }
}
