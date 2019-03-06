package com.yts.ytsoa.business.xmjlcy.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmjlcy.model.XmjlcyModel;
import com.yts.ytsoa.business.xmjlcy.service.XmjlcyService;
import com.yts.ytsoa.business.xmwp.service.XmwpService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "项目交流成员", description = "项目交流成员接口")
@RestController
@RequestMapping("/xmjlcy")
public class XmjlcyController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private XmjlcyService xmjlcyService;
    @Autowired
    private XmwpService xmwpService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmjlcyModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            XmjlcyModel xmjlcyModel) throws Exception {
        return xmjlcyService.findAll(pageNow, yamlPageUtils.getPageSize(), xmjlcyModel);
    }

    /**
     * 新增知识库
     */
    @ApiOperation(value = "新增项目交流成员")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<XmjlcyModel> add(@RequestBody XmjlcyModel xmjlcyModel, HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        ResponseResult<String> xmfzr = xmwpService.findXmfzr(xmjlcyModel.getXmid());
        if (xmfzr.getData().equals(accId)) {
            ResponseResult<XmjlcyModel> add = xmjlcyService.add(xmjlcyModel);
            if (add.isSuccess()) {
                return new ResponseResult<>(true, "添加成功");
            }
        }
        return new ResponseResult<>(false, "只有项目负责人才能添加成员");
    }

    @ApiOperation(value = "根据id删除")
    @RequestMapping(value = "/delById/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<XmjlcyModel> delById(@PathVariable("uuid") String uuid, HttpServletRequest request) throws Exception {
        return xmjlcyService.delById(uuid);
    }

    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<List<XmjlcyModel>> findById(@PathVariable("uuid") String uuid) throws Exception {
        return xmjlcyService.findById(uuid);
    }
}
