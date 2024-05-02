package com.logitech.service;

import com.logitech.exception.GameNotFoundException;
import com.logitech.exception.QuestionNotFoundException;
import com.logitech.model.Game;
import com.logitech.model.Question;
import com.logitech.repository.GameRepository;
import com.logitech.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    private final GameRepository gameRepository;
    private final QuestionRepository questionRepository;

    public Game getGame() {
        UUID gameId = UUID.randomUUID();
        List<Question> questions = selectRandomQuestions();
        Game game = new Game();
        game.setGameId(gameId);
        game.setQuestions(questions);

        LOGGER.info("New game created with ID: {}", gameId);

        return gameRepository.save(game);
    }

    public Game useLifeline(UUID gameId, String lifeline, Long questionId) {
        LOGGER.info("Lifeline '{}' requested for game with ID: {}", lifeline, gameId);

        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isEmpty()) {
            LOGGER.error("Game not found for ID: {}", gameId);
            throw new GameNotFoundException("Game not found for ID: " + gameId);
        }
        Game game = optionalGame.get();

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isEmpty()) {
            LOGGER.error("Question not found for ID: {}", questionId);
            throw new QuestionNotFoundException("Question not found for ID: " + questionId);
        }
        Question question = optionalQuestion.get();

        if (lifeline.equalsIgnoreCase("50/50")) {
            if (!game.isFiftyFiftyUsed()) {
                // logic to reduce options to 2
                List<String> options = new ArrayList<>(question.getOptions());
                int correctIndex = question.getCorrectOptionIndex();
                options.remove(correctIndex); // Remove correct option

                // Shuffle the options to ensure randomness
                Collections.shuffle(options);

                // Remove two incorrect options
                options.subList(2, options.size()).clear();

                // Add back the correct option to ensure it remains
                options.add(correctIndex, question.getOptions().get(correctIndex));

                question.setOptions(options);
                game.setFiftyFiftyUsed(true);

                LOGGER.info("50/50 lifeline used for game with ID: {}", gameId);
            } else {
                LOGGER.error("50/50 lifeline already used for game with ID: {}", gameId);
                throw new IllegalStateException("50/50 lifeline has already been used.");
            }
        } else if (lifeline.equalsIgnoreCase("+10s")) {
            if (!game.isExtraTimeUsed()) {
                question.setRemainingTime(question.getRemainingTime() + 10);
                game.setExtraTimeUsed(true);

                LOGGER.info("+10s lifeline used for game with ID: {}", gameId);
            } else {
                LOGGER.error("+10s lifeline already used for game with ID: {}", gameId);
                throw new IllegalStateException("+10s lifeline has already been used.");
            }
        }

        return gameRepository.save(game);
    }

    private List<Question> selectRandomQuestions() {
        List<Question> selectedQuestions = new ArrayList<>();
        List<Question> allQuestions = questionRepository.findAll();
        Random random = new Random();

        // Randomly select 10 questions
        int totalQuestions = allQuestions.size();
        int numberOfQuestionsToSelect = Math.min(totalQuestions, 10);
        for (int i = 0; i < numberOfQuestionsToSelect; i++) {
            int index = random.nextInt(totalQuestions);
            selectedQuestions.add(allQuestions.get(index));
            allQuestions.remove(index);
        }
        return selectedQuestions;
    }
}
