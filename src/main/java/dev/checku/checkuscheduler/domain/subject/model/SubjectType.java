package dev.checku.checkuscheduler.domain.subject.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SubjectType {

    MAJOR_REQUIRED("전필"),
    MAJOR_ELECTIVE("전선"),
    LIBERAL_ARTS_REQUIRED("지교"),
    LIBERAL_ARTS_ELECTIVE("일선"),
    LIBERAL_ARTS_PRIMARY("지교"),
    LIBERAL_ARTS_ADVANCED("심교"),
    TEACHING("교직"),
    UNKNOWN("");

    private String subjectTypeKor;

    SubjectType(String subjectTypeKor) {
        this.subjectTypeKor = subjectTypeKor;
    }

    public static SubjectType of(String subjectTypeKor) {
        return Arrays.stream(SubjectType.values())
                .filter(subjectType -> subjectType.getSubjectTypeKor().equals(subjectTypeKor))
                .findAny()
                .orElse(UNKNOWN);
    }

}
