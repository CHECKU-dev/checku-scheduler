package dev.checku.checkuscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class CheckuSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckuSchedulerApplication.class, args);
    }

}
