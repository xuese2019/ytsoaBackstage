package com.yts.ytsoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author LD
 */
@SpringBootApplication
//开启定时功能注解 定时类路径com.yts.ytsoa.Scheduleds
@EnableScheduling
public class YtsoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YtsoaApplication.class, args);
    }

}

