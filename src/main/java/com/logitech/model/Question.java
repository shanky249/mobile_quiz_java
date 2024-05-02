package com.logitech.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String image;

    @ElementCollection
    @Column(name = "options")
    private List<String> options;

    @Column(name = "correct_option_index")
    private int correctOptionIndex;

    @Column(name = "remaining_time")
    private int remainingTime;

    public boolean isCorrectAnswer(String answer) {
        return options.get(correctOptionIndex).equalsIgnoreCase(answer);
    }
}
