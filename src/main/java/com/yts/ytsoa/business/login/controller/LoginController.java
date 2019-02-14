package com.yts.ytsoa.business.login.controller;

import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.model.AdminModel;
import com.yts.ytsoa.business.account.model.interfaces.AccountLogin;
import com.yts.ytsoa.business.account.service.AccountService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author LD
 */
@Api(value = "登陆接口", description = "登陆接口")
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "获取token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult<String> login(@Validated(value = AccountLogin.class) @RequestBody AccountModel model,
                                        BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        }
        String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
//        验证输入的信息
        ResponseResult<List<AccountModel>> result1 = accountService.findByAccount(model);
        if (result1.isSuccess()) {
            AccountModel model1 = result1.getData().get(0);
            if (md5Password.equals(model1.getPassword())) {
                try {
                    String s = JWTUtils.creaToken(result1.getData().get(0).getAccount(), result1.getData().get(0).getUuid(), result1.getData().get(0).getBm());
                    return new ResponseResult<>(true, "成功", s);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseResult<>(false, "令牌生成错误");
                }
            }
        } else {
            AdminModel model1 = new AdminModel();
            model1.setAccount(model.getAccount());
            ResponseResult<AccountModel> result2 = accountService.getAdminByAccount(model1);
            if (result2.isSuccess()) {
                if (md5Password.equals(result2.getData().getPassword())) {
                    try {
                        String s = JWTUtils.creaToken(result2.getData().getAccount(), result2.getData().getUuid(), "admin");
                        return new ResponseResult<>(true, "成功", s);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new ResponseResult<>(false, "令牌生成错误");
                    }
                }
            }
        }
        return new ResponseResult<>(false, "账号密码错误");
    }

}
