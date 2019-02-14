package com.yts.ytsoa;

import com.yts.ytsoa.utils.FileUtils;
import com.yts.ytsoa.utils.yamlutils.YamlFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 系统启动完成后
 *
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private YamlFileUtils yamlFileUtils;

    @Override
    public void run(ApplicationArguments var1) {
        FileUtils.judeDirExists(new File(yamlFileUtils.getFileModel()));
        FileUtils.judeDirExists(new File(yamlFileUtils.getDowPath()));
        FileUtils.judeDirExists(new File(yamlFileUtils.getUpPath()));
    }

    /**
     * 删除重复语句
     * delete from qxgl_table where uuid in (select uuid from (select uuid from qxgl_table where qxbs in
     * (select qxbs from qxgl_table group by qxbs having count(qxbs)>1) and uuid not in
     * (select min(uuid) from qxgl_table group by qxbs having count(qxbs)>1)) as tmpresult)
     *
     * @return
     */
}
