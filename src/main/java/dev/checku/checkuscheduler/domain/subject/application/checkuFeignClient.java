package dev.checku.checkuscheduler.domain.subject.application;

import dev.checku.checkuscheduler.domain.topic.dto.TopicDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://13.209.191.134:8000", name = "${checku.url}")
public interface checkuFeignClient {

    @PostMapping(value = "/api/notification/topic", consumes = "application/json")
    void sendTopic(
            @RequestBody TopicDto topic
    );

    @GetMapping(value = "/api/portal-session/", consumes = "application/json")
    void updateSession();
}
