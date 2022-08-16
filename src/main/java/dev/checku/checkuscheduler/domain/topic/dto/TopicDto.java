package dev.checku.checkuscheduler.domain.topic.dto;

import dev.checku.checkuscheduler.domain.topic.entity.Topic;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TopicDto {

    private String topic;

    @Builder
    public TopicDto(String topic) {
        this.topic = topic;
    }

    public static TopicDto of(Topic topic) {

        return TopicDto.builder()
                .topic(topic.getSubjectNumber())
                .build();
    }


}
