package com.yts.ytsoa.business.xmcy.Controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.business.xmcy.service.XmcyService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "项目成员接口", description = "项目成员接口")
@RestController
@RequestMapping("/xmcy")
public class XmcyController {
    @Autowired
    private XmcyService xmcyService;
    @Autowired
    private YamlPageUtils yamlPageUtils;

    @ApiOperation(value = "批量/添加一个成员")
    @RequestMapping(value = "/addXmcy", method = RequestMethod.POST)
    public ResponseResult<XmcyModel> addXmcy(@RequestBody List<XmcyModel> models) throws Exception {
        return xmcyService.addXmcy(models);
    }
   /* @ApiOperation(value = "批量/添加一个成员")
    @RequestMapping(value = "/addXmcy", method = RequestMethod.POST)
    public ResponseResult<XmcyModel> addXmcy(@RequestBody XmcyModel model) throws Exception {
        return xmcyService.addXmcy(model);
    }*/

    @ApiOperation(value = "条件查询带分页")
    @RequestMapping(value = "/find/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmcyModel>> find(@PathVariable("pageNow") int pageNow, @RequestBody XmcyModel model) throws Exception {
        return xmcyService.find(pageNow, yamlPageUtils.getPageSize(), model);
    }

    @ApiOperation(value = "修改项目成员评语")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseResult<XmcyModel> update(@RequestBody XmcyModel model) throws Exception {
        if (model != null) {
            return xmcyService.update(model);
        }
        return new ResponseResult<>(false, "修改失败");
    }

    /*@ApiOperation(value = "人工统计")
    @RequestMapping(value = "/rgtj/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<resultModel>> rgtj(@PathVariable("pageNow") int pageNow, @RequestBody resultModel model) throws Exception {
        return xmcyService.rgtj(pageNow, yamlPageUtils.getPageSize(), model);
    }*/

    @ApiModelProperty(value = "根据id查出详细信息")
    @RequestMapping(value = "findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<XmcyModel> findById(@PathVariable("uuid") String uuid) throws Exception {
        if (uuid != null) {
            return xmcyService.findById(uuid);
        }
        return new ResponseResult<>(false, "查无数据");
    }


    @ApiModelProperty(value = "根据xmid查询项目的所有成员")
    @RequestMapping(value = "findxmid/{xmid}", method = RequestMethod.GET)
    public ResponseResult<List<XmcyModel>> findxmid(@PathVariable("xmid") String xmid) throws Exception {
        if (xmid != null) {
            return xmcyService.findxmid(xmid);
        }
        return new ResponseResult<>(false, "查无数据");
    }

    @ApiModelProperty(value = "添加一个项目成员")
    @RequestMapping(value = "/insertXmcy", method = RequestMethod.POST)
    public ResponseResult<XmcyModel> insertXmcy(@RequestBody XmcyModel model) throws Exception {
        if (model != null) {
            return xmcyService.insertXmcy(model);
        }
        return new ResponseResult<>(false, "添加失败");
    }
}
