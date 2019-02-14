package com.yts.ytsoa.business.xmyq.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmyq.model.XmyqModel;
import com.yts.ytsoa.business.xmyq.service.XmyqService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "项目延期", description = "项目延期接口")
@RestController
@RequestMapping("/xmyq")
public class XmyqController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private XmyqService xmyqService;

    /**
     * 新增延期记录
     *
     * @param xmyqModel
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "新增审核记录")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<XmyqModel> add(@RequestBody XmyqModel xmyqModel) throws Exception {
        return xmyqService.add(xmyqModel);
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmyqModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            XmyqModel xmyqModel) throws Exception {
        xmyqModel.setXtdqsj(new Date());
        return xmyqService.findAll(pageNow, yamlPageUtils.getPageSize(), xmyqModel);
    }
}
