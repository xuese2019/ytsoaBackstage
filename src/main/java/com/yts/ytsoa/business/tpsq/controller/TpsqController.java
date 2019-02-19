package com.yts.ytsoa.business.tpsq.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.tpsq.model.ResultTpsqModel;
import com.yts.ytsoa.business.tpsq.model.TpsqModel;
import com.yts.ytsoa.business.tpsq.service.TpsqService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "退票申请", description = "退票申请接口")
@RestController
@RequestMapping("/tpsq")
public class TpsqController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private TpsqService tpsqService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<TpsqModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            TpsqModel tpsqModel) throws Exception {
        return tpsqService.findAll(pageNow, yamlPageUtils.getPageSize(), tpsqModel);
    }

    /**
     * 新增退票申请
     */
    @ApiOperation(value = "添加退票申请")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<TpsqModel> add(@RequestBody TpsqModel tpsqModel) throws Exception {
        return tpsqService.add(tpsqModel);
    }

    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<TpsqModel> findById(@PathVariable(value = "uuid") String uuid) throws Exception {
        return tpsqService.findById(uuid);
    }

    @ApiOperation(value = "退票审核")
    @RequestMapping(value = "/tpsh", method = RequestMethod.POST)
    public ResponseResult<XmshModel> yzsh(@RequestBody XmshModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        if (model != null) {
            model.setShr(accid);
            return tpsqService.tpsh(model);
        }
        return new ResponseResult<>(false, "审核失败");
    }

    @ApiOperation(value = "退票审核记录")
    @RequestMapping(value = "/findByShjl/{prentid}", method = RequestMethod.GET)
    public ResponseResult<List<ResultTpsqModel>> findByShjl(@PathVariable("prentid") String prentid) throws Exception {
        ResponseResult<List<ResultTpsqModel>> result = tpsqService.findByShjl(prentid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result.getData());
        } else {
            return new ResponseResult<>(false, "没有审核记录");
        }
    }
}
