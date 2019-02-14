package com.yts.ytsoa.business.ywjlhf.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xxgl.service.XxglService;
import com.yts.ytsoa.business.ywjlhf.model.YwjlhfModel;
import com.yts.ytsoa.business.ywjlhf.service.YwjlhfService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api(value = "业务交流回复", description = "业务交流回复接口")
@RestController
@RequestMapping("/ywjlhf")
public class YwjlhfController {
    @Autowired
    private YwjlhfService ywjlhfService;
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private XxglService xxglService;

    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<YwjlhfModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            YwjlhfModel ywjlhfModel) throws Exception {
        return ywjlhfService.findAll(pageNow, yamlPageUtils.getPageSize(), ywjlhfModel);
    }

    @ApiOperation(value = "新增业务交流")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<YwjlhfModel> add(@RequestBody YwjlhfModel ywjlhfModel, HttpServletRequest request) throws Exception {
        String dqdlr = JWTUtils.getAccId(request);
        ywjlhfModel.setHfr(dqdlr);
        ywjlhfModel.setHfsj(new Date());
        return ywjlhfService.add(ywjlhfModel);
    }
}
