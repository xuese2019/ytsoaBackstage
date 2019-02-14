package com.yts.ytsoa.business.bgflbh.controller;

import com.yts.ytsoa.business.bgflbh.model.BglxModel;
import com.yts.ytsoa.business.bgflbh.service.BgflbhService;
import com.yts.ytsoa.utils.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "生成报告编号", description = "生成报告编号")
@RestController
@RequestMapping("/bgbh")
public class BgbhController {
    @Autowired
    private BgflbhService bgflbhService;

  /*  @RequestMapping(value = "/bgbh", method = RequestMethod.POST)
    public ResponseResult<BgflbhModel> bgbh(@RequestBody BgflbhModel model) throws Exception {
        if (model!=null) {
            return bgflbhService.find(model);
        }
        return new ResponseResult<>(false, "生成失败");
    }*/
/*   @RequestMapping(value = "/bgbh", method = RequestMethod.POST)
   public ResponseResult<BgflbhModel> bgbh(@RequestBody BgflbhModel model) throws Exception {
       if (model!=null) {
           return bgflbhService.find(model);
       }
       return new ResponseResult<>(false, "生成失败");
   }*/

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseResult<BglxModel> find(@RequestBody BglxModel model) throws Exception {
        return bgflbhService.find(model);
    }
}
