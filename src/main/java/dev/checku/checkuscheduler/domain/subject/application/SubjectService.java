package dev.checku.checkuscheduler.domain.subject.application;

import dev.checku.checkuscheduler.domain.portal.application.PortalFeignClient;
import dev.checku.checkuscheduler.domain.portal.application.PortalSessionService;
import dev.checku.checkuscheduler.domain.subject.entity.Subject;
import dev.checku.checkuscheduler.domain.topic.entity.Topic;
import dev.checku.checkuscheduler.domain.topic.dto.TopicDto;
import dev.checku.checkuscheduler.domain.portal.dto.PortalRes;
import dev.checku.checkuscheduler.global.util.Values;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SubjectService {

    private final PortalFeignClient portalFeignClient;
    private final checkuFeignClient checkuFeignClient;
    private final PortalSessionService portalSessionService;

    private PortalRes.SubjectDto getSubjectFromPortal(String subjectNumber) {
        ResponseEntity<PortalRes> response = portalFeignClient.getSubject(
                portalSessionService.getPortalSession().getSession(),
                Values.headers,
                Values.getSubjectBody("", "", subjectNumber));

        if (response.getBody().getSubjects() == null) { // 세션 만료
            checkuFeignClient.updateSession();
            return null;
        }

        return response.getBody().getSubjects().get(0);
    }


    public void findVacancy(List<Topic> topicList) {

        List<Topic> vacantSubjects = new ArrayList<>();
        for (Topic topic : topicList) {
            PortalRes.SubjectDto subject = getSubjectFromPortal(topic.getSubjectNumber());
            if (subject == null) {
                break;
            }
            Subject subjectFromPortal = subject.toSubject();
            if (subjectFromPortal.getCurrentNumber() < subjectFromPortal.getLimitNumber()) {
                vacantSubjects.add(topic);
            }
        }

        if (vacantSubjects.size() != 0) {
            for (Topic topic : vacantSubjects) {
                log.info("빈 자리 찾음({})", topic.getSubjectNumber());
                checkuFeignClient.sendTopic(TopicDto.of(topic));
            }
        }
    }

}