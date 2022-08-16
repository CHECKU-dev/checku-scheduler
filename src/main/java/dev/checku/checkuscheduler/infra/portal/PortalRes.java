package dev.checku.checkuscheduler.infra.portal;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.checku.checkuscheduler.domain.subject.entity.Subject;
import dev.checku.checkuscheduler.domain.subject.model.SubjectGrade;
import dev.checku.checkuscheduler.domain.subject.model.SubjectType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PortalRes {

    @JsonProperty(value = "DS_SUSTTIMETABLE")
    private List<SubjectDto> subjects;

    @Getter
    @Setter
    @ToString
    @JsonIncludeProperties(value = {
            "OPEN_SHYR",
            "KOR_NM",
            "TYPL_KOR_NM",
            "TLSN",
            "REMK",
            "ROOM_NM",
            "POBT_DIV_NM", // 기교, 심교, ...
            "DMND_SUST",
            "SBJT_ID"
    })

    public static class SubjectDto {

        // 학년 *
        @JsonProperty(value = "OPEN_SHYR")
        private Integer grade;

        // 교수명
        @JsonProperty(value = "KOR_NM")
        private String professor;

        // 과목명 *
        @JsonProperty(value = "TYPL_KOR_NM")
        private String name;

        // 현재 인원/제한인원 *
        @JsonProperty(value = "TLSN")
        private String numberOfPeople;

        // 비고 *
        @JsonProperty(value = "REMK")
        private String remark;

        // 강의요시(강의실)
        @JsonProperty(value = "ROOM_NM")
        private String timeAndPlace;

        // 이수구분 *
        @JsonProperty(value = "POBT_DIV_NM")
        private String subjectType;

        // 학과
        @JsonProperty(value = "DMND_SUST")
        private String department;

        // 과목번호 *
        @JsonProperty(value = "SBJT_ID")
        private String subjectNumber;

        public Subject toSubject() {
            Integer currentNumber = null;
            Integer limitNumber = null;

            String[] nums = numberOfPeople.split("/");

            if (nums.length == 2) {
                currentNumber = Integer.parseInt(nums[0]);
                limitNumber = Integer.parseInt(nums[1]);
            }

            return Subject.builder()
                    .name(name)
                    .subjectType(SubjectType.of(subjectType))
                    .subjectNumber(subjectNumber)
                    .remark(remark)
                    .subjectGrade(SubjectGrade.of(grade))
                    .department(department)
                    .timeAndPlace(timeAndPlace)
                    .professor(professor == null ? null : professor.trim())
                    .currentNumber(currentNumber)
                    .limitNumber(limitNumber)
                    .build();
        }



    }

}


