package com.xinhua.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户中心启动类〉
 *
 * @author Administrator
 * @create 2020/10/12
 * @since 1.0.0
 */
@SpringBootApplication
//@ComponentScan(basePackages={"com.xinhua.ucenter","com.guli.common"})
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
