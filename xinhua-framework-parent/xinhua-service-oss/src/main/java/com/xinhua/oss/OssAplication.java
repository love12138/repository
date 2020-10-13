package com.xinhua.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: xinhua-framework-parent
 * @description:
 * @author: wys
 * @create: 2020-10-12 12:29
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages={"com.xinhua.oss","com.xinhua.onlineedu.common"})
public class OssAplication {
    public static void main(String[] args) {
        SpringApplication.run(OssAplication.class, args);
    }
}