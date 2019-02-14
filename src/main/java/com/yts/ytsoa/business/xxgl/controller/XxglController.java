package com.yts.ytsoa.business.xxgl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xxgl.model.XxglModel;
import com.yts.ytsoa.business.xxgl.service.XxglService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Api(value = "消息(通知)管理接口", description = "消息(通知)管理接口")
@Slf4j
@RestController
@RequestMapping("/xxgl")
public class XxglController {

    @Autowired
    private YamlPageUtils yamlPageUtils;

    @Autowired
    private XxglService service;

    /**
     * 根据状态分页查询
     *
     * @param pageNow 当前页数
     * @return 消息结果集
     * @throws Exception
     */
    @ApiOperation(value = "分页查询当前登陆人的消息")
    @RequestMapping(value = "/xxgl/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XxglModel>> findByZtAndJsr(@PathVariable("pageNow") int pageNow,
                                                              HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        XxglModel model = new XxglModel();
        model.setJsr(accId);
        return service.findAll(pageNow, yamlPageUtils.getPageSize(), model);
    }

    @ApiOperation(value = "返回当前登陆人最后10条未读取的消息")
    @RequestMapping(value = "/wdq", method = RequestMethod.GET)
    public ResponseResult<PageInfo<XxglModel>> wdq(HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        XxglModel model = new XxglModel();
        model.setJsr(accId);
        model.setZt(1);
        return service.findAll(1, 10, model);
    }

    /**
     * 保存发送消息记录
     *
     * @param model
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "保存发送的消息记录，所有的前台发送的消息都通过此接口进行消息的发送以及推送")
    @RequestMapping(value = "/xxgl", method = RequestMethod.POST)
    public ResponseResult<XxglModel> save(@Valid @RequestBody XxglModel model,
                                          BindingResult result,
                                          HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        String accId = JWTUtils.getAccId(request);
        model.setFsr(accId);
        return service.save(model);
    }

    /**
     * 点击消息后将消息状态更改为已读
     *
     * @param zt
     * @param uuid
     * @return
     */
    @ApiOperation(value = "点击消息后将消息状态更改为已读")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zt", value = "状态", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "uuid", value = "消息的主键", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "/xxgl/xxzt/{zt}/{uuid}", method = RequestMethod.GET)
    public ResponseResult<XxglModel> setZt(@PathVariable("zt") int zt,
                                           @PathVariable("uuid") String uuid) throws Exception {
        return service.updateZtByUuid(uuid, zt);
    }
}
