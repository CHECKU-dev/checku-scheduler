package dev.checku.checkuscheduler.global.config;

import dev.checku.checkuscheduler.domain.subject.application.SubjectService;
import dev.checku.checkuscheduler.domain.topic.application.TopicService;
import dev.checku.checkuscheduler.domain.topic.entity.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfig {

    private final SubjectService subjectService;

    private final TopicService topicService;

    @Scheduled(cron = "0/59 * * * * *")
    public void findVacancy() {
        List<Topic> topicList = topicService.getTopicList();
        subjectService.findVacancy(topicList);
    }

}
