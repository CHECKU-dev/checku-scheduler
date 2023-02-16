package dev.checku.checkuscheduler.global.config;

import dev.checku.checkuscheduler.domain.subject.application.SubjectService;
import dev.checku.checkuscheduler.domain.topic.application.TopicService;
import dev.checku.checkuscheduler.domain.topic.entity.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfig implements SchedulingConfigurer {
    private final int POOL_SIZE = 3;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // Thread Pool 설정
        ThreadPoolTaskScheduler threadPool = new ThreadPoolTaskScheduler();

        // Thread 개수 설정
        threadPool.setPoolSize(POOL_SIZE);
        threadPool.initialize();

        taskRegistrar.setTaskScheduler(threadPool);
    }

}
