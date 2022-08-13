package dev.checku.checkuscheduler.application;

import dev.checku.checkuscheduler.domain.topic.Topic;
import dev.checku.checkuscheduler.domain.topic.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchedulerService {

    private final SubjectService subjectService;
    private final TopicService topicService;

    //TODO 주기 수정
    @Scheduled(cron = "0/20 * * * * *")
    public void findVacancy() {
        List<Topic> topicList = topicService.getTopicList();
        subjectService.findVacancy(topicList);
    }

}
