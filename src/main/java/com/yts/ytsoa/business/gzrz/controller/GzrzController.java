package com.yts.ytsoa.business.gzrz.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gzrz.model.GzrzModel;
import com.yts.ytsoa.business.gzrz.service.GzrzService;
import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.business.xmcy.service.XmcyService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.DateUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(value = "工作日志接口", description = "工作日志接口")
@RestController
@RequestMapping("/gzrz")
public class GzrzController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private GzrzService gzrzService;
    @Autowired
    private XmcyService xmcyService;

    @ApiOperation(value = "条件查询带分页，默认搜索全部")
    @RequestMapping(value = "/find/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<GzrzModel>> find(@PathVariable("pageNow") int pageNow, @RequestBody GzrzModel model) {
        return gzrzService.find(pageNow, yamlPageUtils.getPageSize(), model);
    }

    @ApiOperation(value = "添加一条日志（非/项目日志）")
    @RequestMapping(value = "/addGzrz", method = RequestMethod.POST)
    public ResponseResult<GzrzModel> addGzrz(@RequestBody GzrzModel model, HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        ResponseResult<List<XmcyModel>> response = xmcyService.findYgid(model.getXmid());
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("HHSS");
        String dqsj = s.format(date);
        if (response != null) {
            for (int i = 0; i < response.getData().size(); i++) {
                if (accId.equals(response.getData().get(i).getYgid())) {
                    model.setTjr(accId);
                    model.setTjsj(new Date());
                    return gzrzService.addGzrz(model);
                } else if (DateUtils.rztjsjqr(dqsj, "1700")) {
                    model.setTjr(accId);
                    model.setTjsj(new Date());
                    return gzrzService.addGzrz(model);
                } else {
                    return new ResponseResult<>(false, "非项目成员不能提交日志");
                }
            }
        }
        return new ResponseResult<>(false, "工作日志必须5点以后才能提交");
    }

    @ApiOperation(value = "根据项目id，查出该项目的所有日志")
    @RequestMapping(value = "/findByXmid/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<GzrzModel>> findByXmid(@PathVariable("pageNow") int pageNow, @RequestBody GzrzModel model) throws Exception {
        return gzrzService.findByXmid(pageNow, yamlPageUtils.getPageSize(), model);
    }
}
