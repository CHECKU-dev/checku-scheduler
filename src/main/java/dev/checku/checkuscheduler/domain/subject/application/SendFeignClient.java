package dev.checku.checkuscheduler.domain.subject.application;

import dev.checku.checkuscheduler.domain.topic.dto.TopicDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8000", name = "SendFeignClient")

public interface SendFeignClient {

    @PostMapping(value = "/api/notification/topic", consumes = "application/json")
    void sendTopic(
            @RequestBody TopicDto topic
    );
}