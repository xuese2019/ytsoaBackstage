package com.yts.ytsoa.business.account.mapper;

import com.yts.ytsoa.business.account.mapper.sql.AccountSql;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.account.model.AdminModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface AccountMapper {

    /**
     * @param model
     * @throws SQLException
     */
    @Insert({
            "insert into " + Tables.ACCOUNT_TABLE + " (uuid,zj,gzdw,account,password,bm,zwlx,ygzt,rzrq,zzrq,zjxs,bz,scdl,lx,wsxx) " +
                    " values (replace(uuid(), '-', ''),#{model.zj},#{model.gzdw},#{model.account},#{model.password},#{model.bm},#{model.zwlx}," +
                    "#{model.ygzt},#{model.rzrq},#{model.zzrq},#{model.zjxs},#{model.bz},0,0,0)"
    })
    void add(@Param("model") AccountModel model) throws SQLException;

    /**
     * @param uuid
     * @throws SQLException
     */
    @Delete({
            "delete from " + Tables.ACCOUNT_TABLE + " where uuid = #{uuid}"
    })
    void deleteById(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = AccountSql.class, method = "updateByIdSql")
    void updateById(@Param("model") AccountModel model) throws SQLException;

    @SelectProvider(type = AccountSql.class, method = "findAllSql")
    @Results(id = "accountMap", value = {
            @Result(property = "bm", column = "zzjgmc")
    })
    List<AccountModel> findAll(@Param("model") AccountModel model) throws SQLException;

    @Select({
            "select * from " + Tables.ACCOUNT_TABLE + " where account = #{account}"
    })
    List<AccountModel> findByAccount(@Param("account") String account) throws SQLException;

    @Select({
            "select * from account_table a where a.account = #{model.account}"
    })
    @ResultMap(value = "accountMap")
    List<AccountModel> findByAccountAndPassword(@Param("model") AccountModel model) throws SQLException;

    @Select({
            "select * from admin_table a where a.account = #{model.account}"
    })
    AccountModel getAdminByAccount(@Param("model") AdminModel model) throws SQLException;

    @Select({
            "select a.name from " + Tables.ACCOUNT_TABLE + " a where a.uuid = #{uuid}"
    })
    String getByUuid(@Param("uuid") String uuid) throws SQLException;

    @UpdateProvider(type = AccountSql.class, method = "updById")
    int updById(@Param("model") AccountModel model) throws SQLException;
}
