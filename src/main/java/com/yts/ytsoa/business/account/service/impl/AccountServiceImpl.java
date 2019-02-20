package com.yts.ytsoa.business.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.account.mapper.AccountMapper;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.model.AdminModel;
import com.yts.ytsoa.business.account.service.AccountService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.poi.ExcelModelExportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局统一返回
 * 所有查询相关的方法判断有无值返回true or false 方便前台做出判断
 *
 * @author ld
 * @name
 * @table
 * @remarks
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<AccountModel> add(AccountModel model) throws Exception {
        List<AccountModel> list = mapper.findByAccount(model.getAccount());
        if (list.size() > 0) {
            return new ResponseResult<>(false, "账户重复", null);
        } else {
            mapper.add(model);
            return new ResponseResult<>(true, "成功", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<AccountModel> deleteById(String uuid) throws Exception {
        mapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<AccountModel> updateById(AccountModel model, int i) throws Exception {
        switch (i) {
//            修改密码
            case 1:
                model.setScdl(1);
                break;
//                完善资料
            case 2:
                model.setWsxx(1);
                model.setPassword(null);
                break;
            default:
                break;
        }
        mapper.updateById(model);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<PageInfo<AccountModel>> findAll(int pageNow, int pageSize, AccountModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<AccountModel> list = mapper.findAll(model);
        PageInfo<AccountModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "成功", page);
        } else {
            return new ResponseResult<>(false, "未查询到记录", null);
        }
    }

    @Override
    public ResponseResult<List<AccountModel>> findAll(AccountModel model) throws Exception {
        List<AccountModel> list = mapper.findAll(model);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "成功", list);
        } else {
            return new ResponseResult<>(false, "未查询到记录", null);
        }
    }

    @Override
    public ResponseResult<String> excel(String basePath, String outPath, AccountModel model) throws Exception {
        List<AccountModel> list = mapper.findAll(model);
        List<String[]> datas = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String strs[] = new String[5];
                strs[0] = String.valueOf(i + 1);
                strs[1] = list.get(i).getName();
                strs[2] = String.valueOf(list.get(i).getXb());
                strs[3] = list.get(i).getSzm();
                strs[4] = list.get(i).getSfzh();
                datas.add(strs);
            }
            //        生成excel
            return new ExcelModelExportUtils().exportCurrency(basePath, outPath, datas);
        } else {
            return new ResponseResult<>(false, "未查询到记录");
        }
    }

    @Override
    public ResponseResult<List<AccountModel>> findByAccount(AccountModel model) throws Exception {
        List<AccountModel> list = mapper.findByAccountAndPassword(model);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "成功", list);
        } else {
            return new ResponseResult<>(false, "未查询到记录", null);
        }
    }

    @Override
    public ResponseResult<AccountModel> getAdminByAccount(AdminModel model) throws Exception {
        AccountModel model1 = mapper.getAdminByAccount(model);
        if (model1 != null) {
            return new ResponseResult<>(true, "成功", model1);
        } else {
            return new ResponseResult<>(false, "未查询到记录", null);
        }
    }

    @Override
    public ResponseResult<AccountModel> updById(AccountModel model) throws Exception {
        int result = mapper.updById(model);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        } else return new ResponseResult<>(false, "修改失败");
    }
}
