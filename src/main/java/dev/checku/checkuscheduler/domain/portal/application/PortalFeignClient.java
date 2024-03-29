package dev.checku.checkuscheduler.domain.portal.application;

import dev.checku.checkuscheduler.domain.portal.dto.PortalRes;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;


@FeignClient(url = "https://kuis.konkuk.ac.kr", name = "kuis.konkuk.ac.kr")
public interface PortalFeignClient {

    @PostMapping(value = "/CourTotalTimetableInq/find.do", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<PortalRes> getSubject(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader Map<String, String> headers,
            MultiValueMap<String, String> subjectBody);

}


