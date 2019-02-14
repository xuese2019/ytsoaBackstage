package com.yts.ytsoa.business.xxgl.mapper;

import com.yts.ytsoa.business.xxgl.mapper.sql.XxglSql;
import com.yts.ytsoa.business.xxgl.model.XxglModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Mapper
public interface XxglMapper {

    /**
     * 新增消息记录
     *
     * @param model 消息实体
     * @throws Exception
     */
    @Insert({
            "insert into " + Tables.XXGL_TABLE + " (uuid,xxlx,xxbt,xxnr,tzlj,xxfssj,xxcksj,zt,fsr,jsr,tyt_flag) " +
                    "values (replace(uuid(), '-', ''),#{model.xxlx},#{model.xxbt},#{model.xxnr},#{model.tzlj},#{model.xxfssj}," +
                    "#{model.xxcksj},#{model.zt},#{model.fsr},#{model.jsr},#{model.tytFlag})"
    })
    void save(@Param("model") XxglModel model) throws Exception;

    @InsertProvider(type = XxglSql.class, method = "savesSql")
    void saves(@Param("models") List<XxglModel> models) throws Exception;

    /**
     * 根据id修改消息状态
     *
     * @param uuid 消息主键
     * @param zt   消息状态
     * @throws Exception
     */
    @Update({
            "update " + Tables.XXGL_TABLE + " set zt = #{zt} where uuid = #{uuid}"
    })
    void updateZtByUuid(@Param("uuid") String uuid, @Param("zt") int zt) throws Exception;

    /**
     * 根据查询条件返回结果集
     *
     * @param model 消息管理实体
     * @return List<XxglModel>
     * @throws Exception
     */
    @SelectProvider(type = XxglSql.class, method = "findAllSql")
    @Results(id = "xxglMap", value = {
            @Result(property = "fsr", column = "name")
    })
    List<XxglModel> findAll(@Param("model") XxglModel model) throws Exception;
}
