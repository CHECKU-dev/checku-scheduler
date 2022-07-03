package dev.checku.checkuscheduler.application;

import dev.checku.checkuscheduler.domain.subject.dao.SubjectRepository;
import dev.checku.checkuscheduler.domain.subject.dto.SubjectListDto;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchedulerService {

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

    @Transactional
    public SubjectListDto getAllSubjects() {
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

    public void insertSubjects() {
        SubjectListDto subjectListDto = getAllSubjects();
        List<SubjectListDto.SubjectDto> subjects = subjectListDto.getSubjects();

        for (SubjectListDto.SubjectDto subjectDto: subjects) {
            subjectRepository.save(subjectDto.toEntity());
        }

    }

}