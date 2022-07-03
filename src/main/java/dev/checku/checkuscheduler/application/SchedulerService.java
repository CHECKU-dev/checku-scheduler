package dev.checku.checkuscheduler.application;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchedulerService {

    private final SubjectService subjectService;

    //TODO 주기 수정
    @Scheduled(cron = "0/10 * * * * *")
    @Transactional
    public void updateSubjectTable() {
        subjectService.deleteSubjects();
        subjectService.insertSubjects();
    }

    //TODO 주기 수정
    @Scheduled(cron = "0/15 * * * * *")
    public void findVacancy() {
        subjectService.findVacancy();
    }

}
