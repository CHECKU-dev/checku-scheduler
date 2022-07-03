package dev.checku.checkuscheduler.application;

import dev.checku.checkuscheduler.domain.subject.dao.SubjectRepository;
import dev.checku.checkuscheduler.domain.subject.dto.SubjectListDto;
import dev.checku.checkuscheduler.domain.subject.entity.Subject;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SubjectService {

    private final PortalFeignClient portalFeignClient;
    private final SubjectRepository subjectRepository;

    @Value("${portal.id}")
    private String ID;

    @Value("${portal.password}")
    private String PASSWORD;

    private String getSession() {
        Response response = portalFeignClient.getSession();
        String cookie = response.headers().get("set-cookie").toString();
        String jSessionId = cookie.substring(cookie.indexOf('=') + 1, cookie.indexOf(';'));
        return jSessionId;
    }

    public void login(String jSessionId) {
        portalFeignClient.login(
                "JSESSIONID=" + jSessionId,
                "https://kuis.konkuk.ac.kr/index.do",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36",
                "#9e4ki",
                "e&*\biu",
                "W^_zie",
                "_qw3e4",
                "Ajd%md",
                "ekmf3",
                "JDow871",
                "NuMoe6",
                "ne+3|q",
                "Qnd@%1",
                "@d1#",
                "dsParam",
                "dm",
                ID,
                PASSWORD,
                "ko"
        );
    }

    private SubjectListDto getAllSubjectsFromPortal() {
        String jSessionId = getSession();
        login(jSessionId);

        SubjectListDto subjectListDto = portalFeignClient.getSubjects(
                "JSESSIONID=" + jSessionId,
                "https://kuis.konkuk.ac.kr/index.do",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36",
                "#9e4ki",
                "e&*\biu",
                "W^_zie",
                "_qw3e4",
                "Ajd%md",
                "ekmf3",
                "JDow871",
                "NuMoe6",
                "ne+3|q",
                "Qnd@%1",
                "@d1#",
                "dsParam",
                "dm",
                "1130420",
                "2022",
                "B01012",
                "",
                "",
                "",
                "",
                "",
                "1",
                ""
        );

        return subjectListDto;
    }

    private SubjectListDto getSubjectFromPortal(String subjectNumber) {
        String jSessionId = getSession();
        login(jSessionId);

        SubjectListDto subjectListDto = portalFeignClient.getSubjects(
                "JSESSIONID=" + jSessionId,
                "https://kuis.konkuk.ac.kr/index.do",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36",
                "#9e4ki",
                "e&*\biu",
                "W^_zie",
                "_qw3e4",
                "Ajd%md",
                "ekmf3",
                "JDow871",
                "NuMoe6",
                "ne+3|q",
                "Qnd@%1",
                "@d1#",
                "dsParam",
                "dm",
                "1130420",
                "2022",
                "B01012",
                "",
                "",
                subjectNumber,
                "",
                "",
                "1",
                ""
        );

        return subjectListDto;
    }

    private List<Subject> getFullSubjectsFromDb() {
        return subjectRepository.findFullSubjects();
    }

    public void findVacancy() {
        List<Subject> fullSubjects = getFullSubjectsFromDb();

        List<Subject> vacantSubjects = fullSubjects.stream()
                .filter(subject -> {
                    SubjectListDto subjectListDto = getSubjectFromPortal(subject.getSubjectNumber());
                    //TODO subjectListDto empty 검사
                    Subject subjectFromPortal = subjectListDto.getSubjects().get(0).toEntity();
                    log.info("빈 자리 찾음({}): {}/{}", subjectFromPortal.getSubjectNumber(), subjectFromPortal.getCurrentNumber(), subjectFromPortal.getLimitNumber());
                    return subjectFromPortal.getCurrentNumber() < subjectFromPortal.getLimitNumber();
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void insertSubjects() {
        SubjectListDto subjectListDto = getAllSubjectsFromPortal();
        List<SubjectListDto.SubjectDto> subjects = subjectListDto.getSubjects();

        for (SubjectListDto.SubjectDto subjectDto : subjects) {
            subjectRepository.save(subjectDto.toEntity());

            /* 테스트 */
//            if (subjectDto.getSubjectNumber().equals("0001")) {
//                Subject subject = subjectDto.toEntity();
//                subject.setCurrentNumber(subject.getLimitNumber());
//                subjectRepository.save(subject);
//            }

        }
    }

    @Transactional
    public void deleteSubjects() {
        subjectRepository.deleteAllInBatch();
    }

}