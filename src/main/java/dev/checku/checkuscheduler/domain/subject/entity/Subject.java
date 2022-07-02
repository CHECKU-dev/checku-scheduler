package dev.checku.checkuscheduler.domain.subject.entity;

import dev.checku.checkuscheduler.domain.subject.model.SubjectGrade;
import dev.checku.checkuscheduler.domain.subject.model.SubjectType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    private String subjectNumber;

    @Lob
    private String remark;

    @Enumerated(EnumType.STRING)
    private SubjectGrade subjectGrade;

    private String department;

    private String timeAndPlace;

    private String professor;

    private Integer currentNumber;

    private Integer limitNumber;

    @Builder
    public Subject(String name,
                   SubjectType subjectType,
                   String subjectNumber,
                   String remark,
                   SubjectGrade subjectGrade,
                   String department,
                   String timeAndPlace,
                   String professor,
                   Integer currentNumber,
                   Integer limitNumber) {
        this.name = name;
        this.subjectType = subjectType;
        this.subjectNumber = subjectNumber;
        this.remark = remark;
        this.subjectGrade = subjectGrade;
        this.department = department;
        this.timeAndPlace = timeAndPlace;
        this.professor = professor;
        this.currentNumber = currentNumber;
        this.limitNumber = limitNumber;
    }

}
