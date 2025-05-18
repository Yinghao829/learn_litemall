package org.hao.litemall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"org.hao.litemall"})
@MapperScan("org.hao.litemall.db.dao")
@EnableTransactionManagement
@EnableScheduling
public class LitemallAllWarApplication {

    public static void main(String[] args) {
        SpringApplication.run(LitemallAllWarApplication.class, args);
    }

}
