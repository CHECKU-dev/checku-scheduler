package dev.checku.checkuscheduler.domain.topic.application;

import dev.checku.checkuscheduler.domain.topic.entity.Topic;
import dev.checku.checkuscheduler.domain.topic.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopicService {

    private final TopicRepository topicRepository;

    public List<Topic> getTopicList() {
        return topicRepository.findAll();
    }

}
