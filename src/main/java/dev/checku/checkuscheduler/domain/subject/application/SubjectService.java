package dev.checku.checkuscheduler.domain.subject.application;

import dev.checku.checkuscheduler.domain.subject.entity.Subject;
import dev.checku.checkuscheduler.domain.topic.entity.Topic;
import dev.checku.checkuscheduler.domain.topic.dto.TopicDto;
import dev.checku.checkuscheduler.infra.portal.LoginService;
import dev.checku.checkuscheduler.infra.portal.PortalFeignClient;
import dev.checku.checkuscheduler.infra.portal.PortalRes;
import dev.checku.checkuscheduler.global.util.Values;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SubjectService {

    private final PortalFeignClient portalFeignClient;
    private final SendFeignClient sendFeignClient;
    private final LoginService loginService;


    private PortalRes.SubjectDto getSubjectFromPortal(String subjectNumber) {
        String session = loginService.login();

        ResponseEntity<PortalRes> response = portalFeignClient.getSubject(
                session,
                Values.headers,
                Values.getSubjectBody("2022", "B01012", "", "", subjectNumber));


        return Objects.requireNonNull(response.getBody()).getSubjects().get(0);

    }



    public void findVacancy(List<Topic> topicList) {

        List<Topic> vacantSubjects = topicList.stream()
                .filter(topic -> {
                    PortalRes.SubjectDto subjectDto = getSubjectFromPortal(topic.getSubjectNumber());
                    Subject subjectFromPortal = subjectDto.toSubject();
                    return subjectFromPortal.getCurrentNumber() < subjectFromPortal.getLimitNumber();
                }).collect(Collectors.toList());

        if (vacantSubjects.size() != 0) {
            for (Topic topic : topicList) {
                log.info("빈 자리 찾음({})", topic.getSubjectNumber());
                sendFeignClient.sendTopic(TopicDto.of(topic));
            }
        }
    }

}