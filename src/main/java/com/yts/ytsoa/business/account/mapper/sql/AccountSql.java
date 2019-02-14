package com.yts.ytsoa.business.account.mapper.sql;

import com.yts.ytsoa.business.account.model.AccountModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * 为了避免sql注入，请采用 new SQL的方式（内部采用set拼接）
 *
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class AccountSql {
    public String updateByIdSql(@Param("model") AccountModel model) {
        return new SQL() {
            {
                UPDATE("account_table");
                if (model.getName() != null && !model.getName().isEmpty()) {
                    SET("name=#{model.name}");
                }
                if (model.getXb() != null) {
                    SET("xb=#{model.xb}");
                }
                if (model.getSzm() != null && !model.getSzm().isEmpty()) {
                    SET("szm=#{model.szm}");
                }
                if (model.getSfzh() != null && !model.getSfzh().isEmpty()) {
                    SET("sfzh=#{model.sfzh}");
                }
                if (model.getGzdw() != null && !model.getGzdw().isEmpty()) {
                    SET("gzdw=#{model.gzdw}");
                }
                if (model.getPassword() != null && !model.getPassword().isEmpty()) {
                    SET("password=#{model.password}");
                }
                if (model.getYgzt() > 0) {
                    SET("ygzt=#{model.ygzt}");
                }
                if (model.getRzrq() != null) {
                    SET("rzrq=#{model.rzrq}");
                }
                if (model.getZzrq() != null) {
                    SET("zzrq=#{model.zzrq}");
                }
                if (model.getBysj() != null) {
                    SET("bysj=#{model.bysj}");
                }
                if (model.getByyx() != null && !model.getByyx().isEmpty()) {
                    SET("byyx=#{model.byyx}");
                }
                if (model.getZcdj() != null && !model.getZcdj().isEmpty()) {
                    SET("zcdj=#{model.zcdj}");
                }
                if (model.getHkszd() != null && !model.getHkszd().isEmpty()) {
                    SET("hkszd=#{model.hkszd}");
                }
                if (model.getXzz() != null && !model.getXzz().isEmpty()) {
                    SET("xzz=#{model.xzz}");
                }
                if (model.getJjlxr() != null && !model.getJjlxr().isEmpty()) {
                    SET("jjlxr=#{model.jjlxr}");
                }
                if (model.getJjlxdh() != null && !model.getJjlxdh().isEmpty()) {
                    SET("jjlxdh=#{model.jjlxdh}");
                }
                if (model.getDh() != null && !model.getDh().isEmpty()) {
                    SET("dh=#{model.dh}");
                }
                if (model.getZzmm() > 0) {
                    SET("zzmm=#{model.zzmm}");
                }
                if (model.getZjxs() != null) {
                    SET("zjxs=#{model.zjxs}");
                }
                if (model.getBz() != null && !model.getBz().isEmpty()) {
                    SET("bz=#{model.bz}");
                }
                if (model.getScdl() > 0) {
                    SET("scdl=#{model.scdl}");
                }
                if (model.getWsxx() > 0) {
                    SET("wsxx=#{model.wsxx}");
                }
                WHERE("uuid = #{model.uuid}");
            }
        }.toString();
    }

    public String findAllSql(@Param("model") AccountModel model) {
        return new SQL() {
            {
                SELECT("a.*,z.zzjgmc");
                FROM("account_table a");
                LEFT_OUTER_JOIN("zzjg_table z on z.uuid = a.bm");
                if (model.getAccount() != null && !model.getAccount().isEmpty()) {
                    model.setAccount("%" + model.getAccount() + "%");
                    WHERE("a.account like #{model.account}");
                }
                if (model.getName() != null && !model.getName().isEmpty()) {
                    model.setName("%" + model.getName() + "%");
                    WHERE("a.name like #{model.name}");
                }
                if (model.getUuid() != null && !model.getUuid().isEmpty()) {
                    WHERE("a.uuid=#{model.uuid}");
                }
                if (model.getBm() != null && !model.getBm().isEmpty()) {
                    WHERE("a.bm=#{model.bm}");
                }
                if (model.getYgzt() > 0) {
                    WHERE("a.ygzt >= #{model.ygzt}");
                }
                if (model.getName() != null && !model.getName().isEmpty()) {
                    WHERE("a.name!=#{model.name}");
                }
                if (model.getName() != null && !model.getName().isEmpty()) {
                    WHERE("a.name!=#{model.name}");
                }
            }
        }.toString();
    }
}
