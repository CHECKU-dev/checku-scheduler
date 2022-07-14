package dev.checku.checkuscheduler.application;

import dev.checku.checkuscheduler.domain.topic.Topic;
import feign.Response;
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
