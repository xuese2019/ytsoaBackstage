package com.yts.ytsoa.business.qxgl.controller;

import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.model.ZzQxModel;
import com.yts.ytsoa.business.qxgl.service.QxglService;
import com.yts.ytsoa.business.qxgl.service.ZzQxService;
import com.yts.ytsoa.business.qxgl.utils.QxUtils;
import com.yts.ytsoa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private ZzQxService zzQxService;

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

    /**
     * 获取菜单tree
     *
     * @return
     * @throws Exception
     */
//    @RequestMapping(value = "/menu", method = RequestMethod.GET)
//    public ResponseResult<List<QxglModel>> menuTree() throws Exception {
//        return service.findMenu();
//    }
    @ApiOperation(value = "赋予取消权限")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/qxgl/zzid/{zzid}/{qxstr}", method = RequestMethod.GET)
    public ResponseResult<ZzQxModel> setQx(@PathVariable("qxstr") String qxstr,
                                           @PathVariable("zzid") String zzid) throws Exception {
//        List<ZzQxModel> list = new ArrayList<>();
//        if (!"".equals(qxstr)) {
//            String[] split = qxstr.split(",");
//            for (int i = 0; i < split.length; i++) {
//                ZzQxModel model = new ZzQxModel();
//                model.setQxid(split[i]);
//                model.setZzid(zzid);
//                list.add(model);
//            }
//        }
        return zzQxService.setQx(zzid, qxstr);
    }

    @ApiOperation(value = "获取左侧菜单")
    @RequestMapping(value = "/zzqx/{zzid}", method = RequestMethod.GET)
    public ResponseResult<List<ZzQxModel>> setQx(@PathVariable("zzid") String zzid) throws Exception {
        return zzQxService.findByZzid(zzid);
    }

    @ApiOperation(value = "获取所有权限以及所拥有的权限，只用作于授权处")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/zzqx/fyqx/{zzid}", method = RequestMethod.GET)
    public ResponseResult<List<QxglModel>> getQx(@PathVariable("zzid") String zzid) throws Exception {
        return zzQxService.findByZzid2(zzid);
    }
}
