package dev.checku.checkuscheduler.domain.topic;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "topic")
@Getter
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    @Column(nullable = false, unique = true)
    private String subjectNumber;

    @Builder
    public Topic(String subjectNumber) {
        this.subjectNumber = subjectNumber;
    }

    public static Topic of() {
        return Topic.builder().subjectNumber("1225").build();
    }


}
