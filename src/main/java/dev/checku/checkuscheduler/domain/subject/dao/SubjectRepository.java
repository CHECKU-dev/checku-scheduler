package dev.checku.checkuscheduler.domain.subject.dao;

import dev.checku.checkuscheduler.domain.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("select s from Subject s " +
            "where s.currentNumber = s.limitNumber")
    List<Subject> findFullSubjects();

}
