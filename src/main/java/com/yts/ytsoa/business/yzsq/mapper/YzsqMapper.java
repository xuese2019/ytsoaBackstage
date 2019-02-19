package com.yts.ytsoa.business.yzsq.mapper;

import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.business.yzsq.mapper.YzsqSql.YzsqSql;
import com.yts.ytsoa.business.yzsq.model.ResultsModel;
import com.yts.ytsoa.business.yzsq.model.YzsqModel;
import com.yts.ytsoa.business.yzsq.result.result;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface YzsqMapper {
    @InsertProvider(type = YzsqSql.class, method = "addYzsq")
    int addYzsq(@Param("model") YzsqModel model) throws SQLException;

    /**
     * 查询所有
     *
     * @param model
     * @return
     * @throws SQLException
     */
    @SelectProvider(type = YzsqSql.class, method = "find")
    List<YzsqModel> find(@Param("model") YzsqModel model) throws SQLException;

    /**
     * 修改
     *
     * @param model
     * @return
     * @throws SQLException
     */
    @UpdateProvider(type = YzsqSql.class, method = "update")
    int update(@Param("model") YzsqModel model) throws SQLException;

    @UpdateProvider(type = YzsqSql.class, method = "updateByShjg")
    int updateByShjg(@Param("model") YzsqModel model) throws SQLException;

    @Select({
            "select * from " + Tables.YZSQ_TABLE + " where uuid=#{uuid}"
    })
    /**
     * 根据id查询
     */
    YzsqModel findById(@Param("uuid") String uuid) throws SQLException;

    /**
     * 用章管理
     */
    @SelectProvider(type = YzsqSql.class, method = "yzgl")
    List<result> yzgl(@Param("model") YzsqModel model) throws SQLException;


    /**
     * 判断登陆人是否在项目成员中
     * 如果没有则不允许申请用章
     *
     * @param xmid
     * @return
     */
    @Select({
            "SELECT x.uuid\n" +
                    "FROM xmcy_table x join xmwp_table x1 on x1.uuid=x.xmid\n" +
                    "where x.xmid=#{xmid}"
    })
    List<XmcyModel> findXmcyByXmid(@Param("xmid") String xmid);

    @UpdateProvider(type = YzsqSql.class, method = "yzsh")
    int yzsh(@Param("model") YzsqModel model);

    @SelectProvider(type = YzsqSql.class, method = "findByShjl")
    List<ResultsModel> findByShjl(@Param("prentid") String prentid) throws SQLException;
}
