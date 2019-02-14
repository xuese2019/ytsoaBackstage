package com.yts.ytsoa.business.ywzt.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.ywzt.model.YwztModel;
import com.yts.ytsoa.business.ywzt.service.YwztService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api(value = "业务交流主题接口", description = "业务交流主题接口")
@RestController
@RequestMapping("/ywzt")
public class YwztController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private YwztService ywztService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<YwztModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            YwztModel ywztModel) throws Exception {
        return ywztService.findAll(pageNow, yamlPageUtils.getPageSize(), ywztModel);
    }


    @ApiOperation(value = "新增交流主题")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<YwztModel> add(@RequestBody YwztModel ywztModel, HttpServletRequest request) throws Exception {
        String dqdlr = JWTUtils.getAccId(request);
        ywztModel.setHfr(dqdlr);
        ywztModel.setHfsj(new Date());
        return ywztService.add(ywztModel);
    }
}
