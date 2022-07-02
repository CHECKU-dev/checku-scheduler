package dev.checku.checkuscheduler.domain.subject.dao;

import dev.checku.checkuscheduler.domain.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
