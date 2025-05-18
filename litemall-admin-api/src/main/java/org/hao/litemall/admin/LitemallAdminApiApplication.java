package org.hao.litemall.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"org.hao.litemall.db", "org.hao.litemall.core", "org.hao.litemall.admin"})
@MapperScan("org.hao.litemall.db.dao")
@EnableTransactionManagement
@EnableScheduling
public class LitemallAdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LitemallAdminApiApplication.class, args);
    }

}
