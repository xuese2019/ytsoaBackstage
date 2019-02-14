package com.yts.ytsoa.business.kqtj.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.kqtj.model.KqtjModel;
import com.yts.ytsoa.business.kqtj.service.KqtjService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "考勤统计", description = "空勤统计接口")
@RestController
@RequestMapping("/kqtj")
public class KqtjController {
    @Autowired
    private KqtjService kqtjService;
    @Autowired
    private YamlPageUtils yamlPageUtils;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<KqtjModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            KqtjModel kqtjModel) throws Exception {
        return kqtjService.findAll(pageNow, yamlPageUtils.getPageSize(), kqtjModel);
    }
}
