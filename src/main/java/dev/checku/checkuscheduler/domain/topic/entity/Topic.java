package dev.checku.checkuscheduler.domain.topic.entity;

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
    @Column(name = "topic_id")
    private Long Id;

    @Column(nullable = false, unique = true)
    private String subjectNumber;


}
