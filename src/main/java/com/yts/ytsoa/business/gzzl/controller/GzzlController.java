package com.yts.ytsoa.business.gzzl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gzzl.model.GzzlModel;
import com.yts.ytsoa.business.gzzl.service.GzzlService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(value = "工作指令", description = "工工作指令接口")
@RestController
@RequestMapping("/gzzl")
public class GzzlController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private GzzlService gzzlService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<GzzlModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            GzzlModel gzzlModel, HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        gzzlModel.setJsr(accId);
        return gzzlService.findAll(pageNow, yamlPageUtils.getPageSize(), gzzlModel);
    }

    @ApiOperation(value = "保存发送的消息记录，所有的前台发送的消息都通过此接口进行消息的发送以及推送")
    @RequestMapping(value = "/add/{xmid}", method = RequestMethod.POST)
    public ResponseResult<GzzlModel> save(@Valid @RequestBody GzzlModel gzzlModel,
                                          BindingResult result,
                                          HttpServletRequest request, @PathVariable("xmid") String xmid) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        String accId = JWTUtils.getAccId(request);
        gzzlModel.setFsr(accId);
        gzzlModel.setXmid(xmid);
        return gzzlService.add(gzzlModel, accId);
    }


}
