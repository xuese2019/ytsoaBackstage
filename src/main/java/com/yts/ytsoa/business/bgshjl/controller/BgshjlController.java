package com.yts.ytsoa.business.bgshjl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bgshjl.model.BgshjlModel;
import com.yts.ytsoa.business.bgshjl.service.BgshjlService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(value = "报告审核记录", description = "报告审核记录")
@RestController
@RequestMapping("/bgshjl")
public class BgshjlController {

    @Autowired
    private BgshjlService bgshjlService;
    @Autowired
    private YamlPageUtils yamlPageUtils;

    @ApiOperation(value = "添加报告审核记录")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseResult<BgshjlModel> insert(@RequestBody BgshjlModel model) {
        if (model != null) {
            return bgshjlService.insert(model);
        }
        return new ResponseResult<>(false, "添加失败");
    }

    @ApiOperation(value = "分页查询带条件")
    @RequestMapping(value = "/find/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<BgshjlModel>> find(@PathVariable("pageNow") int pageNow, @RequestBody BgshjlModel model) {
        return bgshjlService.find(pageNow, yamlPageUtils.getPageSize(), model);
    }
}
