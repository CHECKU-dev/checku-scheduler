package dev.checku.checkuscheduler.domain.subject.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SubjectGrade {

    FRESHMAN(1, "1학년"),
    SOPHOMORE(2, "2학년"),
    JUNIOR(3, "3학년"),
    SENIOR(4, "4학년"),
    ALL(9, "전체"),
    UNKNOWN(null, "");

    private final Integer grade;
    private final String description;

    SubjectGrade(Integer grade, String description) {
        this.grade = grade;
        this.description = description;
    }

    public static SubjectGrade of(Integer grade) {
        return Arrays.stream(values())
                .filter(subjectGrade -> subjectGrade.grade.equals(grade))
                .findAny()
                .orElse(UNKNOWN);
    }

}
