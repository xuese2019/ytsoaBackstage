package com.yts.ytsoa.business.qxgl.utils;

import com.yts.ytsoa.business.qxgl.model.QxglModel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LD
 * @date:
 * @description: 权限的所有信息
 */
@Slf4j
public class QxUtils {

    public List<QxglModel> getQxData() {
        List<QxglModel> list = new ArrayList<>();

        list.add(new QxglModel("a", "业务中心", "ywzx", "0", "1", "treeview", "fa fa-sticky-note-o", null));

        list.add(new QxglModel("aa", "项目委派管理", "xmwpgl", "a", "1", "", "", null));
        list.add(new QxglModel("aaa", "项目委派管理删除", "xmwpglDelete", "aa", "2", "", "", null));
        list.add(new QxglModel("aab", "项目委派管理导出", "xmwpglExcept", "aa", "2", "", "", null));

        list.add(new QxglModel("ab", "项目委派", "xmwp", "a", "1", "", "", null));

        list.add(new QxglModel("ac", "项目管理", "xmgl", "a", "1", "", "", null));
        list.add(new QxglModel("aca", "项目管理详情", "xmglDetails", "ac", "2", "", "", null));
        list.add(new QxglModel("acb", "项目管理审核记录", "xmglAuditingRecord", "ac", "2", "", "", null));

        list.add(new QxglModel("ae", "项目承接", "xmcj", "a", "1", "", "", null));

        list.add(new QxglModel("af", "项目审核", "shjl", "a", "1", "", "", null));

        list.add(new QxglModel("ag", "项目延期", "xmyq", "a", "1", "", "", null));

        list.add(new QxglModel("ah", "工作日志管理", "gzrzgl", "a", "1", "", "", null));

        list.add(new QxglModel("ai", "工作日志", "gzrz", "a", "1", "", "", null));

        list.add(new QxglModel("aj", "报告管理", "bggl", "a", "1", "", "", null));
        list.add(new QxglModel("aja", "报告管理审核记录", "bgglAuditingRecord", "aj", "2", "", "", null));

        list.add(new QxglModel("ak", "报告申请", "bgsq", "a", "1", "", "", null));

        list.add(new QxglModel("al", "报告审核", "bgsh", "a", "1", "", "", null));

        list.add(new QxglModel("am", "归档管理", "gdgl", "a", "1", "", "", null));
        list.add(new QxglModel("ama", "归档管理借阅记录", "gdglBorrowingRecord", "am", "2", "", "", null));
        list.add(new QxglModel("amb", "归档管理审核记录", "gdglAuditingRecord", "am", "2", "", "", null));

        list.add(new QxglModel("an", "归档申请", "gdsq", "a", "1", "", "", null));

        list.add(new QxglModel("ao", "归档审核", "gdsh", "a", "1", "", "", null));

        list.add(new QxglModel("ap", "借阅管理", "jygl", "a", "1", "", "", null));
        list.add(new QxglModel("apa", "借阅管理审核记录", "jyglAuditingRecord", "ap", "2", "", "", null));

        list.add(new QxglModel("aq", "借阅申请", "jysq", "a", "1", "", "", null));

        list.add(new QxglModel("ar", "借阅申请审核", "jysqsh", "a", "1", "", "", null));

        list.add(new QxglModel("as", "借阅归还", "jygh", "a", "1", "", "", null));

        list.add(new QxglModel("at", "客户管理", "khgl", "a", "1", "", "", null));


        list.add(new QxglModel("b", "行政管理", "xzgl", "0", "1", "treeview", "fa fa-sticky-note-o", null));

        list.add(new QxglModel("ba", "票据管理", "pjgl", "b", "1", "", "", null));
        list.add(new QxglModel("baa", "票据管理审核记录", "pjglAuditingRecord", "ba", "2", "", "", null));
        list.add(new QxglModel("bab", "票据管理导出", "pjglExcept", "ba", "2", "", "", null));

        list.add(new QxglModel("bb", "票据申请", "pjsq", "b", "1", "", "", null));

        list.add(new QxglModel("bc", "票据审核", "pjsh", "b", "1", "", "", null));

        list.add(new QxglModel("bd", "退票管理", "tpgl", "b", "1", "", "", null));
        list.add(new QxglModel("bda", "退票管理审核记录", "tpglAuditingRecord", "bd", "2", "", "", null));
        list.add(new QxglModel("bdb", "退票管理导出", "tpglExcept", "bd", "2", "", "", null));

        list.add(new QxglModel("be", "退票申请", "tpsq", "b", "1", "", "", null));

        list.add(new QxglModel("bf", "退票审核", "tpsh", "b", "1", "", "", null));

        list.add(new QxglModel("bg", "员工管理", "yggl", "b", "1", "", "", null));
        list.add(new QxglModel("bga", "员工管理添加员工", "ygglAdd", "bg", "2", "", "", null));
        list.add(new QxglModel("bgb", "员工管理修改", "ygglUpdate", "bg", "2", "", "", null));
        list.add(new QxglModel("bgc", "员工管理重置密码", "ygglResetPassword", "bg", "2", "", "", null));

        list.add(new QxglModel("bh", "考勤管理", "kqgl", "b", "1", "", "", null));

        list.add(new QxglModel("bi", "我的考勤", "wdkq", "b", "1", "", "", null));

        list.add(new QxglModel("bj", "请假申请", "qjsq", "b", "1", "", "", null));

        list.add(new QxglModel("bk", "请假审核", "qjsh", "b", "1", "", "", null));

        list.add(new QxglModel("bl", "事务所动态", "swsdt", "b", "1", "", "", null));
        list.add(new QxglModel("bla", "事务所动态发布", "swsdtAdd", "bl", "2", "", "", null));
        list.add(new QxglModel("blb", "事务所动态删除", "swsdtDelete", "bl", "2", "", "", null));

        list.add(new QxglModel("bn", "用章管理", "yzgl", "b", "1", "", "", null));
        list.add(new QxglModel("bna", "用章管理审核记录", "yzglAuditingRecord", "bn", "2", "", "", null));

        list.add(new QxglModel("bo", "用章申请", "yzsq", "b", "1", "", "", null));

        list.add(new QxglModel("bp", "用章审核", "yzsh", "b", "1", "", "", null));

        list.add(new QxglModel("bq", "用车管理", "ycgl", "b", "1", "", "", null));
        list.add(new QxglModel("bqa", "用车管理审核记录", "ycglAuditingRecord", "bq", "2", "", "", null));

        list.add(new QxglModel("br", "用车申请", "ycsq", "b", "1", "", "", null));

        list.add(new QxglModel("bs", "用车审核", "ycsh", "b", "1", "", "", null));

        list.add(new QxglModel("bt", "用车归还", "ycgh", "b", "1", "", "", null));

        list.add(new QxglModel("bu", "通知管理", "tzgl", "b", "1", "", "", null));

        list.add(new QxglModel("bv", "发送通知", "fstz", "b", "1", "", "", null));


        list.add(new QxglModel("c", "专业天地", "zytd", "0", "1", "treeview", "fa fa-sticky-note-o", null));

        list.add(new QxglModel("ca", "项目内部讨论组", "xmnbtlz", "c", "1", "", "", null));
        list.add(new QxglModel("caa", "项目内部讨论组增加人员", "xmnbtlzAddPeople", "ca", "2", "", "", null));
        list.add(new QxglModel("cab", "项目内部讨论组删除人员", "xmnbtlzDeletePeople", "ca", "2", "", "", null));

        list.add(new QxglModel("cb", "业务交流", "ywjl", "c", "1", "", "", null));
        list.add(new QxglModel("cba", "业务交流新增主题", "ywjlAdd", "cb", "2", "", "", null));
        list.add(new QxglModel("cbb", "业务交流删除主题", "ywjlDelete", "cb", "2", "", "", null));

        list.add(new QxglModel("cc", "知识库", "zsk", "c", "1", "", "", null));
        list.add(new QxglModel("cca", "知识库增加", "zskAdd", "cc", "2", "", "", null));
        list.add(new QxglModel("ccb", "知识库删除", "zskDelete", "cc", "2", "", "", null));
        list.add(new QxglModel("ccc", "知识库下载", "zskDownload", "cc", "2", "", "", null));

        list.add(new QxglModel("d", "系统管理", "xtgl", "0", "1", "treeview", "fa fa-sticky-note-o", null));
        list.add(new QxglModel("da", "项目审核相关设置", "xmshxgsz", "d", "1", "treeview", "fa fa-sticky-note-o", null));
        list.add(new QxglModel("db", "报告审核相关设置", "bgshxgsz", "d", "1", "treeview", "fa fa-sticky-note-o", null));
        list.add(new QxglModel("dc", "审核相关设置", "shxgsz", "d", "1", "treeview", "fa fa-sticky-note-o", null));

        return list;
    }
}
