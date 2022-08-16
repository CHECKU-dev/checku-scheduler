package dev.checku.checkuscheduler.domain.topic.repository;

import dev.checku.checkuscheduler.domain.topic.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
