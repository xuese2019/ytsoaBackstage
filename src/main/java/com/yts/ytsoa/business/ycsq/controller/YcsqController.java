package com.yts.ytsoa.business.ycsq.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.ycsq.model.ResultModel;
import com.yts.ytsoa.business.ycsq.model.YcsqModel;
import com.yts.ytsoa.business.ycsq.service.YcsqService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "用车申请", description = "用车申请接口")
@RestController
@RequestMapping("/ycsq")
public class YcsqController {
    @Autowired
    private YcsqService ycsqService;
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Value("${file.file-model}")
    private String fileModel;
    @Value("${file.dow-path}")
    private String dowPath;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<YcsqModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            YcsqModel ycsqModel) throws Exception {
        return ycsqService.findAll(pageNow, yamlPageUtils.getPageSize(), ycsqModel);
    }

    /**
     * 新增用车申请
     */
    @ApiOperation(value = "新增用车申请")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<YcsqModel> add(@RequestBody YcsqModel ycsqModel) throws Exception {
        return ycsqService.add(ycsqModel);
    }

    /**
     * 根据id查询
     *
     * @param uuid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<YcsqModel> findById(@PathVariable("uuid") String uuid) throws Exception {
        YcsqModel ycsqModel = new YcsqModel();
        ycsqModel.setUuid(uuid);
        ResponseResult<List<YcsqModel>> result = ycsqService.findById(ycsqModel);
        return new ResponseResult<>(result.isSuccess(), result.getMessage(), result.getData() != null ? result.getData().get(0) : null);
    }

    @ApiOperation(value = "审核记录")
    @RequestMapping(value = "/findByShjl/{prentid}", method = RequestMethod.GET)
    public ResponseResult<List<ResultModel>> findByShjl(@PathVariable("prentid") String prentid) throws Exception {
        ResponseResult<List<ResultModel>> result = ycsqService.findByShjl(prentid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result.getData());
        } else {
            return new ResponseResult<>(false, "没有审核记录");
        }
    }

    @ApiOperation(value = "根据id修改")
    @RequestMapping(value = "/updById", method = RequestMethod.PUT)
    public ResponseResult<YcsqModel> updById(@RequestBody YcsqModel ycsqModel, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return ycsqService.updById(ycsqModel);
    }

    @ApiOperation(value = "用车审核")
    @RequestMapping(value = "/ycsh", method = RequestMethod.POST)
    public ResponseResult<XmshModel> update(@RequestBody XmshModel model, HttpServletRequest request, String fsr) throws Exception {
        String accid = JWTUtils.getAccId(request);
        if (model != null) {
            model.setShr(accid);
            return ycsqService.update(model, fsr);
        }
        return new ResponseResult<>(false, "审核失败");
    }

}
