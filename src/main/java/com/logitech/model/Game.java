package com.logitech.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "game")
@Data
public class Game {
    @Id
    @Column(name = "game_id")
    private UUID gameId;

    @ElementCollection
    @Column(name = "questions")
    private List<Question> questions;

    @Column(name = "fifty_fifty_used")
    private boolean fiftyFiftyUsed;

    @Column(name = "extra_time_used")
    private boolean extraTimeUsed;

    @Column(name = "correct_answers")
    private int correctAnswers;
}
