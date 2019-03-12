package com.yts.ytsoa.business.sys.mapper.sql;

import com.yts.ytsoa.utils.Tables;
import com.yts.ytsoa.utils.annot.AnnotC;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: LD
 * @date:
 * @description:
 */
@AnnotC
@Slf4j
public class SysSql {

    public String deleteAll() {
        return
                "delete from " + Tables.XMWP_TABLE + ";" +
                        "delete from " + Tables.XXGL_TABLE + ";" +
                        "delete from " + Tables.ZSK_TABLE + ";" +
                        "delete from " + Tables.XJSQ_TABLE + ";" +
                        "delete from " + Tables.XJSP_TABLE + ";" +
                        "delete from " + Tables.GZRZ_TABLE + ";" +
                        "delete from " + Tables.SHJL_TABLE + ";" +
                        "delete from " + Tables.XMYQ_TABLE + ";" +
                        "delete from " + Tables.BGGL_TABLE + ";" +
                        "delete from " + Tables.DGGD_TABLE + ";" +
                        "delete from " + Tables.PJSQ_TABLE + ";" +
                        "delete from " + Tables.YZSQ_TABLE + ";" +
                        "delete from " + Tables.YCSQ_TABLE + ";" +
                        "delete from " + Tables.TPSQ_TABLE + ";" +
                        "delete from " + Tables.XMCY_TABLE + ";" +
                        "delete from " + Tables.JYGL_TABLE + ";" +
                        "delete from " + Tables.XMJZ_TABLE + ";" +
                        "delete from " + Tables.YWJL_TABLE + ";" +
                        "delete from " + Tables.YWZT_TABLE + ";" +
                        "delete from " + Tables.CWGL_TABLE + ";" +
                        "delete from " + Tables.XMJLCY_TABLE + ";" +
                        "delete from " + Tables.YWJLHF_TABLE + ";" +
                        "delete from " + Tables.SHJD_TABLE + ";" +
                        "delete from " + Tables.JYJL_TABLE + ";" +
                        "delete from " + Tables.BGSHJL_TABLE + ";" +
                        "delete from " + Tables.XMZMC_TABLE;
    }
}
