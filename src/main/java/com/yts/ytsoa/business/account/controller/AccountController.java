package com.yts.ytsoa.business.account.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.GlobalExceptionHandler;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.model.AdminModel;
import com.yts.ytsoa.business.account.model.PwdModel;
import com.yts.ytsoa.business.account.model.interfaces.AccountAdd;
import com.yts.ytsoa.business.account.service.AccountService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.poi.ExcelModelExportUtils;
import com.yts.ytsoa.utils.yamlutils.YamlFileUtils;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * //@GetMapping 因为此类注解某些情况可能存在兼容性，不推荐这种类型的注解
 *
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Api(value = "员工信息接口", description = "员工信息接口")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private YamlFileUtils yamlFileUtils;

    @Autowired
    private AccountService service;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<AccountModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                          @RequestBody AccountModel model) throws Exception {
        return service.findAll(pageNow, yamlPageUtils.getPageSize(), model);
    }

    @ApiOperation(value = "根据部门id查询")
    @RequestMapping(value = "/bmid/{bmid}", method = RequestMethod.GET)
    public ResponseResult<List<AccountModel>> findAll(@PathVariable("bmid") String bmid) throws Exception {
        AccountModel model = new AccountModel();
        model.setBm(bmid);
        return service.findAll(model);
    }

    @ApiOperation(value = "根据员工id查询")
    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.GET)
    public ResponseResult<AccountModel> getById(@PathVariable("uuid") String uuid) throws Exception {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        ResponseResult<List<AccountModel>> result = service.findAll(model);
        return new ResponseResult<>(result.isSuccess(), result.getMessage(), result.getData() != null ? result.getData().get(0) : null);
    }

    @ApiOperation(value = "当前登陆人的个人信息")
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResponseResult<AccountModel> getByToken(HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        AccountModel model = new AccountModel();
        model.setUuid(accId);
        ResponseResult<List<AccountModel>> result = service.findAll(model);
        if (result.isSuccess()) {
            AccountModel model1 = result.getData().get(0);
            model1.setLx(0);
            return new ResponseResult<>(true, "成功", model1);
        } else {
            AdminModel adminModel = new AdminModel();
            String acc = JWTUtils.getAcc(request);
            adminModel.setAccount(acc);
            adminModel.setLx(1);
            ResponseResult<AccountModel> adminByAccount = service.getAdminByAccount(adminModel);
            if (adminByAccount.isSuccess()) {
                return new ResponseResult<>(true, "成功", adminByAccount.getData());
            } else {
                return new ResponseResult<>(false, "未获取到数据");
            }
        }
    }

    /**
     * 新增员工
     *
     * @param model
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "新增员工")
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseResult<AccountModel> add(@Validated(value = AccountAdd.class) @RequestBody AccountModel model,
                                            BindingResult result) throws Exception {
       /* if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        }*/
        if (model.getUuid() == null || model.getUuid().isEmpty()) {
            //对密码进行 md5 加密
            String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
            model.setPassword(md5Password);
            model.setLx(1);
            return service.add(model);
        }
        model.setAccount(null);
        model.setPassword(null);
        return service.updateById(model, 0);
    }

    @ApiOperation(value = "根据员工id删除")
    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<AccountModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return service.deleteById(uuid);
    }

    @ApiOperation(value = "根据员工id完善个人资料")
    @RequestMapping(value = "/grzl", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> updateById(@RequestBody AccountModel model,
                                                   BindingResult result,
                                                   HttpServletRequest request) throws Exception {
        /*if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }*/
        if (model.getUuid() == null || model.getUuid().isEmpty()) {
            String accId = JWTUtils.getAccId(request);
            model.setUuid(accId);
        }
        return service.updateById(model, 2);
    }

    @ApiOperation(value = "根据员工id修改")
    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> updateById2(@RequestBody AccountModel model,
                                                    BindingResult result) throws Exception {
        /*if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }*/
        return service.updateById(model, 0);
    }

    @ApiOperation(value = "当前登陆人修改密码")
    @RequestMapping(value = "/updatePwd", method = RequestMethod.PUT)
    public ResponseResult<String> updateByAccount(@RequestBody PwdModel model,
                                                  BindingResult result,
                                                  HttpServletRequest request) throws Exception {
        /*if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        }*/

        String accId = JWTUtils.getAccId(request);
        AccountModel model1 = new AccountModel();
        model1.setUuid(accId);
        ResponseResult<List<AccountModel>> result2 = service.findAll(model1);
        if (result2.isSuccess()) {
            if (model.isPasswordOk()) {
                String md5Password2 = DigestUtils.md5DigestAsHex(model.getPasswordBack().getBytes(StandardCharsets.UTF_8));
                if (!md5Password2.equals(result2.getData().get(0).getPassword())) {
                    return new ResponseResult<>(false, "原密码错误", null);
                }
                AccountModel model3 = new AccountModel();
                model3.setUuid(accId);
                String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
                model3.setPassword(md5Password);
                ResponseResult<AccountModel> result1 = service.updateById(model3, 1);
                if (result1.isSuccess()) {
                    return new ResponseResult<>(true, "成功,请从新登陆", "logout");
                } else {
                    return new ResponseResult<>(false, "当前账户已不存在", null);
                }
            } else {
                return new ResponseResult<>(false, "两次输入的密码不一致", null);
            }
        } else {
            return new ResponseResult<>(false, "当前账户已不存在", null);
        }
    }

    @ApiOperation(value = "根据员工id重置密码")
    @RequestMapping(value = "/czmm/{uuid}", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> czmm(@PathVariable("uuid") String uuid) throws Exception {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        String md5Password = DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8));
        model.setPassword(md5Password);
        return service.updateById(model, 0);
    }

    @ApiOperation(value = "根据查询条件导出员工信息")
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public void excel(@RequestBody AccountModel model,
                      HttpServletResponse response) throws Exception {
        ResponseResult<String> result = service.excel(yamlFileUtils.getFileModel() + "accountModel.xls", yamlFileUtils.getDowPath(), model);
        if (result.isSuccess()) {
//            调用下载
            new ExcelModelExportUtils().download2(yamlFileUtils.getDowPath(), result.getData(), response);
        } else {
            new GlobalExceptionHandler().res(response, "下载失败");
        }
    }

    @ApiOperation(value = "根据id修改")
    @RequestMapping(value = "/updById", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> updById(@RequestBody AccountModel model, BindingResult result) throws Exception {
        /*if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }*/
        return service.updById(model);
    }

    @ApiOperation(value = "项目承接，搜索除项目负责人外所有的员工，项目的id：uuid，员工的姓名：name")
    @RequestMapping(value = "/findExceptXmfzr/{uuid}", method = RequestMethod.POST)
    public ResponseResult<List<AccountModel>> findExceptXmfzr(@PathVariable(value = "uuid") String uuid,
                                                              @RequestBody String mc) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType2 = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, String.class);
        Map<String, String> userMap = mapper.readValue(mc, javaType2);
        String mc1 = userMap.get("mc");
        return service.findExceptXmfzr(uuid, mc1);
    }
}
