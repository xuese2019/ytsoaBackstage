package com.yts.ytsoa.business.xmjz.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmjz.modedl.XmjzModel;
import com.yts.ytsoa.business.xmjz.service.XmjzService;
import com.yts.ytsoa.business.xmwp.service.XmwpService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "项目进展", description = "项目进展接口")
@RestController
@RequestMapping("/xmjz")
public class XmjzController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private XmjzService xmjzService;
    @Autowired
    private XmwpService xmwpService;

    /**
     * 分页条件查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmjzModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody XmjzModel xmjzModel)
            throws Exception {
        return xmjzService.findAll(pageNow, yamlPageUtils.getPageSize(), xmjzModel);
    }

    /**
     * 新增项目进展
     */
    @ApiOperation(value = "新增项目进展")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<XmjzModel> add(@RequestBody XmjzModel xmjzModel, HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        ResponseResult<String> xmfzr = xmwpService.findXmfzr(xmjzModel.getXmid());
        if (xmfzr.isSuccess()) {
            if (xmfzr.getData().equals(accId)) {
                return xmjzService.add(xmjzModel);
            }
        }
        return new ResponseResult<>(false, "只有项目负责人才能发布项目进展");
    }
}
