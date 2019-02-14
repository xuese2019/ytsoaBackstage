package com.yts.ytsoa.sys;

import com.yts.ytsoa.utils.yamlutils.YamlFileUtils;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * applicationContext创建后，所有的bean注入后
 *
 * @author LD
 */
@Slf4j
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        jcDataSouces();
        jcYaml();
    }

    /**
     * 数据库连接验证
     */
    private void jcDataSouces() {
        try {
            // ===== 在项目初始化bean后检验数据库连接是否
            DataSource dataSource = (DataSource) getApplicationContext().getBean("dataSource");
            dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            // ===== 当检测数据库连接失败时, 停止项目启动
            log.error("数据库连接检查失败，请检查数据库连接地址、用户名、密码、驱动是否正确");
            System.exit(-1);
        }
    }

    /**
     * yaml配置文件自定义值验证
     */
    private void jcYaml() {
        try {
            YamlFileUtils yamlFileUtils = (YamlFileUtils) getApplicationContext().getBean("yamlFileUtils");
            if (yamlFileUtils.getFileModel() == null || yamlFileUtils.getFileModel().isEmpty()) {
                log.error("请在yml配置文件中完善导出excel模板路径 file:file-model:x://x/");
            }
            if (yamlFileUtils.getUpPath() == null || yamlFileUtils.getUpPath().isEmpty()) {
                log.error("请在yml配置文件中完善文件上传路径 file:up-path:x://x/");
            }
            if (yamlFileUtils.getDowPath() == null || yamlFileUtils.getDowPath().isEmpty()) {
                log.error("请在yml配置文件中完善文件导出excel暂存的路径 file:dow-path:x://x/");
            }
            if (yamlFileUtils.getUpPrefix() == null || yamlFileUtils.getUpPrefix().isEmpty()) {
                log.error("请在yml配置文件中完善允许上传文件的后缀名 file:up-prefix:jpg,png,doc,docx,xls,xlsx,zip,7z,rar");
            }
            YamlPageUtils yamlPageUtils = (YamlPageUtils) getApplicationContext().getBean("yamlPageUtils");
            if (yamlPageUtils.getPageSize() <= 0) {
                log.error("请在yml配置文件中完善page:page-size:的值为大于0的正整数");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("yml自定义属性验证未通过，请检查自定义属性的完整性");
            System.exit(-1);
        }
    }

    public ApplicationContext getApplicationContext() {
        return context;
    }
}
