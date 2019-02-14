package com.yts.ytsoa.business.xmjl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmjl.model.XmjlModel;
import com.yts.ytsoa.business.xmjl.service.XmjlService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "项目内部交流", description = "项目内部交流接口")
@RestController
@RequestMapping("/xmjl")
public class XmjlController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private XmjlService xmjlService;


    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmjlModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            XmjlModel xmjlModel) throws Exception {
        return xmjlService.findAll(pageNow, yamlPageUtils.getPageSize(), xmjlModel);
    }
}
