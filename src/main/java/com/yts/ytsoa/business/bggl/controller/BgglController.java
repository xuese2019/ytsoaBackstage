package com.yts.ytsoa.business.bggl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bggl.model.Bggl2Model;
import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.business.bggl.service.BgglService;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlFileUtils;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "报告管理", description = "报告管理接口")
@RestController
@RequestMapping("/bggl")
public class BgglController {
    @Autowired
    private YamlFileUtils yamlFileUtils;
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private BgglService bgglService;

    /**
     * 添加一条报告
     *
     * @param model
     * @return
     * @throws Exception
     */
    /*@ApiOperation(value = "申请报告")
    @RequestMapping(value = "/addBggl", method = RequestMethod.POST)
    public ResponseResult<BgglModel> addBggl(@RequestBody BgglModel model) throws Exception {
        if (model != null) {
            return bgglService.addBggl(model);
        }
        return new ResponseResult<>(false, "申请失败");
    }*/

    /**
     * 分页查询带条件
     *
     * @param pageNow
     * @param model
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "分页查询带条件")
    @RequestMapping(value = "/find/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<BgglModel>> find(@PathVariable("pageNow") int pageNow, @RequestBody BgglModel model, String fsr) throws Exception {
        if (model != null) {
            return bgglService.find(pageNow, yamlPageUtils.getPageSize(), model, fsr);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @ApiOperation(value = "删除一条记录")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseResult<BgglModel> delete(@RequestBody BgglModel model) {
        if (model != null) {
            return bgglService.delete(model);
        }
        return new ResponseResult<>(false, "数据删除失败");
    }

    @ApiOperation(value = "报告审核")
    @RequestMapping(value = "/bgsh", method = RequestMethod.PUT)
    public ResponseResult<XmshModel> update(@RequestBody XmshModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        if (model != null) {
            model.setShr(accid);
            return bgglService.update(model);
        }
        return new ResponseResult<>(false, "审核失败");
    }

    @ApiOperation(value = "excel导出")
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public ResponseResult<String> result(@RequestBody BgglModel model) throws Exception {
        if (model != null) {
            return bgglService.excel(yamlFileUtils.getFileModel() + "bgglModel.xls", yamlFileUtils.getDowPath(), model);
        }
        return new ResponseResult<>(false, "导出失败");
    }

    @ApiOperation(value = "根据id查询该条记录")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<Bggl2Model> findById(@PathVariable("uuid") String uuid) throws Exception {
        if (uuid != null && !uuid.isEmpty()) {
            return bgglService.findById(uuid);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @ApiOperation(value = "批量/申请报告")
    @RequestMapping(value = "/addBggl", method = RequestMethod.POST)
    public ResponseResult<BgglModel> addBggl(@RequestBody BgglModel model) throws Exception {
        if (model != null) {
            return bgglService.addBggl(model);
        }
        return new ResponseResult<>(false, "申请报告失败");
    }
}

