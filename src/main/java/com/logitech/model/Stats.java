package com.logitech.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "stats")
@Data
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_id")
    private Long gameId;

    private String username;

    @ElementCollection
    @Column(name = "question_ids")
    private List<Long> questionIds;

    @ElementCollection
    @Column(name = "selected_options")
    private Map<Long, Integer> selectedOptions;

    @Column(name = "total_correct_answers")
    private int totalCorrectAnswers;

    @Column(name = "total_lifelines_used")
    private int totalLifelinesUsed;
}
