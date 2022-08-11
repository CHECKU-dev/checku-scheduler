package dev.checku.checkuscheduler.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@EnableFeignClients(basePackages = "dev.checku.checkuscheduler")
@Import(FeignClientsConfiguration.class)
public class FeignConfig {

//    @Bean
//    public Retryer retryer() {
//        System.out.println("retryer()");
//        // 재시도는 1초를 시작으로 최대 2초로 재시도 하고, 최대 3번으로 재시도 하도록 설정
//        // 최초 1초이고, 그 이후는 1.5를 곱하면서 재시도
//        return new Retryer.Default(1000, 2000, 3);
//    }



}
